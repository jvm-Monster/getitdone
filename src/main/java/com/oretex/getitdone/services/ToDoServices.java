package com.oretex.getitdone.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oretex.getitdone.database.Database;
import com.oretex.getitdone.model.ToDo;
import com.oretex.getitdone.model.User;

public class ToDoServices {
    // this is used to be able to initialize the user variable with the entries of
    // the User objects
    User user = new User();
    UserServices userServices = new UserServices();
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

    // addint a todo item
    public boolean addToDoItem(ToDo todo, int id, String userAuth) {

        // Check if the user info exists, returns a HashMap, it can be empty or null
        Map<String, User> userData = userServices.getUserInfo(id);
        for (Map.Entry<String, User> you : userData.entrySet()) {
            System.out.println(userAuth);
            user = you.getValue();
        }
        // Verify user input and login status
        if ((userServices.verify(user, userAuth))
                && (isValidUser(todo) && todo.getWhatToDo().length() > 3)) {
            // Set the id of the new ToDo object
            todo.setId(user.getUserListToDoList().size() + 1);

            // Add the new ToDo object to the user's to-do list
            user.getUserListToDoList().add(todo);

            // Return true to indicate that the addition was successful
            return true;

        }
        return false;
    }

    // Helper method to check for invalid user or login status
    private boolean isValidUser(ToDo todo) {
        return user != null && userServices.loggedIn(user) && todo.getWhatToDo() != null;
    }

    public void deleteUserToDoList(int userId, int todoIndex) {
        Map<String, User> userData = userServices.getUserInfo(userId);
        for (Map.Entry<String, User> you : userData.entrySet()) {
            user = you.getValue();
        }
        user.getUserListToDoList().remove(todoIndex);
    }
}
