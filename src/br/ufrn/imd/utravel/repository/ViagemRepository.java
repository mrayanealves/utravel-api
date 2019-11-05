package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Viagem;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
public class ViagemRepository extends AbstractRepository<Viagem> {
    @Override
    public List<Viagem> buscarTodos() {
        return (List<Viagem>) em.createQuery("select v from Viagem v").getResultList();
    }

    @Override
    public Viagem buscarPorId(long id) {
        try {
            return em.find(Viagem.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Viagem salvar(Viagem entity) {
        if (entity.getId() > 0) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Viagem entity) {
        em.remove(entity);
    }
}
