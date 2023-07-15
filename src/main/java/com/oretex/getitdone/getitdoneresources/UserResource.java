package com.oretex.getitdone.getitdoneresources;

import com.oretex.getitdone.getitdoneresources.validatingusers.Authentication;
import com.oretex.getitdone.getitdoneresources.validatingusers.Registration;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    // for user registration
    @Path("/registration")
    public Registration register() {

        return new Registration();
    }

    // for user login
    @Path("/authentication")
    public Authentication login() {

        return new Authentication();
    }

    // for getting user to do list and adding what to do in user to do list
    @Path("/{userId}/todolist")
    public ToDoResource getUserTodList() {

        return new ToDoResource();
    }

}
