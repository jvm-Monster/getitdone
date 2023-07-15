package com.oretex.getitdone.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class OverErrorMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An Error Occured,Make sure you have implemented every thing well")
                .build();
    }

}
