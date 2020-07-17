package casa.mi.modelo.pojo;

public class Rol {

	// los valores de ADMIN y USER han de ser los mismos que las id que tienen asignados en la BBDD
	public static final int ADMIN = 421;
	public static final int USER = 256;
	
	private int id;
	private String nombre;
	
	public Rol() {
		super();
		this.id = 256;
		this.nombre = "";
	}

	public Rol(int id) {
		this();
		this.id = id;
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

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + "]";
	}

}
