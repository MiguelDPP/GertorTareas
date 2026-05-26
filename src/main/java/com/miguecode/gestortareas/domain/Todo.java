package com.miguecode.gestortareas.domain;

public class Todo {
    private static long lastId = 1;
    private long id;
    private String title;
    private String description;
    private boolean isCompleted;

    public Todo(String title, String description, boolean isCompleted) {
        this.id = lastId++;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }
}
