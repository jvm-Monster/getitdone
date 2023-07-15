package com.oretex.getitdone.exceptionmappers;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidJsonFormat implements ExceptionMapper<ProcessingException> {

    @Override
    public Response toResponse(ProcessingException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("An error occured, is it a correct json format ?")
                .build();
    }

}
