package com.appliedscience.api.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Around("within(com.appliedscience.api.web.controllers.*)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();
        Object o = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        logger.info("[Execution time] {}.{}: {}ms", className, methodName, (endTime - startTime));

        return o;
    }
}
