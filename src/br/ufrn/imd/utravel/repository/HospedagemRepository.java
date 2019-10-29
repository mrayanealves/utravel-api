package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Hospedagem;

@Stateless
public class HospedagemRepository extends AbstractRepository<Hospedagem>{

	@Override
	@SuppressWarnings("unchecked")
	public List<Hospedagem> buscarTodos() {
		return (List<Hospedagem>) em.createQuery("select h from Hospedagem h").getResultList();
	}

	@Override
	public Hospedagem buscarPorId(long id) {
		try {
			return (Hospedagem) em.find(Hospedagem.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Hospedagem salvar(Hospedagem entity) {
		if (entity.getId() > 0) {
			em.persist(entity);
			
			return entity;
		} else {
			em.merge(entity);
			
			return entity;
		}
	}

	@Override
	public void remover(Hospedagem entity) {
		em.remove(entity);
	}
	
}
