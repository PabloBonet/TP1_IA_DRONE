package frsf.ia.tp.libreriaclases;

public class NodoLista {

	private int cuadrante;
	private int intensidad;
	private boolean visitado;
	
	public NodoLista(int cuadrante, int intensidad)
	{
		super();
		this.cuadrante = cuadrante;
		this.intensidad = intensidad;
		this.visitado = false;
	}

	public boolean getVisitado()
	{
		return visitado;
	}
	
	public void visitar()
	{
		visitado = true;
	}
	public int getCuadrante() {
		return cuadrante;
	}

	public void setCuadrante(int cuadrante) {
		this.cuadrante = cuadrante;
	}

	public int getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(int intensidad) {
		this.intensidad = intensidad;
	}
	
	
}
