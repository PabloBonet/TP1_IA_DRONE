package frsf.ia.tp.libreriaclases;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Converter {
	/** Lista de Strigs que alojara los datos leidos del archivo */
	private List<List<String>> listaDeDatos;
	
	/**Lista auxiliar para imprimir todos los elementos*/
	private List<String> lista;

	/**Lista de Enlaces y Nodos auxiliares*/
	private ArrayList<Nodo> listaNodos;
	private ArrayList<Enlace> listaEnlaces;
	
	/**Variables auxiliares para crear las intancias de los objetos*/
	String categoria;
	
	public Converter(File archivo) throws IOException{
		inicializarConvertidor(archivo);
		listaNodos = new ArrayList<Nodo>();
		listaEnlaces = new ArrayList<Enlace>();
	}
	
	/**Metodo que genera una lista de String donde cada elemento
	   será utilizado para generar las instancias de los Nodos y Enlaces*/
	private void inicializarConvertidor(File archivo) throws IOException{
		
		LectorCsv lector = new LectorCsv(archivo);
		listaDeDatos = lector.leerArchivo();
		
		lista = new ArrayList<String>();
		
		for (int i = 0; i < listaDeDatos.size(); i++) {
			for (int j = 0; j < listaDeDatos.get(i).size(); j++) {
				lista.add(listaDeDatos.get(i).get(j));
			}
		}
	}
	
	public void crearComponentesGrafo(){
		/**Recorro la lista hasta el final y regunto que categoria es. En funcion de la categoria
		 * creo la instancia del objeto correspondiente y luego lo agrego a la lista que corresponde*/
		for (int i = 0; i < listaDeDatos.size(); i++){
			
			categoria = listaDeDatos.get(i).get(0);
			
			if(categoria.equals("nodo")){
				//creo nodo
				//lo agrego a la lista de nodos
			}
			if(categoria.equals("enlace")){
				//creo enlace
				//lo agrego a la ista de enlaces
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
