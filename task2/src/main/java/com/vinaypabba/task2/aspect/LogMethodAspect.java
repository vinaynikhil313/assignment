package com.vinaypabba.task2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethodAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogMethodParam.class);

    @Pointcut("@annotation(l)")
    public void annotationPointCut(LogMethodParam l) {
    }

    @Before("annotationPointCut(l) && execution(* *(..))")
    public void beforeMethod(JoinPoint joinPoint, LogMethodParam l) {
        switch (l.logLevel()) {
            case DEBUG:
                LOGGER.debug("Method: {}, Parameters: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
                break;
            case INFO:
                LOGGER.info("Method: {}, Parameters: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
                break;
        }
    }

}
