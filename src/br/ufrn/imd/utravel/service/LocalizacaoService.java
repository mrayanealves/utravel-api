package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.LocalizacaoRepository;

@Stateless
public class LocalizacaoService extends AbstractService<Localizacao> {
    @Inject
    private LocalizacaoRepository repository;

    @Override
    protected AbstractRepository<Localizacao> repository() {
        return this.repository;
    }
}
