package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Empresa;
import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.EmpresaService;
import br.com.sisAmostra.Service.UsuarioService;

@ManagedBean(name = "empresaMB")
@ViewScoped
public class EmpresaMB {

	private Empresa empresa = new Empresa();
	private List<Empresa> listEmpresas;
	
	public boolean cadastrar;
	public boolean editar;
	
	Usuario usuario = new Usuario();
	
	@Inject
	EmpresaService empresaService;
	
	@Inject
	UsuarioService usuarioService;

	@PostConstruct
	public void init() {
		carregarListas();
		
	}

	private void carregarListas() {
		listEmpresas = empresaService.findAll();
	}

	public void salvar() {
		
		try {
			
			empresaService.inserirOuAtualizar(empresa);
			
			empresa = new Empresa();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		empresa = new Empresa();
	}

	public void deletar() {
		try {
			empresaService.excluir(empresa);
			
			carregarListas();
			
			empresa = new Empresa();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolução Amostra deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listEmpresas = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listEmpresas = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getListEmpresas() {
		return listEmpresas;
	}

	public void setListEmpresas(List<Empresa> listEmpresas) {
		this.listEmpresas = listEmpresas;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


}
