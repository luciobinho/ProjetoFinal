package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.MotivoDevolucao;

@Stateless
public class MotivoDevolucaoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public MotivoDevolucao inserirOuAtualizar(MotivoDevolucao motivoDevolucao) {
		
		entityManager.merge(motivoDevolucao);
		
		return motivoDevolucao;
	}

	public MotivoDevolucao buscar(Integer id) {
		return entityManager.find(MotivoDevolucao.class, id);
	}
	
	public List<MotivoDevolucao> findAll(){		
		TypedQuery<MotivoDevolucao> query = entityManager.createNamedQuery("MotivoDevolucao.findAll", MotivoDevolucao.class);
		
        return query.getResultList();
 
	}

	public MotivoDevolucao atualizar(MotivoDevolucao motivoDevolucao) {
		return entityManager.merge(motivoDevolucao);
	}

	public void excluir(MotivoDevolucao motivoDevolucao) {
		motivoDevolucao = entityManager.merge(motivoDevolucao);
		entityManager.remove(motivoDevolucao);
	}
}	



