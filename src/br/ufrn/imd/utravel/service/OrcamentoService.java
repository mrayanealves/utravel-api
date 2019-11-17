package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.dto.OrcamentoDTO;
import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.OrcamentoRepository;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

@Stateless
public class OrcamentoService {
    @Inject
    private OrcamentoRepository orcamentoRepository;
    
    @Inject
    private ViagemService viagemService;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Orcamento> buscarTodos() {
        return orcamentoRepository.buscarTodos();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Orcamento buscarPorId(long id) {
        return orcamentoRepository.buscarPorId(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Orcamento salvar(OrcamentoDTO orcamentoDTO) {
    	Viagem viagem = viagemService.buscarPorId(orcamentoDTO.getViagem());
    	
    	if (viagem == null) {
			throw new EntityNotFoundException("Não foi possível encontrar uma viagem com este id.");
		}
    	
    	Orcamento orcamento = new Orcamento();
        orcamento.setValorEstimado(orcamentoDTO.getValorEstimado());
        orcamento.setTipoOrcamento(orcamentoDTO.getTipoOrcamento());
        orcamento.setViagem(viagem);
    	
    	return orcamentoRepository.salvar(orcamento);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String remover(Orcamento entity) {
        orcamentoRepository.remover(entity);

        return "Removido com sucesso";
    }
}
