package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.ViagemRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ViagemService extends AbstractService<Viagem> {
    @Inject
    ViagemRepository viagemRepository;

    @Override
    protected AbstractRepository<Viagem> repository() {
        return viagemRepository;
    }
}
