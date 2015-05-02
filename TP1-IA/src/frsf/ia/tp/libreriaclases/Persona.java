package frsf.ia.tp.libreriaclases;

public class Persona {

	/** id: numero identificador unico para cada persona*/
	private int id;
	
	/** tipo de persona: 0: victima, 1: victimario*/
	private int tipo;
	
	/** Constructor de clase Persona
	 * @param idP identificador unico de la persona
	 * por defecto la persona es victima (tipo = 0)
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
	
	public int getId()
	{
		return this.id;
	}
	
	public int getTipo()
	{
		return this.tipo;
	}
	
	public void setTipo(int t)
	{
		this.tipo = t;
	}
	
	/**
	 * Retorna True si la persona es un victimario
	 * */
	public boolean esVictimario()
	{
		if(tipo == 1)
			return true;
		
		
			return false;
	}
	/**
	 * Retorna True si la persona es una victima
	 * */
	public boolean esVictima()
	{
		
		if(tipo == 0)
			return true;

		return false;
	
	}
	
}
