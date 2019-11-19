package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Transporte;

public class TransporteRepository extends AbstractRepository<Transporte>{
	@Override
    @SuppressWarnings("unchecked")
    public List<Transporte> buscarTodos() {
        return (List<Transporte>) em.createQuery("select t from Transporte t").getResultList();
    }

    @Override
    public Transporte buscarPorId(long id) {
        try {
            return (Transporte) em.find(Transporte.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Transporte salvar(Transporte entity) {
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Transporte entity) {
        em.remove(entity);
    }
}
