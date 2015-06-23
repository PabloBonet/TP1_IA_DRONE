package frsf.ia.tp.libreriaclases;

public class NodoLista {

	//N�mero de cuadrante. Para nivel alto: 1, 2, 3, 4. Para nivel medio: 11 (cuadrante 1 subcuadrante 1), 
	//12 (cuadrante 1 subcuadrante 2), 13, 14, 21, 22, ..., 31, 32, ..., 43, 44.
	private int cuadrante;
	
	//La intensidad de se�al es de acuerdo a la cantidad de personas que se encuantran en ese cuadrante, 
	//subcuadrante o esquina entre calles. 
	private int intensidad;
	
	//Variable que refleja que el agente ya pas� por este nodo.
	private boolean visitado;
	
	
	public NodoLista()
	{
		super();
	}
	public NodoLista(int cuadrante, int intensidad, boolean visitado)
	{
		super();
		this.cuadrante = cuadrante;
		this.intensidad = intensidad;
		this.visitado = visitado;
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
