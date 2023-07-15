package com.oretex.getitdone.model;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;

public class User {
    private int userId;
    private String userName;
    private boolean userLoggedIn;
    private List<ToDo> userListToDoList;

    public User() {
    }

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User [id=" + userId + ", name=" + userName + " loggedIn= " + userLoggedIn + "]";
    }

    @JsonbTransient
    public boolean isLoggedIn() {
        return userLoggedIn;
    }

    @JsonbTransient
    public void setLoggedIn(boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public List<ToDo> getUserListToDoList() {
        return userListToDoList;
    }

    public void setUserListToDoList(List<ToDo> userListToDoList) {
        this.userListToDoList = userListToDoList;
    }

}
