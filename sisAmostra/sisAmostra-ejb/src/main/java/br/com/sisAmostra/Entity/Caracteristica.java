package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Caracteristica")
@NamedQueries({
	@NamedQuery(name = "Caracteristica.findAll", query = "SELECT SA FROM Caracteristica SA"),
	@NamedQuery(name = "Caracteristica.sequence", query = "SELECT count(idCaracteristica) + 1 FROM Caracteristica SA")
})

public class Caracteristica {
	
	@Id
	@Column
	private Long idCaracteristica;
	
	@Column
	private String descricao;
	
	public Long getIdCaracteristica() {
		return idCaracteristica;
	}

	public void setIdCaracteristica(Long idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
