package com.uni.sistemagestiontareas;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
    @Before("execution(* com.example.taskmanager.TaskService.addTask(..)) && args(taskName)")
    public void validateTask(String taskName) {
        if (taskName == null || taskName.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tarea no puede estar vacío");
        }
    }
}
