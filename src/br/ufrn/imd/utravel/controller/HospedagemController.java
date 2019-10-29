package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.HospedagemService;

@Stateless
@Path("/hospedagem")
public class HospedagemController extends AbstractController<Hospedagem>{
	@EJB
	private HospedagemService service;

	@Override
	protected AbstractService<Hospedagem> service() {
		return this.service;
	};
}
