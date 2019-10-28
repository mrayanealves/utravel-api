package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.EmpresaRepository;

@Stateless
public class EmpresaService extends AbstractService<Empresa>{
	@Inject
	private EmpresaRepository repository;
	
	@Override
	protected AbstractRepository<Empresa> repository() {
		return this.repository;
	}
}
