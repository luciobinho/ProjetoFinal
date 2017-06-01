package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.TipoEnsaio;

@Stateless
public class TipoEnsaioService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6472682353477163109L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public TipoEnsaio inserirOuAtualizar(TipoEnsaio tipoEnsaio) {
		
		entityManager.merge(tipoEnsaio);
		
		return tipoEnsaio;
	}

	public TipoEnsaio buscar(Integer id) {
		return entityManager.find(TipoEnsaio.class, id);
	}
	
	public List<TipoEnsaio> findAll(){		
		TypedQuery<TipoEnsaio> query = entityManager.createNamedQuery("TipoEnsaio.findAll", TipoEnsaio.class);
		
        return query.getResultList();
 
	}

	public TipoEnsaio atualizar(TipoEnsaio tipoEnsaio) {
		return entityManager.merge(tipoEnsaio);
	}

	public void excluir(TipoEnsaio tipoEnsaio) {
		tipoEnsaio = entityManager.merge(tipoEnsaio);
		entityManager.remove(tipoEnsaio);
	}


}
