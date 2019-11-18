package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.EnderecoDTO;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.EnderecoRepository;

@Stateless
public class EnderecoService extends AbstractService<Endereco> {
    @Inject
    private EnderecoRepository repository;
    
    @Inject LocalizacaoService localizacaoService;

    @Override
    protected AbstractRepository<Endereco> repository() {
        return this.repository;
    }
    
    public Endereco buscarEndereco(EnderecoDTO enderecoDTO) {
    	return repository.buscarEndereco(enderecoDTO);
    }
    
    public Endereco montarEndereco(EnderecoDTO enderecoDTO) {
    	Endereco endereco = this.buscarEndereco(enderecoDTO);
    	
    	if (endereco == null) {
			Localizacao localizacao = localizacaoService.buscarLocalizacao(enderecoDTO);
			
			if (localizacao == null) {
				localizacao = new Localizacao();
				
				localizacao.setCidade(enderecoDTO.getCidade());
				localizacao.setEstado(enderecoDTO.getEstado());
				localizacao.setPais(enderecoDTO.getPais());
			}
			
			endereco = new Endereco();
			
			endereco.setEndereco(enderecoDTO.getEndereco());
			endereco.setLocalizacao(localizacao);
		}
    	
    	return endereco;
    }
}
