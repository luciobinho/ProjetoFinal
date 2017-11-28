package br.com.sisAmostra.Entity;

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
@Table(name="Resultado")
@NamedQueries({
	@NamedQuery(name = "Resultado.findAll", query = "SELECT R FROM Resultado R"),
	@NamedQuery(name = "Resultado.recuperarPorIdAnalise", query = "SELECT R FROM Resultado R JOIN R.analise A WHERE A.idAnalise = :idAnalise ")
})

public class Resultado {
	
	@Id
	@Column
	@GeneratedValue
	private Long idResultado;
	
	@Column
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="idAnalise", nullable=true)
	private Analise analise;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idCaracteristica")
	private Caracteristica caracteristica;	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idNorma")
	private Norma norma;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idEspecificacao")
	private Especificacao especificacao;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Norma getNorma() {
		return norma;
	}

	public void setNorma(Norma norma) {
		this.norma = norma;
	}

	public Especificacao getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(Especificacao especificacao) {
		this.especificacao = especificacao;
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	public Analise getAnalise() {
		return analise;
	}

	public void setAnalise(Analise analise) {
		this.analise = analise;
	}

}
