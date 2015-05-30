package frsf.ia.tp.libreriaclases;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Converter {
	/** Lista de Strigs que alojara los datos leidos del archivo */
	private List<List<String>> listaDeDatos;
	
	/**Lista auxiliar para imprimir todos los elementos*/
	private List<String> lista = new ArrayList<String>();

	/**Lista de Enlaces y Nodos auxiliares*/
	private ArrayList<Nodo> listaNodos;
	private ArrayList<Enlace> listaEnlaces;

	
	/**Variables auxiliares para crear las intancias de los objetos*/
	String categoria;
	
	public Converter(File archivo) throws IOException{
		listaNodos = new ArrayList<Nodo>();
		listaEnlaces = new ArrayList<Enlace>();
		
		inicializarConvertidor(archivo);
		crearComponentesGrafo();
		
	}
	
	/**Metodo que genera una lista de String donde cada elemento
	   será utilizado para generar las instancias de los Nodos y Enlaces*/
	private void inicializarConvertidor(File archivo) throws IOException{
		
		LectorCsv lector = new LectorCsv(archivo);
		listaDeDatos = lector.leerArchivo();
		
		for (int i = 0; i < listaDeDatos.size(); i++) {
			for (int j = 0; j < listaDeDatos.get(i).size(); j++) {
				lista.add(listaDeDatos.get(i).get(j));
			}
		}

	}
	
	private void crearComponentesGrafo(){
		/**Recorro la lista hasta el final y pregunto qué categoría es. En función de la categoría
		 * creo la instancia del objeto correspondiente y luego lo agrego a la lista que corresponde*/
		for (int i = 0; i < listaDeDatos.size(); i++){
			categoria = listaDeDatos.get(i).get(0);		
			if(categoria.equals("Nodo")){
				// Creo una instancia de Nodo y lo agrego a la lista de nodos
				Nodo nodo = new Nodo(Integer.parseInt(listaDeDatos.get(i).get(1)),
									 Integer.parseInt(listaDeDatos.get(i).get(2)),
									 Integer.parseInt(listaDeDatos.get(i).get(3)));
				//lo agrego a la lista de nodos
				listaNodos.add(nodo);
			}
			if(categoria.equals("Enlace") || categoria.equals("enlace")){
				//creo una instancia de Enlace y lo agrego a la lista de enlaces
				Enlace enlace = new Enlace(Integer.parseInt(listaDeDatos.get(i).get(1)),
										   Integer.parseInt(listaDeDatos.get(i).get(2)),
										   Integer.parseInt(listaDeDatos.get(i).get(3)));
				//lo agrego a la ista de enlaces
				listaEnlaces.add(enlace);
			}
		}
	}


	public ArrayList<Nodo> getListaNodos() {
		return listaNodos;
	}

	public ArrayList<Enlace> getListaEnlaces() {
		return listaEnlaces;
	}
	
	public List<List<String>> getListaDeDatos() {
		return listaDeDatos;
	}

	public List<String> getLista() {
		return lista;
	}

}
