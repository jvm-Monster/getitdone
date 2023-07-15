package com.oretex.getitdone.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.oretex.getitdone.database.Database;
import com.oretex.getitdone.model.User;

public class UserServices {

    // adds new users to the database
    public void storeUserInfo(User user) {
        Map<String, User> userToStore = new HashMap<>();
        // automatically generate an id for the user
        user.setUserId(Database.databaseSize() + 1);
        // generates an autamatic authenticaion id for the user and adds the use to the
        // userTostore
        userToStore.put("Bearer " + UUID.randomUUID().toString(), user);
        // adds the user to the database by storing the userToStore map object
        Database.storedata(userToStore);
    }

    // This is used to get user info
    public Map<String, User> getUserInfo(int userId) {
        // it gets all the data in the database and stores them in the dataa object list
        List<Map<String, User>> dataaa = Database.getAllData();
        // if the this if statement is not met it returns a new HashMap meaning null i
        // guess
        if (userId <= 0 || userId > dataaa.size()) {
            return new HashMap<>(); // Or handle the case of invalid user ID accordingly
        }
        // executes the else statment if the if is not met
        else {
            // returns the user information
            return dataaa.get(userId - 1);
        }
    }

    public boolean verify(User user, String authentication) {
        // for storing the user authentication key after verification
        String key = "";
        // for storing the user name after verification
        String userName = "";
        // for updating whether the user verification is a success
        boolean verificationSuccess = false;
        // making suer that the user id is not lessthan or equall zero or the user id is
        // not greater thatn the database for wich i don't even know if i implemented it
        // well
        if (user.getUserId() <= 0 || user.getUserId() > Database.databaseSize() || authentication == null) {
            return verificationSuccess;
        } // runs the else statement if the if statement was not met
        else {
            // gets the orginal user in the database if it exist by using its id
            Map<String, User> verifying = getUserInfo(user.getUserId());
            // gets the user entries such as it keys and username to verify
            for (Map.Entry<String, User> entry : verifying.entrySet()) {
                key = entry.getKey();
                userName = entry.getValue().getUserName();
            }
            /*
             * checks if the user object passed from the verify() method is same as the one
             * got from the database.
             * if the same that means the verification is successful
             */
            if (key.equals(authentication) && userName.equals(user.getUserName())) {
                verificationSuccess = true;
            }
            // returns the verificationstatus
            return verificationSuccess;
        }

    }

    public boolean loggedIn(User user) {
        return user.isLoggedIn();
    }

}
