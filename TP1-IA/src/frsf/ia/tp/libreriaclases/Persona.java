package frsf.ia.tp.libreriaclases;

public class Persona {

	/** id: numero identificador unico para cada persona*/
	private int id;
	
	/** tipo de persona: 0: victima, 1: victimario*/
	private int tipo;
	
	/** Constructor de clase Persona
	 * @param idP identificador unico de la persona
	 * */
	public Persona(int idP)
	{
		super();
		this.id = idP;
		this.tipo = 0;
	
	}
	
	/**
	 * @param idP identificador unico de la persona
	 * @param tipo indica el tipo de persona:
	 * 				0: victima
	 * 				1: victimario
	 * */
	
	public Persona(int idP, int tipo)
	{
		super();
		this.id = idP;
		this.tipo = tipo;
		
	}
}
