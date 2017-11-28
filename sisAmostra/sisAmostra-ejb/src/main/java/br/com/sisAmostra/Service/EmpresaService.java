package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Empresa;

@Stateless
public class EmpresaService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Empresa inserirOuAtualizar(Empresa empresa) {
		
		entityManager.merge(empresa);
		
		return empresa;
	}

	public Empresa buscar(Long id) {
		return entityManager.find(Empresa.class, id);
	}
	
	public List<Empresa> findAll(){		
		TypedQuery<Empresa> query = entityManager.createNamedQuery("Empresa.findAll", Empresa.class);
		
        return query.getResultList();
 
	}

	public Empresa atualizar(Empresa empresa) {
		return entityManager.merge(empresa);
	}

	public void excluir(Empresa empresa) {
		empresa = entityManager.merge(empresa);
		entityManager.remove(empresa);
	}
}	



