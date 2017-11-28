package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Caracteristica;
import br.com.sisAmostra.Service.CaracteristicaService;

@ManagedBean(name = "caracteristicaMB")
@ViewScoped
public class CaracteristicaMB {

	private Caracteristica caracteristica = new Caracteristica();
	private List<Caracteristica> listCaracteristicas;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	CaracteristicaService caracteristicaService;

	@PostConstruct
	public void init() {
		carregarListas();

	}

	private void carregarListas() {
		listCaracteristicas = caracteristicaService.findAll();
	}

	public String salvar() {
		try {
			if(caracteristica.getIdCaracteristica() == null){
				caracteristica.setIdCaracteristica(caracteristicaService.sequence());
			}	
			caracteristicaService.inserirOuAtualizar(caracteristica);
			
			caracteristica = new Caracteristica();
			limpar();
			carregarListas();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Característica cadastrado/alterado com sucesso!", ""));
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
			caracteristicaService.excluir(caracteristica);
			
			carregarListas();
			
			caracteristica = new Caracteristica();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Característica deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
		return null;
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listCaracteristicas = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listCaracteristicas = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public List<Caracteristica> getListCaracteristicas() {
		return listCaracteristicas;
	}

	public void setListCaracteristicas(List<Caracteristica> listCaracteristicas) {
		this.listCaracteristicas = listCaracteristicas;
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
