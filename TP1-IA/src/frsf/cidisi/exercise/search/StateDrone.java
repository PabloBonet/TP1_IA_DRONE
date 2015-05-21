

package frsf.cidisi.exercise.search;



import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.ia.tp.libreriaclases.*;


/**
 * Represent the internal state of the Agent.
 */
public class StateDrone extends SearchBasedAgentState {
	
    private Point ubicacionD;
    private String altura;
    
    /*Las listas de intensidades van guardando los nodos o cuadrantes
     * de los que se recibieron señal sin impotar la altura en la que se encuentra.
     * Estas listas irán actualizando su contenido según el recorrido del agente
     * A medida que el agente recorre los nodos o cuadrantes estos se van quitando de la lista.
     */
    private ArrayList<NodoLista> intensidadSeñalA;
    private ArrayList<NodoLista> intensidadSeñalM;
    private ArrayList<Nodo> intensidadSeñalB;
    private String direccion;
    private ArrayList<Persona> victimarios;
    private int energia;
    private Grafo grafoSubCuadrante;

	public StateDrone(Point p, String a, String d, int e) {

    	ubicacionD = p;
    	altura = a;
    	intensidadSeñalA = new ArrayList<NodoLista>();
    	intensidadSeñalM = new ArrayList<NodoLista>();
    	intensidadSeñalB = new ArrayList<Nodo>();
    	direccion = d;
    	victimarios = new ArrayList<Persona>();
    	energia = e;
    	grafoSubCuadrante = new Grafo();
    }
    
    public StateDrone()
    {
    	ubicacionD = new Point();
		intensidadSeñalA = new ArrayList<NodoLista>();
		intensidadSeñalM = new ArrayList<NodoLista>();
		intensidadSeñalB = new ArrayList<Nodo>();
		victimarios = new ArrayList<Persona>();
		grafoSubCuadrante = new Grafo();
   
		this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
    	StateDrone nuevoEstado = new StateDrone(this.ubicacionD, this.altura, this.direccion, this.energia);
    	
    	ArrayList<NodoLista> nuevaIntensidadSeñalA = new ArrayList<NodoLista>();
    	ArrayList<NodoLista> nuevaIntensidadSeñalM = new ArrayList<NodoLista>();
    	ArrayList<Nodo> nuevaIntensidadSeñalB = new ArrayList<Nodo>();
    	ArrayList<Persona> nuevaListaVictimarios = new ArrayList<Persona>();
    	
    	for(NodoLista n: this.intensidadSeñalA)
    	{
    		nuevaIntensidadSeñalA.add(n);
    	}
    	
    	for(NodoLista n: this.intensidadSeñalM)
    	{
    		nuevaIntensidadSeñalM.add(n);
    	}
    	
    	for(Nodo n: this.intensidadSeñalB)
    	{
    		nuevaIntensidadSeñalB.add(n);
    	}
    	
    	for(Persona p: this.victimarios)
    	{
    		nuevaListaVictimarios.add(p);
    	}
    	
    	nuevoEstado.setintensidadSeñalA(nuevaIntensidadSeñalA);
    	nuevoEstado.setintensidadSeñalB(nuevaIntensidadSeñalB);
    	nuevoEstado.setintensidadSeñalM(nuevaIntensidadSeñalM);
    	nuevoEstado.setvictimario(nuevaListaVictimarios);
    	
    	ArrayList<Nodo> nodosNuevo = new ArrayList<Nodo>();
    	ArrayList<Enlace> enlacesNuevo = new ArrayList<Enlace>();
    	for(Nodo n: this.grafoSubCuadrante.getListaNodos())
    		nodosNuevo.add(n);
    	for(Enlace e: this.grafoSubCuadrante.getListaEnlaces())
    		enlacesNuevo.add(e);
    	Grafo subGrafo = new Grafo(nodosNuevo, enlacesNuevo);
    	nuevoEstado.setGrafoSubCuadrante(subGrafo);
    	
        return nuevoEstado;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
    	AgentDronePerception percepcion = (AgentDronePerception) p;
    	
    	 ubicacionD = percepcion.getgps().getPosiciongps();
    	 altura = percepcion.getaltura();
    	 victimarios = new ArrayList<Persona>();
    	 energia = percepcion.getenergia();
    	 
    	 if(altura != "B")
    	 {
    		 ArrayList<NodoLista> listaI = percepcion.getantena().getIntensidadSeñal();
    		 if(altura == "A")
    		 {
    			 for(NodoLista n: listaI)
        		 {
        			 if(!n.getVisitado())
        			 	intensidadSeñalA.add(n);
        		 }
    		 }
    		 else
    		 {
    			 	 for(NodoLista n : listaI)
        			 {
        				 if(!n.getVisitado())
        					 intensidadSeñalM.add(n);
        			 }
    		 }
    	 }
    	 else
    	 {
    		 ArrayList<Nodo> listaIB = percepcion.getantena().getIntensidadSeñal();

    		 for(Nodo n : listaIB)
    		 {
    			 intensidadSeñalB.add(n);
    		 }
    		 
    		 grafoSubCuadrante = new Grafo(percepcion.getgps().getGrafoSubCuadrante().getListaNodos(), 
    				 percepcion.getgps().getGrafoSubCuadrante().getListaEnlaces());
    	 }	 
    	 
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {

    	//Inicializa la posición
    	ubicacionD.x = 150;
    	ubicacionD.y = 150;
    	
    	//Inicializa la altura
    	altura = "A";
    	
    	//Inicializa la dirección
    	direccion = "N";
    	
    	//Inicializa la energía
    	this.energia = 100;
    	
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "------ Estado Agente VANT -----\n";
        str += "Altura: "+this.altura+"\n";
        str += "Ubicación: ";
        if(altura== "B")
        	str += FuncionesAuxiliares.perteneceASubCuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        else
        	str += FuncionesAuxiliares.perteneceACuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        
        str += "Intensidad de señal\nNivel Alto \n";
        for(int i=0; i<intensidadSeñalA.size();i++)
        	str += "\tCuadrante: "+intensidadSeñalA.get(i).getCuadrante()+"\tIntensidad: "+intensidadSeñalA.get(i).getIntensidad()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSeñalM.size();i++)
        	str += "\tCuadrante: "+intensidadSeñalM.get(i).getCuadrante()+"\tIntensidad: "+intensidadSeñalM.get(i).getIntensidad()+"\n";
        str += "Nivel Bajo \n";
        for(int i=0; i<intensidadSeñalB.size();i++)
        	str += "\tPosición (x, y): "+intensidadSeñalB.get(i).getPosX()+" "+intensidadSeñalB.get(i).getPosY()+"\tCantidad de Personas: "+intensidadSeñalB.get(i).getPersonas().size()+"\n";
        str += "Dirección: "+this.direccion+"\n";
        str += "Victimarios (ID): ";
        for(int i=0; i<victimarios.size();i++)
        	str += victimarios.get(i).getId()+" ";
        str += "\nEnergía: "+energia+"\n\n";

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
       //TODO: Complete Method
        
    	 if (!(obj instanceof StateDrone))
             return false;
    	
    	 Point ubicacion = ((StateDrone) obj).getubicacionD();
    	 String altura = ((StateDrone) obj).getaltura();
    	 int energia = ((StateDrone) obj).getenergia();
    	 
    	 
    	 if(ubicacion.x != this.ubicacionD.x || ubicacion.y != this.ubicacionD.y)
    		 return false;
    	 
    	 if(!altura.equals(this.altura))
    		 return  false;
    	 
    	 if(energia != this.energia)
    		 return false;
    	 
    	 
    	 
        return true;
    }

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     public Point getubicacionD(){
        return ubicacionD;
     }
     public void setubicacionD(Point arg){
        ubicacionD = arg;
     }
     public String getaltura(){
        return altura;
     }
     public void setaltura(String arg){
        altura = arg;
     }
     public ArrayList<NodoLista> getintensidadSeñalA(){
        return intensidadSeñalA;
     }
     public void setintensidadSeñalA( ArrayList<NodoLista> arg){
        intensidadSeñalA = arg;
     }
     public ArrayList<NodoLista> getintensidadSeñalM(){
    	 return intensidadSeñalM;
     }
     public void setintensidadSeñalM( ArrayList<NodoLista> arg){
        intensidadSeñalM = arg;
     }
     public  ArrayList<Nodo> getintensidadSeñalB(){
        return intensidadSeñalB;
     }
     public void setintensidadSeñalB(ArrayList<Nodo> arg){
        intensidadSeñalB = arg;
     }
     public String getdireccion(){
        return direccion;
     }
     public void setdireccion(String arg){
        direccion = arg;
     }
     public ArrayList<Persona> getvictimario(){
        return victimarios;
     }
     public void setvictimario(ArrayList<Persona> arg){
        victimarios = arg;
     }

