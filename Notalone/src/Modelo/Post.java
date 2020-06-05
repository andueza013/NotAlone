package Modelo;

import java.io.Serializable;

public class Post implements Serializable{
	public Post(int idusuario, String titulo, String contenido, String imagen) {
		super();
		this.idusuario = idusuario;
		this.titulo = titulo;
		this.contenido = contenido;
		this.imagen = imagen;
	}

	public Post() {

		this.idusuario = 0;
		this.titulo = "";
		this.contenido = "";
		this.imagen = "";
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	int idusuario;
	String titulo;
	String contenido;
	String imagen;
}
