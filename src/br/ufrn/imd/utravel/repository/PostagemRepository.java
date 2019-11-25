package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Postagem;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
public class PostagemRepository extends AbstractRepository<Postagem> {

    @Override
    @SuppressWarnings("unchecked")
    public List<Postagem> buscarTodos() {
        return (List<Postagem>) em.createQuery("select l from Postagem l").getResultList();
    }

    @Override
    public Postagem buscarPorId(long id) {
        try {
            return em.find(Postagem.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Postagem salvar(Postagem entity) {
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Postagem entity) {
        em.remove(entity);
    }
}
