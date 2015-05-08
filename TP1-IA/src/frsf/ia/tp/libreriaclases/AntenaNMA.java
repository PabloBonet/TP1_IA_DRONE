package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;

public class AntenaNMA extends Antena {

	private ArrayList<NodoLista> listaIntensidadSe�al;
	public AntenaNMA()
	{
		listaIntensidadSe�al = new ArrayList<NodoLista>();
	}
	
	public AntenaNMA(ArrayList<NodoLista> intensidadSe�al)
	{
		this.listaIntensidadSe�al =  intensidadSe�al;
	}

	@Override
	public void agregarIntensidadSe�al(Object intensidad) {
		listaIntensidadSe�al.add((NodoLista) intensidad);
	}

	@Override
	public ArrayList<NodoLista> getIntensidadSe�al() {

		return listaIntensidadSe�al;
	}

	
	public void setIntensidadSe�al(ArrayList<NodoLista> listaIntensidades) {
	
		this.listaIntensidadSe�al = listaIntensidades;
		
	}
}
