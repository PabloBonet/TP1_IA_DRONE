package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;
import java.util.List;



public class Grafo {
	/** Lista de nodos. */
	private List<Nodo> listaNodos;

	/** Lista de enlaces. */
	private List<Enlace> listaEnlaces;

	/** Constructor por defecto. */
	public Grafo() {
		super();
		this.listaNodos = new  ArrayList<Nodo>();
		this.listaEnlaces = new ArrayList<Enlace>();
	}
}
