package frsf.ia.tp.libreriaclases;

import java.awt.Point;
import java.util.ArrayList;


public class Gps {

	//Grafo que contiene los nodos y enlaces para un subcuadrante (esquinas y caminos)
	private Grafo grafoSubCuadrante;
	private Point posiciongps;
	
	public Gps() {
		grafoSubCuadrante = new Grafo();
		posiciongps = new Point();
	}
	
	/**
	 * Constructor
	 * @param grafo grafo s�lo de un cuadrante de nivel bajo 
	 * @param gps posici�n en el mapa
	 */
	public Gps(Grafo grafo, Point gps){
		grafoSubCuadrante = grafo;
		posiciongps = gps;
	}

	public Grafo getGrafoSubCuadrante() {
		return grafoSubCuadrante;
	}

	public void setGrafoSubCuadrante(Grafo grafoSubCuadrante) {
		this.grafoSubCuadrante = grafoSubCuadrante;
	}

	public Point getPosiciongps() {
		return posiciongps;
	}

	public void setPosiciongps(Point posiciongps) {
		
		this.posiciongps = posiciongps;
	}
	
	/**
	 * Carga la parte del grafo correspondiente al subcuadrante en donde esta ubicado el agente
	 * 
	 * @param grafoMapa grafo completo del mapa
	 */
	public void cargarGrafoSubCuadrante(Grafo grafoMapa) {
		int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(posiciongps.x, posiciongps.y);

		ArrayList<Nodo> nodos = grafoMapa.getListaNodos();
		ArrayList<Enlace> enlaces = grafoMapa.getListaEnlaces();
		
		//agrego los nodos que pertenecen al subcuadrante
		for(Nodo n: nodos){
			if(FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY()) == subCuadrante)
			{
				this.grafoSubCuadrante.getListaNodos().add(n);
				System.out.println("Nodo: " +n.getId());
			}
				
		}
		
		//agrego los enlaces que comuniquen s�lo los nodos seleccionados arriba
		for(Enlace e: enlaces){
			if(this.grafoSubCuadrante.buscarNodo(e.getIdNodo1()) != null && this.grafoSubCuadrante.buscarNodo(e.getIdNodo2()) != null)
				this.grafoSubCuadrante.getListaEnlaces().add(e);
		}
		
	}

	/**
	 * Carga la parte del grafo correspondiente al cuadrante en donde esta ubicado el agente (cuadrante 1, 2, 3 o 4)
	 * 
	 * @param grafoMapa
	 */
	public void cargarGrafoCuadrante(Grafo grafoMapa) {
		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(posiciongps.x, posiciongps.y);

		ArrayList<Nodo> nodos = grafoMapa.getListaNodos();
		ArrayList<Enlace> enlaces = grafoMapa.getListaEnlaces();
		
		//agrego los nodos que pertenecen al cuadrante
		for(Nodo n: nodos){
			if(FuncionesAuxiliares.perteneceACuadrante(n.getPosX(), n.getPosY()) == cuadrante)
			{
				this.grafoSubCuadrante.getListaNodos().add(n);
				System.out.println("Nodo: " +n.getId());
			}
				
		}
		
		//agrego los enlaces que comuniquen s�lo los nodos seleccionados arriba
		for(Enlace e: enlaces){
			if(this.grafoSubCuadrante.buscarNodo(e.getIdNodo1()) != null && this.grafoSubCuadrante.buscarNodo(e.getIdNodo2()) != null)
				this.grafoSubCuadrante.getListaEnlaces().add(e);
		}
		
	}

}
