package com.oretex.getitdone.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oretex.getitdone.database.Database;
import com.oretex.getitdone.model.ToDo;
import com.oretex.getitdone.model.User;

public class ToDoServices {
    List<Map<String, User>> data = Database.getAllData();

    // return the particaular user to do list
    public List<ToDo> userToDoList(User user) {
        // runs this if statement
        if (user.getUserId() <= 0 || user.getUserId() > data.size()) {
            return new ArrayList<>();
        } // runs the else statement
        else {
            // if the list is null it creates a list for the user to store it what to do
            if (user.getUserListToDoList() == null) {
                user.setUserListToDoList(new ArrayList<>());
            }
            // return the user to do list
            return user.getUserListToDoList();
        }

    }

}
