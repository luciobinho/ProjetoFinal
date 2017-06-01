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

import br.com.sisAmostra.Entity.Equipamento;
import br.com.sisAmostra.Entity.Laboratorio;
import br.com.sisAmostra.Entity.StatusEquipamento;
import br.com.sisAmostra.Service.EquipamentoService;
import br.com.sisAmostra.Service.LaboratorioService;
import br.com.sisAmostra.Service.StatusEquipamentoService;

@ManagedBean(name = "equipamentoMB")
@ViewScoped
public class EquipamentoMB {

	private Equipamento equipamento = new Equipamento();
	private List<Equipamento> listaEquipamentos;
	
	private List<SelectItem> listaStatus;
	private List<SelectItem> listaLaboratorio;
	
	private Long idLaboratorio;
	private Long idStatus;
	
	public boolean listar;
	public boolean cadastrar;
	public boolean editar;
	public boolean visualizar;

	@Inject
	EquipamentoService equipamentoService;
	
	@Inject
	StatusEquipamentoService statusEquipamentoService;
	
	@Inject
	LaboratorioService laboratorioService;

	@PostConstruct
	public void init() {
		carregarListas();
		
		if (listaEquipamentos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe Equipamento cadastrado!", ""));
		}
	}

	private void carregarListas() {
		listaEquipamentos = equipamentoService.findAll();
		popularComboLaboratorio();
		popularComboStatus();
	}
	
	public List<SelectItem> popularComboStatus() {

		this.listaStatus = new ArrayList<SelectItem>();
		List<StatusEquipamento> saida;
		try {
			saida = statusEquipamentoService.findAll();

			for (StatusEquipamento statusEquipamento : saida) {
				SelectItem select = new SelectItem();
				select.setValue(statusEquipamento.getIdStatus());
				select.setLabel(statusEquipamento.getDescricao());
				this.listaStatus.add(select);
			}
		} catch (Exception e) {

			e.getMessage();
			e.getStackTrace();
		}
		return listaStatus;
	}
	
	public List<SelectItem> popularComboLaboratorio() {

		this.listaLaboratorio = new ArrayList<SelectItem>();
		List<Laboratorio> saida;
		try {
			saida = laboratorioService.findAll();

			for (Laboratorio laboratorio : saida) {
				SelectItem select = new SelectItem();
				select.setValue(laboratorio.getIdLaboratorio());
				select.setLabel(laboratorio.getDescricao());
				this.listaLaboratorio.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaLaboratorio;
	}
	
	private void limpar() {
		idLaboratorio = null;
		idStatus = null;
		equipamento = new Equipamento();
		cadastrar = Boolean.FALSE;
		visualizar = Boolean.FALSE;
		editar = Boolean.FALSE;
	}

	public void salvar() {
		try {
			equipamento.setLaboratorio(laboratorioService.buscar(idLaboratorio.intValue()));
			equipamento.setStatusEquipamento(statusEquipamentoService.buscar(idStatus.intValue()));
			equipamentoService.inserirOuAtualizar(equipamento);
			
			equipamento = new Equipamento();
			carregarListas();
			limpar();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipamento cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao cadastrar/alterar equipamento.", ""));
		}
	}
	
	public void deletar() {
		
		try {
			equipamentoService.excluir(equipamento);
			
			carregarListas();
			
			equipamento = new Equipamento();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipamento excluído com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao excluir o equipamento.", ""));
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listaEquipamentos = new ArrayList<>();
	}
	
	public void alterar(){
		idLaboratorio = equipamento.getLaboratorio().getIdLaboratorio().longValue();
		idStatus = equipamento.getStatusEquipamento().getIdStatus().longValue();
		editar = Boolean.TRUE;
		listaEquipamentos = new ArrayList<>();
	}
	
	public void visualizar(){
		idLaboratorio = equipamento.getLaboratorio().getIdLaboratorio().longValue();
		idStatus = equipamento.getStatusEquipamento().getIdStatus().longValue();
		visualizar = Boolean.TRUE;
		listaEquipamentos = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}
	
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getListaEquipamentos() {
		return listaEquipamentos;
	}

	public void setListaEquipamentos(List<Equipamento> listaEquipamentos) {
		this.listaEquipamentos = listaEquipamentos;
	}

	public Long getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Long idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public List<SelectItem> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<SelectItem> listaStatus) {
		this.listaStatus = listaStatus;
	}

	public List<SelectItem> getListaLaboratorio() {
		return listaLaboratorio;
	}

	public void setListaLaboratorio(List<SelectItem> listaLaboratorio) {
		this.listaLaboratorio = listaLaboratorio;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
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

	public boolean isVisualizar() {
		return visualizar;
	}

	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}

	public boolean isListar() {
		return listar;
	}

	public void setListar(boolean listar) {
		this.listar = listar;
	}

}
