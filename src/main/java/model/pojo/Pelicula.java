package model.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Pelicula {
	
	// Tema validaciones
	// Van encima del campo a validar, empiezan con @ y se pueden poner varias para el mismo campo
	// OJO AL MANOJO que los campos que esten definidos como type="number" no admiten un @NotBlank
	
	private int id;
	
	@NotBlank( message = "El titulo no puede estar en blanco")
	@Size( min = 2, max = 100, message = "La longitud debe ser entre 2 y 100 caracteres")
	private String nombre;
	
	@Min( value = 60, message = "La duracion minima debe ser de 60 minutos")
	@Max( value = 300, message = "La duracion maxima debe ser de 300 minutos")
	private int duracion;
	
	@Min( value = 1800, message = "La peliculas deben haber sido producidas del año 1.800 en adelante")
	@Max( value = 2020, message = "No puede haber peliculas superiores al año actual (2.020)")
	private int anio;
	
	@NotBlank( message = "La pelicula debe tener una caratula")
	private String caratula;
	
	public Pelicula() {
		super();
		this.id = 0;
		this.nombre = "";
		this.duracion = 0;
		this.anio = 0;
		this.caratula = "https://picsum.photos/50/75";
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", anio=" + anio + ", caratula="
				+ caratula + "]";
	}

	
	
	
	
	

}
