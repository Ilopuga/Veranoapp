package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoSolicitud;

public class Solicitud {
	private int id;
	private String dni;
	private int cod_actividad;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String direccion;
	private int telefono;
	private String f_nacimiento;
	private int num_sorteo;
	private boolean seleccionado;
	private boolean pago;
	private String observaciones;

	public Solicitud() {
		
	}

	public Solicitud(int id, String dni, int cod_actividad, String nombre, String apellido1, String apellido2,
			String email, String direccion, int telefono, String f_nacimiento, int num_sorteo, boolean seleccionado,
			boolean pago, String observaciones) {
		super();
		this.id = id;
		this.dni = dni;
		this.cod_actividad = cod_actividad;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.f_nacimiento = f_nacimiento;
		this.num_sorteo = num_sorteo;
		this.seleccionado = seleccionado;
		this.pago = pago;
		this.observaciones = observaciones;
	}

	//Otro sin id para mandar a la BDD
	public Solicitud(String dni, int cod_actividad, String nombre, String apellido1, String apellido2, String email,
			String direccion, int telefono, String f_nacimiento) {
		super();
		this.dni = dni;
		this.cod_actividad = cod_actividad;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.f_nacimiento = f_nacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getCod_actividad() {
		return cod_actividad;
	}

	public void setCod_actividad(int cod_actividad) {
		this.cod_actividad = cod_actividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getF_nacimiento() {
		return f_nacimiento;
	}

	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	public int getNum_sorteo() {
		return num_sorteo;
	}

	public void setNum_sorteo(int num_sorteo) {
		this.num_sorteo = num_sorteo;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	public void insertar() throws SQLException {
		DaoSolicitud.getInstance().insertar(this);
	}
		
	public void obtenerPorId(int id) throws SQLException {

		DaoSolicitud dao = DaoSolicitud.getInstance();
		Solicitud aux = dao.obtenerPorId(id);
	    
	    // Establecer propiedades de la actividad actual con los valores del actividad seleccionada
	    this.setId(aux.getId());
	    this.setDni(aux.getDni());
	    this.setCod_actividad(aux.getCod_actividad());
	    this.setNombre(aux.getNombre());
	    this.setApellido1(aux.getApellido1());
	    this.setApellido2(aux.getApellido2());
	    this.setEmail(aux.getEmail());
	    this.setDireccion(aux.getDireccion());
	    this.setTelefono(aux.getTelefono());
	    this.setF_nacimiento(aux.getF_nacimiento());
	    
	}
	
	public String dameJson() {
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		return json;
		
	}
	
	public void actualizar() throws SQLException {

		DaoSolicitud.getInstance().actualizar(this);
	}
	
	
	

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", dni=" + dni + ", cod_actividad=" + cod_actividad + ", nombre=" + nombre
				+ ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", email=" + email + ", direccion="
				+ direccion + ", telefono=" + telefono + ", f_nacimiento=" + f_nacimiento + ", num_sorteo=" + num_sorteo
				+ ", seleccionado=" + seleccionado + ", pago=" + pago + ", observaciones=" + observaciones + "]";
	}
	
	
	
	
}
