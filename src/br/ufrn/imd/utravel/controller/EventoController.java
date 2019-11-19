package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.EventoService;
import io.swagger.annotations.Api;

@Api("Evento")
@Stateless
@Path("/evento")
public class EventoController {
	@EJB 
	private EventoService service;
	
	@GET
    @Produces("application/json; charset=UTF-8")
    @Path("/")
    @Secured
    public Response buscarTodos() {
        return Response.ok(service.buscarTodos()).build();
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    @Secured
    public Response buscarPorId(@PathParam("id") long id) {
        return Response.ok(service.buscarPorId(id)).build();
    }
}
