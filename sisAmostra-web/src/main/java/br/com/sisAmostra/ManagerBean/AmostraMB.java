package br.com.sisAmostra.ManagerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sisAmostra.Entity.Amostra;

@ManagedBean
@ViewScoped
public class AmostraMB {

	private Amostra amostra;

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	
	
	
}
