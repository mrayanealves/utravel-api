package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Evento;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.EventoRepository;

@Stateless
public class EventoService extends AbstractService<Evento>{
	@Inject
	private EventoRepository repository;
	
	@Override
	protected AbstractRepository<Evento> repository() {
		return this.repository;
	}
}
