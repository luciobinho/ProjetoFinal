package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Analise;

@Stateless
public class AnaliseService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Analise inserirOuAtualizar(Analise analise) {
		
		entityManager.merge(analise);
		
		return analise;
	}

	public Analise buscar(Integer id) {
		return entityManager.find(Analise.class, id);
	}
	
	public List<Analise> findAll(){		
		TypedQuery<Analise> query = entityManager.createNamedQuery("Analise.findAll", Analise.class);
		
        return query.getResultList();
 
	}

	public Analise atualizar(Analise analise) {
		return entityManager.merge(analise);
	}

	public void excluir(Analise analise) {
		analise = entityManager.merge(analise);
		entityManager.remove(analise);
	}
}	



