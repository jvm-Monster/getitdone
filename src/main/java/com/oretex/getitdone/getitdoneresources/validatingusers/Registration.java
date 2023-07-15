package com.oretex.getitdone.getitdoneresources.validatingusers;

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
            userServices.storeUserInfo(user);
            return Response.status(Response.Status.CREATED)
                    .entity(userServices.getUserInfo(user.getUserId()))
                    .build();
        }
    }

}
