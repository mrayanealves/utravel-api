package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.AvaliacaoDTO;
import br.ufrn.imd.utravel.dto.HospedagemDTO;
import br.ufrn.imd.utravel.model.Avaliacao;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.HospedagemRepository;

@Stateless
public class HospedagemService extends AbstractService<Hospedagem>{
    @Inject
    private HospedagemRepository repository;
    
    @Inject
    private EnderecoService enderecoService;
    
    @Inject
    private EmpresaService empresaService;
    
    @Inject AvaliacaoService avaliacaoService;
    
	@Override
	protected AbstractRepository<Hospedagem> repository() {
		return this.repository;
	}
	
	public Hospedagem montarHospedagem(HospedagemDTO hospedagemDTO) {
		Endereco endereco = new Endereco();
		Empresa empresa = new Empresa();
		
		if (hospedagemDTO.getEnderecoDTO().getIdEndereco() != 0) {
			endereco = enderecoService.buscarPorId(hospedagemDTO.getEnderecoDTO().getIdEndereco());
			
			if (endereco == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar um endereço com este id.");
			}
			
		} else {
			endereco = enderecoService.buscarEndereco(hospedagemDTO.getEnderecoDTO());
			
			if (endereco == null) {
				endereco = enderecoService.montarEndereco(hospedagemDTO.getEnderecoDTO());
				
				endereco = enderecoService.salvar(endereco);
			}
		}
		
		if (hospedagemDTO.getEmpresaDTO().getIdEmpresa() != 0) {
			empresa = empresaService.buscarPorId(hospedagemDTO.getEmpresaDTO().getIdEmpresa());
			
			if (empresa == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar uma empresa com este id.");
			}
			
		} else {
			empresa = empresaService.montarEmpresa(hospedagemDTO.getEmpresaDTO());
			
			empresa = empresaService.salvar(empresa);
		}
		
		Hospedagem hospedagem = new Hospedagem();
		
		hospedagem.setTitulo(hospedagemDTO.getTitulo());
		hospedagem.setTipoHospedagem(hospedagemDTO.getTipoHospedagem());
		hospedagem.setEndereco(endereco);
		hospedagem.setEmpresa(empresa);
		
		return hospedagem;
	}
	
	public Avaliacao avaliar(long id, AvaliacaoDTO avaliacaoDTO, Usuario usuario) {
		Hospedagem hospedagem = this.buscarPorId(id);

		if (hospedagem == null) {
    		throw new EntityNotFoundException("Não foi possível localizar uma hospedagem com este id.");
		}
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setComentario(avaliacaoDTO.getComentario());
		avaliacao.setNotaAtendimento(avaliacaoDTO.getNotaAvaliacao());
		avaliacao.setUsuarioAvaliador(usuario);

		avaliacao.setHospedagem(hospedagem);

		return avaliacaoService.salvar(avaliacao);
	}
}
