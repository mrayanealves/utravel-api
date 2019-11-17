package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Avaliacao;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.AvaliacaoService;
import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Api("Avaliacao")
@Stateless
@Path("/avaliacao")
public class AvaliacaoController extends AbstractController<Avaliacao> {
    @EJB
    private AvaliacaoService avaliacaoService;

    @Override
    protected AbstractService<Avaliacao> service() {
        return avaliacaoService;
    }
}
