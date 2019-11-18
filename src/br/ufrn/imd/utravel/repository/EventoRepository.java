package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

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

    @Override
    public Evento salvar(Evento evento) {
        if (evento.getId() > 0) {
            em.persist(evento);

            return evento;
        } else {
            em.merge(evento);

            return evento;
        }
    }

    @Override
    public void remover(Evento evento) {
        em.remove(evento);
    }
}
