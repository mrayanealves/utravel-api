package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.PasseioDTO;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Passeio;
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

    @Override
    protected AbstractRepository<Passeio> repository() {
        return this.repository;
    }
    
    public Passeio montarPasseio(PasseioDTO passeioDTO) {
    	Endereco endereco = new Endereco();
		Empresa empresa = new Empresa();
		
		if (passeioDTO.getEnderecoDTO().getIdEndereco() != 0) {
			endereco = enderecoService.buscarPorId(passeioDTO.getEnderecoDTO().getIdEndereco());
		} else {
			endereco = enderecoService.montarEndereco(passeioDTO.getEnderecoDTO());
			
			endereco = enderecoService.salvar(endereco);
		}
		
		if (passeioDTO.getEmpresaDTO().getIdEmpresa() != 0) {
			empresa = empresaService.buscarPorId(passeioDTO.getEmpresaDTO().getIdEmpresa());
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
}
