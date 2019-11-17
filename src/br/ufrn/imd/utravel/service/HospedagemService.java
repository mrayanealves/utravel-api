package br.ufrn.imd.utravel.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.HospedagemDTO;
import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.HospedagemRepository;

@Stateless
public class HospedagemService {
    @Inject
    private HospedagemRepository repository;
    
    @Inject
    private ViagemService viagemService;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Hospedagem> buscarTodos() {
        return repository.buscarTodos();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Hospedagem buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Hospedagem salvar(HospedagemDTO hospedagemDTO, Usuario usuario) {
    	Viagem viagem = viagemService.buscarPorId(hospedagemDTO.getViagem());
    	
    	if (viagem == null) {
			throw new EntityNotFoundException("Não foi possível encontrar uma viagem com este id.");
		}
    	
    	viagemService.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	
    	
    	Hospedagem hospedagem = new Hospedagem();
    	
    	return null;
        // return repository.salvar(entity);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String remover(Hospedagem hospedagem) {
        repository.remover(hospedagem);

        return "Removido com sucesso";
    }
}
