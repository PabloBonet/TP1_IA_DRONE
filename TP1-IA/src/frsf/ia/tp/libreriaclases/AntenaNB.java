package frsf.ia.tp.libreriaclases;

import java.util.ArrayList;

public class AntenaNB extends Antena{

	private ArrayList<Nodo> listaIntensidadSe�al;
	public AntenaNB()
	{
		listaIntensidadSe�al = new ArrayList<Nodo>();
	}
	
	public AntenaNB(ArrayList<Nodo> intensidadSe�al)
	{
		this.listaIntensidadSe�al =  intensidadSe�al;
	}

	@Override
	public void agregarIntensidadSe�al(Object intensidad) {
		listaIntensidadSe�al.add((Nodo) intensidad);
	}

	@Override
	public ArrayList<Nodo> getIntensidadSe�al() {

		return listaIntensidadSe�al;
	}

	
	public void setIntensidadSe�al(ArrayList<Nodo> listaIntensidades) {
	
		this.listaIntensidadSe�al = listaIntensidades;
		
	}

	
	
}
