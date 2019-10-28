package br.ufrn.imd.utravel.service;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.ufrn.imd.utravel.model.AbstractModel;
import br.ufrn.imd.utravel.repository.AbstractRepository;

public abstract class AbstractService<T extends AbstractModel> {
	protected abstract AbstractRepository<T> repositorio();
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<T> buscarTodos(){
		return repositorio().buscarTodos();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T buscarPorId(long id) {
		return repositorio().buscarPorId(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T salvar(T entity) {
		return repositorio().salvar(entity);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String remover(T entity) {
		repositorio().remover(entity);
		
		return "Removido com sucesso";
	}
}
