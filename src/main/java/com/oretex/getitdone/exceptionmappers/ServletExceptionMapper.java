package com.oretex.getitdone.exceptionmappers;

import jakarta.servlet.ServletException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServletExceptionMapper implements ExceptionMapper<ServletException> {

    @Override
    public Response toResponse(ServletException exception) {

        System.err.println(exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An Error Occured, this may be from our end")
                .build();

    }

}
