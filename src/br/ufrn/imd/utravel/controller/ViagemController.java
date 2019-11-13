package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.dto.ViagemDTO;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.security.AuthenticatedUser;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.ViagemService;
import io.swagger.annotations.Api;

import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Api("Viagem")
@Stateless
@Path("/viagem")
public class ViagemController {
    @EJB
    ViagemService viagemService;
    
    @Inject
   	@AuthenticatedUser
   	private Usuario usuarioLogado;
    
    @GET
	@Produces("application/json; charset=UTF-8")
	@Path("/")
	@Secured
	public Response buscarTodos(){
		return Response.ok(viagemService.buscarTodos()).build();
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/{id}")
	@Secured
	public Response buscarPorId(@PathParam("id") long id){	
		return Response.ok(viagemService.buscarPorId(id)).build();
	}
	
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/")
	@Secured
	public Response salvar(ViagemDTO viagemDTO) {
		try {
			System.out.println(usuarioLogado.getNome());
			return Response.ok(viagemService.salvar(viagemDTO, usuarioLogado)).build();
		} catch (ParseException e) {
			e.printStackTrace();
			
			return Response.status(Status.BAD_REQUEST).encoding("O formato padrão das datas é dd/MM/yyyy.").build();
		}	
	}
	
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/{id}")
	@Secured
	public Response remover(@PathParam("id") long id){
		Viagem viagem = viagemService.buscarPorId(id);
		
		if (viagem == null) {
			return Response.status(404).build();
		}

		return Response.ok(viagemService.remover(viagem)).build();
	}
}
