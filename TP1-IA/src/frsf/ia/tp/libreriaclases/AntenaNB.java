package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;

public class AntenaNB extends Antena{

	private ArrayList<Nodo> listaIntensidadSeñal;
	public AntenaNB()
	{
		listaIntensidadSeñal = new ArrayList<Nodo>();
	}
	
	public AntenaNB(ArrayList<Nodo> intensidadSeñal)
	{
		this.listaIntensidadSeñal =  intensidadSeñal;
	}

	@Override
	public void agregarIntensidadSeñal(Object intensidad) {
		listaIntensidadSeñal.add((Nodo) intensidad);
	}

	@Override
	public ArrayList<Nodo> getIntensidadSeñal() {

		return listaIntensidadSeñal;
	}

	
	public void setIntensidadSeñal(ArrayList<Nodo> listaIntensidades) {
	
		this.listaIntensidadSeñal = listaIntensidades;
		
	}

	
	
}
