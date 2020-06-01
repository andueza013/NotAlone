package Modelo;

public class Conversacion {
	public Conversacion(int idusuarioa, int idusuariob) {
		super();
		this.idusuarioa = idusuarioa;
		this.idusuariob = idusuariob;
	}

	public Conversacion() {

		this.idusuarioa = 0;
		this.idusuariob = 0;
	}

	public int getIdusuarioa() {
		return idusuarioa;
	}

	public void setIdusuarioa(int idusuarioa) {
		this.idusuarioa = idusuarioa;
	}

	public int getIdusuariob() {
		return idusuariob;
	}

	public void setIdusuariob(int idusuariob) {
		this.idusuariob = idusuariob;
	}

	int idusuarioa;
	int idusuariob;
}
