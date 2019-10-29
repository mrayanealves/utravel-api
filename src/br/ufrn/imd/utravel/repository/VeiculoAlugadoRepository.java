package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.VeiculoAlugado;

@Stateless
public class VeiculoAlugadoRepository extends AbstractRepository<VeiculoAlugado>{

	@Override
	@SuppressWarnings("unchecked")
	public List<VeiculoAlugado> buscarTodos() {
		return (List<VeiculoAlugado>) em.createQuery("select v from VeiculoAlugado v").getResultList();
	}

	@Override
	public VeiculoAlugado buscarPorId(long id) {
		try {
			return (VeiculoAlugado) em.find(VeiculoAlugado.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public VeiculoAlugado salvar(VeiculoAlugado entity) {
		if (entity.getId() > 0) {
			em.persist(entity);
			
			return entity;
		} else {
			em.merge(entity);
			
			return entity;
		}
	}

	@Override
	public void remover(VeiculoAlugado entity) {
		em.remove(entity);
	}
}
