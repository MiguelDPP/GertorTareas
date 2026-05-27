package com.miguecode.gestortareas.service;

import com.miguecode.gestortareas.domain.Todo;
import com.miguecode.gestortareas.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private List<Todo> todos;
    private static final TodoService INSTANCE = new TodoService();

    public TodoService() {
        todos = new ArrayList<>();
    }

    public static TodoService getInstance() {
        return INSTANCE;
    }

    public void addTodo(String todoTitle, String todoDescription) {
        todos.add(new Todo(todoTitle, todoDescription, false));
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public Todo getTodoById(long todoId) {
        for (Todo todo : todos) {
            if (todo.checkId(todoId)) {
                return todo;
            }
        }
        throw new EntityNotFoundException("Tarea con el id "+todoId+" no encontrada");
    }

    public void updateTodo(Todo todo, String title, String description) {
        todo.setTitle(title);
        todo.setDescription(description);
    }

    public void deleteTodo (Todo todo) {
        this.todos.remove(todo);
    }
}
