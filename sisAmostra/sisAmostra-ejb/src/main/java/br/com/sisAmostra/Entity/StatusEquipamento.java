package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="StatusEquipamento")
@NamedQueries({
	@NamedQuery(name = "StatusEquipamento.findAll", query = "SELECT SE FROM StatusEquipamento SE"),
	@NamedQuery(name = "StatusEquipamento.sequence", query = "SELECT count(idStatus) + 1 FROM StatusEquipamento SE")
})

public class StatusEquipamento {
	
	@Id
	@Column
	private Long idStatus;
	
	@Column
	private String descricao;
	
	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
