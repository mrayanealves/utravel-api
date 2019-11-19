package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.dto.GrupoUsuariosDTO;
import br.ufrn.imd.utravel.dto.ReservaDTO;
import br.ufrn.imd.utravel.dto.TurismoDTO;
import br.ufrn.imd.utravel.dto.AlimentacaoDTO;
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
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(securityContext.getUserPrincipal().getName());
            
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
    
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}/adicionar/participantes")
    @Secured
    public Response adicionarParticipantes(@PathParam("id") long id, GrupoUsuariosDTO grupoUsuariosDTO, 
    										@Context SecurityContext securityContext) {
    	Usuario usuario = usuarioService.buscarUsuarioPorEmail(securityContext.getUserPrincipal().getName());
    	
    	return Response.ok(viagemService.adicionarParticipantes(id, grupoUsuariosDTO, usuario)).build();
    }
    
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}/adicionar/reserva/hospedagem")
    @Secured
    public Response adicionarReservaHospedagem(@PathParam("id") long id, ReservaDTO reservaDTO, 
    										@Context SecurityContext securityContext) {
    	Usuario usuario = usuarioService.buscarUsuarioPorEmail(securityContext.getUserPrincipal().getName());
    	
    	try {
			return Response.ok(viagemService.adicionarReservaHospedagem(id, reservaDTO, usuario)).build();
		} catch (ParseException e) {
			e.printStackTrace();
			
            return Response.status(Response.Status.BAD_REQUEST).encoding("O formato padrão das datas é dd/MM/yyyy.").build();
		}
    }
    
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}/adicionar/restaurante")
    @Secured
    public Response adicionarRestaurante(@PathParam("id") long id, AlimentacaoDTO alimentacaoDTO, 
    										@Context SecurityContext securityContext) {
    	Usuario usuario = usuarioService.buscarUsuarioPorEmail(securityContext.getUserPrincipal().getName());
    	
    	try {
			return Response.ok(viagemService.adicionarRestaurantes(id, alimentacaoDTO, usuario)).build();
		} catch (ParseException e) {
			e.printStackTrace();
			
            return Response.status(Response.Status.BAD_REQUEST).encoding("O formato padrão das datas é dd/MM/yyyy.").build();
		}
    }
    
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}/adicionar/passeio")
    @Secured
    public Response adicionarPasseios(@PathParam("id") long id, TurismoDTO turismoDTO, 
    										@Context SecurityContext securityContext) {
    	Usuario usuario = usuarioService.buscarUsuarioPorEmail(securityContext.getUserPrincipal().getName());
    	
    	try {
			return Response.ok(viagemService.adicionarPasseios(id, turismoDTO, usuario)).build();
		} catch (ParseException e) {
			e.printStackTrace();
			
            return Response.status(Response.Status.BAD_REQUEST).encoding("O formato padrão das datas é dd/MM/yyyy.").build();
		}
    }
}
