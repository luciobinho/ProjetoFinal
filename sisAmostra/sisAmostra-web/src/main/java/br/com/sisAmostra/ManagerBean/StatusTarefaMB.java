package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.StatusTarefa;
import br.com.sisAmostra.Service.StatusTarefaService;

@ManagedBean(name = "statusTarefaMB")
@ViewScoped
public class StatusTarefaMB {

	private StatusTarefa statusTarefa = new StatusTarefa();
	private List<StatusTarefa> listStatusTarefas;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	StatusTarefaService statusTarefaService;

	@PostConstruct
	public void init() {
		carregarListas();

		if (listStatusTarefas.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe StatusTarefa cadastrado!", ""));
		}

	}

	private void carregarListas() {
		listStatusTarefas = statusTarefaService.findAll();
	}

	public void salvar() {
		
		try {
			statusTarefaService.inserirOuAtualizar(statusTarefa);
			
			statusTarefa = new StatusTarefa();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "StatusTarefa cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
	}

	public void deletar() {
		try {
			statusTarefaService.excluir(statusTarefa);
			
			carregarListas();
			
			statusTarefa = new StatusTarefa();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "StatusTarefa deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listStatusTarefas = new ArrayList<>();
	}
	
	public void alterar(StatusTarefa statusTarefa){
		editar = Boolean.TRUE;
		listStatusTarefas = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
		statusTarefa = new StatusTarefa();
	}

	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	public List<StatusTarefa> getListStatusTarefas() {
		return listStatusTarefas;
	}

	public void setListStatusTarefas(List<StatusTarefa> listStatusTarefas) {
		this.listStatusTarefas = listStatusTarefas;
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
