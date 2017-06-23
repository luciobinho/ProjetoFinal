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

import br.com.sisAmostra.Entity.DevolucaoAmostra;
import br.com.sisAmostra.Entity.MotivoDevolucao;
import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.DevolucaoAmostraService;
import br.com.sisAmostra.Service.MotivoDevolucaoService;
import br.com.sisAmostra.Service.UsuarioService;

@ManagedBean(name = "devolucaoAmostraMB")
@ViewScoped
public class DevolucaoAmostraMB {

	private DevolucaoAmostra devolucaoAmostra = new DevolucaoAmostra();
	private List<DevolucaoAmostra> listDevolucaoAmostras;
	private List<MotivoDevolucao> listMotivoDevolucoes;
	
	private List<SelectItem> listaMotivos;

	public boolean cadastrar;
	public boolean editar;
	
	private Long idMotivo;
	
	Usuario usuario = new Usuario();
	
	@Inject
	DevolucaoAmostraService devolucaoAmostraService;
	
	@Inject
	MotivoDevolucaoService motivoDevolucaoService;
	
	@Inject
	UsuarioService usuarioService;

	@PostConstruct
	public void init() {
		carregarListas();
		
		usuario = usuarioService.buscar(537);//TODO recuperar usuario logado
		
	}

	private void carregarListas() {
		listDevolucaoAmostras = devolucaoAmostraService.findAll();
		popularComboMotivo();
	}

	public void salvar() {
		
		try {
			
			Calendar dtDevolucao = Calendar.getInstance();
			dtDevolucao.setTime(devolucaoAmostra.getDtDevolucaoBean());
			devolucaoAmostra.setDtDevolucao(dtDevolucao);
			
			devolucaoAmostra.setUsuario(usuario);
			devolucaoAmostra.setMotivoDevolucao(motivoDevolucaoService.buscar(idMotivo.intValue()));
			
			devolucaoAmostraService.inserirOuAtualizar(devolucaoAmostra);
			
			devolucaoAmostra = new DevolucaoAmostra();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolução Amostra cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		devolucaoAmostra = new DevolucaoAmostra();
		idMotivo = null;
	}

	public void deletar() {
		try {
			devolucaoAmostraService.excluir(devolucaoAmostra);
			
			carregarListas();
			
			devolucaoAmostra = new DevolucaoAmostra();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolução Amostra deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listDevolucaoAmostras = new ArrayList<>();
	}
	
	public void alterar(){
		devolucaoAmostra.setDtDevolucaoBean(devolucaoAmostra.getDtDevolucao().getTime());
		idMotivo = devolucaoAmostra.getMotivoDevolucao().getIdMotivoDevolucao().longValue();
		editar = Boolean.TRUE;
		listDevolucaoAmostras = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public List<SelectItem> popularComboMotivo() {
		this.listaMotivos = new ArrayList<SelectItem>();
		List<MotivoDevolucao> saida;
		try {
			saida = motivoDevolucaoService.findAll();
			for (MotivoDevolucao motivoDevolucao : saida) {
				SelectItem select = new SelectItem();
				select.setValue(motivoDevolucao.getIdMotivoDevolucao());
				select.setLabel(motivoDevolucao.getDescricao());
				this.listaMotivos.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaMotivos;
	}
	
	public DevolucaoAmostra getDevolucaoAmostra() {
		return devolucaoAmostra;
	}

	public void setDevolucaoAmostra(DevolucaoAmostra devolucaoAmostra) {
		this.devolucaoAmostra = devolucaoAmostra;
	}

	public List<DevolucaoAmostra> getListDevolucaoAmostras() {
		return listDevolucaoAmostras;
	}

	public void setListDevolucaoAmostras(List<DevolucaoAmostra> listDevolucaoAmostras) {
		this.listDevolucaoAmostras = listDevolucaoAmostras;
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

	public List<SelectItem> getListaMotivos() {
		return listaMotivos;
	}

	public void setListaMotivos(List<SelectItem> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}

	public Long getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(Long idMotivo) {
		this.idMotivo = idMotivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


}
