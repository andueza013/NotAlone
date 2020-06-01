package Modelo;

public class Mensaje {
	public Mensaje(int idusuarioemisor,int idmensaje, String contenido, int idusuarioreceptor) {
		super();
		this.idusuarioemisor = idusuarioemisor;
		this.contenido = contenido;
		this.idusuarioreceptor = idusuarioreceptor;
		this.idmensaje=idmensaje;
	}
 
	public Mensaje() {

		this.idusuarioemisor = 0;
		this.contenido = "";
		this.idusuarioreceptor=0;
		this.idmensaje=0;
	}

	public int getIdusuarioemisor() {
		return idusuarioemisor;
	}

	public void setIdusuarioemisor(int idusuarioemisor) {
		this.idusuarioemisor = idusuarioemisor;
	}
	public int getIdusuarioreceptor() {
		return idusuarioreceptor;
	}

	public void setIdusuarioreceptor(int idusuarioreceptor) {
		this.idusuarioreceptor = idusuarioreceptor;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	

	
	public int getidmensaje() {
		return idmensaje;
	}

	public void setidmensaje(int idmensaje) {
		this.idmensaje = idmensaje;
	}

	int idusuarioemisor;
	int idusuarioreceptor;
	String contenido;
	
	int idmensaje;

}
