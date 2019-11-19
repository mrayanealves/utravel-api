package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import br.ufrn.imd.utravel.dto.AvaliacaoDTO;
import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.HospedagemService;
import br.ufrn.imd.utravel.service.UsuarioService;
import io.swagger.annotations.Api;

@Api("Hospedagem")
@Stateless
@Path("/hospedagem")
public class HospedagemController extends AbstractController<Hospedagem>{
    @EJB
    private HospedagemService service;
    
    @EJB
    private UsuarioService usuarioService;

	@Override
	protected AbstractService<Hospedagem> service() {
		return this.service;
	}
	
	@POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}/avaliar")
    @Secured
    public Response avaliar(@PathParam("id") long id, @Valid AvaliacaoDTO avaliacaoDTO, 
    						@Context SecurityContext securityContext) {
    	Usuario usuario = usuarioService.buscarUsuarioPorEmail(securityContext.getUserPrincipal().getName());
    	
    	return Response.ok(service.avaliar(id, avaliacaoDTO, usuario)).build();
    }
}
