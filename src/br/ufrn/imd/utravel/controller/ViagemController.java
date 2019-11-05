package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.ViagemService;
import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Api("Viagem")
@Stateless
@Path("/viagem")
public class ViagemController extends AbstractController<Viagem> {
    @EJB
    ViagemService viagemService;
    
    @Override
    protected AbstractService<Viagem> service() {
        return viagemService;
    }
}
