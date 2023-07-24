package com.oretex.getitdone.getitdoneresources.validatingusers;

import java.util.Map;

import com.oretex.getitdone.model.User;
import com.oretex.getitdone.services.ToDoServices;
import com.oretex.getitdone.services.UserServices;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;

public class Registration {

    UserServices userServices = new UserServices();
    ToDoServices userToDoListServices = new ToDoServices();

    // user registrtion
    @POST
    public Response register(final User user) {
        if (user == null || user.getUserName() == null || user.getUserName().isEmpty()) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("name must not be null, user : \"userName\"")
                    .build();
        } else {
            // stores the user information/Registers the user
            userServices.storeUserInfo(user);

            // this use object is used to send a response of the user currently registered
            // to show the success of registration of the user
            User use = new User();
            String useKey = "";
            /*
             * The resean for the iteration over the entries is because the getUserInfo
             * returns a Map<String,User>
             * so I don't want to send a map object as a response i only want send so normal
             * basic information to the user
             * so I filtered the key, so the key is not sent only the user and also
             * using @jsonbtransient also helps filter
             * some information about the user, so it doesn't serialize or deserialize those
             * information used with the @jsonbtransient
             * 
             */
            for (Map.Entry<String, User> map : userServices.getUserInfo(user.getUserId()).entrySet()) {
                // gets the user information
                use = map.getValue();
                // gets the user key so for this we are passing it to the header of the response
                // cause of authentication purposes
                useKey = map.getKey();
            }
            // We now return the response
            return Response.status(Response.Status.CREATED)
                    .header("Authorization", useKey)
                    .entity(use)
                    .build();
        }
    }

}
