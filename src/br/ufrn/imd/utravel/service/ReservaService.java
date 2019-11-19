package br.ufrn.imd.utravel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.ReservaDTO;
import br.ufrn.imd.utravel.model.Evento;
import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.model.Reserva;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.ReservaRepository;

@Stateless
public class ReservaService {
	@Inject
	private ReservaRepository repository;
	
	@Inject
	private HospedagemService hospedagemService;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Reserva> buscarTodos() {
		return repository.buscarTodos();
	}

	@SuppressWarnings("null")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Reserva buscarPorId(long id) {
		Reserva entity = repository.buscarPorId(id);

		if (entity == null) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Não foi possível localizar um (a) ").append(entity.getClass().getName())
					.append(" com este id.");

			throw new EntityNotFoundException(stringBuilder.toString());
		}

		return entity;
	}
	
	public Reserva salvar(ReservaDTO reservaDTO, Viagem viagem) throws ParseException {
		Hospedagem hospedagem = new Hospedagem();
		
		if (reservaDTO.getHospedagemDTO().getIdHospedagem() != 0) {
			hospedagem = hospedagemService.buscarPorId(reservaDTO.getHospedagemDTO().getIdHospedagem());
		} else {
			hospedagem = hospedagemService.montarHospedagem(reservaDTO.getHospedagemDTO());
			
			hospedagem = hospedagemService.salvar(hospedagem);
		}
		
		Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(reservaDTO.getDataHospedagem());
		Date dataFim = null;
	    
		if (reservaDTO.getDataSaida() == null || reservaDTO.getDataSaida().isEmpty()) {
			dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(reservaDTO.getDataSaida());
		}
		
		Reserva reserva = new Reserva();
		Evento evento = new Evento();
		
		evento.setDataFinal(dataFim);
		evento.setDataInicio(dataInicio);
		evento.setValorEstimado(reservaDTO.getValorGastoPrevisto());
		evento.setReserva(reserva);
		evento.setViagem(viagem);
		
		reserva.setCodigo(reservaDTO.getCodigo());
		reserva.setQuantidadeQuartos(reservaDTO.getQuantidadeQuartos());
		reserva.setHospedagem(hospedagem);
		reserva.getEventos().add(evento);
		
		return repository.salvar(reserva);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String remover(Reserva entity) {
        repository.remover(entity);

        return "Removido com sucesso";
    }
}