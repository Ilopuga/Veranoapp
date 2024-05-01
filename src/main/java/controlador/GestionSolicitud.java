package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Solicitud;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Servlet implementation class GestionActividad
 */
@MultipartConfig
public class GestionSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionSolicitud() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dni = request.getParameter("dni");
		int cod_actividad = Integer.parseInt(request.getParameter("cod_actividad"));
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String email = request.getParameter("email");
		String direccion = request.getParameter("direccion");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String f_nacimiento = request.getParameter("f_nacimiento");
		//int id = Integer.parseInt(request.getParameter("id"));
	
		
		/* Modificar al poner UPDATE y quitar este constructor anterior*/
		try {
	        Solicitud s1 = new Solicitud(dni, cod_actividad, nombre, apellido1, apellido2, email, direccion, telefono, f_nacimiento);
	        s1.insertar();

	        response.sendRedirect("admin/add_actividad.html");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        //System.out.println("Error al insertar en la base de datos");

	        // Redirige a add_actividad con un parámetro de error
	        response.sendRedirect("admin/error.html?error=Error al insertar en la base de datos");

	    }
	}

}
		 
	


