package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Laboratorio")
@NamedQueries({
	@NamedQuery(name = "Laboratorio.findAll", query = "SELECT L FROM Laboratorio L")
})

public class Laboratorio {
	
	@Id
	@Column
	@GeneratedValue
	private Long idLaboratorio;
	
	@Column
	private String descricao;
	
	public Long getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Long idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
