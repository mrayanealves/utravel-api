package br.ufrn.imd.utravel.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.PassagemDTO;
import br.ufrn.imd.utravel.dto.TransporteDTO;
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
	
//	@Inject
//    private EnderecoService enderecoService;
//    
//    @Inject
//    private EmpresaService empresaService;
    
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
		VeiculoAlugado veiculoAlugado = new VeiculoAlugado();
		InformacoesTransporteProprio informacoesTransporteProprio = new InformacoesTransporteProprio();
		
		if (!transporteDTO.isTransporteProprio() && transporteDTO.getVeiculoAlugadoDTO() == null) {
			if (transporteDTO.getPassagensDTO() != null && !transporteDTO.getPassagensDTO().isEmpty()) {
				Passagem passagem = new Passagem();
				
				for (PassagemDTO passagemDTO : transporteDTO.getPassagensDTO()) {
					passagem = passagemService.montarPassagem(passagemDTO);
					
					passagens.add(passagem);
				}
			}
			
		} 
		else if(!transporteDTO.isTransporteProprio() && transporteDTO.getVeiculoAlugadoDTO() != null) {
			veiculoAlugado = veiculoAlugadoService.montarVeiculoAlugado(transporteDTO.getVeiculoAlugadoDTO());
			
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
