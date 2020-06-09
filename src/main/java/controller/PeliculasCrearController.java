package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.pojo.Alerta;
import model.pojo.Pelicula;
import modelo.dao.PeliculaDao;

/**
 * Servlet implementation class PeliculasCrearController
 */
@WebServlet("/crear-pelicula")
public class PeliculasCrearController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//String parametroNombre = request.getParameter("nombre");
			String parametroId = request.getParameter("id");
			Pelicula pelicula = new Pelicula();
			
			//if ( parametroNombre != null && !"".equals(parametroNombre) ) {
			//	String nombre = parametroNombre;
			//	PeliculaDao dao = PeliculaDao.getInstance();
			//	pelicula = dao.getByNombre(nombre);
			//} //if
			
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
		
	}

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
			
			PeliculaDao dao = PeliculaDao.getInstance();
			
			pelicula.setId(id);
			pelicula.setNombre(nombre);
			pelicula.setDuracion(duracion);
			pelicula.setAnio(anio);
			pelicula.setCaratula(caratula);
			
			// validar que el campo introducido no este vacio y este dentro de los limites de 3 y 100 caracteres
			if ( (nombre != null) && (nombre.length() >= 3) && (nombre.length() <= 100) ) {
				
				// lineas comentadas para cuando se añada el SQL_UPDATE
				//if ( id == 0 ) {
					dao.insert(pelicula);
				//} else {
				//	dao.update(pelicula);
				//}
				
				alerta = new Alerta("success", "Datos guardados");
				
			} else {
				
				// mensaje de alerta
				alerta = new Alerta("danger", "El titulo debe tener entre 3 y 100 caracteres");
				
				// reenviar los datos a la vista para que no haga falta volver a introducirlos
				request.setAttribute("pelicula", pelicula);
				
			} // if-else
			
			
			
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
			
		}
		
	}

}

