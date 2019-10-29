package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.LocalizacaoService;

@Stateless
@Path("/localizacao")
public class LocalizacaoController extends AbstractController<Localizacao>{
	@EJB
	private LocalizacaoService service;

	@Override
	protected AbstractService<Localizacao> service() {
		return this.service;
	}
}
