package br.ufrn.imd.utravel.service;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.model.AbstractModel;
import br.ufrn.imd.utravel.repository.AbstractRepository;

public abstract class AbstractService<T extends AbstractModel> {
    protected abstract AbstractRepository<T> repository();

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<T> buscarTodos() {
        return repository().buscarTodos();
    }

    @SuppressWarnings("null")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T buscarPorId(long id) {
    	T entity = repository().buscarPorId(id);
        
    	if (entity == null) {
    		StringBuilder stringBuilder = new StringBuilder();
    		stringBuilder.append("Não foi possível localizar um (a) ")
    					 .append(entity.getClass().getName()).append(" com este id.");
			
    		throw new EntityNotFoundException(stringBuilder.toString());
		}
    	
    	return entity;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T salvar(T entity) {
        return repository().salvar(entity);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String remover(T entity) {
        repository().remover(entity);

        return "Removido com sucesso";
    }
}
