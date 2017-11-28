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
import br.com.sisAmostra.Entity.ClasseAmostra;
import br.com.sisAmostra.Entity.Empresa;
import br.com.sisAmostra.Entity.Laboratorio;
import br.com.sisAmostra.Entity.StatusAmostra;
import br.com.sisAmostra.Entity.TipoEnsaio;
import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.AmostraService;
import br.com.sisAmostra.Service.ClasseAmostraService;
import br.com.sisAmostra.Service.EmpresaService;
import br.com.sisAmostra.Service.LaboratorioService;
import br.com.sisAmostra.Service.StatusAmostraService;
import br.com.sisAmostra.Service.TipoEnsaioService;
import br.com.sisAmostra.Service.UsuarioService;
import br.com.sisAmostra.Util.Constantes;


@ManagedBean(name = "amostraMB")
@ViewScoped
public class AmostraMB {

	private Amostra amostra = new Amostra();
	private List<Amostra> listaAmostras;
	private List<SelectItem> listaLaboratorio;
	private List<SelectItem> listaClasseAmostra;
	private List<SelectItem> listaTipoEnsaio;
	private List<SelectItem> listaEmpresa;
	private List<SelectItem> listaStatus;
	
	public boolean cadastrar;
	public boolean editar;
	public boolean bloquear;
	
	Usuario usuario = new Usuario();
	
	private Long idStatus;
	private Long idLaboratorio;
	private Long idEmpresa;
	private Long idTipoEnsaio;
	private Long idClasseAmostra;
	
	@Inject
	AmostraService amostraService;
	
	@Inject
	UsuarioService usuarioService;

	@Inject
	ClasseAmostraService classeAmostraService;
	
	@Inject
	LaboratorioService laboratorioService;
	
	@Inject
	TipoEnsaioService tipoEnsaioService;
	
	@Inject
	EmpresaService empresaService;
	
