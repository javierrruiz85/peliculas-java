package casa.mi.modelo.pojo;

import java.util.ArrayList;

public class Distribuidora {

	private int id;
	private String nombre;
	private ArrayList<Pelicula> peliculas;
	
	public Distribuidora() {
		super();
		this.id = 0;
		this.nombre = "";
		this.peliculas = new ArrayList<Pelicula>();
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
	
	public ArrayList<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	// en el toString, no poner la categoria porque entraria en redundancia ciclica y casca
	@Override
	public String toString() {
		return "Distribuidora [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
	
	
}
