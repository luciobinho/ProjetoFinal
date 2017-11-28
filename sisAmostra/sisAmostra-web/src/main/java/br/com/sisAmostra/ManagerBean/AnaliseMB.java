package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.sisAmostra.Entity.Amostra;
import br.com.sisAmostra.Entity.Analise;
import br.com.sisAmostra.Entity.Caracteristica;
import br.com.sisAmostra.Entity.Especificacao;
import br.com.sisAmostra.Entity.MotivoDevolucao;
import br.com.sisAmostra.Entity.Norma;
import br.com.sisAmostra.Entity.Resultado;
import br.com.sisAmostra.Entity.StatusAnalise;
import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.AmostraService;
import br.com.sisAmostra.Service.AnaliseService;
import br.com.sisAmostra.Service.CaracteristicaService;
import br.com.sisAmostra.Service.EspecificacaoService;
import br.com.sisAmostra.Service.NormaService;
import br.com.sisAmostra.Service.ResultadoService;
import br.com.sisAmostra.Service.StatusAnaliseService;
import br.com.sisAmostra.Service.UsuarioService;
import br.com.sisAmostra.Util.Constantes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean(name = "analiseMB")
@ViewScoped
public class AnaliseMB {

	private Analise analise = new Analise();
	private Resultado resultado = new Resultado();
	private List<Analise> listAnalises;
	private List<MotivoDevolucao> listMotivoDevolucoes;
	private List<Resultado> listResultados = new ArrayList<>();
	
	private List<SelectItem> listaTarefas;
	private List<SelectItem> listaFuncionario;
	private List<SelectItem> listaStatus;
	private List<SelectItem> listaAmostras;
	private List<SelectItem> listaCaracteristicas;
	private List<SelectItem> listaNormas;
	private List<SelectItem> listaEspecificacoes;

	public boolean cadastrar;
	public boolean editar;
	public boolean bloquear;
	
	private Long idStatus;
	private Long idFuncionario;
	private Long idAmostra;
	private Long idNorma;
	private Long idEspecificacao;
	private Long idCaracteristica;
	
	Usuario usuario = new Usuario();
	
	@PersistenceContext(name="sisAmostra")
	protected EntityManager entityManager;
	
	@Inject
	AnaliseService analiseService;
	
	@Inject
	AmostraService amostraService;
	
	@Inject
	UsuarioService usuarioService;
	
	@Inject
	StatusAnaliseService statusAnaliseService;
	
	@Inject
	NormaService normaService;
	
	@Inject
	EspecificacaoService especificacaoService;
	
	@Inject
	CaracteristicaService caracteristicaService;
	
	@Inject
	ResultadoService resultadoService;
	
	@PostConstruct
	public void init() {
		carregarListas();
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	}

	private void carregarListas() {
		listAnalises = analiseService.findAll();
		popularComboFuncionario();
		popularComboStatus();
		popularComboAmostras(Constantes.NOVO);
		popularComboCaracteristica();
		popularComboNorma();
		popularComboEspecificacao();		
	}

