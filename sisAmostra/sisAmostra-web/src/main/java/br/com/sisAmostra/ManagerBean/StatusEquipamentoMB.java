package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.StatusEquipamento;
import br.com.sisAmostra.Service.StatusEquipamentoService;

@ManagedBean(name = "statusEquipamentoMB")
@ViewScoped
public class StatusEquipamentoMB {

	private StatusEquipamento statusEquipamento = new StatusEquipamento();
	private List<StatusEquipamento> listStatusEquipamentos;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	StatusEquipamentoService statusEquipamentoService;

	@PostConstruct
	public void init() {
		carregarListas();

		if (listStatusEquipamentos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe Status Equipamento cadastrado!", ""));
		}

	}

	private void carregarListas() {
		listStatusEquipamentos = statusEquipamentoService.findAll();
	}

	public void salvar() {
		
		try {
			statusEquipamento.setDtUltAlteracao(Calendar.getInstance());
			statusEquipamento.setUsuUltAlteracao("123456");
			statusEquipamentoService.inserirOuAtualizar(statusEquipamento);
			
			statusEquipamento = new StatusEquipamento();
			limpar();
			carregarListas();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Equipamento cadastrado/alterado com sucesso!", ""));
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
			statusEquipamentoService.excluir(statusEquipamento);
			
			carregarListas();
			
			statusEquipamento = new StatusEquipamento();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Equipamento deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao excluir o Equipamento - " + e.getMessage(), ""));
			throw new EJBException(e);
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listStatusEquipamentos = new ArrayList<>();
	}
	
	public void alterar(StatusEquipamento Equipamento){
		editar = Boolean.TRUE;
		listStatusEquipamentos = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public StatusEquipamento getStatusEquipamento() {
		return statusEquipamento;
	}

	public void setStatusEquipamento(StatusEquipamento statusEquipamento) {
		this.statusEquipamento = statusEquipamento;
	}

	public List<StatusEquipamento> getListStatusEquipamentos() {
		return listStatusEquipamentos;
	}

	public void setListStatusEquipamentos(List<StatusEquipamento> listStatusEquipamentos) {
		this.listStatusEquipamentos = listStatusEquipamentos;
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
