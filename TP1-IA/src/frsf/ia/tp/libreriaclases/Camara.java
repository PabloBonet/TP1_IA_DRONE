package frsf.ia.tp.libreriaclases;

import java.awt.Point;
import java.util.ArrayList;

public class Camara {

	//lista de personas identificadas
	private ArrayList<Persona> personas;
	//lista de personas identificadas como victimarios
	//private ArrayList<Persona> victimarios;
	//nodo donde se encuentra la cámara (nodo donde se encuentra el agente)
	private Nodo nodo;
	
	public Camara()
	{
		personas = new ArrayList<Persona>();
	//	victimarios = new ArrayList<Persona>();
		nodo = null;
	
	}
	
	/**
	 * Contructor
	 * @param p
	 * 		lista de personas que la cámara puede detectar
	 * @param nodo
	 * 		nodo en el que se encuentra el agente con su cámara
	 * */
	public Camara(ArrayList<Persona> p, Nodo nodo)
	{
		personas = p;
	//	victimarios = new ArrayList<Persona>();
		this.nodo = nodo;
		
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	
	public ArrayList<Persona> getPersonasEnLugar()
	{
		return (ArrayList<Persona>) nodo.getPersonas();
	}

	/*public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	*/
	/*public ArrayList<Persona> getVictimarios()
	{
		
		return victimarios;
	}*/
	
	/*public ArrayList<Persona> getVictimas()
	{
		ArrayList<Persona> victimas = new ArrayList<Persona>();
		
		for(Persona p: personas)
		{
			if(p.esVictima())
				victimas.add(p);
		}
		
		return victimas;
	}*/
	
	
}
