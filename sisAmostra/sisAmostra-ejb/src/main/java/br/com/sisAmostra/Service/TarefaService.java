package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Tarefa;

@Stateless
public class TarefaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;

	@PersistenceContext(name = "sisAmostra")
	protected EntityManager entityManager;

	public Tarefa inserirOuAtualizar(Tarefa tarefa) {

		entityManager.merge(tarefa);

		return tarefa;
	}

	public Tarefa buscar(Integer id) {
		return entityManager.find(Tarefa.class, id);
	}

	public List<Tarefa> findAll() {
		TypedQuery<Tarefa> query = entityManager.createNamedQuery("Tarefa.findAll", Tarefa.class);

		return query.getResultList();

	}

	public Tarefa atualizar(Tarefa tarefa) {
		return entityManager.merge(tarefa);
	}

	public void excluir(Tarefa tarefa) {
		tarefa = entityManager.merge(tarefa);
		entityManager.remove(tarefa);
	}

	public List<Tarefa> recuperarTarefasFuncionario(Integer idUsuario) {
		TypedQuery<Tarefa> query = entityManager.createNamedQuery("Tarefa.findAllFunc", Tarefa.class);
		
		query.setParameter("idUsuario", idUsuario);
		
		return query.getResultList();
	}
}
