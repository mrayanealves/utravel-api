package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.utravel.model.AbstractModel;

public abstract class AbstractRepository<T extends AbstractModel> {
    @PersistenceContext
    protected EntityManager em;

    public abstract List<T> buscarTodos();

    public abstract T buscarPorId(long id);

    public abstract T salvar(T entity);

    public abstract void remover(T entity);
}
