package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Actividad;

public class DaoActividad {

	public static Connection con = null;
	// Singelton
	private static DaoActividad instance = null;

	public DaoActividad() throws SQLException {

		this.con = DBConexion.getConexion();

	}
	
	// Singelton
	public static DaoActividad getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoActividad();
		}
		return instance;

	}

	public void insertar(Actividad a) throws SQLException {
		// Inserto en la bdd con esas variables y luego hago la conexión con esa query
		String sql = "INSERT INTO actividad (nombreA,lugar,tema,descripcion,imagen,f_inicio,f_fin,e_min,e_max,plazas) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, a.getNombreA());
		ps.setString(2, a.getLugar());
		ps.setString(3, a.getTema());
		ps.setString(4, a.getDescripcion());
		ps.setString(5, a.getImagen());
		ps.setString(6, a.getF_inicio());
		ps.setString(7, a.getF_fin());
		ps.setInt(8, a.getE_min());
		ps.setInt(9, a.getE_max());
		ps.setInt(10, a.getPlazas());

		int filas = ps.executeUpdate();
		ps.close();

	}
	
	public Actividad obtenerPorCod_actividad(int cod_actividad) throws SQLException {
	
			String sql = "SELECT * FROM actividad WHERE cod_actividad=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod_actividad);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Actividad a = new Actividad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));

			return a;
		}	
		
	
	
	//Lo pongo private para dar seguridad. Llamaré al ListarJson
	private ArrayList<Actividad> listar() throws SQLException {
		// Aquí directamente le paso la query
		PreparedStatement ps = con.prepareStatement("SELECT * FROM actividad");

		ResultSet rs = ps.executeQuery();

		// el array que devuelvo
		ArrayList<Actividad> result = null;

		// el puntero está en el null del principio y con el next va hasta el del final
		while (rs.next()) {
			// inicializar el result
			if (result == null) {

				result = new ArrayList<Actividad>();
			}
			// solo defino el tipo y la posición pero también puedo poner el nombre, me guía mejor
			result.add(new Actividad(rs.getInt("cod_actividad"), rs.getString("nombreA"), rs.getString("lugar"),
					rs.getString("tema"), rs.getString("descripcion"), rs.getString("imagen"), rs.getString("f_inicio"),
					rs.getString("f_fin"), rs.getInt("e_min"), rs.getInt("e_max"), rs.getInt("plazas")));

		}

		return result;

	}

	public String listarJson() throws SQLException {
		//Meto en json lo que me devuelve del método listar
		String json = "";
		// Utilizo esta librería
		Gson gson = new Gson();

		json = gson.toJson(this.listar());

		return json;

	}
	
	public Actividad actualizar(Actividad a) throws SQLException {
		
		String sql = "UPDATE actividad SET nombreA = ?, lugar = ?, tema = ?, descripcion = ?, imagen = ?, f_inicio = ?, f_fin = ?, e_min = ?, e_max = ?, plazas = ? WHERE cod_actividad = ?";
		
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, a.getNombreA());
		ps.setString(2, a.getLugar());
		ps.setString(3, a.getTema());
		ps.setString(4, a.getDescripcion());
		ps.setString(5, a.getImagen());
		ps.setString(6, a.getF_inicio());
		ps.setString(7, a.getF_fin());
		ps.setInt(8, a.getE_min());
		ps.setInt(9, a.getE_max());
		ps.setInt(10, a.getPlazas());
		ps.setInt(11, a.getCod_actividad()); // Al final porque es el criterio en el WHERE

	    int result = ps.executeUpdate();

	    ps.close();
	    return a;
	}
	
public void borrar(int cod_actividad) throws SQLException {
		
		String sql = "DELETE FROM actividad WHERE cod_actividad=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,cod_actividad);
		int filas = ps.executeUpdate();
		ps.close();
	}

}
