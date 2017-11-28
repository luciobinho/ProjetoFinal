package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Norma;

@Stateless
public class NormaService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Norma inserirOuAtualizar(Norma norma) {
		
		entityManager.merge(norma);
		
		return norma;
	}

	public Norma buscar(Long id) {
		return entityManager.find(Norma.class, id);
	}
	
	public List<Norma> findAll(){		
		TypedQuery<Norma> query = entityManager.createNamedQuery("Norma.findAll", Norma.class);
		
        return query.getResultList();
 
	}
	
	public Long sequence(){		
		TypedQuery<Long> query = entityManager.createNamedQuery("Norma.sequence", Long.class);
		
        return query.getSingleResult();
	}

	public Norma atualizar(Norma norma) {
		return entityManager.merge(norma);
	}

	public void excluir(Norma norma) {
		norma = entityManager.merge(norma);
		entityManager.remove(norma);
	}
}	



