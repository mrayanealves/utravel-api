package br.ufrn.imd.utravel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.EnderecoDTO;
import br.ufrn.imd.utravel.dto.PassagemDTO;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Passagem;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.PassagemRepository;

@Stateless
public class PassagemService extends AbstractService<Passagem>{
	@Inject
	private PassagemRepository repository;
	
	@Inject
	private EnderecoService enderecoService;
	
	@Inject
	private EmpresaService empresaService;

	@Override
	protected AbstractRepository<Passagem> repository() {
		return this.repository;
	}
	
	public Passagem montarPassagem(PassagemDTO passagemDTO) throws ParseException {
		Endereco enderecoSaidaOrigem = new Endereco();
		Endereco enderecoChegadaDestino = new Endereco();
		List<Endereco> enderecosParadas = new ArrayList<Endereco>();
		Empresa empresa = new Empresa();
		
		if (passagemDTO.getEnderecoSaidaOrigem().getIdEndereco() != 0) {
			enderecoSaidaOrigem = enderecoService.buscarPorId(passagemDTO.getEnderecoSaidaOrigem().getIdEndereco());
			
			if (enderecoSaidaOrigem == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar um endereço com este id.");
			}
		} else {
			enderecoSaidaOrigem = enderecoService.buscarEndereco(passagemDTO.getEnderecoSaidaOrigem());
			
			if (enderecoSaidaOrigem == null) {
				enderecoSaidaOrigem = enderecoService.montarEndereco(passagemDTO.getEnderecoSaidaOrigem());
				
				enderecoSaidaOrigem = enderecoService.salvar(enderecoSaidaOrigem);
			}
		}
		
		if (passagemDTO.getEnderecoChegadaDestino().getIdEndereco() != 0) {
			enderecoChegadaDestino = enderecoService.buscarPorId(passagemDTO.getEnderecoChegadaDestino().getIdEndereco());
		
			if (enderecoChegadaDestino == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar um endereço com este id.");
			}
		} else {
			enderecoChegadaDestino = enderecoService.buscarEndereco(passagemDTO.getEnderecoChegadaDestino());
			
			if (enderecoChegadaDestino == null) {
				enderecoChegadaDestino = enderecoService.montarEndereco(passagemDTO.getEnderecoChegadaDestino());
				
				enderecoChegadaDestino = enderecoService.salvar(enderecoChegadaDestino);
			}
		}
		
		if (passagemDTO.getEnderecosParadas() != null && !passagemDTO.getEnderecosParadas().isEmpty()) {
			for (EnderecoDTO enderecoParada : passagemDTO.getEnderecosParadas()) {
				Endereco endereco = new Endereco();
				
				if (enderecoParada.getIdEndereco() != 0) {
					endereco = enderecoService.buscarPorId(enderecoParada.getIdEndereco());
					
					if (endereco == null) {
			    		throw new EntityNotFoundException("Não foi possível localizar um endereço com este id.");
					}
				} else {
					endereco = enderecoService.buscarEndereco(enderecoParada);
					
					if (endereco == null) {
						endereco = enderecoService.montarEndereco(enderecoParada);
						
						endereco = enderecoService.salvar(endereco);
					}
				}
				
				enderecosParadas.add(endereco);
			}
		}
		
		if (passagemDTO.getEmpresaDTO().getIdEmpresa() != 0) {
			empresa = empresaService.buscarPorId(passagemDTO.getEmpresaDTO().getIdEmpresa());
			
			if (empresa == null) {
	    		throw new EntityNotFoundException("Não foi possível localizar uma empresa com este id.");
			}
		} else {
			empresa = empresaService.montarEmpresa(passagemDTO.getEmpresaDTO());
			
			empresa = empresaService.salvar(empresa);
		}
		
		Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(passagemDTO.getDataPartida());
        Date dataFim = null;

        if ((passagemDTO.getDataChegada() != null) && (!passagemDTO.getDataChegada().equals(""))) {
            dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(passagemDTO.getDataChegada());
        }
		
		Passagem passagem = new Passagem();
		
		passagem.setDataPartida(dataInicio);
		passagem.setDataChegada(dataFim);
		passagem.setEnderecoSaidaOrigem(enderecoSaidaOrigem);
		passagem.setEnderecoChegadaDestino(enderecoChegadaDestino);
		passagem.getEnderecosParadas().addAll(enderecosParadas);
		passagem.setEmpresa(empresa);
		
		return passagem;
	}
}
