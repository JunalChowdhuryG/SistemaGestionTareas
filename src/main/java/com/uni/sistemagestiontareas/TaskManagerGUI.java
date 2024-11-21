package com.uni.sistemagestiontareas;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerGUI {
    private JFrame frame;
    private JTextField taskInputField;
    private DefaultListModel<String> pendingTasksModel;
    private DefaultListModel<String> completedTasksModel;

    private TaskService taskService;

    public TaskManagerGUI() {
        taskService = new TaskService();
    }

    public void show() {
        frame = new JFrame("Gestión de Tareas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Input de tarea
        JPanel inputPanel = new JPanel(new FlowLayout());
        taskInputField = new JTextField(30);
        JButton addTaskButton = new JButton("Agregar Tarea");
        inputPanel.add(taskInputField);
        inputPanel.add(addTaskButton);

        // Lista de tareas pendientes y completadas
        pendingTasksModel = new DefaultListModel<>();
        completedTasksModel = new DefaultListModel<>();

        JList<String> pendingTasksList = new JList<>(pendingTasksModel);
        JList<String> completedTasksList = new JList<>(completedTasksModel);

        JPanel tasksPanel = new JPanel(new GridLayout(1, 2));
        tasksPanel.add(new JScrollPane(pendingTasksList));
        tasksPanel.add(new JScrollPane(completedTasksList));

        // Botones para operar sobre tareas
        JButton completeTaskButton = new JButton("Completar Tarea");
        JButton deleteTaskButton = new JButton("Eliminar Tarea");

        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.add(completeTaskButton);
        actionPanel.add(deleteTaskButton);

        // Agregar componentes al panel principal
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(tasksPanel, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Listeners para botones
        addTaskButton.addActionListener(e -> {
            String taskName = taskInputField.getText().trim();
            taskService.addTask(taskName);
            pendingTasksModel.addElement(taskName);
            taskInputField.setText("");
        });

        completeTaskButton.addActionListener(e -> {
            int selectedIndex = pendingTasksList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = pendingTasksModel.remove(selectedIndex);
                taskService.completeTask(task);
                completedTasksModel.addElement(task);
            }
        });

        deleteTaskButton.addActionListener(e -> {
            int selectedIndex = pendingTasksList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = pendingTasksModel.remove(selectedIndex);
                taskService.deleteTask(task);
            }
        });
    }
}
