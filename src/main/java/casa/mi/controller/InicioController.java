package casa.mi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import casa.mi.modelo.dao.DistribuidoraDao;
import casa.mi.modelo.dao.PeliculaDao;
import casa.mi.modelo.pojo.Distribuidora;
import casa.mi.modelo.pojo.Pelicula;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String TODAS_LAS_DISTRIBUIDORAS = "-1";
	
	private static final PeliculaDao peliculaDao = PeliculaDao.getInstance();
	private static final DistribuidoraDao distribuidoraDao = DistribuidoraDao.getInstance();

	// En vez de poner el codigo en el post y en el get ponerle un doPost o viceversa, aqui lo hemos puesto
	// en doProccess y dirigido get y post a el

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// parametros
		String paramIdDistribuidora = ( request.getParameter("idDistribuidora") == null) ? TODAS_LAS_DISTRIBUIDORAS : request.getParameter("idDistribuidora");
		String paramDistNombre = ( request.getParameter("distribuidora") == null ) ? "Todas las peliculas" : request.getParameter("distribuidora");
		
		// inicializar atributos a retornar a la vista
		ArrayList<Distribuidora> distribuidorasConPeliculas = new ArrayList<Distribuidora>();
		String encabezado = "";
		
		if ( TODAS_LAS_DISTRIBUIDORAS.equals(paramIdDistribuidora) ) {		// todos los productos de todas las categorias
			
			distribuidorasConPeliculas = distribuidoraDao.getAllWithPeliculas();
			encabezado = "Todas las peliculas por distribuidora";
			
		} else {
			
			// obtener las peliculas
			int idDistribuidora = Integer.parseInt(paramIdDistribuidora);
			ArrayList<Pelicula> peliculas = peliculaDao.getAllByDistribuidora(idDistribuidora, 10);
			
			// crear distribuidora para añadir productos
			Distribuidora d = new Distribuidora();
			d.setId(idDistribuidora);
			d.setNombre(paramDistNombre);
			d.setPeliculas(peliculas);
			
			// guardar en el array la distribuidora
			distribuidorasConPeliculas.add(d);
			
			encabezado = "<b>" + peliculas.size() + "<b> ultimas peliculas de <b>" + paramDistNombre + "<b>";
			
		} // if-else
		
		
		// atributos
		request.setAttribute("distribuidorasConPeliculas", distribuidorasConPeliculas);
		request.setAttribute("encabezado", encabezado);
		
		// forward vista
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
