package model.pojo;

public class Pelicula {
	
	private int id;
	private String nombre;
	private int duracion;
	private int anio;
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
