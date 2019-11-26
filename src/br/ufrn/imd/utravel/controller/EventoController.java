package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.EventoService;
import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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

    @GET
    @Path("/{id}/buscar")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Secured
    public Response buscarPorViagemId(@PathParam("id") long idViagem) {
        return Response.ok(service.buscarPorViagemId(idViagem)).build();
    }
}
