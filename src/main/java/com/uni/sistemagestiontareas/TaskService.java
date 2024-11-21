package com.uni.sistemagestiontareas;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private List<String> tasks = new ArrayList<>();

    public void addTask(String taskName) {
        tasks.add(taskName);
    }

    public void completeTask(String taskName) {
        tasks.remove(taskName);
    }

    public void deleteTask(String taskName) {
        tasks.remove(taskName);
    }
}