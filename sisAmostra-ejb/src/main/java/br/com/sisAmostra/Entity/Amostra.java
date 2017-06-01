package br.com.sisAmostra.Entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Amostra")
public class Amostra implements Serializable {
	
	private static final long serialVersionUID = 1677472312289441987L;

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
	
	/*idLaboratorio;	
	idStatus	int		private	
	idTipoEnsaio	int		private	
	idDevolucao	int		private	
	idSCAD	int		private	
	idClasseAmostra	int		private	
	idCliente	int		private*/	

	
}
