package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Reserva;

@Stateless
public class ReservaRepository extends AbstractRepository<Reserva>{

	@Override
    @SuppressWarnings("unchecked")
    public List<Reserva> buscarTodos() {
        return (List<Reserva>) em.createQuery("select r from Reserva r").getResultList();
    }

    @Override
    public Reserva buscarPorId(long id) {
        try {
            return (Reserva) em.find(Reserva.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Reserva salvar(Reserva entity) {
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Reserva entity) {
        em.remove(entity);
    }
}
