package br.com.sisAmostra.ManagerBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sisAmostra.Entity.Produto;
import br.com.sisAmostra.Service.ProdutoService;

@ManagedBean(name = "produtoMB")
@ViewScoped
public class ProdutoMB {

	private Produto produto = new Produto();
	private List<Produto> listProdutos;

	public boolean cadastrar;
	public boolean editar;
	
	@Inject
	ProdutoService ProdutoService;

	@PostConstruct
	public void init() {
		carregarListas();

	}

	private void carregarListas() {
		listProdutos = ProdutoService.findAll();
	}

	public void salvar() {
		
		try {
			ProdutoService.inserirOuAtualizar(produto);
			
			produto = new Produto();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
		produto = new Produto();
	}

	public void deletar() {
		try {
			ProdutoService.excluir(produto);
			
			carregarListas();
			
			produto = new Produto();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto deletada com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - " + e.getMessage()+e.getCause(), ""));
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listProdutos = new ArrayList<>();
	}
	
	public void alterar(){
		editar = Boolean.TRUE;
		listProdutos = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
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
