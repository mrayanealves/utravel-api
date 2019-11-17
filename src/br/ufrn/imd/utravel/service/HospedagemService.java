package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.repository.HospedagemRepository;

@Stateless
public class HospedagemService {
    @Inject
    private HospedagemRepository repository;
}
