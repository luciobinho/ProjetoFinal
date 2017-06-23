package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

@ManagedBean(name = "amostraMB")
@ViewScoped
public class AmostraMB {

	private Amostra amostra = new Amostra();
	private List<Amostra> listaAmostras;
	private List<Laboratorio> listaLaboratorio;
	private List<ClasseAmostra> listaClasseAmostra;
	private List<TipoEnsaio> listaTipoEnsaio;
	private List<Empresa> listaEmpresa;
	private List<Usuario> listaFuncionario;
	private List<StatusAmostra> listaStatus;
	
	public boolean cadastrar;
	public boolean editar;
	
	Usuario usuario = new Usuario();
	
	private Long idFuncionario;
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
		carregarListas();
		
	}

	private void carregarListas() {
		listaAmostras = amostraService.findAll();
		listaClasseAmostra = classeAmostraService.findAll();
		listaLaboratorio = laboratorioService.findAll();
		listaTipoEnsaio = tipoEnsaioService.findAll();
		listaEmpresa = empresaService.findAll();
		listaFuncionario = usuarioService.findAll();
		listaStatus = statusAmostraService.findAll();
	}

	public void salvar() {
		
		try {
			
			amostraService.inserirOuAtualizar(amostra);
			
			amostra = new Amostra();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Amostra cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		amostra = new Amostra();
	}

	public void deletar() {
		try {
			amostraService.excluir(amostra);
			
			carregarListas();
			
			amostra = new Amostra();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolução Amostra deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listaAmostras = new ArrayList<>();
	}
	
	public void alterar(){
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

	public List<Laboratorio> getListaLaboratorio() {
		return listaLaboratorio;
	}

	public void setListaLaboratorio(List<Laboratorio> listaLaboratorio) {
		this.listaLaboratorio = listaLaboratorio;
	}

	public List<ClasseAmostra> getListaClasseAmostra() {
		return listaClasseAmostra;
	}

	public void setListaClasseAmostra(List<ClasseAmostra> listaClasseAmostra) {
		this.listaClasseAmostra = listaClasseAmostra;
	}

	public List<TipoEnsaio> getListaTipoEnsaio() {
		return listaTipoEnsaio;
	}

	public void setListaTipoEnsaio(List<TipoEnsaio> listaTipoEnsaio) {
		this.listaTipoEnsaio = listaTipoEnsaio;
	}

	public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public List<Usuario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Usuario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public List<StatusAmostra> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<StatusAmostra> listaStatus) {
		this.listaStatus = listaStatus;
	}

}
