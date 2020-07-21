package casa.mi.controller.frontoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import casa.mi.modelo.dao.PeliculaDao;
import casa.mi.modelo.pojo.Pelicula;
import casa.mi.modelo.pojo.Usuario;

/**
 * Servlet implementation class PeliculasFrontOfficeController
 */
@WebServlet("/vistas/frontoffice/peliculas")
public class PeliculasFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PeliculasFrontOfficeController.class);
	private static final PeliculaDao daoPelicula = PeliculaDao.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String validados = request.getParameter("validadas");
		String titulo = "";
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
		
		try {
			
			Usuario usuarioSession = (Usuario) request.getSession().getAttribute("usuario_login");
			int idUsuario = usuarioSession.getId();
			
			if ( validados == null ) {
				titulo = "Peliculas aprobadas";
				peliculas = daoPelicula.getAllByUser( idUsuario, true );
			} else {
				titulo = "Peliculas pendientes de aprobacion";
				peliculas = daoPelicula.getAllByUser( idUsuario, false );
			} // if-else
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("peliculas", peliculas);
			request.setAttribute("titulo", titulo);
			request.getRequestDispatcher("peliculas.jsp").forward(request, response);			
		} // try-catch-finally
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
