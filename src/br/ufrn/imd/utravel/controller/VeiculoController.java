package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.Veiculo;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.VeiculoService;
import io.swagger.annotations.Api;

@Api("Veiculo")
@Stateless
@Path("/veiculo")
public class VeiculoController extends AbstractController<Veiculo> {
    @EJB
    private VeiculoService service;

    @Override
    protected AbstractService<Veiculo> service() {
        return this.service;
    }
}
