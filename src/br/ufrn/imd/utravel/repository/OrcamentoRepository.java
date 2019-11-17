package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Orcamento;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
public class OrcamentoRepository extends AbstractRepository<Orcamento> {
    @Override
    public List<Orcamento> buscarTodos() {
        return (List<Orcamento>) em.createQuery("select e from Orcamento e").getResultList();
    }

    @Override
    public Orcamento buscarPorId(long id) {
        try {
            return em.find(Orcamento.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Orcamento salvar(Orcamento entity) {
        if (entity.getId() > 0) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
        return entity;
    }

    @Override
    public void remover(Orcamento entity) {
        em.remove(entity);
    }
}
