package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;
import java.util.List;


public class Nodo {
	/** El id del nodo. */
	private int id;
	
	/** El posicion del nodo: posX, posY. */
	private int posX;
	private int posY;
	
	/** Lista de personas ubicadas en el nodo. */
	private List<Persona> personas;

	/**
	 * Constructor que inicializa las propiedades del nodo.
	 * 
	 * @param idNodo
	 *            El id del v�rtice.
	 * @param pX
	 * 			  Posici�n X del nodo
	 * 
	 * @param pY
	 * 			  Posici�n Y del nodo
	 */
	public Nodo(int idNodo, int pX, int pY) {
		super();
		this.id = idNodo;
		this.posX = pX;
		this.posY = pY;
		this.personas = new  ArrayList<Persona>();
	}
}