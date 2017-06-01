package br.com.sisAmostra.Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sisAmostra.Entity.Usuario;

@Stateless
public class UsuarioService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874679541493339991L;
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;

	public Usuario inserirOuAtualizar(Usuario usuario) {
		
		entityManager.merge(usuario);
		
		return usuario;
	}

	public Usuario buscar(Integer id) {
		return entityManager.find(Usuario.class, id);
	}
	
	public List<Usuario> findAll(){		
		TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findAll", Usuario.class);
		
        return query.getResultList();
 
	}

	public Usuario atualizar(Usuario usuario) {
		return entityManager.merge(usuario);
	}

	public void excluir(Usuario usuario) {
		usuario = entityManager.merge(usuario);
		entityManager.remove(usuario);
	}
}	



