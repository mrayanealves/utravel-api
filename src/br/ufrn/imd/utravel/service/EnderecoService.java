package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.EnderecoRepository;

@Stateless
public class EnderecoService extends AbstractService<Endereco>{
	@Inject
	private EnderecoRepository repository;

	@Override
	protected AbstractRepository<Endereco> repository() {
		return this.repository;
	}
}
