package frsf.ia.tp.libreriaclases;

import java.awt.Point;
import java.sql.Struct;
import java.util.ArrayList;


public abstract class Antena {

	private ArrayList listaIntensidadSeñal;
	
	public abstract ArrayList getIntensidadSeñal();
	
	
	
	public abstract void agregarIntensidadSeñal(Object intensidad);

	
	
	//private int[] senial;
	/*private ArrayList<int[]> seniales;
	
	public Antena()
	{
		seniales = new ArrayList<int[]>();
		
	}
	
	public void agregarSenial(Point posicion, int intensidad)
	{
		int[] senial = new int[3];
		senial[0]  = posicion.x;
		senial[1] = posicion.y;
		senial[2] = intensidad;
		
		seniales.add(senial);
	}
	
	public ArrayList<int[]> getSeniales()
	{
		return seniales;
	}*/
}
