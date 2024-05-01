package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Administrador;

public class DaoAdministrador {

	public static Connection con = null;
	// Singelton
	private static DaoAdministrador instance = null;

	public DaoAdministrador() throws SQLException {

		this.con = DBConexion.getConexion();

	}
	
	// Singelton
	public static DaoAdministrador getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoAdministrador();
		}
		return instance;

	}

	public void insertar(Administrador ad) throws SQLException {
		// Inserto en la bdd con esas variables y luego hago la conexión con esa query
		String sql = "INSERT INTO admin (usuario,pass) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, ad.getUsuario());
		ps.setString(2, ad.getPass());

		int filas = ps.executeUpdate();
		ps.close();

	}

	public Administrador obtenerPorId(int id) throws SQLException {

		String sql = "SELECT * FROM admin WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Administrador ad = new Administrador(rs.getInt(1), rs.getString(2), rs.getString(3));

		return ad;
	}

	// Lo pongo private para dar seguridad. Llamaré al ListarJson
	private ArrayList<Administrador> listar() throws SQLException {
		// Aquí directamente le paso la query
		PreparedStatement ps = con.prepareStatement("SELECT * FROM admin");

		ResultSet rs = ps.executeQuery();

		// el array que devuelvo
		ArrayList<Administrador> result = null;

		// el puntero está en el null del principio y con el next va hasta el del final
		while (rs.next()) {
			// inicializar el result
			if (result == null) {

				result = new ArrayList<Administrador>();
			}
			// solo defino el tipo y la posición pero también puedo poner el nombre, me guía
			// mejor
			result.add(new Administrador(rs.getInt("id"), rs.getString("usuario"), rs.getString("pass")));

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

public Administrador actualizar(Administrador a) throws SQLException {
		
		String sql = "UPDATE admin SET usuario = ?, pass = ? WHERE id = ?";
		
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, a.getUsuario());
		ps.setString(2, a.getPass());
		ps.setInt(3, a.getId()); // Al final porque es el criterio en el WHERE

	    int result = ps.executeUpdate();

	    ps.close();
	    return a;
	}
	
public void borrar(int id) throws SQLException {
		
		String sql = "DELETE FROM admin WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		int filas = ps.executeUpdate();
		ps.close();
	}

}

