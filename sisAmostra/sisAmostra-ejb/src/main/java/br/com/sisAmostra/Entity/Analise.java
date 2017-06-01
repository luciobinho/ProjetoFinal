package br.com.sisAmostra.Entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Analise")
@NamedQueries({
	@NamedQuery(name = "Analise.findAll", query = "SELECT A FROM Analise A")
})

public class Analise {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idAnalise;
	
	@Column
	private String descricao;
	
	@Column
	private Calendar dtAnalise;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idTarefa")
	private Tarefa tarefa;
	
	private transient Date dtAnaliseBean;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdAnalise() {
		return idAnalise;
	}

	public void setIdAnalise(Integer idAnalise) {
		this.idAnalise = idAnalise;
	}

	public Calendar getDtAnalise() {
		return dtAnalise;
	}

	public void setDtAnalise(Calendar dtAnalise) {
		this.dtAnalise = dtAnalise;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Date getDtAnaliseBean() {
		return dtAnaliseBean;
	}

	public void setDtAnaliseBean(Date dtAnaliseBean) {
		this.dtAnaliseBean = dtAnaliseBean;
	}

}
