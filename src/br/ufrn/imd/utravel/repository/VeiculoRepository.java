package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.Veiculo;

@Stateless
public class VeiculoRepository extends AbstractRepository<Veiculo> {

    @Override
    @SuppressWarnings("unchecked")
    public List<Veiculo> buscarTodos() {
        return (List<Veiculo>) em.createQuery("select v from Veiculo v").getResultList();
    }

    @Override
    public Veiculo buscarPorId(long id) {
        try {
            return (Veiculo) em.find(Veiculo.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Veiculo salvar(Veiculo entity) {
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Veiculo entity) {
        em.remove(entity);
    }
}
