package com.oretex.getitdone.resources;

import org.glassfish.jersey.message.internal.Statuses;

import com.oretex.getitdone.model.User;

import jakarta.transaction.Status;
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
    
   

    @POST
    public Response register(User user){

        if(user == null){

            return Response.status(Response.Status.CREATED).build();
         }

        return Response.status(Response.Status.ACCEPTED)
              .header("Authentication", user.getAuth())
              .entity(user.getName())
              .build();
    }

    @POST
    public Response login(User user, @HeaderParam("Authentication")String authentication){
        if(user == null || authentication==null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
        
    }
}