	@Inject
	StatusAmostraService statusAmostraService;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");		
		carregarListas();
	}

	private void carregarListas() {
		if(usuario.getTipo().equals(Constantes.INTERNO)){
			listaAmostras = amostraService.findAll();
		}else{
			listaAmostras = amostraService.findPorEmpresa(usuario.getEmpresa().getIdEmpresa());
		}
		popularComboClasseAmostra();
		popularComboLaboratorio();
		popularComboTipoEnsaio();
		popularComboEmpresa();
		popularComboStatus();
	}

	public List<SelectItem> popularComboClasseAmostra() {

		listaClasseAmostra = new ArrayList<SelectItem>();
		List<ClasseAmostra> saida;
		try {
			saida = classeAmostraService.findAll();

			for (ClasseAmostra classeAmostra : saida) {
				SelectItem select = new SelectItem();
				select.setValue(classeAmostra.getIdClasse());
				select.setLabel(classeAmostra.getDescricao());
				listaClasseAmostra.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaClasseAmostra;
	}
	
	public List<SelectItem> popularComboLaboratorio() {

		listaLaboratorio = new ArrayList<SelectItem>();
		List<Laboratorio> saida;
		try {
			saida = laboratorioService.findAll();

			for (Laboratorio laboratorio : saida) {
				SelectItem select = new SelectItem();
				select.setValue(laboratorio.getIdLaboratorio());
				select.setLabel(laboratorio.getDescricao());
				listaLaboratorio.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaLaboratorio;
	}
	
	public List<SelectItem> popularComboTipoEnsaio() {

		listaTipoEnsaio = new ArrayList<SelectItem>();
		List<TipoEnsaio> saida;
		try {
			saida = tipoEnsaioService.findAll();

			for (TipoEnsaio tipoEnsaio : saida) {
				SelectItem select = new SelectItem();
				select.setValue(tipoEnsaio.getIdTipoEnsaio());
				select.setLabel(tipoEnsaio.getDescricao());
				listaTipoEnsaio.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaTipoEnsaio;
	}
	
	public List<SelectItem> popularComboEmpresa() {

		listaEmpresa = new ArrayList<SelectItem>();
		List<Empresa> saida;
		try {
			saida = empresaService.findAll();

			for (Empresa empresa : saida) {
				SelectItem select = new SelectItem();
				select.setValue(empresa.getIdEmpresa());
				select.setLabel(empresa.getDescricao());
				listaEmpresa.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaEmpresa;
	}
	
	public List<SelectItem> popularComboStatus() {

		listaStatus = new ArrayList<SelectItem>();
		List<StatusAmostra> saida;
		try {
			saida = statusAmostraService.findAll();

			for (StatusAmostra statusAmostra : saida) {
				SelectItem select = new SelectItem();
				select.setValue(statusAmostra.getIdStatus());
				select.setLabel(statusAmostra.getDescricao());
				listaStatus.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaStatus;
	}
	
	public void salvar() {
		
		try {
			Empresa empresa = empresaService.buscar(idEmpresa);
			Laboratorio laboratorio = laboratorioService.buscar(idLaboratorio);
			StatusAmostra statusAmostra = statusAmostraService.buscar(idStatus);
			TipoEnsaio tipoEnsaio = tipoEnsaioService.buscar(idTipoEnsaio);
			ClasseAmostra classeAmostra = classeAmostraService.buscar(idClasseAmostra);
			
			amostra.setEmpresa(empresa);
			amostra.setLaboratorio(laboratorio);
			amostra.setStatusAmostra(statusAmostra);
			amostra.setTipoEnsaio(tipoEnsaio);
			amostra.setClasseAmostra(classeAmostra);
			amostraService.inserirOuAtualizar(amostra);
			
			amostra = new Amostra();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Amostra cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		amostra = new Amostra();
		idClasseAmostra = null;
		idEmpresa = null;
		idLaboratorio = null;
		idStatus = null;
		idTipoEnsaio = null;
	}

	public void deletar() {
		try {
			amostraService.excluir(amostra);
			
			carregarListas();
			
			amostra = new Amostra();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolução Amostra deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		bloquear = Boolean.TRUE;
		idStatus = Constantes.NOVO;
		listaAmostras = new ArrayList<>();
	}
	
	public void alterar(){
		idClasseAmostra = amostra.getClasseAmostra().getIdClasse();
		idEmpresa = amostra.getEmpresa().getIdEmpresa();
		idLaboratorio = amostra.getLaboratorio().getIdLaboratorio();
		idStatus = amostra.getStatusAmostra().getIdStatus();
		idTipoEnsaio = amostra.getTipoEnsaio().getIdTipoEnsaio();
		editar = Boolean.TRUE;
		listaAmostras = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra Amostra) {
		this.amostra = Amostra;
	}

	public List<Amostra> getListaAmostras() {
		return listaAmostras;
	}

	public void setListaAmostras(List<Amostra> listaAmostras) {
		this.listaAmostras = listaAmostras;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public Long getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Long idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdTipoEnsaio() {
		return idTipoEnsaio;
	}

	public void setIdTipoEnsaio(Long idTipoEnsaio) {
		this.idTipoEnsaio = idTipoEnsaio;
	}

	public Long getIdClasseAmostra() {
		return idClasseAmostra;
	}

	public void setIdClasseAmostra(Long idClasseAmostra) {
		this.idClasseAmostra = idClasseAmostra;
	}

	public List<SelectItem> getListaLaboratorio() {
		return listaLaboratorio;
	}

	public void setListaLaboratorio(List<SelectItem> listaLaboratorio) {
		this.listaLaboratorio = listaLaboratorio;
	}

	public List<SelectItem> getListaClasseAmostra() {
		return listaClasseAmostra;
	}

	public void setListaClasseAmostra(List<SelectItem> listaClasseAmostra) {
		this.listaClasseAmostra = listaClasseAmostra;
	}

	public List<SelectItem> getListaTipoEnsaio() {
		return listaTipoEnsaio;
	}

	public void setListaTipoEnsaio(List<SelectItem> listaTipoEnsaio) {
		this.listaTipoEnsaio = listaTipoEnsaio;
	}

	public List<SelectItem> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(List<SelectItem> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public List<SelectItem> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<SelectItem> listaStatus) {
		this.listaStatus = listaStatus;
	}

	public boolean isBloquear() {
		return bloquear;
	}

	public void setBloquear(boolean bloquear) {
		this.bloquear = bloquear;
	}

}
