package frsf.ia.tp.libreriaclases;

import java.awt.Point;
import java.util.ArrayList;

public class Camara {

	//lista de personas identificadas
	private ArrayList<Persona> personas;
	//lista de personas identificadas como victimarios
	private ArrayList<Persona> victimarios;
	//nodo donde se encuentra la cámara (nodo donde se encuentra el agente)
	private Nodo nodo;
	
	public Camara()
	{
		personas = new ArrayList<Persona>();
		victimarios = new ArrayList<Persona>();
		nodo = null;
	
	}
	
	public Camara(ArrayList<Persona> p, Nodo nodo)
	{
		personas = p;
		victimarios = new ArrayList<Persona>();
		this.nodo = nodo;
		
		//Identificar los victimarios 
		identificarVictimarios();
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	/*public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	*/
	public ArrayList<Persona> getVictimarios()
	{
		
		return victimarios;
	}
	
	public ArrayList<Persona> getVictimas()
	{
		ArrayList<Persona> victimas = new ArrayList<Persona>();
		
		for(Persona p: personas)
		{
			if(p.esVictima())
				victimas.add(p);
		}
		
		return victimas;
	}
	
	/**
	 * Método que identifica a las personas victimarias
	 * 
	 * */
	private void identificarVictimarios()
	{
		
		for(Persona p : nodo.getPersonas())
		{
			
			if(esVictimario(p))
			{
				p.setTipo(1);
				victimarios.add(p);
			}else
				p.setTipo(0);
		}
	}
	
	/**
	 * Función que identifica si la persona p es victimario
	 * 
	 * @param p
	 * 		Persona a analizar
	 * */
	private boolean esVictimario(Persona p)
	{
		boolean r = false;
		
		
		
		/*CÓDIGO PARA IDENTIFICAR SI UNA PERSONA ES O NO VICTIMARIO
		 * 
		 * (SE VA A REALIZAR EN LA SEGUNDA PARTE DEL TP)
		 * 
		 * */
		
		return r;
	}
}
