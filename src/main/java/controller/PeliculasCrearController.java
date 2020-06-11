package controller;

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

import model.pojo.Alerta;
import model.pojo.Pelicula;
import modelo.dao.PeliculaDao;

/**
 * Servlet implementation class PeliculasCrearController
 */
@WebServlet("/crear-pelicula")
public class PeliculasCrearController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static PeliculaDao dao = PeliculaDao.getInstance();
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
			
			int id = Integer.parseInt(idParametro);
			int duracion = Integer.parseInt(duracionParametro);
			int anio = Integer.parseInt(anioParametro);
			
			// Como lo tenemos declarado arriba del todo no lo necesitamos aqui, estando arriba podemos usarlo en todo
			//PeliculaDao dao = PeliculaDao.getInstance();
			
			pelicula.setId(id);
			pelicula.setNombre(nombre);
			pelicula.setDuracion(duracion);
			pelicula.setAnio(anio);
			pelicula.setCaratula(caratula);
			
			
			Set<ConstraintViolation<Pelicula>> violations = validator.validate(pelicula);
			
			if ( violations.isEmpty() ) { // sin errores de validacion, lo guarda en la BBDD
				
				// if-else para hacer insert (si al id que recoge es 0) o un update (cualquier otra id)
				if ( id == 0 ) {
					dao.insert(pelicula);
				} else {
					dao.update(pelicula);
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
			
			//ir a la vista o jsp
			request.getRequestDispatcher("formulario-pelicula.jsp").forward(request, response);
			
		} // try-catch-catch-finally
		
	} // doPost

}

