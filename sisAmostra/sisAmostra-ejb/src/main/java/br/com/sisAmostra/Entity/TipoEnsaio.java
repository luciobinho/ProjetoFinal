package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TipoEnsaio")
@NamedQueries({
	@NamedQuery(name = "TipoEnsaio.findAll", query = "SELECT TE FROM TipoEnsaio TE")
})

public class TipoEnsaio {
	
	@Id
	@Column
	@GeneratedValue
	private Long idTipoEnsaio;
	
	@Column
	private String descricao;
	
	public Long getIdTipoEnsaio() {
		return idTipoEnsaio;
	}

	public void setIdTipoEnsaio(Long idTipoEnsaio) {
		this.idTipoEnsaio = idTipoEnsaio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
