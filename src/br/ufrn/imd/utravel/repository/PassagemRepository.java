package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Passagem;

@Stateless
public class PassagemRepository extends AbstractRepository<Passagem>{
	@Override
    @SuppressWarnings("unchecked")
    public List<Passagem> buscarTodos() {
        return (List<Passagem>) em.createQuery("select p from Passagem p").getResultList();
    }

    @Override
    public Passagem buscarPorId(long id) {
        try {
            return (Passagem) em.find(Passagem.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Passagem salvar(Passagem entity) {
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Passagem entity) {
        em.remove(entity);
    }
}
