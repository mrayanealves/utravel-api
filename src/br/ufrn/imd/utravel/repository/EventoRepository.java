package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrn.imd.utravel.model.Evento;

@Stateless
public class EventoRepository extends AbstractRepository<Evento>{

	@Override
    @SuppressWarnings("unchecked")
    public List<Evento> buscarTodos() {
        return (List<Evento>) em.createQuery("select e from Evento e").getResultList();
    }

    @Override
    public Evento buscarPorId(long id) {
        try {
            return (Evento) em.find(Evento.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List buscarPorViagemId(long viagemId) {
	    Query query =  em.createQuery("select e from Evento e where e.viagem.id = :viagemId");
	    query.setParameter("viagemId", viagemId);
	    return query.getResultList();
    }

    @Override
    public Evento salvar(Evento entity) {
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Evento entity) {
        em.remove(entity);
    }
}
