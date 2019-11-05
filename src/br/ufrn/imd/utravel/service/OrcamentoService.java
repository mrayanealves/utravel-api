package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.OrcamentoRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class OrcamentoService extends AbstractService<Orcamento> {
    @Inject
    private OrcamentoRepository orcamentoRepository;
    @Override
    protected AbstractRepository<Orcamento> repository() {
        return orcamentoRepository;
    }
}
