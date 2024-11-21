package com.uni.sistemagestiontareas;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {
    @After("execution(* com.example.taskmanager.TaskService.*(..))")
    public void logAction(JoinPoint joinPoint) {
        String message = "Acción realizada: " + joinPoint.getSignature().getName();
        System.out.println(message);
    }
}
