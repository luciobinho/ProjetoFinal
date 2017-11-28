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
@Table(name="Amostra")
@NamedQueries({
	@NamedQuery(name = "Amostra.findAll", query = "SELECT A FROM Amostra A"),
	@NamedQuery(name = "Amostra.findPorStatus", query = "SELECT A FROM Amostra A JOIN A.statusAmostra SA WHERE SA.idStatus = :idStatus"),
	@NamedQuery(name = "Amostra.findPorEmpresa", query = "SELECT A FROM Amostra A JOIN A.empresa E WHERE E.idEmpresa = :idEmpresa")
})

public class Amostra {
	
	@Id
	@Column
	@GeneratedValue
	private Long idAmostra;
	
	@Column
	private String dsCondicaoArmazenamento;	
	
	@Column
	private Date dtCriacao;	
	
	@Column
	private String dsObsSMS;
	
	@Column
	private String codSCAD;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idStatus")
	private StatusAmostra statusAmostra;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idTipoEnsaio")
	private TipoEnsaio tipoEnsaio;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idClasseAmostra")
	private ClasseAmostra classeAmostra;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idLaboratorio")
	private Laboratorio laboratorio;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idEmpresa")
	private Empresa empresa;
	
	public Long getIdAmostra() {
		return idAmostra;
	}

	public void setIdAmostra(Long idAmostra) {
		this.idAmostra = idAmostra;
	}

	public String getDsCondicaoArmazenamento() {
		return dsCondicaoArmazenamento;
	}

	public void setDsCondicaoArmazenamento(String dsCondicaoArmazenamento) {
		this.dsCondicaoArmazenamento = dsCondicaoArmazenamento;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getDsObsSMS() {
		return dsObsSMS;
	}

	public void setDsObsSMS(String dsObsSMS) {
		this.dsObsSMS = dsObsSMS;
	}

	public String getCodSCAD() {
		return codSCAD;
	}

	public void setCodSCAD(String codSCAD) {
		this.codSCAD = codSCAD;
	}

	public StatusAmostra getStatusAmostra() {
		return statusAmostra;
	}

	public void setStatusAmostra(StatusAmostra statusAmostra) {
		this.statusAmostra = statusAmostra;
	}

	public TipoEnsaio getTipoEnsaio() {
		return tipoEnsaio;
	}

	public void setTipoEnsaio(TipoEnsaio tipoEnsaio) {
		this.tipoEnsaio = tipoEnsaio;
	}

	public ClasseAmostra getClasseAmostra() {
		return classeAmostra;
	}

	public void setClasseAmostra(ClasseAmostra classeAmostra) {
		this.classeAmostra = classeAmostra;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
