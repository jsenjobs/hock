package com.jsen.test.config.dbs.help;

import com.google.common.base.Throwables;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 读写分离，自动切换的AOP
 * 根据方法前缀
 */
// @Aspect
// @Component
public class ReadWriteAspect {
    private static final Logger logger = LoggerFactory.getLogger(ReadWriteAspect.class);


    @Pointcut("execution(* com.jsen.test.mapper.*.list*(..))||" +
            "execution(* com.jsen.test.mapper.*.get*(..))||" +
            "execution(* com.jsen.test.mapper.*.find*(..))||" +
            "execution(* com.jsen.test.mapper.*.count*(..))")
    public void slave() {}

    @Pointcut("execution(* com.jsen.test.mapper.*.save*(..))||" +
            "execution(* com.jsen.test.mapper.*.update*(..))||" +
            "execution(* com.jsen.test.mapper.*.delete*(..))||" +
            "execution(* com.jsen.test.mapper.*.insert*(..))")
    public void master() {}


    @Around("slave()")
    public Object slaveAround(ProceedingJoinPoint proceedingJoinPoint) {
        logger.warn("slaveAround start...");
        try {
            DataSourceContextHolder.setDB(DbTypes.DB3);
            Object o = proceedingJoinPoint.proceed();
            logger.warn("slaveAround end...");
            return o;
        } catch (Throwable throwable) {
            Throwables.throwIfUnchecked(throwable);
            throwable.printStackTrace();

            logger.warn("slaveAround end exception...");
            return null;
        } finally {
            DataSourceContextHolder.clearDB();
        }
    }


    @Around("master()")
    public Object masterAround(ProceedingJoinPoint proceedingJoinPoint) {
        logger.warn("masterAround start...");
        try {
            DataSourceContextHolder.setDB(DbTypes.DB1);
            Object o = proceedingJoinPoint.proceed();
            logger.warn("masterAround end...");
            DataSourceContextHolder.clearDB();
            return o;
        } catch (Throwable throwable) {
            Throwables.throwIfUnchecked(throwable);
            throwable.printStackTrace();

            logger.warn("masterAround end exception...");
            return null;
        } finally {
            DataSourceContextHolder.clearDB();
        }
    }

}
