package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;

public class AntenaNMA extends Antena {

	private ArrayList<NodoLista> listaIntensidadSeņal;
	public AntenaNMA()
	{
		listaIntensidadSeņal = new ArrayList<NodoLista>();
	}
	
	public AntenaNMA(ArrayList<NodoLista> intensidadSeņal)
	{
		this.listaIntensidadSeņal =  intensidadSeņal;
	}

	@Override
	public void agregarIntensidadSeņal(Object intensidad) {
		listaIntensidadSeņal.add((NodoLista) intensidad);
	}

	@Override
	public ArrayList<NodoLista> getIntensidadSeņal() {

		return listaIntensidadSeņal;
	}

	
	public void setIntensidadSeņal(ArrayList<NodoLista> listaIntensidades) {
	
		this.listaIntensidadSeņal = listaIntensidades;
		
	}
}
