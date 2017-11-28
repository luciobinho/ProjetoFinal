package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Especificacao;
import br.com.sisAmostra.Service.EspecificacaoService;

@ManagedBean(name = "especificacaoMB")
@ViewScoped
public class EspecificacaoMB {

	private Especificacao especificacao = new Especificacao();
	private List<Especificacao> listEspecificacoes;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	EspecificacaoService especificacaoService;

	@PostConstruct
	public void init() {
		carregarListas();

	}

	private void carregarListas() {
		listEspecificacoes = especificacaoService.findAll();
	}

	public String salvar() {
		try {
			if(especificacao.getIdEspecificacao() == null){
				especificacao.setIdEspecificacao(especificacaoService.sequence());
			}	
			especificacaoService.inserirOuAtualizar(especificacao);
			
			especificacao = new Especificacao();
			limpar();
			carregarListas();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Especificação cadastrado/alterado com sucesso!", ""));
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
			especificacaoService.excluir(especificacao);
			
			carregarListas();
			
			especificacao = new Especificacao();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Especificação deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
		return null;
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listEspecificacoes = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listEspecificacoes = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Especificacao getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(Especificacao especificacao) {
		this.especificacao = especificacao;
	}

	public List<Especificacao> getListEspecificacoes() {
		return listEspecificacoes;
	}

	public void setListEspecificacoes(List<Especificacao> listEspecificacoes) {
		this.listEspecificacoes = listEspecificacoes;
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
