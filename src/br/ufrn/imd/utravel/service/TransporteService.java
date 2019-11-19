package br.ufrn.imd.utravel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.PassagemDTO;
import br.ufrn.imd.utravel.dto.TransporteDTO;
import br.ufrn.imd.utravel.model.Evento;
import br.ufrn.imd.utravel.model.InformacoesTransporteProprio;
import br.ufrn.imd.utravel.model.Passagem;
import br.ufrn.imd.utravel.model.Transporte;
import br.ufrn.imd.utravel.model.VeiculoAlugado;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.TransporteRepository;

@Stateless
public class TransporteService extends AbstractService<Transporte>{
	@Inject
	private TransporteRepository repository;
    
    @Inject
    private PassagemService passagemService;
    
    @Inject
    private VeiculoAlugadoService veiculoAlugadoService;
    
    @Inject
    private InformacoesTransporteProprioService informacoesTransporteProprioService;

	@Override
	protected AbstractRepository<Transporte> repository() {
		return this.repository;
	}
	
	public Transporte adicionarTrasporte(TransporteDTO transporteDTO, Viagem viagem) throws ParseException {
		List<Passagem> passagens = new ArrayList<Passagem>();
		VeiculoAlugado veiculoAlugado = null;
		InformacoesTransporteProprio informacoesTransporteProprio = null;
		
		if (!transporteDTO.isTransporteProprio() && transporteDTO.getVeiculoAlugadoDTO() == null) {
			if (transporteDTO.getPassagensDTO() != null && !transporteDTO.getPassagensDTO().isEmpty()) {
				Passagem passagem = new Passagem();
				
				for (PassagemDTO passagemDTO : transporteDTO.getPassagensDTO()) {
					passagem = passagemService.montarPassagem(passagemDTO);
					
					Evento evento = new Evento();
					
					evento.setTitulo("Passagem da viagem");
					evento.setDataInicio(passagem.getDataPartida());
					evento.setDataFinal(passagem.getDataChegada());
					evento.setValorEstimado(passagemDTO.getValorGasto());
					evento.setViagem(viagem);
					evento.setPassagem(passagem);
					
					passagem.getEventos().add(evento);
					
					passagem = passagemService.salvar(passagem);
					
					passagens.add(passagem);
				}
			}
			
		} 
		else if(!transporteDTO.isTransporteProprio() && transporteDTO.getVeiculoAlugadoDTO() != null) {
			veiculoAlugado = veiculoAlugadoService.montarVeiculoAlugado(transporteDTO.getVeiculoAlugadoDTO());
			
			Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(transporteDTO.getVeiculoAlugadoDTO().getDataAluguel());
	        Date dataFim = null;

	        if ((transporteDTO.getVeiculoAlugadoDTO().getDataEntrega() != null) && (!transporteDTO.getVeiculoAlugadoDTO().getDataEntrega().equals(""))) {
	            dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(transporteDTO.getVeiculoAlugadoDTO().getDataEntrega());
	        }
			
			Evento evento = new Evento();
			
			evento.setTitulo("Alguel de veículo");
			evento.setDataInicio(dataInicio);
			evento.setDataFinal(dataFim);
			evento.setValorEstimado(transporteDTO.getVeiculoAlugadoDTO().getValorGastoPrevisto());
			evento.setViagem(viagem);
			evento.setVeiculoAlugado(veiculoAlugado);
			
			veiculoAlugado.getEventos().add(evento);
						
			veiculoAlugado = veiculoAlugadoService.salvar(veiculoAlugado);
		}
		else if(transporteDTO.isTransporteProprio()) {
			informacoesTransporteProprio = informacoesTransporteProprioService.montarInformacoesTransporteProprio(transporteDTO.getInformacoesVeiculoProprioDTO());
			
			informacoesTransporteProprio = informacoesTransporteProprioService.salvar(informacoesTransporteProprio);
		}
		
		Transporte transporte = new Transporte();
		
		transporte.setProprio(transporteDTO.isTransporteProprio());
		transporte.setTipoTransporte(transporteDTO.getTipoTransporte());
		transporte.setPassagens(passagens);
		transporte.setVeiculoAlugado(veiculoAlugado);
		transporte.setInformacoesTransporteProprio(informacoesTransporteProprio);
		transporte.setViagem(viagem);
		
		return repository.salvar(transporte);
	}
}
