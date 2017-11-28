package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Especificacao;

@Stateless
public class EspecificacaoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Especificacao inserirOuAtualizar(Especificacao especificacao) {
		
		entityManager.merge(especificacao);
		
		return especificacao;
	}

	public Especificacao buscar(Long id) {
		return entityManager.find(Especificacao.class, id);
	}
	
	public List<Especificacao> findAll(){		
		TypedQuery<Especificacao> query = entityManager.createNamedQuery("Especificacao.findAll", Especificacao.class);
		
        return query.getResultList();
 
	}
	
	public Long sequence(){		
		TypedQuery<Long> query = entityManager.createNamedQuery("Especificacao.sequence", Long.class);
		
        return query.getSingleResult();
	}

	public Especificacao atualizar(Especificacao especificacao) {
		return entityManager.merge(especificacao);
	}

	public void excluir(Especificacao especificacao) {
		especificacao = entityManager.merge(especificacao);
		entityManager.remove(especificacao);
	}
}	



