package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Endereco;

@Stateless
public class EnderecoRepository extends AbstractRepository<Endereco>{

	@Override
	@SuppressWarnings("unchecked")
	public List<Endereco> buscarTodos() {
		return (List<Endereco>) em.createQuery("select e from Endereco e").getResultList();
	}

	@Override
	public Endereco buscarPorId(long id) {
		try {
			return (Endereco) em.find(Endereco.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Endereco salvar(Endereco entity) {
		if (entity.getId() > 0) {
			em.persist(entity);
			
			return entity;
		} else {
			em.merge(entity);
			
			return entity;
		}
	}

	@Override
	public void remover(Endereco entity) {
		em.remove(entity);
	}

}
