package dominio;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L; // Mapping JPA

	
	@Column(name = "Nombre", nullable = false)
	private String nombreE;

	@Id
	@Column(name = "IdEvento", nullable = false)
	private int idEvento;
	
	
	public Evento() {}

	public Evento(String nombreE, int idEvento) {
		this.nombreE = nombreE;
		this.idEvento = idEvento;		
	}


	public Evento(Evento ev) {
		this.nombreE = ev.getNombreE();
		this.idEvento = ev.getIdEvento();		
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