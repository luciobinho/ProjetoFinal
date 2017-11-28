package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.StatusAnalise;
import br.com.sisAmostra.Service.StatusAnaliseService;

@ManagedBean(name = "statusAnaliseMB")
@ViewScoped
public class StatusAnaliseMB {

	private StatusAnalise statusAnalise = new StatusAnalise();
	private List<StatusAnalise> listStatusAnalises;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	StatusAnaliseService statusAnaliseService;

	@PostConstruct
	public void init() {
		carregarListas();

	}

	private void carregarListas() {
		listStatusAnalises = statusAnaliseService.findAll();
	}

	public String salvar() {
		try {
			if(statusAnalise.getIdStatus() == null){
				statusAnalise.setIdStatus(statusAnaliseService.sequence());
			}	
			statusAnaliseService.inserirOuAtualizar(statusAnalise);
			
			statusAnalise = new StatusAnalise();
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
			statusAnaliseService.excluir(statusAnalise);
			
			carregarListas();
			
			statusAnalise = new StatusAnalise();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Amostra deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
		return null;
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listStatusAnalises = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listStatusAnalises = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public StatusAnalise getStatusAnalise() {
		return statusAnalise;
	}

	public void setStatusAnalise(StatusAnalise statusAnalise) {
		this.statusAnalise = statusAnalise;
	}

	public List<StatusAnalise> getListStatusAnalises() {
		return listStatusAnalises;
	}

	public void setListStatusAnalises(List<StatusAnalise> listStatusAnalises) {
		this.listStatusAnalises = listStatusAnalises;
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
