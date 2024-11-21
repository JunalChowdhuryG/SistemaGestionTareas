package com.uni.sistemagestiontareas;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Aspect
@Component
public class ErrorHandlingAspect {
    @AfterThrowing(pointcut = "execution(* com.example.taskmanager.TaskService.*(..))", throwing = "ex")
    public void handleError(Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}