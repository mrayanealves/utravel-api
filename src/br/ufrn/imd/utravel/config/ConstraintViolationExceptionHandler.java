package br.ufrn.imd.utravel.config;

import br.ufrn.imd.utravel.exception.FieldError;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<FieldError> fieldErrors = e.getConstraintViolations().stream().map(x ->
                new FieldError(x.getPropertyPath().toString(), x.getMessage()))
                .collect(Collectors.toList());
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(fieldErrors)
                .type("application/json")
                .build();
    }
}
