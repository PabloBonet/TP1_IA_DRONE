package frsf.cidisi.exercise.search;


import java.awt.Point;
import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.*;

/**
 * This class represents the real world state.
 */
public class StateMap extends EnvironmentState {
	
	private ArrayList<NodoLista> intensidadSeñalA;
    private ArrayList<NodoLista> intensidadSeñalM;
    private ArrayList<Nodo> intensidadSeñalB;
    private Grafo grafoMapa;
    private int energiaAgente;
    private Point posicionAgente;
    private String alturaAgente;
	
    public StateMap(Grafo grafo) {
        
    	intensidadSeñalA = new ArrayList<NodoLista>();
		intensidadSeñalM = new ArrayList<NodoLista>();
		intensidadSeñalB = new ArrayList<Nodo>();
		grafoMapa = grafo;
		posicionAgente = new Point();
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {
    	
        this.energiaAgente = 1000;
        this.posicionAgente.setLocation(150, 150);
        this.alturaAgente  = "A";
       
        inicializarListasSeñales();
    }

    /**
     * Inicializa la lista de intensidad de señales
     */
    private void inicializarListasSeñales() {
        /*pregunta a que subcuadrante pertenece
         * y si éste existe en la lista correspondiente, suma la intensidad
         * sino agrega ese cuadrante en la lista correspondiente
         */
        
    	
    	for(Nodo n : this.getgrafoMapa().getListaNodos())
    	{
    		int intensidad = 0;
    		boolean existe = false;
    		if(n.getPersonas().size() > 0) //Si el nodo tiene personas (Si tiene personas estas producen señales)
    		{
    			Point ubicacion = new Point();
    			ubicacion.x = n.getPosX();
    			ubicacion.y = n.getPosY();
    			Nodo nodoNuevo=new Nodo(n.getId(), n.getPosX(), n.getPosY(), n.getVisitado());

    				for(Persona p: n.getPersonas())
    					nodoNuevo.agregarPersona(new Persona(p.getId(), p.getTipo()));
    				intensidadSeñalB.add(nodoNuevo);

    			int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(ubicacion.x, ubicacion.y);
    			int cuadrante = FuncionesAuxiliares.perteneceACuadrante(ubicacion.x, ubicacion.y);

    			/*
    			 * Tratamiento para la lista intensidadSeñalM
    			 */
    			for(NodoLista subCuad: intensidadSeñalM)
    			{
    				if(subCuad.getCuadrante() == subCuadrante)
    				{
    					intensidad = subCuad.getIntensidad();
    					
    					intensidad += (n.getPersonas().size()*20);
    					subCuad.setIntensidad(intensidad);
    					existe = true;
    					break;
    					
    				}
    			}
    			if(!existe)
    			{
    				//crea un nodo para el subCuadrante y lo agrega a la lista de nivel medio
    				intensidad = n.getPersonas().size()*20; 
    				NodoLista nodo = new NodoLista(subCuadrante, intensidad, n.getVisitado());
    				intensidadSeñalM.add(nodo);
    			}
    			
    			existe = false;
    			intensidad = 0;

    			/*
    			 * Tratamiento para la lista intensidadSeñalA
    			 */

    			//Busca en la lista intensidadSeñalA si el cuadrante ya se encuentra 

    			for(NodoLista nodo : intensidadSeñalA)
    			{
    				if(nodo.getCuadrante() == cuadrante){
    					//adiciona a la intensidad de señal del cuadrante ya existente
    					intensidad = n.getPersonas().size()*10;
    					nodo.setIntensidad(nodo.getIntensidad()+intensidad);
    					existe = true;
    					break;
    				}
    			}

    			if(!existe)
    			{
    				//crea un nodo para el cuadrante y lo agrega a la lista de nivel alto
    				intensidad = n.getPersonas().size()*10; 
    				NodoLista nodo = new NodoLista(cuadrante, intensidad, n.getVisitado());
    				intensidadSeñalA.add(nodo);
    			}
    		}
    	}

    }

	/**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "-------- Estado Ambiente Mapa --------\n";
        str += "Intensidad de señal\nNivel Alto \n";
        for(int i=0; i<intensidadSeñalA.size();i++)
        	str += "\tCuadrante: "+intensidadSeñalA.get(i).getCuadrante()+"\tIntensidad: "+intensidadSeñalA.get(i).getIntensidad()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSeñalM.size();i++)
        	str += "\tCuadrante: "+intensidadSeñalM.get(i).getCuadrante()+"\tIntensidad: "+intensidadSeñalM.get(i).getIntensidad()+"\n";
        str += "Nivel Bajo \n";
        for(int i=0; i<intensidadSeñalB.size();i++)
        	str +="\tPosición (x, y): "+intensidadSeñalB.get(i).getPosX()+" "+intensidadSeñalB.get(i).getPosY()+" \tCantidad de Personas: "+intensidadSeñalB.get(i).getPersonas().size()+"\n";
       
        return str;
    }

    // The following methods are agent-specific:
	
     public ArrayList<NodoLista> getintensidadSeñalA(){
        return intensidadSeñalA;
     }
     public void setintensidadSeñalA(ArrayList<NodoLista> arg){
        intensidadSeñalA = arg;
     }
     
     /**
      * Retorna la intensidades de señales para todo el nivel medio
      * */
     public ArrayList<NodoLista> getintensidadSeñalM(){
        return intensidadSeñalM;
     }
    
     public void setintensidadSeñalM(ArrayList<NodoLista> arg){
        intensidadSeñalM = arg;
     }
     public ArrayList<Nodo> getintensidadSeñalB(){
        return intensidadSeñalB;
     }
     public void setintensidadSeñalB(ArrayList<Nodo> arg){
        intensidadSeñalB = arg;
     }
     public Grafo getgrafoMapa(){
        return grafoMapa;
     }
     public void setgrafoMapa(Grafo arg){
        grafoMapa = arg;
     }
     public int getenergiaAgente(){
        return energiaAgente;
     }
     public void setenergiaAgente(int arg){
        energiaAgente = arg;
     }
     public Point getposicionAgente(){
        return posicionAgente;
     }
     public void setposicionAgente(Point arg){
        posicionAgente = arg;
     }
	public String getAlturaAgente(){
		return alturaAgente;
	}
     public void setAlturaAgente(String a){
    	 this.alturaAgente = a;
     }
     
     
      
     /**
      * Función que recorre los nodos adyacentes al nodo donde se encuentra el agente y 
      * retorna las personas que son visibles al agente desde la posición donde se encuentra:
      * las que estan en la misma posición del agente y las que se encuentran en linea recta
      * 
      * @param nodoAgente
      * @param subgrafo
      * */
     public ArrayList<Persona> getPersonasQueVe(Nodo nodoAgente, Grafo subgrafo)
     {

    	 ArrayList<Persona> personasObservables = new ArrayList<Persona>();

    	 //retorna los nodos adyacentes a la posicion pasada como parametro
    	 //Los nodos adyacentes serán los nodos que la cámara va a poder ver en todas las direcciones

    	 if(subgrafo != null)
    	 {
    		 ArrayList<Nodo> nodosAdyacentes = grafoMapa.nodosAdyacentesAPosicion(nodoAgente, subgrafo);

    		 //agrego la posición del agente si es que existen personas en la misma
    		 if(nodoAgente.getPersonas().size()>0)
    			 nodosAdyacentes.add(nodoAgente);

    		 for(Nodo n: nodosAdyacentes)
    		 {
    			 for(Persona p: n.getPersonas())
    				 if(p.esVictimario())
    					 personasObservables.add(p);
    		 }
    	 }

    	 return personasObservables;
     }

}

