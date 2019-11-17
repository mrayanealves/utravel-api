package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.VeiculoAlugado;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.VeiculoAlugadoService;
import io.swagger.annotations.Api;

@Api("VeiculoAlugado")
@Stateless
@Path("/veiculo-alugado")
public class VeiculoAlugadoController extends AbstractController<VeiculoAlugado> {
    @EJB
    private VeiculoAlugadoService service;

    @Override
    protected AbstractService<VeiculoAlugado> service() {
        return this.service;
    }
}
