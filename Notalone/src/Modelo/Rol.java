package Modelo;

public class Rol {
	public Rol(int idrol, String nombre, String descripcion, boolean condicion) {
		super();
		this.idrol = idrol;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.condicion = condicion;
	}

	public Rol() {

		this.idrol = 0;
		this.nombre = "";
		this.descripcion = "";
		this.condicion = true;
	}

	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isCondicion() {
		return condicion;
	}

	public void setCondicion(boolean condicion) {
		this.condicion = condicion;
	}

	int idrol;
	String nombre;
	String descripcion;
	boolean condicion;
}
