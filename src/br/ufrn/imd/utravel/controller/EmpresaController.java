package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.EmpresaService;
import io.swagger.annotations.Api;

@Api("Empresa")
@Stateless
@Path("/empresa")
public class EmpresaController extends AbstractController<Empresa> {
    @EJB
    private EmpresaService service;

    @Override
    protected AbstractService<Empresa> service() {
        return this.service;
    }
}
