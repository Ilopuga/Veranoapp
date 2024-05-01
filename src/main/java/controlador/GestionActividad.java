package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Actividad;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import dao.DaoActividad;

/**
 * Servlet implementation class GestionActividad
 */
@MultipartConfig
public class GestionActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Indico la carpeta en la que voy a guardar los archivos
	// CUANDO ESTE EN EL SERVIDOR private String pathFiles = "/files";
	private String pathFiles = "C:\\Users\\ilopu\\eclipse-workspace\\Verano\\src\\main\\webapp\\files";
	private File uploads = new File(pathFiles);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionActividad() {
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
		String nombreA = request.getParameter("nombreA");
		String lugar = request.getParameter("lugar");
		String tema = request.getParameter("tema");
		String descripcion = request.getParameter("descripcion");
		String imagen = request.getParameter("imagen");
		String f_inicio = request.getParameter("f_inicio");
		String f_fin = request.getParameter("f_fin");
		int e_min = Integer.parseInt(request.getParameter("e_min"));
		int e_max = Integer.parseInt(request.getParameter("e_max"));
		int plazas = Integer.parseInt(request.getParameter("plazas"));
		String cod_actividad = request.getParameter("cod_actividad");

		// Recupero el archivo desde el HTML. Contenido
		Part part = request.getPart("imagen");
		// Obtengo la ruta/nombre del archivo. Contenedor
		Path path = Paths.get(part.getSubmittedFileName());
		// Para llevar a la BDD, el nombre del archivo y ruta (contenido y continente)
		// que le hemos indicado
		String fileName = path.getFileName().toString();

		// Ya tengo la lectura en el origen

		// Camino para enviar los datos. Buffer
		InputStream input = part.getInputStream();
		// Creo el contenedor, donde voy a guardar los datos
		File file = new File(uploads, fileName);
		// Copio los datos del archivo dentro del contenedor utilizando el buffer creado
		// en el input
		// (HTML,contenedor.método). Añado try no ha copiado el archivo
		try {
			Files.copy(input, file.toPath());
		} catch (Exception e) {
			e.printStackTrace();
			/*response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{\"error\": \"Error al subir la foto. Contacte con el administrador.\"}");
			response.sendRedirect("admin/add_actividad.html");*/
		}

		// ¡¡¡CUIDADO!!!
		// Aquí no ponemos imagen (nombre de la variable)
		// ponemos fileName(la variable que contiene el nombre de la foto)
		// De no ser así error BDD

		Actividad a;
		try {
			a = new Actividad(nombreA, lugar, tema, descripcion, fileName, f_inicio, f_fin, e_min, e_max, plazas);
			if (cod_actividad == null || cod_actividad.trim().isEmpty()) {
				DaoActividad dao = new DaoActividad();
				dao.insertar(a);

			} else {
				int idInt = Integer.parseInt(cod_actividad);
				a.setCod_actividad(idInt);
				a.actualizar();

			}
			response.sendRedirect("admin/add_actividad.html");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Redirige a add_actividad con un parámetro de error
			response.sendRedirect("admin/error.html?error=Error al insertar en la base de datos");
		}

	}
}
