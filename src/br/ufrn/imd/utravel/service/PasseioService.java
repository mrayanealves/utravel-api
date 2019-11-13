package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Passeio;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.PasseioRepository;

@Stateless
public class PasseioService extends AbstractService<Passeio> {
    @Inject
    private PasseioRepository repository;

    @Override
    protected AbstractRepository<Passeio> repository() {
        return this.repository;
    }
}
