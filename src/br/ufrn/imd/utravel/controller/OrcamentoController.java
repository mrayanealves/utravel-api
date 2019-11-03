package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.OrcamentoService;
import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

@Api("Orcamento")
@Stateless
@Path("/orcamento")
public class OrcamentoController extends AbstractController<Orcamento> {
    @EJB
    private OrcamentoService orcamentoService;
    @Override
    protected AbstractService<Orcamento> service() {
        return orcamentoService;
    }
}
