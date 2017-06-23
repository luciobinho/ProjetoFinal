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

	private Produto Produto = new Produto();
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
			ProdutoService.inserirOuAtualizar(Produto);
			
			Produto = new Produto();
			carregarListas();
			limpar();
			
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto cadastrado/alterado com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void limpar() {
		cadastrar = Boolean.FALSE;
		editar = Boolean.FALSE;
	}

	public void deletar() {
		try {
			ProdutoService.excluir(Produto);
			
			carregarListas();
			
			Produto = new Produto();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto deletada com sucesso!", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void novo(){
		cadastrar = Boolean.TRUE;
		listProdutos = new ArrayList<>();
	}
	
	public void alterar(Produto Produto){
		editar = Boolean.TRUE;
		listProdutos = new ArrayList<>();
	}
	
	public void cancelar(){
		limpar();
		carregarListas();
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto Produto) {
		this.Produto = Produto;
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
