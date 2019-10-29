package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Restaurante;

@Stateless
public class RestauranteRepository extends AbstractRepository<Restaurante>{

	@Override
	@SuppressWarnings("unchecked")
	public List<Restaurante> buscarTodos() {
		return (List<Restaurante>) em.createQuery("select r from Restaurante r").getResultList();
	}

	@Override
	public Restaurante buscarPorId(long id) {
		try {
			return (Restaurante) em.find(Restaurante.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Restaurante salvar(Restaurante entity) {
		if (entity.getId() > 0) {
			em.persist(entity);
			
			return entity;
		} else {
			em.merge(entity);
			
			return entity;
		}
	}

	@Override
	public void remover(Restaurante entity) {
		em.remove(entity);
	}
}
