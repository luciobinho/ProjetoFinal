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

import br.com.sisAmostra.Entity.Amostra;
import br.com.sisAmostra.Entity.DevolucaoAmostra;
import br.com.sisAmostra.Entity.MotivoDevolucao;
import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.AmostraService;
import br.com.sisAmostra.Service.DevolucaoAmostraService;
import br.com.sisAmostra.Service.MotivoDevolucaoService;
import br.com.sisAmostra.Service.UsuarioService;
import br.com.sisAmostra.Util.Constantes;

@ManagedBean(name = "devolucaoAmostraMB")
@ViewScoped
public class DevolucaoAmostraMB {

	private DevolucaoAmostra devolucaoAmostra = new DevolucaoAmostra();
	private List<DevolucaoAmostra> listDevolucaoAmostras;
	private List<MotivoDevolucao> listMotivoDevolucoes;
	
	private List<SelectItem> listaMotivos;
	private List<SelectItem> listaAmostras;

	public boolean cadastrar;
	public boolean editar;
	
	private Long idMotivo;
	private Long idAmostra;
	
	Usuario usuario = new Usuario();
	
	@Inject
	DevolucaoAmostraService devolucaoAmostraService;
	
	@Inject
	MotivoDevolucaoService motivoDevolucaoService;
	
	@Inject
	UsuarioService usuarioService;
	
	@Inject
	AmostraService amostraService;

	@PostConstruct
	public void init() {
		carregarListas();
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	}

	private void carregarListas() {
		listDevolucaoAmostras = devolucaoAmostraService.findAll();
		popularComboMotivo();
		popularComboAmostra();
	}

	public void salvar() {
		
		try {
			
			Amostra amostra = new Amostra();
			amostra = amostraService.buscar(idAmostra);
			amostra.getStatusAmostra().setIdStatus(Constantes.DEVOLVIDA);
			amostraService.inserirOuAtualizar(amostra);
			
			devolucaoAmostra.setAmostra(amostra);
			devolucaoAmostra.setUsuario(usuario);
			MotivoDevolucao motivoDevolucao = motivoDevolucaoService.buscar(idMotivo);
			devolucaoAmostra.setMotivoDevolucao(motivoDevolucao);
			
			devolucaoAmostraService.inserirOuAtualizar(devolucaoAmostra);
			
			devolucaoAmostra = new DevolucaoAmostra();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolução Amostra cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listDevolucaoAmostras = new ArrayList<>();
	}
	
	public void alterar(){
		idMotivo = devolucaoAmostra.getMotivoDevolucao().getIdMotivoDevolucao().longValue();
		idAmostra = devolucaoAmostra.getAmostra().getIdAmostra();
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
	
	public List<SelectItem> popularComboAmostra() {
		this.listaAmostras = new ArrayList<SelectItem>();
		List<Amostra> saida;
		try {
			saida = amostraService.findPorStatus(Constantes.NOVO);
			for (Amostra amostra : saida) {
				SelectItem select = new SelectItem();
				select.setValue(amostra.getIdAmostra());
				select.setLabel(amostra.getCodSCAD());
				this.listaAmostras.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaAmostras;
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

	public List<SelectItem> getListaAmostras() {
		return listaAmostras;
	}

	public void setListaAmostras(List<SelectItem> listaAmostras) {
		this.listaAmostras = listaAmostras;
	}

	public Long getIdAmostra() {
		return idAmostra;
	}

	public void setIdAmostra(Long idAmostra) {
		this.idAmostra = idAmostra;
	}

}
