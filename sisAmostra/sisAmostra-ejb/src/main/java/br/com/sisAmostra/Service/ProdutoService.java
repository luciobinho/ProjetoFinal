package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Produto;

@Stateless
public class ProdutoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Produto inserirOuAtualizar(Produto Produto) {
		
		entityManager.merge(Produto);
		
		return Produto;
	}

	public Produto buscar(Integer id) {
		return entityManager.find(Produto.class, id);
	}
	
	public List<Produto> findAll(){		
		TypedQuery<Produto> query = entityManager.createNamedQuery("Produto.findAll", Produto.class);
		
        return query.getResultList();
 
	}

	public Produto atualizar(Produto Produto) {
		return entityManager.merge(Produto);
	}

	public void excluir(Produto Produto) {
		Produto = entityManager.merge(Produto);
		entityManager.remove(Produto);
	}


}
