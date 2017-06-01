package br.com.sisAmostra.ManagerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GenericMB {

	private boolean novo;
	private boolean editar;
	
	public boolean isNovo() {
		return novo;
	}
	public void setNovo(boolean novo) {
		this.novo = novo;
	}
	public boolean isEditar() {
		return editar;
	}
	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	
	
	
}
