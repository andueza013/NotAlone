package Modelo;

public class Usuario {
	public Usuario(int idusuario, int idrol, String nombre, String telefono, String email, String password_encripted,
			boolean condicion) {
		super();
		this.idusuario = idusuario;
		this.idrol = idrol;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.password_encripted = password_encripted;
		this.condicion = condicion;
	}

	public Usuario() {
		this.idusuario = 0;
		this.idrol = 2;
		this.condicion = true;
		this.email = "";
		this.password_encripted = "";
		this.telefono = "";
		this.nombre = "";
		// TODO Auto-generated constructor stub
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword_encripted() {
		return password_encripted;
	}

	public void setPassword_encripted(String password_encripted) {
		this.password_encripted = password_encripted;
	}

	public boolean isCondicion() {
		return condicion;
	}

	public void setCondicion(boolean condicion) {
		this.condicion = condicion;
	}

	int idusuario;
	int idrol;
	String nombre;
	String telefono;
	String email;
	String password_encripted;
	boolean condicion;
}
