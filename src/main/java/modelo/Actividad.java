package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoActividad;


public class Actividad {
	private int cod_actividad;
	private String nombreA;
	private String lugar;
	private String tema;
	private String descripcion;
	private String imagen;
	private String f_inicio;
	private String f_fin;
	private int e_min;
	private int e_max;
	private int plazas;

	public Actividad() {

	}


	public Actividad(int cod_actividad, String nombreA, String lugar, String tema, String descripcion, String imagen, String f_inicio,
			String f_fin, int e_min, int e_max, int plazas) {
		this.cod_actividad = cod_actividad;
		this.nombreA = nombreA;
		this.lugar = lugar;
		this.tema = tema;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		this.e_min = e_min;
		this.e_max = e_max;
		this.plazas = plazas;
	}
	
	
	//Otro sin Cod_actividad para mandar a la BDD
	public Actividad(String nombreA, String lugar, String tema, String descripcion, String imagen, String f_inicio,
			String f_fin, int e_min, int e_max, int plazas) {
		super();
		this.nombreA = nombreA;
		this.lugar = lugar;
		this.tema = tema;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		this.e_min = e_min;
		this.e_max = e_max;
		this.plazas = plazas;
	}
	
	//Para consulta actividad
	public Actividad(int cod_actividad, String nombreA) {
		super();
		this.cod_actividad = cod_actividad;
		this.nombreA = nombreA;
	}
	
	//Empiezan los Getters y Setters

	public int getCod_actividad() {
		return cod_actividad;
	}

	public void setCod_actividad(int cod_actividad) {
		this.cod_actividad = cod_actividad;
	}

	public String getNombreA() {
		return nombreA;
	}

	public void setNombreA(String nombreA) {
		this.nombreA = nombreA;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getF_inicio() {
		return f_inicio;
	}

	public void setF_inicio(String f_inicio) {
		this.f_inicio = f_inicio;
	}

	public String getF_fin() {
		return f_fin;
	}

	public void setF_fin(String f_fin) {
		this.f_fin = f_fin;
	}

	public int getE_min() {
		return e_min;
	}

	public void setE_min(int e_min) {
		this.e_min = e_min;
	}

	public int getE_max() {
		return e_max;
	}

	public void setE_max(int e_max) {
		this.e_max = e_max;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int n_plazas) {
		this.plazas = n_plazas;
	}

	public void insertar() throws SQLException {

		/*DaoActividad dao = new DaoActividad();
		dao.Insertar(this);*/
		//SINGELTON. Llamo al método estático getInstance y desde ahí ya tendría disponible ().todos los métodos
		DaoActividad.getInstance().insertar(this);
	}
	
	public void obtenerPorCod_actividad(int cod_actividad) throws SQLException {

		DaoActividad dao = DaoActividad.getInstance();
		Actividad aux = dao.obtenerPorCod_actividad(cod_actividad);
	    
	    // Establecer propiedades de la actividad actual con los valores del actividad seleccionada
	    this.setCod_actividad(aux.getCod_actividad());
	    this.setNombreA(aux.getNombreA());
	    this.setLugar(aux.getLugar());
	    this.setTema(aux.getTema());
	    this.setDescripcion(aux.getDescripcion());
	    this.setImagen(aux.getImagen());
	    this.setF_inicio(aux.getF_inicio());
	    this.setF_fin(aux.getF_fin());
	    this.setE_min(aux.getE_min());
	    this.setE_max(aux.getE_max());
	    this.setPlazas(aux.getPlazas());
	    
	}
	
	public String dameJson() {
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		return json;
		
	}
	
    public void actualizar() throws SQLException {
        DaoActividad.getInstance().actualizar(this);
    }

	
	public void borrar(int cod_actividad) throws SQLException {
		DaoActividad.getInstance().borrar(cod_actividad);
	}

	@Override
	public String toString() {
		return "Actividad [cod_actividad=" + cod_actividad + ", nombreA=" + nombreA + ", lugar=" + lugar + ", tema="
				+ tema + ", descripcion=" + descripcion + ", imagen=" + imagen + ", f_inicio=" + f_inicio + ", f_fin="
				+ f_fin + ", e_min=" + e_min + ", e_max=" + e_max + ", plazas=" + plazas + "]";
	}



}
