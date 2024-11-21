
# **Gestión de Tareas con AOP**

Este proyecto implementa un **Sistema de Gestión de Tareas** con interfaz gráfica en Java utilizando **Spring Boot** y **AOP (Programación Orientada a Aspectos)**. El propósito es demostrar cómo AOP puede facilitar la gestión de funcionalidades transversales, como auditoría, validación y manejo de errores, separándolas de la lógica principal de la aplicación.

---

## **Características del Proyecto**

1. **Gestión de Tareas**:
    - Agregar tareas.
    - Marcar tareas como completadas.
    - Eliminar tareas.
    - Visualización de tareas pendientes y completadas.

2. **Interfaz Gráfica**:
    - Basada en **Swing**.
    - Incluye botones y listas para gestionar las tareas.

3. **Programación Orientada a Aspectos (AOP)**:
    - **Auditoría**: Registra todas las operaciones realizadas por el usuario.
    - **Validación**: Verifica que los datos ingresados sean válidos antes de procesarlos.
    - **Manejo de Errores**: Intercepta y gestiona errores globalmente.

---

## **Tecnologías Utilizadas**

- **Java 17**
- **Spring Boot 2.7.x**
- **Swing** para la interfaz gráfica.
- **Apache Commons IO** para manejo de archivos.
- **Maven** para gestión de dependencias.

---
<!--
## **Estructura del Proyecto**

```
src/main/java/com/example/taskmanager/
├── TaskManagerApp.java       // Clase principal que inicializa la aplicación
├── TaskManagerGUI.java       // Interfaz gráfica de la aplicación
├── TaskService.java          // Lógica de negocio para gestionar tareas
├── aspects/
│   ├── AuditAspect.java      // Aspecto para registrar acciones
│   ├── ValidationAspect.java // Aspecto para validar datos
│   └── ErrorHandlingAspect.java // Aspecto para gestionar errores
```
-->
---

## **Aspectos en el Proyecto**

### **1. Auditoría**

**Archivo**: `AuditAspect.java`  
**Propósito**: Registrar todas las operaciones realizadas por el usuario, como agregar, completar y eliminar tareas.

- **Ubicación en el Código**: Este aspecto intercepta cualquier método dentro de `TaskService`.
- **Implementación**:

```java
@Aspect
@Component
public class AuditAspect {
    @After("execution(* com.example.taskmanager.TaskService.*(..))")
    public void logAction(JoinPoint joinPoint) {
        String message = "Acción realizada: " + joinPoint.getSignature().getName();
        System.out.println(message);
    }
}
```

- **Rol**: Mantener un registro de todas las acciones. Por ejemplo, al agregar una tarea, se registrará en la consola:  
  `Acción realizada: addTask`.

---

### **2. Validación**

**Archivo**: `ValidationAspect.java`  
**Propósito**: Asegurar que los datos ingresados por el usuario sean válidos antes de procesarlos.

- **Ubicación en el Código**: Este aspecto intercepta el método `addTask` de `TaskService` y verifica que el nombre de la tarea no sea vacío.
- **Implementación**:

```java
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
```

- **Rol**: Antes de agregar una tarea, el sistema verifica que no esté vacía. Si está vacía, se arroja una excepción, la cual será manejada por el aspecto de manejo de errores.

---

### **3. Manejo de Errores**

**Archivo**: `ErrorHandlingAspect.java`  
**Propósito**: Interceptar todas las excepciones lanzadas por `TaskService` y manejar los errores de forma amigable.

- **Ubicación en el Código**: Este aspecto captura cualquier excepción que ocurra en los métodos de `TaskService`.
- **Implementación**:

```java
@Aspect
@Component
public class ErrorHandlingAspect {
    @AfterThrowing(pointcut = "execution(* com.example.taskmanager.TaskService.*(..))", throwing = "ex")
    public void handleError(Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
```

- **Rol**: Mostrar un mensaje en la interfaz gráfica cuando ocurra un error. Por ejemplo, si se intenta agregar una tarea con un nombre vacío, se muestra:  
  `Error: El nombre de la tarea no puede estar vacío`.

---

## **Cómo Ejecutar el Proyecto**

1. **Clonar el Repositorio**:

   ```bash
   git clone https://github.com/tu-repositorio/task-manager-aop.git
   cd task-manager-aop
   ```

2. **Construir y Ejecutar**:

   ```bash
   mvn spring-boot:run
   ```

3. **Interfaz Gráfica**:
    - Al iniciar, aparecerá una ventana con las opciones para gestionar tareas.

---

## **Pruebas**

### **Caso 1: Agregar una Tarea Válida**

1. Ingresar un nombre en el campo de texto y presionar "Agregar Tarea".
2. La tarea aparecerá en la lista de pendientes.
3. En la consola, se registrará:  
   `Acción realizada: addTask`.

### **Caso 2: Agregar una Tarea Inválida**

1. Presionar "Agregar Tarea" con el campo vacío.
2. Se mostrará el mensaje de error:  
   `Error: El nombre de la tarea no puede estar vacío`.

### **Caso 3: Completar una Tarea**

1. Seleccionar una tarea de la lista de pendientes y presionar "Completar Tarea".
2. La tarea se moverá a la lista de completadas.
3. En la consola, se registrará:  
   `Acción realizada: completeTask`.

### **Caso 4: Eliminar una Tarea**

1. Seleccionar una tarea de la lista de pendientes y presionar "Eliminar Tarea".
2. La tarea desaparecerá de la lista.
3. En la consola, se registrará:  
   `Acción realizada: deleteTask`.

---

## **Conclusión**

Este proyecto demuestra cómo AOP puede facilitar la implementación de funcionalidades transversales (auditoría, validación y manejo de errores), manteniendo la lógica de negocio limpia y enfocada en sus responsabilidades principales.

- **Librerías utilizadas para AOP**:
    - Spring AOP (proporcionada por `spring-boot-starter-aop`).
