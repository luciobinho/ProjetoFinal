package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Norma;
import br.com.sisAmostra.Service.NormaService;

@ManagedBean(name = "normaMB")
@ViewScoped
public class NormaMB {

	private Norma norma = new Norma();
	private List<Norma> listNormas;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	NormaService normaService;

	@PostConstruct
	public void init() {
		carregarListas();

	}

	private void carregarListas() {
		listNormas = normaService.findAll();
	}

	public String salvar() {
		try {
			if(norma.getIdNorma() == null){
				norma.setIdNorma(normaService.sequence());
			}	
			normaService.inserirOuAtualizar(norma);
			
			norma = new Norma();
			limpar();
			carregarListas();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Norma cadastrado/alterado com sucesso!", ""));
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
			normaService.excluir(norma);
			
			carregarListas();
			
			norma = new Norma();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Norma deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
		return null;
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listNormas = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listNormas = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Norma getNorma() {
		return norma;
	}

	public void setNorma(Norma norma) {
		this.norma = norma;
	}

	public List<Norma> getListNormas() {
		return listNormas;
	}

	public void setListNormas(List<Norma> listNormas) {
		this.listNormas = listNormas;
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
