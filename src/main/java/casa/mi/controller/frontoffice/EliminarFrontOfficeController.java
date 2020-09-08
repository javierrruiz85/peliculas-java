package casa.mi.controller.frontoffice;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import casa.mi.controller.pojo.Alerta;
import casa.mi.modelo.dao.PeliculaDao;
import casa.mi.modelo.dao.SeguridadException;
import casa.mi.modelo.pojo.Pelicula;
import casa.mi.modelo.pojo.Usuario;

/**
 * Servlet implementation class EliminarFrontOfficeController
 */
@WebServlet("/vistas/frontoffice/eliminar")
public class EliminarFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(EliminarFrontOfficeController.class);
	private static final PeliculaDao daoPelicula = PeliculaDao.getInstance();
	


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// crear variables
		String parametroId = request.getParameter("id");
		HttpSession session = request.getSession();
		Alerta alerta = new Alerta();
		Usuario usuario = new Usuario();
		
		LOG.trace("Deseamos eliminar la pelicula " + parametroId);
		
		try {
			
			usuario = (Usuario)session.getAttribute("usuario_login");
			int usuarioId = usuario.getId();
			int peliculaId = Integer.parseInt(parametroId);
			
			Pelicula pelicula = daoPelicula.deleteByUser(peliculaId, usuarioId);
			alerta = new Alerta("success", "Producto " + pelicula.getNombre() + " eliminado con exito.");
			
		} catch (SeguridadException e) {
			
			LOG.error("Ha habido un intento de saltarse la seguridad del usuario " + usuario);
			
		} catch (Exception e) {
			
			LOG.error(e);
			alerta = new Alerta("danger", "Error inesperado");
			
		} finally {
			
			session.setAttribute("alerta", alerta);
			request.getRequestDispatcher("/vistas/frontoffice/inicio").forward(request, response);
			
		}
		
	}

}
