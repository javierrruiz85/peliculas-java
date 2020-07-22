package casa.mi.modelo.pojo;

/**
 * Creamos este pojo para guardar datos estadisticos del usuario
 * Lo hacemos mediante una VIEW de la BBDD
 * 
 * Hacemos esto para evitar hacer 2 sql como teniamos anteriormente
 * 
 * @author javaee
 *
 */

public class ResumenUsuario {

	private int idUsuario;
	private int peliculasTotal;
	private int peliculasAprobadas;
	private int peliculasPendientes;
	
	public ResumenUsuario() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getPeliculasTotal() {
		return peliculasTotal;
	}

	public void setPeliculasTotal(int peliculasTotal) {
		this.peliculasTotal = peliculasTotal;
	}

	public int getPeliculasAprobadas() {
		return peliculasAprobadas;
	}

	public void setPeliculasAprobadas(int peliculasAprobadas) {
		this.peliculasAprobadas = peliculasAprobadas;
	}

	public int getPeliculasPendientes() {
		return peliculasPendientes;
	}

	public void setPeliculasPendientes(int peliculasPendientes) {
		this.peliculasPendientes = peliculasPendientes;
	}

	@Override
	public String toString() {
		return "ResumenUsuario [idUsuario=" + idUsuario + ", peliculasTotal=" + peliculasTotal + ", peliculasAprobadas="
				+ peliculasAprobadas + ", peliculasPendientes=" + peliculasPendientes + "]";
	}
	
}
