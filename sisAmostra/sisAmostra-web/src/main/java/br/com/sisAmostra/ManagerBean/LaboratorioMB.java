package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Laboratorio;
import br.com.sisAmostra.Service.LaboratorioService;

@ManagedBean(name = "laboratorioMB")
@ViewScoped
public class LaboratorioMB {

	private Laboratorio laboratorio = new Laboratorio();
	private List<Laboratorio> listLaboratorios;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	LaboratorioService laboratorioService;

	@PostConstruct
	public void init() {
		carregarListas();

		if (listLaboratorios.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe Laboratório cadastrado!", ""));
		}

	}

	private void carregarListas() {
		listLaboratorios = laboratorioService.findAll();
	}

	public void salvar() {
		
		try {
			laboratorio.setDtUltAlteracao(Calendar.getInstance());
			laboratorio.setUsuUltAlteracao("123456");
			laboratorioService.inserirOuAtualizar(laboratorio);
			
			laboratorio = new Laboratorio();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Laboratório cadastrado/alterado com sucesso!", ""));
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
			laboratorioService.excluir(laboratorio);
			
			carregarListas();
			
			laboratorio = new Laboratorio();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Laboratório deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listLaboratorios = new ArrayList<>();
	}
	
	public void alterar(Laboratorio laboratorio){
		editar = Boolean.TRUE;
		listLaboratorios = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public List<Laboratorio> getListLaboratorios() {
		return listLaboratorios;
	}

	public void setListLaboratorios(List<Laboratorio> listLaboratorios) {
		this.listLaboratorios = listLaboratorios;
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
