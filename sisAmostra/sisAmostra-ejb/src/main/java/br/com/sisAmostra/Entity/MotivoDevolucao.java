package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="MotivoDevolucao")
@NamedQueries({
	@NamedQuery(name = "MotivoDevolucao.findAll", query = "SELECT MD FROM MotivoDevolucao MD")
})

public class MotivoDevolucao {
	
	@Id
	@Column
	@GeneratedValue
	private Long idMotivoDevolucao;
	
	@Column
	private String descricao;
	
	public Long getIdMotivoDevolucao() {
		return idMotivoDevolucao;
	}

	public void setIdMotivoDevolucao(Long idMotivoDevolucao) {
		this.idMotivoDevolucao = idMotivoDevolucao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
