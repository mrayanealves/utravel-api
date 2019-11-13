package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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

    public Usuario buscarUsuarioPorEmailSenha(String email, String senha) {
        try {
            Query query = em.createQuery("select u from Usuario u "
                    + "where email = :email and senha = :senha");
            query.setParameter("email", email);
            query.setParameter("senha", senha);

            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        try {
            Query query = em.createQuery("select u from Usuario u "
                    + "where u.email = :email");
            query.setParameter("email", email);

            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
