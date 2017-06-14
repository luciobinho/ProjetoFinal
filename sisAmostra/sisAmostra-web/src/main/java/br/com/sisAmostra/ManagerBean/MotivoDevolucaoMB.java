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

import br.com.sisAmostra.Entity.MotivoDevolucao;
import br.com.sisAmostra.Service.MotivoDevolucaoService;

@ManagedBean(name = "motivoDevolucaoMB")
@ViewScoped
public class MotivoDevolucaoMB {

	private MotivoDevolucao motivoDevolucao = new MotivoDevolucao();
	private List<MotivoDevolucao> listMotivoDevolucoes;
	
	private List<SelectItem> listaStatus;
	private List<SelectItem> listaFuncionario;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	MotivoDevolucaoService motivoDevolucaoService;
	
	@PostConstruct
	public void init() {
		carregarListas();
	}

	private void carregarListas() {
		listMotivoDevolucoes = motivoDevolucaoService.findAll();
	}

	public void salvar() {
		
		try {
			
			motivoDevolucaoService.inserirOuAtualizar(motivoDevolucao);
			
			motivoDevolucao = new MotivoDevolucao();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Motivo Devolucao cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		motivoDevolucao = new MotivoDevolucao();
	}

	public void deletar() {
		try {
			motivoDevolucaoService.excluir(motivoDevolucao);
			
			carregarListas();
			
			motivoDevolucao = new MotivoDevolucao();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Motivo Devolucao deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listMotivoDevolucoes = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listMotivoDevolucoes = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public MotivoDevolucao getMotivoDevolucao() {
		return motivoDevolucao;
	}

	public void setMotivoDevolucao(MotivoDevolucao tarefa) {
		this.motivoDevolucao = tarefa;
	}

	public List<MotivoDevolucao> getListMotivoDevolucoes() {
		return listMotivoDevolucoes;
	}

	public void setListMotivoDevolucoes(List<MotivoDevolucao> listMotivoDevolucoes) {
		this.listMotivoDevolucoes = listMotivoDevolucoes;
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

	public List<SelectItem> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<SelectItem> listaStatus) {
		this.listaStatus = listaStatus;
	}

	public List<SelectItem> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<SelectItem> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

}
