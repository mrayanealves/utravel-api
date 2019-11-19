package br.ufrn.imd.utravel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrn.imd.utravel.dto.EnderecoDTO;
import br.ufrn.imd.utravel.model.Endereco;

@Stateless
public class EnderecoRepository extends AbstractRepository<Endereco> {

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
    	if (entity.getId() == 0) {
        	em.persist(entity);
            em.flush();
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public void remover(Endereco entity) {
        em.remove(entity);
    }
    
    public Endereco buscarEndereco(EnderecoDTO enderecoDTO) {
		try {
			Query query = em.createQuery("select e from Endereco e where e.endereco = :endereco "
					+ "and e.localizacao.pais = :pais and e.localizacao.estado = :estado "
					+ "and e.localizacao.cidade = :cidade");
			query.setParameter("endereco", enderecoDTO.getEndereco());
			query.setParameter("pais", enderecoDTO.getPais());
			query.setParameter("estado", enderecoDTO.getEstado());
			query.setParameter("cidade", enderecoDTO.getCidade());
			
			return (Endereco) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
