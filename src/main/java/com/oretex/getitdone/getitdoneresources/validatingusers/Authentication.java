package com.oretex.getitdone.getitdoneresources.validatingusers;

import java.util.Map;

import com.oretex.getitdone.model.User;
import com.oretex.getitdone.services.UserServices;

import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;

public class Authentication {
    UserServices userServices = new UserServices();

    // This login method works I didn't really know how i made it worked when i
    // finished it, but it works
    // It gets the headerParam which contains the user Authorization key and a user
    // object which is the name
    @PUT
    public Response login(User user, @HeaderParam("Authorization") String authorization) {
        try {
            boolean verificationSuccess = userServices.verify(user, authorization);
            if (verificationSuccess) {

                for (Map.Entry<String, User> d : userServices.getUserInfo(user.getUserId()).entrySet()) {
                    user = d.getValue();
                }
                user.setUserLoggedIn(verificationSuccess);
                return Response.status(Response.Status.ACCEPTED)
                        .header("login", userServices.loggedIn(user))
                        .build();
            } else {
                user.setUserLoggedIn(verificationSuccess);
                return Response.status(Response.Status.NOT_FOUND)
                        .header("authStatus", userServices.loggedIn(user))
                        .entity("Account not found")
                        .build();
            }
        } catch (ProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("An error occured")
                    .build();
        }
    }

    // logout

}