	public void salvar() {
		
		try {
			Amostra amostra = amostraService.buscar(idAmostra);
			amostra.getStatusAmostra().setIdStatus(Constantes.ANALISANDO);
			amostraService.inserirOuAtualizar(amostra);
			analise.setAmostra(amostra);
			analise.setStatusAnalise(statusAnaliseService.buscar(idStatus));
			analise.setUsuario(usuario);
			
			for (Resultado resultado : listResultados) {
				resultado.setAnalise(analise);
			}
			analise.setResultados(listResultados);
			analiseService.inserirOuAtualizar(analise);
			
			analise = new Analise();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Análise cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void adicionarResultado(){
		
		try {
			
			Caracteristica caracteristica = new Caracteristica();
			caracteristica = caracteristicaService.buscar(idCaracteristica);
			
			Norma norma = new Norma();
			norma = normaService.buscar(idNorma);
			
			Especificacao especificacao = new Especificacao();
			especificacao = especificacaoService.buscar(idEspecificacao);
			
			resultado.setCaracteristica(caracteristica);
			resultado.setNorma(norma);
			resultado.setEspecificacao(especificacao);
			
			listResultados.add(resultado);
			idCaracteristica = null;
			idNorma = null;
			idEspecificacao = null;
			resultado = new Resultado();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		analise = new Analise();
		idStatus = null;
		idFuncionario = null;
		listResultados = new ArrayList<>();
	}

	public void deletar() {
		try {
			analise.getAmostra().getStatusAmostra().setIdStatus(Constantes.NOVO);
			amostraService.inserirOuAtualizar(analise.getAmostra());
			analiseService.excluir(analise);
			
			carregarListas();
			
			analise = new Analise();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Análise deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void deletarLinhaResultado(){
		listResultados.remove(resultado);
		
		resultadoService.excluir(resultado);
		
		resultado = new Resultado();
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		bloquear = Boolean.TRUE;
		idStatus = Constantes.NOVO;
		listAnalises = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		idAmostra = analise.getAmostra().getIdAmostra();
		idStatus = analise.getStatusAnalise().getIdStatus();
		listAnalises = new ArrayList<>();
		listResultados = analise.getResultados();
		popularComboAmostras(Constantes.TODOS);
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public List<SelectItem> popularComboFuncionario() {

		listaFuncionario = new ArrayList<SelectItem>();
		List<Usuario> saida;
		try {
			saida = usuarioService.findAll();

			for (Usuario usuario : saida) {
				SelectItem select = new SelectItem();
				select.setValue(usuario.getIdUsuario());
				select.setLabel(usuario.getNome());
				listaFuncionario.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaFuncionario;
	}

	public List<SelectItem> popularComboStatus() {

		listaStatus = new ArrayList<SelectItem>();
		List<StatusAnalise> saida;
		try {
			saida = statusAnaliseService.findAll();

			for (StatusAnalise status : saida) {
				SelectItem select = new SelectItem();
				select.setValue(status.getIdStatus());
				select.setLabel(status.getDescricao());
				listaStatus.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaFuncionario;
	}
	
	public List<SelectItem> popularComboAmostras(Long status) {

		listaAmostras = new ArrayList<SelectItem>();
		List<Amostra> saida;
		try {
			if(status == Constantes.NOVO){
				saida = amostraService.findPorStatus(status);
			}else{
				saida = amostraService.findAll();
			}

			for (Amostra amostra : saida) {
				SelectItem select = new SelectItem();
				select.setValue(amostra.getIdAmostra());
				select.setLabel(amostra.getCodSCAD());
				listaAmostras.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaFuncionario;
	}
	
	public List<SelectItem> popularComboCaracteristica() {

		listaCaracteristicas = new ArrayList<SelectItem>();
		List<Caracteristica> saida;
		try {
			saida = caracteristicaService.findAll();

			for (Caracteristica caracteristica : saida) {
				SelectItem select = new SelectItem();
				select.setValue(caracteristica.getIdCaracteristica());
				select.setLabel(caracteristica.getDescricao());
				listaCaracteristicas.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaCaracteristicas;
	}

	public List<SelectItem> popularComboNorma() {

		listaNormas = new ArrayList<SelectItem>();
		List<Norma> saida;
		try {
			saida = normaService.findAll();

			for (Norma norma : saida) {
				SelectItem select = new SelectItem();
				select.setValue(norma.getIdNorma());
				select.setLabel(norma.getDescricao());
				listaNormas.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaNormas;
	}
	
	public List<SelectItem> popularComboEspecificacao() {

		listaEspecificacoes = new ArrayList<SelectItem>();
		List<Especificacao> saida;
		try {
			saida = especificacaoService.findAll();

			for (Especificacao especificacao : saida) {
				SelectItem select = new SelectItem();
				select.setValue(especificacao.getIdEspecificacao());
				select.setLabel(especificacao.getDescricao());
				listaEspecificacoes.add(select);
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
		return listaEspecificacoes;
	}
	
	public JasperPrint gerar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, entityManager);
            parameters.put("idAmostra", analise.getAmostra().getIdAmostra());
            JasperReport jasperReport = JasperCompileManager.compileReport(Constantes.RELATORIO);
            
            byte[] lReportData = JasperRunManager.runReportToPdf(jasperReport, parameters);
            if (lReportData != null && lReportData.length > 0){
                //response é um HttpServletResponse
            	response.setContentType("C:/Documents and Settings/CertificadoDeEnsaio.pdf");
                response.setContentType("application/pdf");
                response.setContentLength(lReportData.length);
                ServletOutputStream outputStream;
                try {
                    outputStream = response.getOutputStream();
                    outputStream.write(lReportData, 0, lReportData.length);
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    throw new JRException(e);
                } 
            }
            
            
        } catch (JRException ex) {
            ex.printStackTrace();
        }
		return null;
		
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

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
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

	public List<SelectItem> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<SelectItem> listaStatus) {
		this.listaStatus = listaStatus;
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

	public boolean isBloquear() {
		return bloquear;
	}

	public void setBloquear(boolean bloquear) {
		this.bloquear = bloquear;
	}

	public List<SelectItem> getListaCaracteristicas() {
		return listaCaracteristicas;
	}

	public void setListaCaracteristicas(List<SelectItem> listaCaracteristicas) {
		this.listaCaracteristicas = listaCaracteristicas;
	}

	public List<SelectItem> getListaNormas() {
		return listaNormas;
	}

	public void setListaNormas(List<SelectItem> listaNormas) {
		this.listaNormas = listaNormas;
	}

	public List<SelectItem> getListaEspecificacoes() {
		return listaEspecificacoes;
	}

	public void setListaEspecificacoes(List<SelectItem> listaEspecificacoes) {
		this.listaEspecificacoes = listaEspecificacoes;
	}

	public Long getIdNorma() {
		return idNorma;
	}

	public List<Resultado> getListResultados() {
		return listResultados;
	}

	public void setListResultados(List<Resultado> listResultados) {
		this.listResultados = listResultados;
	}

	public void setIdNorma(Long idNorma) {
		this.idNorma = idNorma;
	}

	public Long getIdEspecificacao() {
		return idEspecificacao;
	}

	public void setIdEspecificacao(Long idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}

	public Long getIdCaracteristica() {
		return idCaracteristica;
	}

	public void setIdCaracteristica(Long idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

}
