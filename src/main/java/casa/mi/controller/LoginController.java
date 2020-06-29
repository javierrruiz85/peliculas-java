package casa.mi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import casa.mi.controller.pojo.Alerta;
import casa.mi.modelo.dao.UsuarioDao;
import casa.mi.modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// LOGIN
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		
		// Buscar usuario y contraseña en la BBDD
		UsuarioDao dao = UsuarioDao.getInstance();
		Usuario usuario = dao.existe(nombre, pass);
		
		if (usuario != null) {
			
			session.setAttribute("usuario_login", usuario);
			
			String mensaje = "Bienvenido " + nombre;
			
			request.setAttribute("alerta", new Alerta("success", mensaje));
			request.getRequestDispatcher("peliculas").forward(request, response);
			
		} else {
			
			request.setAttribute("alerta", new Alerta("danger", "Las credenciales son incorrectas"));
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} // if-else
		
	}

}
