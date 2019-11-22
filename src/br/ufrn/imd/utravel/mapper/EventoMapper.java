package br.ufrn.imd.utravel.mapper;

import br.ufrn.imd.utravel.dto.EventoDTO;
import br.ufrn.imd.utravel.model.Evento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface EventoMapper {
    Evento toEntity(EventoDTO eventoDTO);
    EventoDTO toDto(Evento evento);
}
