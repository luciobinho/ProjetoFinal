package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Amostra;

@Stateless
public class AmostraService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Amostra inserirOuAtualizar(Amostra amostra) {
		
		entityManager.merge(amostra);
		
		return amostra;
	}

	public Amostra buscar(Integer id) {
		return entityManager.find(Amostra.class, id);
	}
	
	public List<Amostra> findAll(){		
		TypedQuery<Amostra> query = entityManager.createNamedQuery("Amostra.findAll", Amostra.class);
		
        return query.getResultList();
 
	}

	public Amostra atualizar(Amostra amostra) {
		return entityManager.merge(amostra);
	}

	public void excluir(Amostra amostra) {
		amostra = entityManager.merge(amostra);
		entityManager.remove(amostra);
	}
}	



