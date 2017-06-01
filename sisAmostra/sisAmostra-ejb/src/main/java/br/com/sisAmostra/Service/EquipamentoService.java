package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Equipamento;

@Stateless
public class EquipamentoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7306462415091093833L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Equipamento inserirOuAtualizar(Equipamento equipamento) {
		
		entityManager.merge(equipamento);
		
		return equipamento;
	}

	public Equipamento buscar(Integer id) {
		return entityManager.find(Equipamento.class, id);
	}
	
	public List<Equipamento> findAll(){		
		TypedQuery<Equipamento> query = entityManager.createNamedQuery("Equipamento.findAll", Equipamento.class);
		
        return query.getResultList();
 
	}

	public Equipamento atualizar(Equipamento equipamento) {
		return entityManager.merge(equipamento);
	}

	public void excluir(Equipamento equipamento) {
		equipamento = entityManager.merge(equipamento);
		entityManager.remove(equipamento);
	}


}
