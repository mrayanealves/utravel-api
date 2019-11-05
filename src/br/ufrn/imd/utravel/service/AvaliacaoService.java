package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.Avaliacao;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.AvaliacaoRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AvaliacaoService extends AbstractService<Avaliacao> {
    @Inject
    private AvaliacaoRepository avaliacaoRepository;

    @Override
    protected AbstractRepository<Avaliacao> repository() {
        return avaliacaoRepository;
    }
}
