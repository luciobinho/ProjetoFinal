package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Empresa;
import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.EmpresaService;
import br.com.sisAmostra.Service.UsuarioService;

@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioMB {

	private Usuario usuario = new Usuario();
	private List<Usuario> listUsuarios;
	private List<SelectItem> listaEmpresa;

	public boolean cadastrar;
	public boolean editar;
	
	private Long idEmpresa;
	
	@Inject
	UsuarioService usuarioService;
	
	@Inject
	EmpresaService empresaService;
	
	public ArrayList<SelectItem> listaTipos;

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
		popularComboTipo();
		popularComboEmpresa();
	}

	public List<SelectItem> popularComboTipo() {

		listaTipos = new ArrayList<SelectItem>();
		
		SelectItem select = new SelectItem();
		select.setValue("Interno");
		select.setLabel("Interno");
		listaTipos.add(select);
		
		select = new SelectItem();
		select.setValue("Externo");
		select.setLabel("Externo");
		listaTipos.add(select);
		
		return listaTipos;
	}
	
	public List<SelectItem> popularComboEmpresa() {

		listaEmpresa = new ArrayList<SelectItem>();
		List<Empresa> saida;
		try {
			saida = empresaService.findAll();

			for (Empresa empresa : saida) {
				SelectItem select = new SelectItem();
				select.setValue(empresa.getIdEmpresa());
				select.setLabel(empresa.getDescricao());
				listaEmpresa.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaEmpresa;
	}
	
	public void salvar() {
		
		try {
			Empresa empresa = empresaService.buscar(idEmpresa);
			usuario.setEmpresa(empresa);
			usuarioService.inserirOuAtualizar(usuario);
			
			usuario = new Usuario();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		usuario = new Usuario();
		idEmpresa = null;
	}

	public void deletar() {
		try {
			usuarioService.excluir(usuario);
			
			carregarListas();
			
			usuario = new Usuario();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listUsuarios = new ArrayList<>();
	}
	
	public void alterar(Usuario usuario){
		editar = Boolean.TRUE;
		listUsuarios = new ArrayList<>();
		if(usuario.getEmpresa() != null){
			idEmpresa = usuario.getEmpresa().getIdEmpresa();
		}	
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

	public ArrayList<SelectItem> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(ArrayList<SelectItem> listaTipos) {
		this.listaTipos = listaTipos;
	}

	public List<SelectItem> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(List<SelectItem> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

}
