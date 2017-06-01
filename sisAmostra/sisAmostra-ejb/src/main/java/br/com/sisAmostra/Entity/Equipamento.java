package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Equipamento")
@NamedQueries({
	@NamedQuery(name = "Equipamento.findAll", query = "SELECT C FROM Equipamento C")
})

public class Equipamento {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idEquipamento;
	
	@Column
	private String cdPatrimonio;
	
	@Column
	private String descricao;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idStatus")
	private StatusEquipamento statusEquipamento;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idLaboratorio")
	private Laboratorio laboratorio;
	
	public Integer getIdEquipamento() {
		return idEquipamento;
	}

	public void setIdEquipamento(Integer idEquipamento) {
		this.idEquipamento = idEquipamento;
	}

	public String getCdPatrimonio() {
		return cdPatrimonio;
	}

	public void setCdPatrimonio(String cdPatrimonio) {
		this.cdPatrimonio = cdPatrimonio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public StatusEquipamento getStatusEquipamento() {
		return statusEquipamento;
	}

	public void setStatusEquipamento(StatusEquipamento statusEquipamento) {
		this.statusEquipamento = statusEquipamento;
	}
	
}
