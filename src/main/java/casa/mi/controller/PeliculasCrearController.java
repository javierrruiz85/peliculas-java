package casa.mi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import casa.mi.controller.pojo.Alerta;
import casa.mi.modelo.dao.DistribuidoraDao;
import casa.mi.modelo.dao.PeliculaDao;
import casa.mi.modelo.pojo.Distribuidora;
import casa.mi.modelo.pojo.Pelicula;

/**
 * Servlet implementation class PeliculasCrearController
 */
@WebServlet("/crear-pelicula")
public class PeliculasCrearController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static PeliculaDao daoPelicula = PeliculaDao.getInstance();
	private static DistribuidoraDao daoDistribuidora = DistribuidoraDao.getInstance();
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String parametroId = request.getParameter("id");
			Pelicula pelicula = new Pelicula();
			
			if (parametroId != null && !"".equals(parametroId)) {
				
				int id =Integer.parseInt(parametroId);
				PeliculaDao dao = PeliculaDao.getInstance();
				pelicula = dao.getById(id);
				
			} //if
			
			request.setAttribute("pelicula", pelicula);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			request.setAttribute("distribuidoras", daoDistribuidora.getAll());
			// ir a la vista o jsp
			request.getRequestDispatcher("formulario-pelicula.jsp").forward(request, response);
			
		} //try-catch-finally
		
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Alerta alerta = new Alerta();
		Pelicula pelicula = new Pelicula();
		
		try {
			
			//recogemos valores del formulario
			String idParametro = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String duracionParametro = request.getParameter("duracion");
			String anioParametro = request.getParameter("anio");
			String caratula = request.getParameter("caratula");
			String distribuidoraId = request.getParameter("id_distribuidora");
			
			// para que no casque en caso de que no pueda parsear, hacemos lo siguiente
			
			// 1º. declaramos las variables
			int duracion = 0;
			int anio = 0;
			
			// 2º. hacemos un try-catch por cada variable a parsear, si no puede las setea a 0 para que siga
			// como el pojo tiene validaciones, va a saltar por ahi el fallo
			try {	
				duracion = Integer.parseInt(duracionParametro);
			} catch (Exception e) {
				duracion = 0;
			} // try-catch validacion duracion
			
			try {	
				anio = Integer.parseInt(anioParametro);
			} catch (Exception e) {
				anio = 0;
			} // try-catch validacion anio

			
			// el id no lo controlamos porque ya tiene un catch que controla esas excepciones
			int id = Integer.parseInt(idParametro);
			int idDistribuidora = Integer.parseInt(distribuidoraId);
			

			// Como lo tenemos declarado arriba del todo no lo necesitamos aqui, estando arriba podemos usarlo en todo
			//PeliculaDao dao = PeliculaDao.getInstance();
			
			pelicula.setId(id);
			pelicula.setNombre(nombre);
			pelicula.setDuracion(duracion);
			pelicula.setAnio(anio);
			pelicula.setCaratula(caratula);
			
			Distribuidora d = new Distribuidora();
			d.setId(idDistribuidora);
			pelicula.setDistribuidora(d);
			
			
			Set<ConstraintViolation<Pelicula>> violations = validator.validate(pelicula);
			
			if ( violations.isEmpty() ) { // sin errores de validacion, lo guarda en la BBDD
				
				// if-else para hacer insert (si al id que recoge es 0) o un update (cualquier otra id)
				if ( id == 0 ) {
					daoPelicula.insert(pelicula);
				} else {
					daoPelicula.update(pelicula);
				} // if-else 2 (seleccion entre insert o update)
				
				alerta = new Alerta("success", "Datos guardados");
				
			} else {
				
				// Con este bloque controlamos los errores
				// Lo que hacemos es, en la variable errores, guardar todas las violaciones que de, concatenando el campo que falla con el mensaje que esta guardado en el pojo Pelicula
				String errores = "";
				for (ConstraintViolation<Pelicula> violaciones : violations) {
					errores += "<p><b>" + violaciones.getPropertyPath() + "</b>: " + violaciones.getMessage() + "</p>";
				} // for
				
				// mensaje de alerta, enviamos TODOS los errores de validacion que haya
				alerta = new Alerta("danger", errores);
				
				// reenviar los datos a la vista para que no haga falta volver a introducirlos
				request.setAttribute("pelicula", pelicula);
				
			} // if-else 1 (para validaciones)
			
			
			
		} catch (SQLException e) {
			
			// para que muestre error si ya existe lo que vamos a insertar
			alerta = new Alerta("danger", "Lo sentimos, ya existe ese registro" );
			
		} catch (Exception e) {
			
			// en caso de que ocurra algun otro tipo de error, mala programacion o algun otro fallo
			alerta = new Alerta("danger", "Lo sentimos, ha ocurrido un error desconocido" );
			e.printStackTrace();

		} finally {
			
			//enviar datos a la vista
			request.setAttribute("alerta", alerta);
			request.setAttribute("pelicula", pelicula);
			request.setAttribute("distribuidoras", daoDistribuidora.getAll());
			
			//ir a la vista o jsp
			request.getRequestDispatcher("formulario-pelicula.jsp").forward(request, response);
			
		} // try-catch-catch-finally
		
	} // doPost

}

