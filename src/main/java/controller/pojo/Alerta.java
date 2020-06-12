package controller.pojo;

// aun siendo un pojo, como no actua con la BBDD, esta mejor situado en el controller, o incluso mejor en un controller.pojo

public class Alerta {
	
	private String tipo;
	private String texto;
	
	public Alerta() {
		super();
		this.tipo = "";
		this.texto = "";
	}
	
	public Alerta(String tipo, String texto) {
		super();
		this.tipo = tipo;
		this.texto = texto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	@Override
	public String toString() {
		return "Alerta [tipo=" + tipo + ", texto=" + texto + "]";
	}
	
	
	
	

}
