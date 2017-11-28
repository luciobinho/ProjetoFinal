package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="StatusAnalise")
@NamedQueries({
	@NamedQuery(name = "StatusAnalise.findAll", query = "SELECT SA FROM StatusAnalise SA"),
	@NamedQuery(name = "StatusAnalise.sequence", query = "SELECT count(idStatus) + 1 FROM StatusAnalise SA")
})

public class StatusAnalise {
	
	@Id
	@Column
	private Long idStatus;
	
	@Column
	private String descricao;
	
	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
