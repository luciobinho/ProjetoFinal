package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.ClasseAmostra;
import br.com.sisAmostra.Service.ClasseAmostraService;

@ManagedBean(name = "classeAmostraMB")
@ViewScoped
public class ClasseAmostraMB {

	private ClasseAmostra classeAmostra = new ClasseAmostra();
	private List<ClasseAmostra> listClasseAmostras;

	@Inject
	ClasseAmostraService classeAmostraService;

	public boolean cadastrar;
	public boolean editar;

	@PostConstruct
	public void init() {
		carregarListas();
		
	}

	private void carregarListas() {
		listClasseAmostras = classeAmostraService.findAll();
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
	}

	public void salvar() {
		
		try {
			classeAmostraService.inserirOuAtualizar(classeAmostra);
			
			classeAmostra = new ClasseAmostra();
			carregarListas();
			limpar();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Amostra cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}

	public void deletar() {
		
		try {
			classeAmostraService.excluir(classeAmostra);
			
			carregarListas();
			
			classeAmostra = new ClasseAmostra();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Amostra deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listClasseAmostras = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listClasseAmostras = new ArrayList<>();
	}

	public void cancelar(){
		limpar();
		carregarListas();
	}
	
	public ClasseAmostra getClasseAmostra() {
		return classeAmostra;
	}

	public void setClasseAmostra(ClasseAmostra classeAmostra) {
		this.classeAmostra = classeAmostra;
	}

	public List<ClasseAmostra> getListClasseAmostras() {
		return listClasseAmostras;
	}

	public void setListClasseAmostras(List<ClasseAmostra> listClasseAmostras) {
		this.listClasseAmostras = listClasseAmostras;
	}

	public Boolean getCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

}
