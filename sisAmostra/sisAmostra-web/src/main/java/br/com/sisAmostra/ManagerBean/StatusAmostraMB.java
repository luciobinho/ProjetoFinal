package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.StatusAmostra;
import br.com.sisAmostra.Service.StatusAmostraService;

@ManagedBean(name = "statusAmostraMB")
@ViewScoped
public class StatusAmostraMB {

	private StatusAmostra statusAmostra = new StatusAmostra();
	private List<StatusAmostra> listStatusAmostras;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	StatusAmostraService statusAmostraService;

	@PostConstruct
	public void init() {
		carregarListas();

	}

	private void carregarListas() {
		listStatusAmostras = statusAmostraService.findAll();
	}

	public String salvar() {
		try {
			if(statusAmostra.getIdStatus() == null){
				statusAmostra.setIdStatus(statusAmostraService.sequence());
			}	
			statusAmostraService.inserirOuAtualizar(statusAmostra);
			
			statusAmostra = new StatusAmostra();
			limpar();
			carregarListas();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Amostra cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
		return null;
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
	}

	public String deletar() {
		try {
			statusAmostraService.excluir(statusAmostra);
			
			carregarListas();
			
			statusAmostra = new StatusAmostra();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Amostra deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
		return null;
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listStatusAmostras = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listStatusAmostras = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public StatusAmostra getStatusAmostra() {
		return statusAmostra;
	}

	public void setStatusAmostra(StatusAmostra statusAmostra) {
		this.statusAmostra = statusAmostra;
	}

	public List<StatusAmostra> getListStatusAmostras() {
		return listStatusAmostras;
	}

	public void setListStatusAmostras(List<StatusAmostra> listStatusAmostras) {
		this.listStatusAmostras = listStatusAmostras;
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
