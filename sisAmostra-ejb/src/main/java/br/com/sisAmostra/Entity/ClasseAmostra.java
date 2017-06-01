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
@Table(name="ClasseAmostra")
@NamedQueries({
	@NamedQuery(name = "ClasseAmostra.findAll", query = "SELECT C FROM ClasseAmostra C")
})

public class ClasseAmostra {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idClasse;
	
	@Column
	private String cdClasse;
	
	@Column
	private String descricao;
	
	@Column
	private Calendar dtUltAlteracao;
	
	@Column
	private String usuUltAlteracao;	

	public Integer getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Integer idClasse) {
		this.idClasse = idClasse;
	}

	public String getCdClasse() {
		return cdClasse;
	}

	public void setCdClasse(String cdClasse) {
		this.cdClasse = cdClasse;
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
