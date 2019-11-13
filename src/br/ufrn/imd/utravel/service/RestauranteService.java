package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.RestauranteRepository;

@Stateless
public class RestauranteService extends AbstractService<Restaurante> {
    @Inject
    private RestauranteRepository repository;

    @Override
    protected AbstractRepository<Restaurante> repository() {
        return this.repository;
    }
}
