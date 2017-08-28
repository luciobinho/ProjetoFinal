package br.com.sisAmostra.Entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tarefa")
@NamedQueries({
	@NamedQuery(name = "Tarefa.findAll", query = "SELECT T FROM Tarefa T"),
	@NamedQuery(name = "Tarefa.findAllFunc", query = "SELECT T FROM Tarefa T WHERE T.usuario.idUsuario = :idUsuario")
})

public class Tarefa {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idTarefa;
	
	@Column
	private String descricao;
	
	@Column
	private Calendar dtInicio;
	
	@Column
	private Calendar dtPrazo;
	
	@Column
	private String observacao;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idStatus")
	private StatusTarefa statusTarefa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	private transient Date dtInicioBean;
	
	private transient Date dtPrazoBean;
	
	public Integer getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(Integer idTarefa) {
		this.idTarefa = idTarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Calendar dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Calendar getDtPrazo() {
		return dtPrazo;
	}

	public void setDtPrazo(Calendar dtPrazo) {
		this.dtPrazo = dtPrazo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDtInicioBean() {
		return dtInicioBean;
	}

	public void setDtInicioBean(Date dtInicioBean) {
		this.dtInicioBean = dtInicioBean;
	}

	public Date getDtPrazoBean() {
		return dtPrazoBean;
	}

	public void setDtPrazoBean(Date dtPrazoBean) {
		this.dtPrazoBean = dtPrazoBean;
	}

	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
