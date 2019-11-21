package br.ufrn.imd.utravel.mapper;

import br.ufrn.imd.utravel.dto.EventoDTO;
import br.ufrn.imd.utravel.model.Evento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface EventoMapper {
    Evento eventoDTOParaEvento(EventoDTO eventoDTO);
    List<Evento> eventoDTOParaEvento(List<EventoDTO> eventoDTO);
    EventoDTO eventoParaEventoDTO(Evento evento);
    List<EventoDTO> eventoParaEventoDTO(List<Evento> evento);
}
