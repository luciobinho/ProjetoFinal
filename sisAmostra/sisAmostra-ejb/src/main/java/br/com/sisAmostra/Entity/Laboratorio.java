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
@Table(name="Laboratorio")
@NamedQueries({
	@NamedQuery(name = "Laboratorio.findAll", query = "SELECT L FROM Laboratorio L")
})

public class Laboratorio {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idLaboratorio;
	
	@Column
	private String descricao;
	
	@Column
	private Calendar dtUltAlteracao;
	
	@Column
	private String usuUltAlteracao;	

	public Integer getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Integer idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
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
