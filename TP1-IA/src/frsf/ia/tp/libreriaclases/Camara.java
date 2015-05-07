package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;

public class Camara {

	//lista de personas identificadas
	private ArrayList<Persona> personas;
	private ArrayList<Persona> victimarios;

	public Camara()
	{
		personas = new ArrayList<Persona>();
		victimarios = new ArrayList<Persona>();
	}
	
	public Camara(ArrayList<Persona> p)
	{
		personas = p;
		victimarios = new ArrayList<Persona>();
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	
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
		for(Persona p : personas)
		{
			if(esVictimario(p))
				p.setTipo(1);
			else
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
		 * */
		
		return r;
	}
}
