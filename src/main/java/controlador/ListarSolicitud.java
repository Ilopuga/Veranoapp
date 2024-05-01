package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Solicitud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoSolicitud;




/**
 * Servlet implementation class ListarActividad
 */
@MultipartConfig
public class ListarSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarSolicitud() {
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
			Solicitud s = new Solicitud();
			try {
				s.obtenerPorId(id);
				respuesta.print(s.dameJson());
				System.out.println(s.dameJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(opcion==1) {
			//solo para listar la tabla, todos los registro de la tabla
		    try {
		        String respuestaJson = DaoSolicitud.getInstance().listarJson();
		        respuesta.print(respuestaJson);
		    } catch (SQLException e) {
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
