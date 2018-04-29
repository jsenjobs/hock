package com.jsen.test.aspect;

import com.google.common.base.Throwables;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/2
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.jsen.test.controller.aspect.*.*(..))")
    public void webLog() {}


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.warn("doBefore start...");

        logger.info("doBefore URL:" + request.getRequestURL().toString());
        logger.info("doBefore HTTP_METHOD:" + request.getMethod());
        logger.info("doBefore IP:" + request.getRemoteAddr());
        logger.info("doBefore CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName());
        logger.info("doBefore ARGS:" + Arrays.toString(joinPoint.getArgs()));

        logger.warn("doBefore end...");

    }

    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        logger.warn("doAfter start...");

        logger.warn("doAfter end...");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturn(Object ret) {
        logger.warn("doAfterReturn start...");

        logger.info("doAfterReturn return:" + ret);

        logger.warn("doAfterReturn end...");

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) {
        logger.warn("doAround start...");

        try {
            Object o = pjp.proceed();
            logger.info("doAround result:" + o);

            logger.warn("doAround end...");
            return o;
        } catch (Throwable throwable) {
            Throwables.throwIfUnchecked(throwable);
            throwable.printStackTrace();

            logger.warn("doAround end exception...");
            return null;
        }

    }

    @AfterThrowing("webLog()")
    public void afterThrow(JoinPoint joinPoint) {
        logger.warn("afterThrow start...");

        logger.info("afterThrow exception");

        logger.warn("afterThrow end...");

    }

}
