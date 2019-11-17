package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.dto.ViagemDTO;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.UsuarioService;
import br.ufrn.imd.utravel.service.ViagemService;
import io.swagger.annotations.Api;

import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Api("Viagem")
@Stateless
@Path("/viagem")
public class ViagemController {
    @EJB
    private ViagemService viagemService;
    
    @EJB
    private UsuarioService usuarioService;

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/")
    @Secured
    public Response buscarTodos() {
        return Response.ok(viagemService.buscarTodos()).build();
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    @Secured
    public Response buscarPorId(@PathParam("id") long id) {
        return Response.ok(viagemService.buscarPorId(id)).build();
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/")
    @Secured
    public Response salvar(ViagemDTO viagemDTO, @Context SecurityContext securityContext) {
        try {
            Usuario usuario = usuarioService.encontrarUsuarioLogado(securityContext.getUserPrincipal().getName());
            
            return Response.ok(viagemService.salvar(viagemDTO, usuario)).build();
        } catch (ParseException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).encoding("O formato padrão das datas é dd/MM/yyyy.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).encoding(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    @Secured
    public Response remover(@PathParam("id") long id) {
        Viagem viagem = viagemService.buscarPorId(id);

        if (viagem == null) {
            return Response.status(404).build();
        }

        return Response.ok(viagemService.remover(viagem)).build();
    }
}
