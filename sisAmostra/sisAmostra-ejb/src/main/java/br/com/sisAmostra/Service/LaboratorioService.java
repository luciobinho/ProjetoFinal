package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Laboratorio;

@Stateless
public class LaboratorioService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Laboratorio inserirOuAtualizar(Laboratorio laboratorio) {
		
		entityManager.merge(laboratorio);
		
		return laboratorio;
	}

	public Laboratorio buscar(Long id) {
		return entityManager.find(Laboratorio.class, id);
	}
	
	public List<Laboratorio> findAll(){		
		TypedQuery<Laboratorio> query = entityManager.createNamedQuery("Laboratorio.findAll", Laboratorio.class);
		
        return query.getResultList();
 
	}

	public Laboratorio atualizar(Laboratorio laboratorio) {
		return entityManager.merge(laboratorio);
	}

	public void excluir(Laboratorio laboratorio) {
		laboratorio = entityManager.merge(laboratorio);
		entityManager.remove(laboratorio);
	}


}
