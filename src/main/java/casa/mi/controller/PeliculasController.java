package casa.mi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import casa.mi.modelo.dao.PeliculaDao;
import casa.mi.modelo.pojo.Pelicula;

/**
 * Servlet implementation class PeliculasController
 */
@WebServlet("/peliculas") // el nombre del controlador tiene que coincidir con el del fichero web.xml para que funcione
public class PeliculasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PeliculaDao dao = PeliculaDao.getInstance();
		
		ArrayList<Pelicula> peliculas = dao.getAll();
		
		request.setAttribute("peliculas", peliculas);
		
		request.getRequestDispatcher("vistas/peliculas/tabla-peliculas.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
