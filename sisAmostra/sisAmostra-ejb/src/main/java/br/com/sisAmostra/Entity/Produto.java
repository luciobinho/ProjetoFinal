package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Produto")
@NamedQueries({
	@NamedQuery(name = "Produto.findAll", query = "SELECT P FROM Produto P")
})

public class Produto {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idProduto;
	
	@Column
	private String descricao;
	
	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
