package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.EnderecoService;
import io.swagger.annotations.Api;

@Api("Endereco")
@Stateless
@Path("/endereco")
public class EnderecoController extends AbstractController<Endereco> {
    @EJB
    private EnderecoService service;

    @Override
    protected AbstractService<Endereco> service() {
        return this.service;
    }
}
