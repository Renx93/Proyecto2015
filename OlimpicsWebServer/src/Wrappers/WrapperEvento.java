package Wrappers;

import com.google.gson.annotations.SerializedName;

public class WrapperEvento {
	
	@SerializedName("nombreE")
	private String nombreE;
	@SerializedName("idEvento")
	private int idEvento;
		
	public WrapperEvento() {
	}
	
	public WrapperEvento(String nombreE, int idEvento) {
	
		this.nombreE = nombreE;
		this.idEvento = idEvento;
		
	}
	
	
	public String imprimirBookData(){		
		return (this.nombreE+" "+this.idEvento);
	}

	public String getNombreE() {
		return nombreE;
	}

	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	
}