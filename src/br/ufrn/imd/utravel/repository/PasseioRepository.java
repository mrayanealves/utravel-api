package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Passeio;

@Stateless
public class PasseioRepository extends AbstractRepository<Passeio>{

	@Override
	@SuppressWarnings("unchecked")
	public List<Passeio> buscarTodos() {
		return (List<Passeio>) em.createQuery("select p from Passeio p").getResultList();
	}

	@Override
	public Passeio buscarPorId(long id) {
		try {
			return (Passeio) em.find(Passeio.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Passeio salvar(Passeio entity) {
		if (entity.getId() > 0) {
			em.persist(entity);
			
			return entity;
		} else {
			em.merge(entity);
			
			return entity;
		}
	}

	@Override
	public void remover(Passeio entity) {
		em.remove(entity);
	}

}
