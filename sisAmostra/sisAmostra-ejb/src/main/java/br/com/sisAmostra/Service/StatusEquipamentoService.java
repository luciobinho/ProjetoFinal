package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.StatusEquipamento;

@Stateless
public class StatusEquipamentoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4822903139866037867L;
	
	@PersistenceContext(name="sisEquipamento")
	protected EntityManager entityManager;

	public StatusEquipamento inserirOuAtualizar(StatusEquipamento statusEquipamento) {
		
		entityManager.merge(statusEquipamento);
		
		return statusEquipamento;
	}

	public StatusEquipamento buscar(Integer id) {
		return entityManager.find(StatusEquipamento.class, id);
	}
	
	public List<StatusEquipamento> findAll(){		
		TypedQuery<StatusEquipamento> query = entityManager.createNamedQuery("StatusEquipamento.findAll", StatusEquipamento.class);
		
        return query.getResultList();
 
	}

	public StatusEquipamento atualizar(StatusEquipamento statusEquipamento) {
		return entityManager.merge(statusEquipamento);
	}

	public void excluir(StatusEquipamento statusEquipamento) {
		statusEquipamento = entityManager.merge(statusEquipamento);
		entityManager.remove(statusEquipamento);
	}


}
