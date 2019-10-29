package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.UsuarioService;

@Stateless
@Path("/usuario")
public class UsuarioController extends AbstractController<Usuario>{
	@EJB
	private UsuarioService service;
	
	@Override
	protected AbstractService<Usuario> service() {
		return service;
	}
	
}
