package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Caracteristica;

@Stateless
public class CaracteristicaService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Caracteristica inserirOuAtualizar(Caracteristica caracteristica) {
		
		entityManager.merge(caracteristica);
		
		return caracteristica;
	}

	public Caracteristica buscar(Long id) {
		return entityManager.find(Caracteristica.class, id);
	}
	
	public List<Caracteristica> findAll(){		
		TypedQuery<Caracteristica> query = entityManager.createNamedQuery("Caracteristica.findAll", Caracteristica.class);
		
        return query.getResultList();
 
	}
	
	public Long sequence(){		
		TypedQuery<Long> query = entityManager.createNamedQuery("Caracteristica.sequence", Long.class);
		
        return query.getSingleResult();
	}

	public Caracteristica atualizar(Caracteristica caracteristica) {
		return entityManager.merge(caracteristica);
	}

	public void excluir(Caracteristica caracteristica) {
		caracteristica = entityManager.merge(caracteristica);
		entityManager.remove(caracteristica);
	}
}	



