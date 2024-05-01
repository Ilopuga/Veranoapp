package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Actividad;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoActividad;

/**
 * Servlet implementation class ListarActividad
 */
@MultipartConfig
public class ListarActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarActividad() {
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

		PrintWriter respuesta = response.getWriter();

		int opcion = Integer.parseInt(request.getParameter("op"));

		if (opcion == 2) {
			// proceso logica edicion. El cód_ac se lo paso al metodo obtener... y me da el
			// Json de ese registro
			int cod_actividad = Integer.parseInt(request.getParameter("cod_actividad"));
			Actividad a = new Actividad();
			try {
				a.obtenerPorCod_actividad(cod_actividad);
				respuesta.print(a.dameJson());
				//System.out.println(a.dameJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion == 1) {
			// solo para listar la tabla, todos los registro de la tbla actividad
			try {
				String respuestaJson = DaoActividad.getInstance().listarJson();
				respuesta.print(respuestaJson);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (opcion == 3) {
			try {
				int cod_actividad = Integer.parseInt(request.getParameter("cod_actividad"));
				DaoActividad a = new DaoActividad();
				a.borrar(cod_actividad);
				//System.out.println("Estoy borrando " + cod_actividad);
				//System.out.println("Estoy opcion " + opcion);
				respuesta.print(a.listarJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
