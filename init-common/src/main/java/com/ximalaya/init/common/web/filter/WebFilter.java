package com.ximalaya.init.common.web.filter;

import com.alibaba.fastjson.JSON;
import com.nali.common.util.web.RequestUtil;
import com.ximalaya.grade.common.thrift.AnchorGradeInfoResult;
import com.ximalaya.grade.common.thrift.AnchorGradeService;
import com.ximalaya.init.common.web.result.Result;
import com.ximalaya.init.common.web.result.ResultStatus;
import com.ximalaya.init.common.web.session.GradeUser;
import com.ximalaya.init.common.web.session.Session;
import com.ximalaya.passport.sso.cookie.CookieManager;
import com.ximalaya.passport.sso.model.SsoSession;
import com.ximalaya.passport.sso.service.ISessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ningcheng
 * @date 2017/11/23
 */
public class WebFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(WebFilter.class);
    private AnchorGradeService.Iface anchorGradeService;
    private String environmentId;
    private ISessionService sessionService;
    private List<String> freePaths = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();
        logger.warn("request path:{}", path);
        if (!freePaths.contains(path)) {
            SsoSession ssoSession = CookieManager.parseSsoSession(environmentId, request.getCookies());
            if (ssoSession == null) {
                ssoSession = CookieManager.parseSsoSession(request);
            }

            boolean isLogin = false;
            if (null != ssoSession && ssoSession.notEmpty()) {
                isLogin = getSessionService().checkSession(ssoSession, null);
            }

            if (!isLogin) {
                loginFail(request, response);
                return;
            }
            injectUserIntoRequest(request, ssoSession);
            fetchUserInfo(request);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }

    private void loginFail(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Result ret = new Result();
        ret.setSuccess(false);
        ret.setStatus(ResultStatus.LOGIN_ERROR);
        res.getWriter().append(JSON.toJSONString(ret));
        logger.warn("login fail from {}", RequestUtil.getRemoteIp(req));
    }

    private void injectUserIntoRequest(HttpServletRequest request, SsoSession ssoSession) {
        request.setAttribute("currentUserId", ssoSession.getUid());
        request.setAttribute("NALIWORLD_LOGIN_USER_SESSION_ID", ssoSession.getTicket());
        request.setAttribute("remember_me", ssoSession.getIsRemember());
        request.setAttribute("XM_SSO_SESSION", ssoSession);
    }

    private void fetchUserInfo(HttpServletRequest request) {
        GradeUser user = new GradeUser();
        String uid = (String) request.getAttribute("currentUserId");
        if (uid != null) {
            user.setId(uid);
            try {
                AnchorGradeInfoResult result = anchorGradeService.getAnchorGradeInfo(Long.valueOf(uid));
                if (result.isSuccess()) {
                    user.setGrade(result.getAnchorGrade());
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        Session.setUser(user);
    }

    public AnchorGradeService.Iface getAnchorGradeService() {
        return anchorGradeService;
    }

    public void setAnchorGradeService(AnchorGradeService.Iface anchorGradeService) {
        this.anchorGradeService = anchorGradeService;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public ISessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    public List<String> getFreePaths() {
        return freePaths;
    }

    public void setFreePaths(List<String> freePaths) {
        this.freePaths = freePaths;
    }
}
