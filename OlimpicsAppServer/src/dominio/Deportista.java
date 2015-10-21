package dominio;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "deportista")
public class Deportista implements Serializable {

	private static final long serialVersionUID = 1L; // Mapping JPA

	@Id
	@Column(name = "Id", nullable = false)
	private int idDep;
	
	@Column(name = "Nombre", nullable = false)
	private String nombre;

	@Column(name = "Edad", nullable = false)
	private int edad;
	
	@Column(name = "Altura", nullable = false)
	private float altura;
	
	@Column(name = "Peso", nullable = false)
	private float peso;
	
	

	public Deportista() {}

	public Deportista(String nombre, int edad, float altura, float peso) {
		this.nombre = nombre;
		this.edad = edad;
		this.altura = altura;
		this.peso = peso;
	}


	public Deportista(Deportista dep) {
		this.nombre = dep.getNombreDep();
				
	}	

	public String getNombreDep() {
		return nombre;
	}

	public void setNombreDep(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad(){
		return edad;
	}

	public void setEdad(int edad){
		this.edad = edad;
	}
	
	public float getAltura(){
		return altura;
	}
	
	public void setAltura(float altura){
		this.altura = altura;
	}
		
	public float getPeso(){
		return peso;
	}
	
	public void setPeso(float peso){
		this.peso = peso;
	}


}