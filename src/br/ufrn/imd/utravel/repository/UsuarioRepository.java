package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Usuario;

@Stateless
public class UsuarioRepository extends AbstractRepository<Usuario> {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() {
		return (List<Usuario>) em.createQuery("select u from Usuario u").getResultList();
	}
	
	@Override
	public Usuario buscarPorId(long id) {
		try {
			return (Usuario) em.find(Usuario.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Usuario salvar(Usuario entity) {
		if (entity.getId() > 0) {
			em.persist(entity);
			
			return entity;
		} else {
			em.merge(entity);
			
			return entity;
		}
	}
	
	@Override
	public void remover(Usuario entity) {
		em.remove(entity);
	}
}
