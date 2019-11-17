package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.VeiculoAlugado;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.VeiculoAlugadoRepository;

@Stateless
public class VeiculoAlugadoService extends AbstractService<VeiculoAlugado> {
    @Inject
    private VeiculoAlugadoRepository repository;

    @Override
    protected AbstractRepository<VeiculoAlugado> repository() {
        return this.repository;
    }
}
