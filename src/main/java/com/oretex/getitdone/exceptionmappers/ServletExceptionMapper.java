package com.oretex.getitdone.exceptionmappers;

import jakarta.servlet.ServletException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ServletExceptionMapper implements ExceptionMapper<ServletException> {

    @Override
    public Response toResponse(ServletException exception) {

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An Error Occured")
                .build();

    }

}
