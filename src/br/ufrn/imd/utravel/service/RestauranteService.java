package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.RestauranteDTO;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.RestauranteRepository;

@Stateless
public class RestauranteService extends AbstractService<Restaurante> {
    @Inject
    private RestauranteRepository repository;
    
    @Inject
    private EnderecoService enderecoService;
    
    @Inject
    private EmpresaService empresaService;

    @Override
    protected AbstractRepository<Restaurante> repository() {
        return this.repository;
    }
    
    public Restaurante montarRestaurante(RestauranteDTO restauranteDTO) {
		Endereco endereco = new Endereco();
		Empresa empresa = new Empresa();
		
		if (restauranteDTO.getEnderecoDTO().getIdEndereco() != 0) {
			endereco = enderecoService.buscarPorId(restauranteDTO.getEnderecoDTO().getIdEndereco());
		} else {
			endereco = enderecoService.montarEndereco(restauranteDTO.getEnderecoDTO());
			
			endereco = enderecoService.salvar(endereco);
		}
		
		if (restauranteDTO.getEmpresaDTO().getIdEmpresa() != 0) {
			empresa = empresaService.buscarPorId(restauranteDTO.getEmpresaDTO().getIdEmpresa());
		} else {
			endereco = enderecoService.buscarEndereco(restauranteDTO.getEnderecoDTO());
			
			if (endereco == null) {
				endereco = enderecoService.montarEndereco(restauranteDTO.getEnderecoDTO());
				
				endereco = enderecoService.salvar(endereco);
			}
		}
		
		Restaurante restaurante = new Restaurante();
		
		restaurante.setNome(restauranteDTO.getNomeRestaurante());
		restaurante.setNumeroEstrelas(restaurante.getNumeroEstrelas());
		restaurante.setEndereco(endereco);
		restaurante.setEmpresa(empresa);
		
		return restaurante;
	}
}
