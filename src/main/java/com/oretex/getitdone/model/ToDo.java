package com.oretex.getitdone.model;

public class ToDo {
    int id;
    String whatToDo;

    public ToDo() {
    }

    public ToDo(int id, String whatTodo) {
        this.id = id;
        this.whatToDo = whatTodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhatTodo() {
        return whatToDo;
    }

    public void setWhatTodo(String whatTodo) {
        this.whatToDo = whatTodo;
    }

}
