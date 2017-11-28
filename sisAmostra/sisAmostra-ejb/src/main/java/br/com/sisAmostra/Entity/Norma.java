package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Norma")
@NamedQueries({
	@NamedQuery(name = "Norma.findAll", query = "SELECT SA FROM Norma SA"),
	@NamedQuery(name = "Norma.sequence", query = "SELECT count(idNorma) + 1 FROM Norma SA")
})

public class Norma {
	
	@Id
	@Column
	private Long idNorma;
	
	@Column
	private String descricao;
	
	public Long getIdNorma() {
		return idNorma;
	}

	public void setIdNorma(Long idNorma) {
		this.idNorma = idNorma;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
