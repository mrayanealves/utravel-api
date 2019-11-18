package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.HospedagemService;
import br.ufrn.imd.utravel.service.UsuarioService;
import io.swagger.annotations.Api;

@Api("Hospedagem")
@Stateless
@Path("/hospedagem")
public class HospedagemController {
    @EJB
    private HospedagemService service;
    
    @EJB
    private UsuarioService usuarioService;
    
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

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    @Secured
    public Response remover(@PathParam("id") long id) {
        Hospedagem hospedagem = service.buscarPorId(id);

        if (hospedagem == null) {
            return Response.status(404).build();
        }

        return Response.ok(service.remover(hospedagem)).build();
    }
}
