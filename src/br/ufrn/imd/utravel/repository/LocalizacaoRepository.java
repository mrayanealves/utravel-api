package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Localizacao;

@Stateless
public class LocalizacaoRepository extends AbstractRepository<Localizacao> {

    @Override
    @SuppressWarnings("unchecked")
    public List<Localizacao> buscarTodos() {
        return (List<Localizacao>) em.createQuery("select l from Localizacao l").getResultList();
    }

    @Override
    public Localizacao buscarPorId(long id) {
        try {
            return (Localizacao) em.find(Localizacao.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Localizacao salvar(Localizacao entity) {
        if (entity.getId() > 0) {
            em.merge(entity);

            return entity;
        } else {
            em.persist(entity);

            return entity;
        }
    }

    @Override
    public void remover(Localizacao entity) {
        em.remove(entity);
    }
}
