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

    public boolean checkId(long id) {
        return this.id == id;
    }

    public void changeState() {
        this.isCompleted = !this.isCompleted;
    }

    public String getDetails() {
        return String.format("Detalles de la tarea\nTitulo: %s\nDescripcion: %s\nEstado: %s", this.title, this.description, this.isCompleted?"Finalizada":"Pendiente");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%d. [%s] %s", id, (isCompleted?"X":""), title);
    }
}
