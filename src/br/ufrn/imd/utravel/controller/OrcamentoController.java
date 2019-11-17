package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.dto.OrcamentoDTO;
import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.OrcamentoService;
import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Api("Orcamento")
@Stateless
@Path("/orcamento")
public class OrcamentoController {
    @EJB
    private OrcamentoService orcamentoService;
    
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/")
    @Secured
    public Response buscarTodos() {
        return Response.ok(orcamentoService.buscarTodos()).build();
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    @Secured
    public Response buscarPorId(@PathParam("id") long id) {
        return Response.ok(orcamentoService.buscarPorId(id)).build();
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/")
    @Secured
    public Response salvar(@Valid OrcamentoDTO orcamentoDTO) {
        return Response.ok(orcamentoService.salvar(orcamentoDTO)).build();
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    @Secured
    public Response remover(@PathParam("id") long id) {
        Orcamento entity = orcamentoService.buscarPorId(id);

        if (entity == null) {
            return Response.status(404).build();
        }

        return Response.ok(orcamentoService.remover(entity)).build();
    }
}
