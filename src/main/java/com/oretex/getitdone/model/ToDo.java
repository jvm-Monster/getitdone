package com.oretex.getitdone.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ToDo {
    int id;
    String whatToDo;

    public ToDo() {
    }

    public ToDo(int id, String whatToDo) {
        this.id = id;
        this.whatToDo = whatToDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhatToDo() {
        return whatToDo;
    }

    public void setWhatToDo(String whatTodo) {
        this.whatToDo = whatTodo;
    }

}
