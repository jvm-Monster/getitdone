package com.oretex.getitdone.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
import com.oretex.getitdone.database.Database;
import com.oretex.getitdone.model.User;
import com.oretex.getitdone.tools.AuthGenerator;

public class UserHouse {
     Map<String,User> userToStore=new HashMap<>();



    

    public void storeUserInfo(User user){
        user.setId(Database.databaseSize()+1);
        userToStore.put(AuthGenerator.getAuth(), user);
        Database.storedata(user.getId(), userToStore);
     }

    public Map<String, User> getUserInfo(int id){
        Map<String,User> userToRetreive=new HashMap<>();
        List<Map<String,User>> dataaa=Database.getAllData();
        for (Map<String, User> userData : dataaa){
             System.out.println(userData.values());
            for (User user: userData.values()) {
                if(user.getId()==id){
                    userToRetreive.putAll(userData);
                    break;
                }
            }
        }
        
        return userToRetreive;
     }
     public static void main(String[] args) {
        UserHouse h=new UserHouse();
        System.out.println(h.getUserInfo(1));
     }
}
