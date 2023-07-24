package com.oretex.getitdone.getitdoneresources;

import com.oretex.getitdone.getitdoneresources.validatingusers.Authentication;
import com.oretex.getitdone.getitdoneresources.validatingusers.Registration;
import com.oretex.getitdone.services.UserServices;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    UserServices user = new UserServices();

    @GET
    public Response allTheUsers() {

        return Response.status(Response.Status.OK)
                .entity(UserServices.allUsers())
                .build();
    }

    @GET
    @Path("/{userId}")
    public Response getAUser(@PathParam("userId") int userId) {
        return Response.status(Response.Status.OK)
                .entity(user.getUserInfo(userId))
                .build();
    }

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

    @Path("/{userId}/todolist/{todoId}")
    public ToDoResource deleteUserTodoList() {

        return new ToDoResource();
    }

}
