package com.uni.sistemagestiontareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaGestionTareasApplication {

    public static void main(String[] args) {

        TaskManagerGUI gui = new TaskManagerGUI();
        gui.show();
    }
}
