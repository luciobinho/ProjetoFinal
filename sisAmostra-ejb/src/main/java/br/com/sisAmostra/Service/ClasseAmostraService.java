package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.ClasseAmostra;

@Stateless
public class ClasseAmostraService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2590902033388156275L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public ClasseAmostra inserirOuAtualizar(ClasseAmostra classeAmostra) {
		
		entityManager.merge(classeAmostra);
		
		return classeAmostra;
	}

	public ClasseAmostra buscar(Integer id) {
		return entityManager.find(ClasseAmostra.class, id);
	}
	
	public List<ClasseAmostra> findAll(){		
		TypedQuery<ClasseAmostra> query = entityManager.createNamedQuery("ClasseAmostra.findAll", ClasseAmostra.class);
		
        return query.getResultList();
 
	}

	public ClasseAmostra atualizar(ClasseAmostra classeAmostra) {
		return entityManager.merge(classeAmostra);
	}

	public void excluir(ClasseAmostra classeAmostra) {
		classeAmostra = entityManager.merge(classeAmostra);
		entityManager.remove(classeAmostra);
	}


}
