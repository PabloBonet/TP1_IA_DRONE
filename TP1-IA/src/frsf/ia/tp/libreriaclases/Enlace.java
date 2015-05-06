package frsf.ia.tp.libreriaclases;



public class Enlace {

	/** El nodo1 del enlace. */
	private int id1;

	/** El nodo2 del enlace. */
	private int id2;
	
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
	public Enlace(int id1, int id2) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.peso = 1;
	}
	
	/**
	 * Constructor que inicializa los enlaces.
	 *
	 * 
	 * @param vertice1
	 *            El id del vertice inicial.
	 * @param vertice2
	 *            El ide del vértice final.
	 *            
	 * @param peso
	 * 			  El peso del enlace
	 */
	public Enlace(int id1, int id2, int peso) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.peso = peso;
	}
	
	public int getIdNodo1()
	{
		return this.id1;
	}
	
	public int getIdNodo2()
	{
		return this.id2;
	}
	
	public void setPeso(int peso)
	{
		this.peso = peso;
	}
	
	public int getPeso()
	{
		return this.peso;
	}
}
