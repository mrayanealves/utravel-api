package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.AvaliacaoDTO;
import br.ufrn.imd.utravel.dto.EmpresaDTO;
import br.ufrn.imd.utravel.model.Avaliacao;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.EmpresaRepository;

@Stateless
public class EmpresaService extends AbstractService<Empresa> {
    @Inject
    private EmpresaRepository repository;
    
    @Inject
    private AvaliacaoService avaliacaoService;
    
    @Inject
    private EnderecoService enderecoService;

    @Override
    protected AbstractRepository<Empresa> repository() {
        return this.repository;
    }
    
    public Empresa avaliar(long id, AvaliacaoDTO avaliacaoDTO, Usuario usuario) {
    	Empresa empresa = this.buscarPorId(id);
    	
    	if (empresa == null) {
			throw new EntityNotFoundException("Não foi possível localizar uma empresa com este id.");
		}
    	
    	Avaliacao avaliacao = new Avaliacao();
    	avaliacao.setComentario(avaliacaoDTO.getComentario());
    	avaliacao.setNotaAtendimento(avaliacaoDTO.getNotaAvaliacao());
    	avaliacao.setUsuarioAvaliador(usuario);
    	
    	avaliacao.setEmpresa(empresa);
    	
		return avaliacaoService.salvar(avaliacao).getEmpresa();
	}
    
    public Empresa montarEmpresa(EmpresaDTO empresaDTO) {
    	Endereco endereco = new Endereco();
    	
    	if (empresaDTO.getEnderecoSede().getIdEndereco() != 0) {
			endereco = enderecoService.buscarPorId(empresaDTO.getIdEmpresa());
		} else {
			endereco = enderecoService.buscarEndereco(empresaDTO.getEnderecoSede());
			
			if (endereco == null) {
				endereco = enderecoService.montarEndereco(empresaDTO.getEnderecoSede());
				
				endereco = enderecoService.salvar(endereco);
			}
		}
    	
    	Empresa empresa = new Empresa();
    	
    	empresa.setNome(empresaDTO.getNome());
    	empresa.setDocumento(empresaDTO.getDocumento());
    	empresa.setEndereco(endereco);
    	
    	return empresa;
    }
}
