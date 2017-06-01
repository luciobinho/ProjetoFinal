package br.com.sisAmostra.ManagerBean;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.StatusAmostra;
import br.com.sisAmostra.Service.StatusAmostraService;

@ManagedBean(name = "statusAmostraMB")
@RequestScoped
public class StatusAmostraMB {

	private StatusAmostra statusAmostra = new StatusAmostra();
	private List<StatusAmostra> listStatusAmostras;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	StatusAmostraService statusAmostraService;

	FacesContext facesContext;

	@PostConstruct
	public void init() {
		listStatusAmostras = statusAmostraService.findAll();

		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;

		facesContext = FacesContext.getCurrentInstance();

		if (listStatusAmostras == null) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe Status Amostra cadastrado!", ""));
		}

	}

	public void salvar() {
		
		try {
			statusAmostra.setDtUltAlteracao(Calendar.getInstance());
			statusAmostra.setUsuUltAlteracao("123456");
			statusAmostraService.inserirOuAtualizar(statusAmostra);
			
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Amostra cadastrado/alterado com sucesso!", ""));
			statusAmostra = new StatusAmostra();
			
			init();
			cadastrar = Boolean.FALSE;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(StatusAmostra statusAmostra) {
		try {
			statusAmostraService.excluir(statusAmostra);
			
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
