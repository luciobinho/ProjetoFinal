package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.DevolucaoAmostra;

@Stateless
public class DevolucaoAmostraService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public DevolucaoAmostra inserirOuAtualizar(DevolucaoAmostra devolucaoAmostra) {
		
		entityManager.merge(devolucaoAmostra);
		
		return devolucaoAmostra;
	}

	public DevolucaoAmostra buscar(Integer id) {
		return entityManager.find(DevolucaoAmostra.class, id);
	}
	
	public List<DevolucaoAmostra> findAll(){		
		TypedQuery<DevolucaoAmostra> query = entityManager.createNamedQuery("DevolucaoAmostra.findAll", DevolucaoAmostra.class);
		
        return query.getResultList();
 
	}

	public DevolucaoAmostra atualizar(DevolucaoAmostra devolucaoAmostra) {
		return entityManager.merge(devolucaoAmostra);
	}

	public void excluir(DevolucaoAmostra devolucaoAmostra) {
		devolucaoAmostra = entityManager.merge(devolucaoAmostra);
		entityManager.remove(devolucaoAmostra);
	}
}	



