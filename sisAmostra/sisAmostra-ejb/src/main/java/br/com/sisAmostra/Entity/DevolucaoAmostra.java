package br.com.sisAmostra.Entity;

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
@Table(name="DevolucaoAmostra")
@NamedQueries({
	@NamedQuery(name = "DevolucaoAmostra.findAll", query = "SELECT DA FROM DevolucaoAmostra DA")
})

public class DevolucaoAmostra {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idDevolucao;
	
	@Column
	private String descricao;
	
	@Column
	private Date dtDevolucao;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idMotivo")
	private MotivoDevolucao motivoDevolucao;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idAmostra")
	private Amostra amostra;
	
	public Integer getIdDevolucao() {
		return idDevolucao;
	}

	public void setIdDevolucao(Integer idDevolucao) {
		this.idDevolucao = idDevolucao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDtDevolucao() {
		return dtDevolucao;
	}

	public void setDtDevolucao(Date dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}

	public MotivoDevolucao getMotivoDevolucao() {
		return motivoDevolucao;
	}

	public void setMotivoDevolucao(MotivoDevolucao motivoDevolucao) {
		this.motivoDevolucao = motivoDevolucao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	
}
