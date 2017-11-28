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

import br.com.sisAmostra.Entity.StatusTarefa;
import br.com.sisAmostra.Entity.Tarefa;
import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.StatusTarefaService;
import br.com.sisAmostra.Service.TarefaService;
import br.com.sisAmostra.Service.UsuarioService;

@ManagedBean(name = "tarefaMB")
@ViewScoped
public class TarefaMB {

	private Tarefa tarefa = new Tarefa();
	private List<Tarefa> listTarefas;
	private List<StatusTarefa> listStatusTarefas;
	
	private List<SelectItem> listaStatus;
	private List<SelectItem> listaFuncionario;

	public boolean cadastrar;
	public boolean editar;
	
	private Long idFuncionario;
	private Long idStatus;
	
	@Inject
	TarefaService tarefaService;
	
	@Inject
	StatusTarefaService statusTarefaService;
	
	@Inject
	UsuarioService usuarioService;
	
	@PostConstruct
	public void init() {
		carregarListas();
	}

	private void carregarListas() {
		listTarefas = tarefaService.findAll();
		popularComboFuncionario();
		popularComboStatus();
	}

	public void salvar() {
		
		try {
			
			Calendar dtInicio = Calendar.getInstance();
			dtInicio.setTime(tarefa.getDtInicioBean());
			tarefa.setDtInicio(dtInicio);
			
			Calendar dtFim = Calendar.getInstance();
			dtFim.setTime(tarefa.getDtPrazoBean());
			tarefa.setDtPrazo(dtFim);
			
			tarefa.setUsuario(usuarioService.buscar(idFuncionario.intValue()));
			tarefa.setStatusTarefa(statusTarefaService.buscar(idStatus.intValue()));
			
			tarefaService.inserirOuAtualizar(tarefa);
			
			tarefa = new Tarefa();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		tarefa = new Tarefa();
		idFuncionario = null;
		idStatus = null;
	}

	public void deletar() {
		try {
			tarefaService.excluir(tarefa);
			
			carregarListas();
			
			tarefa = new Tarefa();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listTarefas = new ArrayList<>();
	}
	
	public void alterar(){
		tarefa.setDtInicioBean(tarefa.getDtInicio().getTime());
		tarefa.setDtPrazoBean(tarefa.getDtPrazo().getTime());
		idFuncionario = tarefa.getUsuario().getIdUsuario().longValue();
		idStatus = tarefa.getStatusTarefa().getIdStatus().longValue();
		editar = Boolean.TRUE;
		listTarefas = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public List<SelectItem> popularComboStatus() {
		this.listaStatus = new ArrayList<SelectItem>();
		List<StatusTarefa> saida;
		try {
			saida = statusTarefaService.findAll();
			for (StatusTarefa statusTarefa : saida) {
				SelectItem select = new SelectItem();
				select.setValue(statusTarefa.getIdStatus());
				select.setLabel(statusTarefa.getDescricao());
				this.listaStatus.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaStatus;
	}
	
	public List<SelectItem> popularComboFuncionario() {
		this.listaFuncionario = new ArrayList<SelectItem>();
		List<Usuario> saida;
		try {
			saida = usuarioService.findAll();
			for (Usuario funcionario : saida) {
				SelectItem select = new SelectItem();
				select.setValue(funcionario.getIdUsuario());
				select.setLabel(funcionario.getNome());
				this.listaFuncionario.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaFuncionario;
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getListTarefas() {
		return listTarefas;
	}

	public void setListTarefas(List<Tarefa> listTarefas) {
		this.listTarefas = listTarefas;
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

	public List<StatusTarefa> getListStatusTarefas() {
		return listStatusTarefas;
	}

	public void setListStatusTarefas(List<StatusTarefa> listStatusTarefas) {
		this.listStatusTarefas = listStatusTarefas;
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

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

}
