package com.oretex.getitdone.exceptionmappers;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    // This trows a NotFounException error may be a class was not found or there was
    // a null value in the objects
    @Override
    public Response toResponse(NotFoundException exception) {
        // sends a bad request
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Bad Request")
                .build();
    }

}
