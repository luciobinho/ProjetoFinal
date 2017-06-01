package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.StatusAmostra;

@Stateless
public class StatusAmostraService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4822903139866037867L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public StatusAmostra inserirOuAtualizar(StatusAmostra statusAmostra) {
		
		entityManager.merge(statusAmostra);
		
		return statusAmostra;
	}

	public StatusAmostra buscar(Integer id) {
		return entityManager.find(StatusAmostra.class, id);
	}
	
	public List<StatusAmostra> findAll(){		
		TypedQuery<StatusAmostra> query = entityManager.createNamedQuery("StatusAmostra.findAll", StatusAmostra.class);
		
        return query.getResultList();
 
	}

	public StatusAmostra atualizar(StatusAmostra statusAmostra) {
		return entityManager.merge(statusAmostra);
	}

	public void excluir(StatusAmostra statusAmostra) {
		statusAmostra = entityManager.merge(statusAmostra);
		entityManager.remove(statusAmostra);
	}


}
