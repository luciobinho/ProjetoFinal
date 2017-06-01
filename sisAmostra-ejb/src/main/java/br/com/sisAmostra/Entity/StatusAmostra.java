package br.com.sisAmostra.Entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="StatusAmostra")
@NamedQueries({
	@NamedQuery(name = "StatusAmostra.findAll", query = "SELECT SA FROM StatusAmostra SA")
})

public class StatusAmostra {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idStatus;
	
	@Column
	private String descricao;
	
	@Column
	private Calendar dtUltAlteracao;
	
	@Column
	private String usuUltAlteracao;	

	public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDtUltAlteracao() {
		return dtUltAlteracao;
	}

	public void setDtUltAlteracao(Calendar dtUltAlteracao) {
		this.dtUltAlteracao = dtUltAlteracao;
	}

	public String getUsuUltAlteracao() {
		return usuUltAlteracao;
	}

	public void setUsuUltAlteracao(String usuUltAlteracao) {
		this.usuUltAlteracao = usuUltAlteracao;
	}
	
	
}
