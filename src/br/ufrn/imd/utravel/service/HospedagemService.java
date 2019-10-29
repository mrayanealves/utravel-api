package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.HospedagemRepository;

@Stateless
public class HospedagemService extends AbstractService<Hospedagem>{
	@Inject
	private HospedagemRepository repository;

	@Override
	protected AbstractRepository<Hospedagem> repository() {
		return this.repository;
	}
}
