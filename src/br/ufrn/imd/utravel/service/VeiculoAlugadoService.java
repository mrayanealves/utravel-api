package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.VeiculoAlugadoDTO;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Veiculo;
import br.ufrn.imd.utravel.model.VeiculoAlugado;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.VeiculoAlugadoRepository;

@Stateless
public class VeiculoAlugadoService extends AbstractService<VeiculoAlugado>{
	@Inject
	private VeiculoAlugadoRepository repository;
    
    @Inject
    private EmpresaService empresaService;
    
    @Inject
    private VeiculoService veiculoService;

	@Override
	protected AbstractRepository<VeiculoAlugado> repository() {
		return this.repository;
	}
	
	public VeiculoAlugado montarVeiculoAlugado(VeiculoAlugadoDTO veiculoAlugadoDTO) {
		Empresa empresa = new Empresa();
		Veiculo veiculo = new Veiculo();
		
		if (veiculoAlugadoDTO.getEmpresaDTO().getIdEmpresa() != 0) {
			empresa = empresaService.buscarPorId(veiculoAlugadoDTO.getEmpresaDTO().getIdEmpresa());
		} else {
			empresa = empresaService.montarEmpresa(veiculoAlugadoDTO.getEmpresaDTO());
			
			empresa = empresaService.salvar(empresa);
		}
		
		if (veiculoAlugadoDTO.getVeiculoDTO().getIdVeiculo() != 0) {
			veiculo = veiculoService.buscarPorId(veiculoAlugadoDTO.getVeiculoDTO().getIdVeiculo());
		} else {
			veiculo = veiculoService.montarVeiculo(veiculoAlugadoDTO.getVeiculoDTO());
			
			veiculo = veiculoService.salvar(veiculo);
		}
		
		VeiculoAlugado veiculoAlugado = new VeiculoAlugado();
		veiculoAlugado.setEmpresaLocadora(empresa);
		veiculoAlugado.setVeiculo(veiculo);
		
		return veiculoAlugado;
	}
}
