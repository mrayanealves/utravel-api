package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrn.imd.utravel.dto.EnderecoDTO;
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
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Localizacao entity) {
        em.remove(entity);
    }
    
    public Localizacao buscarLocalizacao(EnderecoDTO enderecoDTO) {
    	try {
			Query query = em.createQuery("select l from Localizacao l where l.pais = :pais "
					+ "and l.estado = :estado and l.cidade = :cidade");
			query.setParameter("pais", enderecoDTO.getPais());
			query.setParameter("estado", enderecoDTO.getEstado());
			query.setParameter("cidade", enderecoDTO.getCidade());
			
			return (Localizacao) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
    }
}
