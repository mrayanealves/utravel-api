package br.ufrn.imd.utravel.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.ufrn.imd.utravel.model.Passeio;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.PasseioService;
import io.swagger.annotations.Api;

@Api("Passeio")
@Stateless
@Path("/passeio")
public class PasseioController extends AbstractController<Passeio> {
    @EJB
    private PasseioService service;

    @Override
    protected AbstractService<Passeio> service() {
        return this.service;
    }
}
