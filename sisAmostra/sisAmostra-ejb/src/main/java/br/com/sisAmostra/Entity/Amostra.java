package br.com.sisAmostra.Entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Amostra")
@NamedQueries({
	@NamedQuery(name = "Amostra.findAll", query = "SELECT A FROM Amostra A")
})

public class Amostra {
	
	@Id
	@Column
	private Long idAmostra;
	
	@Column
	private String descricao;
	
	@Column
	private String dsCondicaoArmazenamento;	
	
	@Column
	private Calendar dtCriacao;	
	
	@Column
	private String dsObsSMS;
	
	@Column
	private String codSCAD;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idStatus")
	private StatusAmostra statusAmostra;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDsCondicaoArmazenamento() {
		return dsCondicaoArmazenamento;
	}

	public void setDsCondicaoArmazenamento(String dsCondicaoArmazenamento) {
		this.dsCondicaoArmazenamento = dsCondicaoArmazenamento;
	}

	public Calendar getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Calendar dtCriacao) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
