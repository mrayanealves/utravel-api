package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.VeiculoDTO;
import br.ufrn.imd.utravel.model.Veiculo;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.VeiculoRepository;

@Stateless
public class VeiculoService extends AbstractService<Veiculo> {
    @Inject
    private VeiculoRepository repository;

    @Override
    protected AbstractRepository<Veiculo> repository() {
        return this.repository;
    }
    
    public Veiculo montarVeiculo(VeiculoDTO veiculoDTO) {
    	Veiculo veiculo = new Veiculo();
    	
    	veiculo.setCor(veiculoDTO.getCor());
    	veiculo.setMarca(veiculoDTO.getMarca());
    	veiculo.setModelo(veiculoDTO.getModelo());
    	veiculo.setPlaca(veiculoDTO.getPlaca());
    	veiculo.setTipoTransporte(veiculoDTO.getTipoTransporte());
		
		return veiculo;
	}
}
