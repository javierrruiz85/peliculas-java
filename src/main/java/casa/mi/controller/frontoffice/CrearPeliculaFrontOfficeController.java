package casa.mi.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import casa.mi.modelo.pojo.Pelicula;

/**
 * Servlet implementation class CrearPeliculaFrontOfficeController
 */
@WebServlet("/vistas/frontoffice/crear-pelicula")
public class CrearPeliculaFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CrearPeliculaFrontOfficeController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Ir al formulario y enviar una pelicula inicializada
		Pelicula pelicula = new Pelicula();
		
		request.setAttribute("producto", pelicula);
		request.getRequestDispatcher("formulario.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO recoger parametros del formulario
		
		// TODO crear pelicula con los siguientes parametros
		Pelicula pelicula = new Pelicula();
		pelicula.setId(65);
		pelicula.setNombre("Pelicula guardada");
		// TODO validar pojo
		
		// TODO llamar al dao
		
		// TODO volver al formulario nuevo
		request.setAttribute("pelicula", pelicula);
		request.getRequestDispatcher("formulario.jsp").forward(request, response);
		
	}

}
