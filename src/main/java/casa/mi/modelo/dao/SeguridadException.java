package casa.mi.modelo.dao;

public class SeguridadException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public static final String MENSAJE = "No tiene permisos para manipular esta entrada";
	
	public SeguridadException() {
		super(MENSAJE);
	}

}
