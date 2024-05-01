package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Actividad;
import modelo.Solicitud;

public class DaoSolicitud {

	public static Connection con = null;
	// Singelton
	private static DaoSolicitud instance = null;

	public DaoSolicitud() throws SQLException {

		this.con = DBConexion.getConexion();

	}

	// Singelton
	public static DaoSolicitud getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoSolicitud();
		}
		return instance;

	}

	public void insertar(Solicitud s) throws SQLException {
		// Solo pongo los campos que rellenarán los usuarios externos

		String sql = "INSERT INTO solicitud (dni,cod_actividad,nombre,apellido1,apellido2,email,direccion,telefono,f_nacimiento) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, s.getDni());
		ps.setInt(2, s.getCod_actividad());
		ps.setString(3, s.getNombre());
		ps.setString(4, s.getApellido1());
		ps.setString(5, s.getApellido2());
		ps.setString(6, s.getEmail());
		ps.setString(7, s.getDireccion());
		ps.setInt(8, s.getTelefono());
		ps.setString(9, s.getF_nacimiento());

		int filas = ps.executeUpdate();
		ps.close();

	}

	public Solicitud obtenerPorId(int id) throws SQLException {
		// Aquí ya incluyo todos los campos
		String sql = "SELECT * FROM solicitud WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Solicitud s = new Solicitud(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getInt(11),
				rs.getBoolean(12), rs.getBoolean(13), rs.getString(14));

		return s;
	}

	// Lo pongo private para dar seguridad. Llamaré al ListarJson
	private ArrayList<Solicitud> listar() throws SQLException {
		// Aquí directamente le paso la query
		PreparedStatement ps = con.prepareStatement("SELECT * FROM solicitud");

		ResultSet rs = ps.executeQuery();

		// el array que devuelvo
		ArrayList<Solicitud> result = null;

		// el puntero está en el null del principio y con el next va hasta el del final
		while (rs.next()) {
			// inicializar el result
			if (result == null) {

				result = new ArrayList<Solicitud>();
			}
			// solo defino el tipo y la posición pero también puedo poner el nombre, me guía
			// mejor
			result.add(new Solicitud(rs.getInt("id"), rs.getString("Dni"), rs.getInt("cod_actividad"),
					rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("email"),
					rs.getString("direccion"), rs.getInt("telefono"), rs.getString("f_nacimiento"),
					rs.getInt("num_sorteo"), rs.getBoolean("seleccionado"), rs.getBoolean("pago"), rs.getString("observaciones")));

		}

		return result;

	}

	public String listarJson() throws SQLException {
		// Meto en json lo que me devuelve del método listar
		String json = "";
		// Utilizo esta librería
		Gson gson = new Gson();

		json = gson.toJson(this.listar());

		return json;

	}

	public Solicitud actualizar(Solicitud s) throws SQLException {

		String sql = "UPDATE solicitud SET (Dni,cod_actividad,nombre,apellido1,apellido2,email,direccion,telefono,f_nacimiento,num_sorteo,seleccionado,pago,observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)\" WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, s.getDni());
		ps.setInt(2, s.getCod_actividad());
		ps.setString(3, s.getNombre());
		ps.setString(4, s.getApellido1());
		ps.setString(5, s.getApellido2());
		ps.setString(6, s.getEmail());
		ps.setString(7, s.getDireccion());
		ps.setInt(8, s.getTelefono());
		ps.setString(9, s.getF_nacimiento());
		ps.setInt(10, s.getNum_sorteo());
		ps.setBoolean(11, s.isSeleccionado());
		ps.setBoolean(12, s.isPago());
		ps.setString(13, s.getObservaciones());
		ps.setInt(14, s.getId());// Al final porque es el criterio en el WHERE
		int result = ps.executeUpdate();

		ps.close();
		return s;
	}

}