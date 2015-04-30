package frsf.ia.tp.libreriaclases;



public class Enlace {

	/** El nodo1 del enlace. */
	private Nodo nodo1;

	/** El nodo2 del enlace. */
	private Nodo nodo2;
	
	/**Representación del peso del enlace, para este caso podria indicar distancia*/
	private int peso;

	/**
	 * Constructor que inicializa los enlaces.
	 * peso por defecto 1
	 * 
	 * @param vertice1
	 *            El vértice inicial.
	 * @param vertice2
	 *            El vértice final.
	 */
	public Enlace(Nodo nodo1, Nodo nodo2) {
		super();
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
		this.peso = 1;
	}
}
