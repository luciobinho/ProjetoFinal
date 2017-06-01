package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Analise;
import br.com.sisAmostra.Entity.MotivoDevolucao;
import br.com.sisAmostra.Entity.Tarefa;
import br.com.sisAmostra.Service.AnaliseService;
import br.com.sisAmostra.Service.TarefaService;

@ManagedBean(name = "analiseMB")
@ViewScoped
public class AnaliseMB {

	private Analise analise = new Analise();
	private List<Analise> listAnalises;
	private List<MotivoDevolucao> listMotivoDevolucoes;
	
	private List<SelectItem> listaTarefas;

	public boolean cadastrar;
	public boolean editar;
	
	private Long idAnalise;
	
	@Inject
	AnaliseService analiseService;
	
	@Inject
	TarefaService tarefaService; 
	
	@PostConstruct
	public void init() {
		carregarListas();
		
		if (listAnalises.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe Análise cadastrada!", ""));
		}
	}

	private void carregarListas() {
		listAnalises = analiseService.findAll();
		popularTarefa();
	}

	public void salvar() {
		
		try {
			
			Calendar dtAnalise = Calendar.getInstance();
			dtAnalise.setTime(analise.getDtAnaliseBean());
			analise.setDtAnalise(dtAnalise);
			
			analiseService.inserirOuAtualizar(analise);
			
			analise = new Analise();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Análise cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		analise = new Analise();
		idAnalise = null;
	}

	public void deletar() {
		try {
			analiseService.excluir(analise);
			
			carregarListas();
			
			analise = new Analise();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolução Amostra deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listAnalises = new ArrayList<>();
	}
	
	public void alterar(){
		analise.setDtAnaliseBean(analise.getDtAnalise().getTime());
		idAnalise = analise.getTarefa().getIdTarefa().longValue();
		editar = Boolean.TRUE;
		listAnalises = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public List<SelectItem> popularTarefa() {
		this.listaTarefas = new ArrayList<SelectItem>();
		List<Tarefa> saida;
		try {
			saida = tarefaService.findAll();
			for (Tarefa tarefa : saida) {
				SelectItem select = new SelectItem();
				select.setValue(tarefa.getIdTarefa());
				select.setLabel(tarefa.getDescricao());
				this.listaTarefas.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaTarefas;
	}
	
	public Analise getAnalise() {
		return analise;
	}

	public void setAnalise(Analise analise) {
		this.analise = analise;
	}

	public List<Analise> getListAnalises() {
		return listAnalises;
	}

	public void setListAnalises(List<Analise> listAnalises) {
		this.listAnalises = listAnalises;
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

	public List<MotivoDevolucao> getListMotivoDevolucoes() {
		return listMotivoDevolucoes;
	}

	public void setListMotivoDevolucoes(List<MotivoDevolucao> listMotivoDevolucoes) {
		this.listMotivoDevolucoes = listMotivoDevolucoes;
	}

	public List<SelectItem> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<SelectItem> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}

	public Long getIdAnalise() {
		return idAnalise;
	}

	public void setIdAnalise(Long idAnalise) {
		this.idAnalise = idAnalise;
	}

}
