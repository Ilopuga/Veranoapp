package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Administrador;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import dao.DaoActividad;
import dao.DaoAdministrador;

/**
 * Servlet implementation class GestionAdmin
 */
@MultipartConfig
public class GestionAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionAdministrador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		String id = request.getParameter("id");
		

		Administrador a;
		try {
			a = new Administrador(usuario, pass);
			if (id == null || id.trim().isEmpty()) {
				DaoAdministrador dao = new DaoAdministrador();
				dao.insertar(a);

			} else {
				int idInt = Integer.parseInt(id);
				a.setId(idInt);
				a.actualizar();

			}
			response.sendRedirect("admin/add_admin.html");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Redirige a add_actividad con un parámetro de error
			response.sendRedirect("admin/error.html?error=Error al insertar en la base de datos");
		}

	}
}