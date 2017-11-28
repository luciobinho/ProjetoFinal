package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.TipoEnsaio;
import br.com.sisAmostra.Service.TipoEnsaioService;

@ManagedBean(name = "tipoEnsaioMB")
@ViewScoped
public class TipoEnsaioMB {

	private TipoEnsaio tipoEnsaio = new TipoEnsaio();
	private List<TipoEnsaio> listTipoEnsaios;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	TipoEnsaioService tipoEnsaioService;

	@PostConstruct
	public void init() {
		carregarListas();

	}

	private void carregarListas() {
		listTipoEnsaios = tipoEnsaioService.findAll();
	}

	public void salvar() {
		
		try {
			tipoEnsaioService.inserirOuAtualizar(tipoEnsaio);
			
			tipoEnsaio = new TipoEnsaio();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Ensaio cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
	}

	public void deletar() {
		try {
			tipoEnsaioService.excluir(tipoEnsaio);
			
			carregarListas();
			
			tipoEnsaio = new TipoEnsaio();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Ensaio deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listTipoEnsaios = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listTipoEnsaios = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public TipoEnsaio getTipoEnsaio() {
		return tipoEnsaio;
	}

	public void setTipoEnsaio(TipoEnsaio tipoEnsaio) {
		this.tipoEnsaio = tipoEnsaio;
	}

	public List<TipoEnsaio> getListTipoEnsaios() {
		return listTipoEnsaios;
	}

	public void setListTipoEnsaios(List<TipoEnsaio> listTipoEnsaios) {
		this.listTipoEnsaios = listTipoEnsaios;
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
