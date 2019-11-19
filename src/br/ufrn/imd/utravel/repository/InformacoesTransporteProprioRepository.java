package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ufrn.imd.utravel.model.InformacoesTransporteProprio;

@Stateless
public class InformacoesTransporteProprioRepository extends AbstractRepository<InformacoesTransporteProprio>{
	@Override
    @SuppressWarnings("unchecked")
    public List<InformacoesTransporteProprio> buscarTodos() {
        return (List<InformacoesTransporteProprio>) em.createQuery("select i from InformacoesTransporteProprio i").getResultList();
    }

    @Override
    public InformacoesTransporteProprio buscarPorId(long id) {
        try {
            return (InformacoesTransporteProprio) em.find(InformacoesTransporteProprio.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public InformacoesTransporteProprio salvar(InformacoesTransporteProprio entity) {
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(InformacoesTransporteProprio entity) {
        em.remove(entity);
    }
}
