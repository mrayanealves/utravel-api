package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.AvaliacaoDTO;
import br.ufrn.imd.utravel.dto.RestauranteDTO;
import br.ufrn.imd.utravel.model.Avaliacao;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.model.Usuario;
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
    
    @Inject
    private AvaliacaoService avaliacaoService;

    @Override
    protected AbstractRepository<Restaurante> repository() {
        return this.repository;
    }
    
    public Restaurante montarRestaurante(RestauranteDTO restauranteDTO) {
		Endereco endereco = new Endereco();
		Empresa empresa = new Empresa();
		
		if (restauranteDTO.getEnderecoDTO().getIdEndereco() != 0) {
			endereco = enderecoService.buscarPorId(restauranteDTO.getEnderecoDTO().getIdEndereco());
			
			if (endereco == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar um endereço com este id.");
			}
			
		} else {
			endereco = enderecoService.montarEndereco(restauranteDTO.getEnderecoDTO());
			
			endereco = enderecoService.salvar(endereco);
		}
		
		if (restauranteDTO.getEmpresaDTO().getIdEmpresa() != 0) {
			empresa = empresaService.buscarPorId(restauranteDTO.getEmpresaDTO().getIdEmpresa());
			
			if (empresa == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar uma empresa com este id.");
			}
		} else {
			empresa = empresaService.buscarPorId(restauranteDTO.getEmpresaDTO().getIdEmpresa());
			
			if (empresa == null) {
				empresa = empresaService.montarEmpresa(restauranteDTO.getEmpresaDTO());
				
				empresa = empresaService.salvar(empresa);
			}
		}
		
		Restaurante restaurante = new Restaurante();
		
		restaurante.setNome(restauranteDTO.getNomeRestaurante());
		restaurante.setNumeroEstrelas(restaurante.getNumeroEstrelas());
		restaurante.setEndereco(endereco);
		restaurante.setEmpresa(empresa);
		
		return restaurante;
	}
    
    public Avaliacao avaliar(long id, AvaliacaoDTO avaliacaoDTO, Usuario usuario) {
		Restaurante restaurante = this.buscarPorId(id);
		
		if (restaurante == null) {
    		throw new EntityNotFoundException("Não foi possível localizar um restaurante com este id.");
		}

		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setComentario(avaliacaoDTO.getComentario());
		avaliacao.setNotaAtendimento(avaliacaoDTO.getNotaAvaliacao());
		avaliacao.setUsuarioAvaliador(usuario);

		avaliacao.setRestaurante(restaurante);

		return avaliacaoService.salvar(avaliacao);
	}
}
