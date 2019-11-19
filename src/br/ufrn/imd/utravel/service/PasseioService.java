package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.AvaliacaoDTO;
import br.ufrn.imd.utravel.dto.PasseioDTO;
import br.ufrn.imd.utravel.model.Avaliacao;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Passeio;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.PasseioRepository;

@Stateless
public class PasseioService extends AbstractService<Passeio> {
    @Inject
    private PasseioRepository repository;
    
    @Inject
    private EnderecoService enderecoService;
    
    @Inject
    private EmpresaService empresaService;
    
    @Inject
    private AvaliacaoService avaliacaoService;

    @Override
    protected AbstractRepository<Passeio> repository() {
        return this.repository;
    }
    
    public Passeio montarPasseio(PasseioDTO passeioDTO) {
    	Endereco endereco = new Endereco();
		Empresa empresa = new Empresa();
		
		if (passeioDTO.getEnderecoDTO().getIdEndereco() != 0) {
			endereco = enderecoService.buscarPorId(passeioDTO.getEnderecoDTO().getIdEndereco());
			
			if (endereco == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar um endereço com este id.");
			}
		} else {
			endereco = enderecoService.montarEndereco(passeioDTO.getEnderecoDTO());
			
			endereco = enderecoService.salvar(endereco);
		}
		
		if (passeioDTO.getEmpresaDTO().getIdEmpresa() != 0) {
			empresa = empresaService.buscarPorId(passeioDTO.getEmpresaDTO().getIdEmpresa());
			
			if (empresa == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar uma empresa com este id.");
			}
		} else {
			endereco = enderecoService.buscarEndereco(passeioDTO.getEnderecoDTO());
			
			if (endereco == null) {
				endereco = enderecoService.montarEndereco(passeioDTO.getEnderecoDTO());
				
				endereco = enderecoService.salvar(endereco);
			}
		}
		
		Passeio passeio = new Passeio();
		
		passeio.setNome(passeioDTO.getNome());
		passeio.setTipo(passeioDTO.getTipo());
		passeio.setEndereco(endereco);
		passeio.getEmpresasOfertantes().add(empresa);
    	
    	return passeio;
	}
    
    public Avaliacao avaliar(long id, AvaliacaoDTO avaliacaoDTO, Usuario usuario) {
		Passeio passeio = this.buscarPorId(id);
		
		if (passeio == null) {
    		throw new EntityNotFoundException("Não foi possível localizar um passeio com este id.");
		}

		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setComentario(avaliacaoDTO.getComentario());
		avaliacao.setNotaAtendimento(avaliacaoDTO.getNotaAvaliacao());
		avaliacao.setUsuarioAvaliador(usuario);

		avaliacao.setPasseio(passeio);

		return avaliacaoService.salvar(avaliacao);
	}
}
