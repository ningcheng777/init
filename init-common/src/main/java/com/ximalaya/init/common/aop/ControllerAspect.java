package com.ximalaya.init.common.aop;

import com.ximalaya.init.common.exception.CommonServiceException;
import com.ximalaya.init.common.web.result.Result;
import com.ximalaya.init.common.web.result.ResultStatus;
import org.apache.thrift.TException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ningcheng
 * @date 2017/11/20
 */
@Component
@Aspect
public class ControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public com.ximalaya.init.common.web.result.Result *(..))")
    public void returnResult() {
    }

    @Around("@annotation(org.springframework.web.bind.annotation.ResponseBody) && returnResult()")
    public Result afterThrowing(ProceedingJoinPoint joinPoint) throws Exception {
        try {
            return (Result) joinPoint.proceed();
        } catch (Throwable e) {
            Result result = new Result();
            result.setSuccess(false);
            String location = joinPoint.getTarget().getClass().getName() + "#"
                    + joinPoint.getSignature().getName() + " ";
            if (e instanceof CommonServiceException) {
                logger.warn("service error in " + location);
                result.setMsg(e.getMessage());
                result.setStatus(ResultStatus.BUSINESS_ERROR);
            } else if (e instanceof TException) {
                logger.error(location + e.getMessage(), e);
                result.setMsg("系统错误");
                result.setStatus(ResultStatus.RPC_ERROR);
            } else {
                logger.error(location + e.getMessage(), e);
                result.setMsg("系统错误");
                result.setStatus(ResultStatus.SYSTEM_ERROR);
            }
            return result;
        }
    }
}
