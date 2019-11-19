package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.HospedagemDTO;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Hospedagem;
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
    
	@Override
	protected AbstractRepository<Hospedagem> repository() {
		return this.repository;
	}
	
	public Hospedagem montarHospedagem(HospedagemDTO hospedagemDTO) {
		Endereco endereco = new Endereco();
		Empresa empresa = new Empresa();
		
		if (hospedagemDTO.getEnderecoDTO().getIdEndereco() != 0) {
			endereco = enderecoService.buscarPorId(hospedagemDTO.getEnderecoDTO().getIdEndereco());
		} else {
			endereco = enderecoService.montarEndereco(hospedagemDTO.getEnderecoDTO());
			
			endereco = enderecoService.salvar(endereco);
		}
		
		if (hospedagemDTO.getEmpresaDTO().getIdEmpresa() != 0) {
			empresa = empresaService.buscarPorId(hospedagemDTO.getEmpresaDTO().getIdEmpresa());
		} else {
			empresa = empresaService.montarEmpresa(hospedagemDTO.getEmpresaDTO());
			
			empresa = empresaService.salvar(empresa);
		}
		
		Hospedagem hospedagem = new Hospedagem();
		
		hospedagem.setTipoHospedagem(hospedagemDTO.getTipoHospedagem());
		hospedagem.setEndereco(endereco);
		hospedagem.setEmpresa(empresa);
		
		return hospedagem;
	}
}
