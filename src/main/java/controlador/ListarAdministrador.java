package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Administrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoActividad;
import dao.DaoAdministrador;




/**
 * Servlet implementation class ListarActividad
 */
@MultipartConfig
public class ListarAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarAdministrador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter respuesta = response.getWriter();
		
		int opcion = Integer.parseInt(request.getParameter("op"));

		
		if(opcion == 2) {
			//proceso logica edicion. El id se lo paso al metodo obtener... y me da el Json de ese registro
			int id = Integer.parseInt(request.getParameter("id"));
			Administrador ad = new Administrador();
			try {
				ad.obtenerPorId(id);
				respuesta.print(ad.dameJson());
				//System.out.println(ad.dameJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(opcion==1) {
			//solo para listar la tabla, todos los registro de la tbla admin
		    try {
		        String respuestaJson = DaoAdministrador.getInstance().listarJson();
		        respuesta.print(respuestaJson);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		} else if (opcion == 3) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DaoAdministrador a = new DaoAdministrador();
				a.borrar(id);
				//System.out.println("Estoy borrando " + id);
				//System.out.println("Estoy opcion " + opcion);
				respuesta.print(a.listarJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
