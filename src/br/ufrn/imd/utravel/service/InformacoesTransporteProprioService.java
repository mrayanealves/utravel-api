package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.InformacoesVeiculoProprioDTO;
import br.ufrn.imd.utravel.model.InformacoesTransporteProprio;
import br.ufrn.imd.utravel.model.Veiculo;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.InformacoesTransporteProprioRepository;

@Stateless
public class InformacoesTransporteProprioService extends AbstractService<InformacoesTransporteProprio>{
	@Inject
	private InformacoesTransporteProprioRepository repository;
	
	@Inject
    private VeiculoService veiculoService;

	@Override
	protected AbstractRepository<InformacoesTransporteProprio> repository() {
		return this.repository;
	}
	
	public InformacoesTransporteProprio montarInformacoesTransporteProprio(InformacoesVeiculoProprioDTO informacoesVeiculoProprioDTO) {
		Veiculo veiculo = new Veiculo();
		
		if (informacoesVeiculoProprioDTO.getVeiculoDTO().getIdVeiculo() != 0) {
			veiculo = veiculoService.buscarPorId(informacoesVeiculoProprioDTO.getVeiculoDTO().getIdVeiculo());
			
			if (veiculo == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar um veiculo com este id.");
			}
			
		} else {
			veiculo = veiculoService.montarVeiculo(informacoesVeiculoProprioDTO.getVeiculoDTO());
			
			veiculo = veiculoService.salvar(veiculo);
		}
		
		InformacoesTransporteProprio informacoesTransporteProprio = new InformacoesTransporteProprio();
		
		informacoesTransporteProprio.setValorGastoCombustivelPrevisto(informacoesVeiculoProprioDTO.getValorGastoCombustivelPrevisto());;
		informacoesTransporteProprio.setValorGastoPedagioPrevisto(informacoesVeiculoProprioDTO.getValorGastoPedagioPrevisto());;
		informacoesTransporteProprio.setVeiculo(veiculo);
		
		return informacoesTransporteProprio;
	}
}
