package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.UsuarioService;

@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioMB {

	private Usuario usuario = new Usuario();
	private List<Usuario> listUsuarios;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	UsuarioService usuarioService;

	@PostConstruct
	public void init() {
		carregarListas();

		if (listUsuarios.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe Usuario cadastrado!", ""));
		}

	}

	private void carregarListas() {
		listUsuarios = usuarioService.findAll();
	}

	public void salvar() {
		
		try {
			usuarioService.inserirOuAtualizar(usuario);
			
			usuario = new Usuario();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		usuario = new Usuario();
	}

	public void deletar() {
		try {
			usuarioService.excluir(usuario);
			
			carregarListas();
			
			usuario = new Usuario();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listUsuarios = new ArrayList<>();
	}
	
	public void alterar(Usuario usuario){
		editar = Boolean.TRUE;
		listUsuarios = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

}
