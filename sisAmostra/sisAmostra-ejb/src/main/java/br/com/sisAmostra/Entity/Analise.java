package br.com.sisAmostra.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Analise")
@NamedQueries({
	@NamedQuery(name = "Analise.findAll", query = "SELECT A FROM Analise A ")
})

public class Analise {
	
	@Id
	@Column
	@GeneratedValue
	private Long idAnalise;
	
	@Column
	private String descricao;
	
	@Column
	private Date dtAnalise;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idAmostra")
	private Amostra amostra;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idStatus")
	private StatusAnalise statusAnalise;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "analise", targetEntity = Resultado.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Resultado> resultados;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdAnalise() {
		return idAnalise;
	}

	public void setIdAnalise(Long idAnalise) {
		this.idAnalise = idAnalise;
	}

	public Date getDtAnalise() {
		return dtAnalise;
	}

	public void setDtAnalise(Date dtAnalise) {
		this.dtAnalise = dtAnalise;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusAnalise getStatusAnalise() {
		return statusAnalise;
	}

	public void setStatusAnalise(StatusAnalise statusAnalise) {
		this.statusAnalise = statusAnalise;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

}
