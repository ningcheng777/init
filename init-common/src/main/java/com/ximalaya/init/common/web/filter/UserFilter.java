package com.ximalaya.init.common.web.filter;

import com.ximalaya.init.common.web.session.Session;
import com.ximalaya.init.common.web.session.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ningcheng
 * @date 2017/11/22
 */
public class UserFilter implements Filter {

    private static final String UID_ATTRIBUTE = "opsid";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        User user = new User();
        String userId = String.valueOf(request.getAttribute(UID_ATTRIBUTE));
        if (userId != null && !"null".equals(userId)) {
            user.setId(userId);
        }
        Session.setUser(user);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
