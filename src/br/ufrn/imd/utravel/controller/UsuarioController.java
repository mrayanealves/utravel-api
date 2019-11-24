package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.dto.Login;
import br.ufrn.imd.utravel.exception.LoginException;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.UsuarioService;
import io.swagger.annotations.Api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Api("Usuario")
@Stateless
@Path("/usuario")
public class UsuarioController extends AbstractController<Usuario> {
    @Inject
    private UsuarioService service;

    @Override
    protected AbstractService<Usuario> service() {
        return service;
    }
    
    @Override
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/")
    public Response salvar(@Valid Usuario entity) {
        return Response.ok(this.service.salvar(entity)).build();
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Path("/login")
    public Response login(@Valid Login login) throws LoginException {
        return Response.ok(service.login(login)).build();
    }
}
