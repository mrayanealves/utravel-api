package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.dto.EventoDTO;
import br.ufrn.imd.utravel.mapper.EventoMapper;
import br.ufrn.imd.utravel.model.Evento;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.AbstractService;
import br.ufrn.imd.utravel.service.EventoService;
import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Api("Evento")
@Stateless
@Path("/evento")
public class EventoController extends AbstractController<Evento> {
	@EJB 
	private EventoService service;
	@Inject
	private EventoMapper eventoMapper;

    @Override
    protected AbstractService<Evento> service() {
        return service;
    }

    @Secured
    @Override
    public Response buscarTodos() {
        List<Evento> eventos = service.buscarTodos();
        List<EventoDTO> eventosDTO = eventoMapper.eventoParaEventoDTO(eventos);
        return Response.ok(eventosDTO).build();
    }
}
