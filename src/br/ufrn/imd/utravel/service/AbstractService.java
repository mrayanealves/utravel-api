package br.ufrn.imd.utravel.service;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.ufrn.imd.utravel.model.AbstractModel;
import br.ufrn.imd.utravel.repository.AbstractRepository;

public abstract class AbstractService<T extends AbstractModel> {
    protected abstract AbstractRepository<T> repository();

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<T> buscarTodos() {
        return repository().buscarTodos();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T buscarPorId(long id) {
        return repository().buscarPorId(id);
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
