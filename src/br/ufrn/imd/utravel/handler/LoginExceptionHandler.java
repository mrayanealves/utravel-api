package br.ufrn.imd.utravel.handler;

import br.ufrn.imd.utravel.exception.LoginException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginExceptionHandler implements ExceptionMapper<LoginException> {
    @Override
    public Response toResponse(LoginException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
