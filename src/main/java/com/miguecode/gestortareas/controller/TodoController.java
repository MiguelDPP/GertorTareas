package com.miguecode.gestortareas.controller;

import com.miguecode.gestortareas.domain.Todo;
import com.miguecode.gestortareas.service.TodoService;
import com.miguecode.gestortareas.ui.MenuView;
import com.miguecode.gestortareas.util.Console;
import com.miguecode.gestortareas.util.Validator;

import java.sql.SQLOutput;
import java.util.List;

public class TodoController {
    private final TodoService   todoService = TodoService.getInstance();
    public void run() {
        short op = 0;

        System.out.println("---- Bienvenido a su Gestor de Tareas WTS (World Todo Solution) ----");
        do {
            MenuView.showInitialMenu();
            op = Console.readValidator("Seleccione una opción: ", Short::parseShort, Validator::positiveNumber);

            switch (op) {
                case 1:
                    this.addTodo();
                    break;
                case 2:
                    this.printTodos();
                    break;
                case 3:
                    this.changeState();
                    break;
                case 4:
                    this.getTodoDetails();
                    break;
                case 5:
                    this.updateTodo();
                    break;
                case 6:
                    this.deleteTodo();
                    break;
                case 7:
                    System.out.println("--- Ha salido del sistema ---");
                    break;
                default:
                    System.out.println("--- Opción incorrecta ---");
            }
        } while (op != 7);
    }

    public void addTodo() {
        System.out.println("---- Agregar Tarea ----");
        try {
            String todoTitle = Console.readValidator("Ingrese el titulo: ", String::toString, Validator::isNotEmpty);
            String todoDescription = Console.readValidator("Ingrese el descripcion: ", String::toString, Validator::isNotEmpty);
            this.todoService.addTodo(todoTitle, todoDescription);
            System.out.println("--- Tarea agregada correctamente ---");
        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }

    public void printTodos() {
        List<Todo> todos = this.todoService.getTodos();
        System.out.println("Lista de Tareas:");
        if (todos.isEmpty()) {
            System.out.println("No hay tareas disponibles");
            return;
        }
        for (Todo todo : todos) {
            System.out.println(todo);
        }

    }

    public void changeState() {
        System.out.println("---- Cambiar estado de la tarea ----");
        try {
            this.printTodos();
            long todoId = Console.readValidator("Ingrese el numero de la tarea: ", Long::parseLong, Validator::positiveNumber);
            Todo todo = this.todoService.getTodoById(todoId);
            todo.changeState();
            System.out.println("---- Estado actualizado correctamente ----");
        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }

    public void deleteTodo() {
        System.out.println("---- Eliminar Tarea ----");
        try {
            this.printTodos();
            long todoId = Console.readValidator("Ingrese el id de la tarea a eliminar: ", Long::parseLong, Validator::positiveNumber);
            Todo todo = this.todoService.getTodoById(todoId);
            this.todoService.deleteTodo(todo);
            System.out.println("---- Tarea eliminada correctamente ----");
        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }

    public void getTodoDetails() {
        System.out.println("---- Visualizar detalles tareas ----");
        try {
            this.printTodos();
            long todoId = Console.readValidator("Ingrese el id de la tarea a visualizar: ", Long::parseLong, Validator::positiveNumber);
            Todo todo =  this.todoService.getTodoById(todoId);
            System.out.println(todo.getDetails());
        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }

    public void updateTodo() {
        System.out.println("---- Editar Tarea ----");
        try {
            this.printTodos();
            long todoId = Console.readValidator("Ingrese el id de la tarea a editar: ", Long::parseLong, Validator::positiveNumber);
            Todo todo = this.todoService.getTodoById(todoId);
            System.out.println("Titulo anterior: "+todo.getTitle());
            String newTitle = Console.read("Ingrese el nuevo titulo o precione enter si no desea actualizar este campo: ", String::toString);
            if (newTitle.isEmpty()) {
                newTitle = todo.getTitle();
            }
            String newDescription = Console.read("Ingrese la nueva descripcion o precione enter si no desea actualizar este campo: ", String::toString);
            if (newDescription.isEmpty()) {
                newDescription = todo.getDescription();
            }

            this.todoService.updateTodo(todo, newTitle, newDescription);

            System.out.println("---- Tarea actualizada correctamente ----");
        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }
}
