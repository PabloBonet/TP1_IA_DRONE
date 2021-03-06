package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;

public class AntenaNB extends Antena{

	private ArrayList<Nodo> listaIntensidadSeņal;
	public AntenaNB()
	{
		listaIntensidadSeņal = new ArrayList<Nodo>();
	}
	
	public AntenaNB(ArrayList<Nodo> intensidadSeņal)
	{
		this.listaIntensidadSeņal =  intensidadSeņal;
	}

	@Override
	public void agregarIntensidadSeņal(Object intensidad) {
		listaIntensidadSeņal.add((Nodo) intensidad);
	}

	@Override
	public ArrayList<Nodo> getIntensidadSeņal() {

		return listaIntensidadSeņal;
	}

	
	public void setIntensidadSeņal(ArrayList<Nodo> listaIntensidades) {
	
		this.listaIntensidadSeņal = listaIntensidades;
		
	}

	
	
}
