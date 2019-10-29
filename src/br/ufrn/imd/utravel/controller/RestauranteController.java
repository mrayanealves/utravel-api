package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.RestauranteService;

@Stateless
@Path("/restaurante")
public class RestauranteController extends AbstractController<Restaurante>{
	@EJB
	private RestauranteService service;

	@Override
	protected AbstractService<Restaurante> service() {
		return this.service;
	}
}
