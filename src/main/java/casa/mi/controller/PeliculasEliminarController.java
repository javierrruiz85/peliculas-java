package casa.mi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import casa.mi.controller.pojo.Alerta;
import casa.mi.modelo.dao.PeliculaDao;
import casa.mi.modelo.pojo.Pelicula;

/**
 * Servlet implementation class PeliculasEliminarController
 */
@WebServlet("/eliminar-pelicula")
public class PeliculasEliminarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Alerta alerta = new Alerta();
		
		// recoger parametro
		String parametroId = request.getParameter("id");
		int id = Integer.parseInt(parametroId);
		
		// llamar al modelo
		PeliculaDao dao = PeliculaDao.getInstance();
		
		try {
			
			Pelicula p = dao.delete(id);
			alerta = new Alerta("success", p.getNombre() + " ha sido eliminada con exito");
			
		} catch (Exception e) {
			
			alerta = new Alerta("danger", "No se ha podido eliminar");
			e.printStackTrace();
			
		} // try-catch
		
		// enviar datos a la vista
		ArrayList<Pelicula> peliculas = dao.getAll();
		request.setAttribute("peliculas", peliculas);
		request.setAttribute("alerta", alerta);
		
		// ir a la nueva vista o jsp
		request.getRequestDispatcher("peliculas").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
