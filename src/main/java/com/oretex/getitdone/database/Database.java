package com.oretex.getitdone.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oretex.getitdone.model.User;

public class Database {
    private static final Database instance=new Database();
    private static List<Map<String, User>> data=new ArrayList<>();

    private Database(){
        //avoid creation of this object indirectly
    }

    public static Database getInstance() {
        return instance;
    }



    public static void storedata(int id,Map<String,User> dataToStore){
         data.add(id, dataToStore);
     }

     public static int databaseSize(){
        return data.size();
     }

     public static List<Map<String,User>> getAllData(){
        Map<String,User> daa=new HashMap<>();
         User user=new User("name");
        daa.put("sfasfsdf", user);
        storedata(1,daa );
        return data;
     }

    
    
    





}
