package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="StatusTarefa")
@NamedQueries({
	@NamedQuery(name = "StatusTarefa.findAll", query = "SELECT ST FROM StatusTarefa ST")
})

public class StatusTarefa {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idStatus;
	
	@Column
	private String descricao;
	
	public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
