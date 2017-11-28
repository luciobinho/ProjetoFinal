package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Especificacao")
@NamedQueries({
	@NamedQuery(name = "Especificacao.findAll", query = "SELECT SA FROM Especificacao SA"),
	@NamedQuery(name = "Especificacao.sequence", query = "SELECT count(idEspecificacao) + 1 FROM Especificacao SA")
})

public class Especificacao {
	
	@Id
	@Column
	private Long idEspecificacao;
	
	@Column
	private String descricao;
	
	public Long getIdEspecificacao() {
		return idEspecificacao;
	}

	public void setIdEspecificacao(Long idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
