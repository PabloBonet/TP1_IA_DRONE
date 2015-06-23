package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;

public class Camara {

	//lista de personas identificadas como victimarios
	private ArrayList<Persona> personas;

	//nodo donde se encuentra la c�mara (nodo donde se encuentra el agente)
	private Nodo nodo;
	
	public Camara()
	{
		personas = new ArrayList<Persona>();
		nodo = null;
	}
	
	/**
	 * Constructor
	 * @param p
	 * 		lista de personas que la c�mara puede detectar
	 * @param nodo
	 * 		nodo en el que se encuentra el agente con su c�mara
	 * */
	public Camara(ArrayList<Persona> p, Nodo nodo)
	{
		personas = p;
		this.nodo = nodo;
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	
	public ArrayList<Persona> getPersonasEnLugar()
	{
		return (ArrayList<Persona>) nodo.getPersonas();
	}
	
}
