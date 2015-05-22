package frsf.ia.tp.libreriaclases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverterEscenario {
	/** Lista de Strigs que alojara los datos leidos del archivo */
	private List<List<String>> listaDeDatos;

	/**Lista auxiliar para imprimir todos los elementos*/
	private List<String> lista = new ArrayList<String>();
	
	private ArrayList<Nodo> listaNodos ;
	
	public ConverterEscenario(File archivo) throws IOException
	{
		listaNodos = new ArrayList<Nodo>();
		inicializarConvertidor(archivo);
		
		crearNodos();
	}
	
	private void inicializarConvertidor(File archivo) throws IOException{
	
		LectorCsv lector = new LectorCsv(archivo);
		listaDeDatos = lector.leerArchivo();

		for (int i = 0; i < listaDeDatos.size(); i++) {
			for (int j = 0; j < listaDeDatos.get(i).size(); j++) {
				lista.add(listaDeDatos.get(i).get(j));
			}
		}
		
	}
	
	/**
	 * Crea nodos sin importar las posiciones, es solo para utilizar el id y las personas
	 * */
	public void crearNodos()
	{
		for(List<String> renglon: listaDeDatos)
		{
			Persona persona = new Persona(Integer.parseInt(renglon.get(1)), Integer.parseInt(renglon.get(2))); //crea la persona
			
			Nodo nodo = new Nodo(Integer.parseInt(renglon.get(0)), 0, 0); //crea el nodo con id en posicion 1
			
			nodo.agregarPersona(persona);
			listaNodos.add(nodo);
		}
		
	}
	
	public ArrayList<Nodo> getListaNodos()
	{
		return listaNodos;
	}
}
