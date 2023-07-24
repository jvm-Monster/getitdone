package com.oretex.getitdone.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class OverErrorMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        System.err.println(exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An Error Occured,Make sure you are using the right HTTP METHOD")
                .build();
    }

}
