package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.StatusAnalise;

@Stateless
public class StatusAnaliseService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public StatusAnalise inserirOuAtualizar(StatusAnalise status) {
		
		entityManager.merge(status);
		
		return status;
	}

	public StatusAnalise buscar(Long id) {
		return entityManager.find(StatusAnalise.class, id);
	}
	
	public List<StatusAnalise> findAll(){		
		TypedQuery<StatusAnalise> query = entityManager.createNamedQuery("StatusAnalise.findAll", StatusAnalise.class);
		
        return query.getResultList();
 
	}
	
	public Long sequence(){		
		TypedQuery<Long> query = entityManager.createNamedQuery("StatusAnalise.sequence", Long.class);
		
        return query.getSingleResult();
	}

	public StatusAnalise atualizar(StatusAnalise status) {
		return entityManager.merge(status);
	}

	public void excluir(StatusAnalise status) {
		status = entityManager.merge(status);
		entityManager.remove(status);
	}
}	



