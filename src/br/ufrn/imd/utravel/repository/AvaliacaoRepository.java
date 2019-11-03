package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Avaliacao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
public class AvaliacaoRepository extends AbstractRepository<Avaliacao> {
    @Override
    public List<Avaliacao> buscarTodos() {
        return (List<Avaliacao>) em.createQuery("select a from Avaliacao a").getResultList();
    }

    @Override
    public Avaliacao buscarPorId(long id) {
        try {
            return em.find(Avaliacao.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Avaliacao salvar(Avaliacao entity) {
        if (entity.getId() > 0) {
            em.merge(entity);

            return entity;
        } else {
            em.persist(entity);

            return entity;
        }
    }

    @Override
    public void remover(Avaliacao entity) {
        em.remove(entity);
    }
}
