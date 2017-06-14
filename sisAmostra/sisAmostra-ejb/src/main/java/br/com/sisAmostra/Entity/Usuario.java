package br.com.sisAmostra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
@NamedQueries({
	@NamedQuery(name = "Usuario.findAll", query = "SELECT U FROM Usuario U"),
	@NamedQuery(name = "Usuario.findLogin", query = "SELECT U FROM Usuario U WHERE U.login = ?0")
})

public class Usuario {
	
	@Id
	@Column
	@GeneratedValue
	private Integer idUsuario;
	
	@Column
	private String login;
	
	@Column
	private String senha;
	
	@Column
	private String nome;
	
	@Column
	private String email;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
