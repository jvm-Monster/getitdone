package com.oretex.getitdone.getitdoneresources;

import java.util.Map;

import com.oretex.getitdone.model.ToDo;
import com.oretex.getitdone.model.User;
import com.oretex.getitdone.services.ToDoServices;
import com.oretex.getitdone.services.UserServices;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/")
public class ToDoResource {
    // the userServices is used to get the user and do some checking user processing
    // or validation
    UserServices userServices = new UserServices();
    // the userListDoServices is used to either receive users to do list or add a
    // what to do in the todolist
    ToDoServices userListDoServices = new ToDoServices();
    // this is used to be able to initialize the user variable with the entries of
    // the User objects
    User user;

    // this get method is used to get user todo lists
    @GET
    public Response getToDoList(@PathParam("userId") int id) {
        // check if the user info exist, returns a HashMap, it can be empty or null
        Map<String, User> userData = userServices.getUserInfo(id);
        // gets the user entry and gets the values of the user and adds it to the user
        // object
        for (Map.Entry<String, User> d : userData.entrySet()) {
            user = d.getValue();
        }
        // validation to check if user is logged in it returns boolean value(true or
        // false)
        if (userServices.loggedIn(user)) {
            return Response.status(Response.Status.ACCEPTED)
                    .entity(userListDoServices.userToDoList(user))
                    .build();
        } // retuns a response NOT_ACCETABLE if user is not logged in
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("user not logged in ")
                    .build();
        }
    }

    // this method is used to add what to do in the user to do list
    @POST
    public Response addToDoList(ToDo todo, @PathParam("userId") int id) {
        // check if the user info exist, returns a HashMap, it can be empty or null
        Map<String, User> userData = userServices.getUserInfo(id);
        for (Map.Entry<String, User> d : userData.entrySet()) {
            user = d.getValue();
        }
        // does some conditions by checking if user is null or what todo is empty or if
        // user is not logged in
        if (user == null || todo.getWhatTodo().isEmpty() || userServices.loggedIn(user) == false
                || user.getUserListToDoList() == null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Not Acceptable")
                    .build();
        } // proceed to the else statement if the conditions in the if statement are not
          // true
        else {
            // for every todo that user adds to thier todolist we want to add an id to that
            // todo
            todo.setId(user.getUserListToDoList().size() + 1);
            // then after setting the id the todo is added
            user.getUserListToDoList().add(todo);
            // return s a response to show the success of the process and also returns the
            // todlists of the user
            return Response.status(Response.Status.ACCEPTED)
                    .entity(user.getUserListToDoList())
                    .build();
        }
    }

}
