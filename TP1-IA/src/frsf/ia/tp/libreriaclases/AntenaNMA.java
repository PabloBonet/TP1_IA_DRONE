package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;

public class AntenaNMA extends Antena {

	private ArrayList<NodoLista> listaIntensidadSeñal;
	public AntenaNMA()
	{
		listaIntensidadSeñal = new ArrayList<NodoLista>();
	}
	
	public AntenaNMA(ArrayList<NodoLista> intensidadSeñal)
	{
		this.listaIntensidadSeñal =  intensidadSeñal;
	}

	@Override
	public void agregarIntensidadSeñal(Object intensidad) {
		listaIntensidadSeñal.add((NodoLista) intensidad);
	}

	@Override
	public ArrayList<NodoLista> getIntensidadSeñal() {

		return listaIntensidadSeñal;
	}

	
	public void setIntensidadSeñal(ArrayList<NodoLista> listaIntensidades) {
	
		this.listaIntensidadSeñal = listaIntensidades;
		
	}
}
