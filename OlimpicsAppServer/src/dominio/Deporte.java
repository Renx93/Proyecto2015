package dominio;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "deporte")
public class Deporte implements Serializable {

	private static final long serialVersionUID = 1L; // Mapping JPA

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	 
	
	public Deporte() {

	}

	public Deporte(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;		
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}