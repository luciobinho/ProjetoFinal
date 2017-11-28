package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Resultado;

@Stateless
public class ResultadoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Resultado inserirOuAtualizar(Resultado resultado) {
		
		entityManager.merge(resultado);
		
		return resultado;
	}

	public Resultado buscar(Long id) {
		return entityManager.find(Resultado.class, id);
	}
	
	public List<Resultado> findAll(){		
		TypedQuery<Resultado> query = entityManager.createNamedQuery("Resultado.findAll", Resultado.class);
		
        return query.getResultList();
 
	}
	
	public Resultado atualizar(Resultado resultado) {
		return entityManager.merge(resultado);
	}

	public void excluir(Resultado resultado) {
		resultado = entityManager.merge(resultado);
		entityManager.remove(resultado);
	}
}	



