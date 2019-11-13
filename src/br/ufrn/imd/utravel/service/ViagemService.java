package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.dto.ViagemDTO;
import br.ufrn.imd.utravel.exception.ParametersFailException;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.ViagemRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class ViagemService {
    @Inject
    ViagemRepository viagemRepository;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Viagem> buscarTodos(){
		return viagemRepository.buscarTodos();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Viagem buscarPorId(long id) {
		return viagemRepository.buscarPorId(id);
	}
	
	public Viagem salvar(ViagemDTO viagemDTO, Usuario usuario) throws ParseException, ParametersFailException {
		System.out.println(usuario.getId());
		Viagem viagem = new Viagem();
		
		if (viagemDTO.getTitulo() == null || viagemDTO.getTitulo().equals("")) {
			throw new ParametersFailException("Escreva um título para sua viagem.");
		} 
		else if (viagemDTO.getDataInicio() == null || viagemDTO.getDataInicio().equals("")) {
			throw new ParametersFailException("Escreva uma data de início para sua viagem.");
		}
		
		Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(viagemDTO.getDataInicio());
		Date dataFim = null;
		
		if ((viagemDTO.getDataFim() != null) && (!viagemDTO.getDataFim().equals(""))) {
			dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(viagemDTO.getDataFim());
		}
		
		viagem.setTitulo(viagemDTO.getTitulo());
		viagem.setObjetivo(viagemDTO.getObjetivo());
		viagem.setDataInicio(dataInicio);
		viagem.setDataFim(dataFim);
		viagem.getUsuarios().add(usuario);
		
		return viagemRepository.salvar(viagem);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String remover(Viagem viagem) {
		viagemRepository.remover(viagem);
		
		return "Removido com sucesso";
	}
	
//	private void validarCampos(ViagemDTO viagemDTO){
//		
//	}
}
