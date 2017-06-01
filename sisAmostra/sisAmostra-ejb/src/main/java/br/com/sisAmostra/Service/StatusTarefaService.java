package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.StatusTarefa;

@Stateless
public class StatusTarefaService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public StatusTarefa inserirOuAtualizar(StatusTarefa statusTarefa) {
		
		entityManager.merge(statusTarefa);
		
		return statusTarefa;
	}

	public StatusTarefa buscar(Integer id) {
		return entityManager.find(StatusTarefa.class, id);
	}
	
	public List<StatusTarefa> findAll(){		
		TypedQuery<StatusTarefa> query = entityManager.createNamedQuery("StatusTarefa.findAll", StatusTarefa.class);
		
        return query.getResultList();
 
	}

	public StatusTarefa atualizar(StatusTarefa statusTarefa) {
		return entityManager.merge(statusTarefa);
	}

	public void excluir(StatusTarefa statusTarefa) {
		statusTarefa = entityManager.merge(statusTarefa);
		entityManager.remove(statusTarefa);
	}
}	



