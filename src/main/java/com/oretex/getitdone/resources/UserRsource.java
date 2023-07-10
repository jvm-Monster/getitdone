package com.oretex.getitdone.resources;

import com.oretex.getitdone.model.User;
import com.oretex.getitdone.services.UserHouse;
import com.oretex.getitdone.tools.AuthGenerator;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRsource {

    UserHouse storeUser=new UserHouse();
    @POST
    @Path("/register")
    public Response register(User user) {
        if (user == null || user.getName().isEmpty() || user.getName().equals(""))
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        else
             storeUser.storeUserInfo(user);
            return Response.status(Response.Status.ACCEPTED)
                   
                    .build();

    }

    @POST
    public Response login(User user, @HeaderParam("Authentication") String authentication) {
        if (user == null || authentication == null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();

    }
}
