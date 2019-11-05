package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import br.ufrn.imd.utravel.dto.Login;
import br.ufrn.imd.utravel.exception.LoginException;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.security.AuthenticatedUser;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.UsuarioService;

@Stateless
@Path("/usuario")
public class UsuarioController extends AbstractController<Usuario>{
	@EJB
	private UsuarioService service;
	
	@Inject
	@AuthenticatedUser
	private Usuario usuarioLogado;
	
	@Override
	protected AbstractService<Usuario> service() {
		return service;
	}
	
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Path("/login")
	public Response login(Login login) {
		try {
			return Response.ok(service.login(login)).build();
		} catch (LoginException e) {
			return Response.status(Status.UNAUTHORIZED).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logado")
	@Secured
	public Response logado(@Context SecurityContext securityContext) {
		return Response.ok(usuarioLogado.getNome()).build(); 
	}
}