     public Grafo getGrafoSubCuadrante() {
 		return grafoSubCuadrante;
 	}

 	public void setGrafoSubCuadrante(Grafo grafoSubCuadrante) {
 		this.grafoSubCuadrante = grafoSubCuadrante;
 	}
     
     public void agregarVictimario(Persona p)
     {
    	 if(victimarios != null)
    	 {
    		 victimarios.add(p);
    	 }
     }
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        energia = arg;
     }

	public boolean hayIntensidadSeñalBCuadrante(int cuadranteActual) {
		for(Nodo n: intensidadSeñalB){
			if(cuadranteActual == FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(),n.getPosY()))
				return true;
		}
		return false;
	}

	public boolean hayIntensidadSeñalMCuadrante(int cuadranteActual) {
		for(NodoLista n: intensidadSeñalM){
			if(cuadranteActual == n.getCuadrante())
				return true;
		}
		return false;
	}

//	public boolean hayIntensidadSeñalACuadrante(int cuadranteActual) {
//		for(NodoLista n: intensidadSeñalA){
//			if(cuadranteActual == n.getCuadrante())
//				return true;
//		}
//		return false;
//	}

	/**
	 * Elimina un nodo de la lista de intensidad de señal de nivel alto del agente
	 * @param cuadrante
	 */
	public void removerCuadranteNivelA(int cuadrante) {
		for(int i=0; i<intensidadSeñalA.size(); i++)
			if(cuadrante == intensidadSeñalA.get(i).getCuadrante()){
				intensidadSeñalA.remove(i);
				break;
			}
	}

	/**
	 * Elimina un nodo de la lista de intensidad de señal de nivel medio del agente
	 * @param subCuadrante
	 */
	public void removerCuadranteNivelM(int subCuadrante) { //VER SI TIENE QUE RETORNAR ALGO EN CASO DE NO PODER BORRARLO####################################
		for(int i=0; i<intensidadSeñalM.size(); i++)
			if(subCuadrante == intensidadSeñalM.get(i).getCuadrante()){
				intensidadSeñalM.remove(i);
				break;
			}
	}
	
}

