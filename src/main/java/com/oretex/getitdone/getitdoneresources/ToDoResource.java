package com.oretex.getitdone.getitdoneresources;

import java.util.Map;

import com.oretex.getitdone.model.ToDo;
import com.oretex.getitdone.model.User;
import com.oretex.getitdone.services.ToDoServices;
import com.oretex.getitdone.services.UserServices;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ToDoResource {
    // the userServices is used to get the user and do some checking user processing
    // or validation
    UserServices userServices = new UserServices();
    // the userListDoServices is used to either receive users to do list or add a
    // what to do in the todolist
    ToDoServices toDoServices = new ToDoServices();
    // this is used to be able to initialize the user variable with the entries of
    // the User objects
    User user = new User();

    // this get method is used to get user todo lists
    @GET
    public Response getToDoList(@PathParam("userId") int id, @HeaderParam("Authorization") String userAuth) {
        // check if the user info exist, returns a HashMap, it can be empty or null
        Map<String, User> userData = userServices.getUserInfo(id);
        // gets the user entry and gets the values of the user and adds it to the user
        // object
        System.out.println("runing this");
        for (Map.Entry<String, User> d : userData.entrySet()) {
            user = d.getValue();
        }
        System.out.println("still runing this");
        // validation to check if user is logged in it returns boolean value(true or
        // false)

        if (userServices.loggedIn(user) && userServices.verify(user, userAuth)) {
            return Response.status(Response.Status.ACCEPTED)
                    .entity(toDoServices.userToDoList(user))
                    .build();
        } // retuns a response NOT_ACCETABLE if user is not logged in
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("user not logged in or Authorization eror ")
                    .build();
        }
    }

    // this method is used to add what to do in the user to do list
    @POST
    public Response addToDoList(ToDo todo, @PathParam("userId") int id, @HeaderParam("Authorization") String userAuth) {
        System.out.println(todo.getWhatToDo() + " " + id + " " + userAuth);

        if (toDoServices.addToDoItem(todo, id, userAuth)) {
            // this is for sending a response to the client that the transaction or request
            // was successful
            Map<String, User> userData = userServices.getUserInfo(id);
            for (Map.Entry<String, User> d : userData.entrySet()) {
                user = d.getValue();
            }
            return Response.status(Response.Status.CREATED)
                    .entity(user.getUserListToDoList())
                    .build();
        }

        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Not added")
                    .build();
        }

    }

    @DELETE
    public Response deleteFromTodoList(@PathParam("userId") int userId, @PathParam("todoId") int todoId,
            @HeaderParam("Authorization") String userAuth) {
        Map<String, User> userData = userServices.getUserInfo(userId);
        for (Map.Entry<String, User> d : userData.entrySet()) {
            user = d.getValue();
        }
        if (user.isUserLoggedIn() && userServices.verify(user, userAuth)
                && !user.getUserListToDoList().isEmpty()) {
            user.getUserListToDoList().remove(todoId - 1);

            return Response.status(Response.Status.OK)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();

        }

    }

}
