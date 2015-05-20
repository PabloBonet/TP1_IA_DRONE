package frsf.ia.tp.libreriaclases;


import java.io.File;
import java.io.IOException;
import java.awt.Point;

import java.util.ArrayList;
import java.util.List;
import frsf.ia.tp.libreriaclases.*;





public class Grafo {
	/** Lista de nodos. */
	public ArrayList<Nodo> listaNodos;

	/** Lista de enlaces. */
	private ArrayList<Enlace> listaEnlaces;

	
	/** Constructor por defecto. */
	public Grafo() {
		super();
		this.listaNodos = new  ArrayList<Nodo>();
		this.listaEnlaces = new ArrayList<Enlace>();
	}
	
	/**
	 * Constructor que asiga una lista de nodos y una lista de enlaces
	 * */
	public Grafo(ArrayList<Nodo> listaNodos, ArrayList<Enlace> listaEnlaces)
	{
		
		super();
		if(listaNodos != null)
		{	
			this.listaNodos = listaNodos;
		}
		else
		{
			this.listaNodos = new  ArrayList<Nodo>();
		}
		
		if(listaEnlaces != null)
		{
			this.listaEnlaces = listaEnlaces;
		}
		else
		{
			this.listaEnlaces = new ArrayList<Enlace>();
		}
	}
	
	public ArrayList<Nodo> getListaNodos()
	{
		return this.listaNodos;
	}
	
	public ArrayList<Enlace> getListaEnlaces()
	{
		return this.listaEnlaces;
	}
	
	
	/**
	 * Valida que no exista un nodo con un id ya asignado.
	 *  En caso de que la validaci�n no pase, retorna false
	 * 
	 * @param id
	 *            El id a validar.
	 *            	 */
	private boolean validarNodoNoRepetido(int id) {
		
		
		for(Nodo n: listaNodos){
			if(n.getId() == id){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Valida que no exista un enlace con esos mismos nodos
	 *  En caso de que la validaci�n no pase, retorna false
	 * 
	 * @param n1
	 *          El nodo 1 a validar.
	 * @param n2 
	 * 			El nodo 2 a validar.
	 *            	 */
	private boolean validarEnlaceNoRepetido(int n1, int n2)
	{
		for(Enlace e: listaEnlaces)
		{
			if(e.getIdNodo1() == n1 && e.getIdNodo2() == n2 || e.getIdNodo1() == n2 && e.getIdNodo2() == n1 )
			{
				return false;
			}
		}
		
		return true;
	}
	

	/**
	 * Crea un nodo al grafo
	 *Valida que el nodo no exista  y si no existe lo agrega al grafo
	 *
	 *@param idNodo
	 *	id del nodo a crear y agregar
	 *
	 *@param posX
	 *	posicionX del nodo
	 *
	 *@param posY
	 *	posicionY del nodo
	 *
	 * **/
	public boolean crearNodo(int idNodo, int posX, int posY)
	{
	
		if(this.validarNodoNoRepetido(idNodo )) //si True entonces no existe y lo crea
		{
			this.listaNodos.add(new Nodo(idNodo, posX, posY));
			return true;
		}
		
		return false;
	}
	
	/**
	 * Crea un enlace al grafo
	 *Valida que el enlace no exista  y si no existe lo agrega al grafo
	 *
	
	 *@param n1
	 *	nodo1 del enlace
	 *
	 *@param n2
	 *	nodo2 del enlace
	 *
	 *@param peso
	 *	peso del enlace
	 *
	 * **/
	public boolean crearEnlace(int n1, int n2, int peso)
	{
	
		if(this.validarEnlaceNoRepetido(n1, n2)) //si True entonces no existe y lo crea
		{
			this.listaEnlaces.add(new Enlace(n1, n2));
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Busca un nodo a partir de su id
	 * 
	 * @param id
	 *            El id del nodo.
	 * @return nodo
	 *  El nodo buscado o null en caso que no exista
	 
	 */
	public Nodo buscarNodo(int id)  {
		
		
		for(Nodo n: listaNodos){
			if(n.getId() == id){
				return n;
			}
		}
		return null;
	
	}
	
	
	/**
	 * Busca un enlace a partir de sus id de nodos
	 * 
	 * @param idNodo1
	 *            El id del nodo1.
	 *            
	 * @param idNodo2
	 *          El id del nodo2.
	 * @return enlace
	 *  El enlace buscado o null en caso que no exista
	 
	 */
	public Enlace buscarEnlace(int idNodo1 , int idNodo2)  {
		
//		Nodo nodo1 = this.buscarNodo(idNodo1);
//		Nodo nodo2 = this.buscarNodo(idNodo2);
		
		if(idNodo1 > 0 && idNodo2 > 0)
		{	
			for(Enlace e: listaEnlaces){
				if((e.getIdNodo1() == idNodo1 || e.getIdNodo1() == idNodo2) && (e.getIdNodo2() == idNodo1 || e.getIdNodo2() == idNodo2)){
				
					return e;
				}
			}
		}
		return null;
	
	}

	/*
	 * M�TODOS EST�TICOS
	 * */
	
	public static ArrayList<Nodo> nodosAdyacentesAPosicion(Point posicionAgente, boolean incluidoActual)
	{
		ArrayList<Nodo> nodosAdyacentes = new ArrayList<Nodo>();
		
		
		
		return nodosAdyacentes;
	}
	
	/**
	 * Para los Ir en el nivel bajo
	 * @param nodoActual
	 * @return
	 */
	public static ArrayList<Nodo> buscarAdyacentes(Nodo nodoActual) { //TODO VER SI RECIBE NODO, POSICION O CUADRANTE ###############################################
		ArrayList<Nodo> adyacentes = new ArrayList<Nodo>();
//		for (int indice = 0; indice < listaEnlaces.size(); indice++)
//			if (listaEnlaces.get(indice).getIdNodo1() == nodoActual.getId()) //AC� S� SE USAN LOS Nodo DE LOS ENLACES
//				adyacentes.add(listaEnlaces.get(indice).getIdNodo1());
//			else if(listaEnlaces.get(indice).getIdNodo2() == nodoActual.getId())
//				adyacentes.add(listaEnlaces.get(indice).getIdNodo2());
		
		//una vez que tengo los nodos adyacentes hay que ver si pertenecen al mismo cuadrante de nodoActual
		return adyacentes;
	}
	
	public  Nodo nodoEnPosicion(Point pos)
	{
		Nodo nodo = null;
		for(Nodo n: listaNodos)
		{
			if(n.getPosX()==pos.x && n.getPosY() == pos.y)
				return n;
		}
		
		return nodo;
	}

	public void setListaEnlaces(ArrayList<Enlace> listaEnlaces) {
		this.listaEnlaces = listaEnlaces;
	}
	
}
