package br.com.sisAmostra.ManagerBean;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.ClasseAmostra;
import br.com.sisAmostra.Service.ClasseAmostraService;

@ManagedBean(name = "classeAmostraMB")
@RequestScoped
public class ClasseAmostraMB {

	private ClasseAmostra classeAmostra = new ClasseAmostra();
	private List<ClasseAmostra> listClasseAmostras;

	@Inject
	ClasseAmostraService classeAmostraService;

	FacesContext facesContext;
	
	public boolean cadastrar;
	public boolean editar;

	@PostConstruct
	public void init() {
		listClasseAmostras = classeAmostraService.findAll();
		
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		
		facesContext = FacesContext.getCurrentInstance();

		if (listClasseAmostras.get(0) == null) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe Status Amostra cadastrado!", ""));
		}
	}

	public void salvar() {
		
		try {
			classeAmostra.setDtUltAlteracao(Calendar.getInstance());
			classeAmostra.setUsuUltAlteracao("123456");
			classeAmostraService.inserirOuAtualizar(classeAmostra);
			
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Amostra cadastrado/alterado com sucesso!", ""));
			classeAmostra = new ClasseAmostra();
			
			init();
			cadastrar = Boolean.FALSE;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(ClasseAmostra classeAmostra) {
		
		try {
			classeAmostraService.excluir(classeAmostra);
			
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Amostra deletada com sucesso!", ""));
			
			init();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
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
