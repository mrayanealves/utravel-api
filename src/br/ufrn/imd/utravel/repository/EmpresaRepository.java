package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Empresa;

@Stateless
public class EmpresaRepository extends AbstractRepository<Empresa> {

    @Override
    @SuppressWarnings("unchecked")
    public List<Empresa> buscarTodos() {
        return (List<Empresa>) em.createQuery("select e from Empresa e").getResultList();
    }

    @Override
    public Empresa buscarPorId(long id) {
        try {
            return (Empresa) em.find(Empresa.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Empresa salvar(Empresa entity) {
        if (entity.getId() > 0) {
            em.persist(entity);

            return entity;
        } else {
            em.merge(entity);

            return entity;
        }
    }

    @Override
    public void remover(Empresa entity) {
        em.remove(entity);
    }
}
