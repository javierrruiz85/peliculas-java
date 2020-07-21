package casa.mi.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class InicioFrontOfficeController
 */
@WebServlet("/vistas/frontoffice/inicio")
public class InicioFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(InicioFrontOfficeController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO recuperar datos de inicio para el usuario
		
		request.setAttribute("peliculas_aprobadas", 4);
		request.setAttribute("peliculas_pendientes", 2);
		
		// CUIDADO: mirar la URL del servlet "/vistas/frontoffice/inicio"
		// al hacer forward se pierde lo ultimo de la url y se le suma la variable pagina
		// ----------------------------------------------------------------------------------------
		// el forward resuleve la url de la siguiente manera:
		//
		//          "/vistas/frontoffice/inicio" + "index.jsp"  =  "/vistas/frontoffice/index.jsp"
		//  ------------------------------------------------------------------------------------------
		String pagina = "index.jsp";
		LOG.debug("forward: " + pagina);
		
		request.getRequestDispatcher(pagina).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
