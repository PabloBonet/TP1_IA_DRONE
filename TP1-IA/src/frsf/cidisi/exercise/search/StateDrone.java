package frsf.cidisi.exercise.search;

import java.awt.Point;
import java.util.ArrayList;

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
    private Persona victimario;
    private int energia;
    private Grafo grafoSubCuadrante;

	public StateDrone(Point p, String a, String d, int e) {

    	ubicacionD = p;
    	altura = a;
    	intensidadSeñalA = new ArrayList<NodoLista>();
    	intensidadSeñalM = new ArrayList<NodoLista>();
    	intensidadSeñalB = new ArrayList<Nodo>();
    	direccion = d;
    	victimario = null;
    	energia = e;
    	grafoSubCuadrante = new Grafo();
    }
    
    public StateDrone()
    {
    	ubicacionD = new Point();
		intensidadSeñalA = new ArrayList<NodoLista>();
		intensidadSeñalM = new ArrayList<NodoLista>();
		intensidadSeñalB = new ArrayList<Nodo>();
		victimario = null;
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
    	Persona nuevoVictimario = null;

    	for(NodoLista n: this.intensidadSeñalA)
    	{
    		NodoLista nodoNuevo = new NodoLista(n.getCuadrante(), n.getIntensidad(), n.getVisitado());
    		nuevaIntensidadSeñalA.add(nodoNuevo);
    	}
    	
    	for(NodoLista n: this.intensidadSeñalM)
    	{
    		NodoLista nodoNuevo = new NodoLista(n.getCuadrante(), n.getIntensidad(), n.getVisitado());
    		nuevaIntensidadSeñalM.add(nodoNuevo);
    	}
    	
    	for(Nodo n: this.intensidadSeñalB)
    	{
    		Nodo nodoNuevo = new Nodo(n.getId(), n.getPosX(), n.getPosY(), n.getVisitado());
    		for(Persona p: n.getPersonas())
    			nodoNuevo.agregarPersona(new Persona(p.getId(), p.getTipo()));
    		nuevaIntensidadSeñalB.add(nodoNuevo);
    	}

    	nuevoEstado.setintensidadSeñalA(nuevaIntensidadSeñalA);
    	nuevoEstado.setintensidadSeñalB(nuevaIntensidadSeñalB);
    	nuevoEstado.setintensidadSeñalM(nuevaIntensidadSeñalM);
    	if(victimario != null)
    		nuevoVictimario = new Persona(victimario.getId(), victimario.getTipo());
    	nuevoEstado.setvictimario(nuevoVictimario);
    	
    	ArrayList<Nodo> nodosNuevo = new ArrayList<Nodo>();
    	ArrayList<Enlace> enlacesNuevo = new ArrayList<Enlace>();
    	for(Nodo n: this.grafoSubCuadrante.getListaNodos())
    	{
    		Nodo nodo = new Nodo(n.getId(), n.getPosX(), n.getPosY(), n.getVisitado());
    		for(Persona p: n.getPersonas())
    			nodo.agregarPersona(new Persona(p.getId(), p.getTipo()));
    		nodosNuevo.add(nodo);
    	}
    	for(Enlace e: this.grafoSubCuadrante.getListaEnlaces())
    		enlacesNuevo.add(new Enlace(e.getIdNodo1(), e.getIdNodo2(), e.getPeso()));
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
    	
    	 energia = percepcion.getenergia();
    	 
    	 if(altura != "B")
    	 {
    		 ArrayList<NodoLista> listaI = percepcion.getantena().getIntensidadSeñal();
    		 if(altura == "A")
    		 {
    			 for(NodoLista n: listaI)
        		 {
        			 if(!n.getVisitado())
        			 {
        				 boolean existe = false;
        				 for(NodoLista nodo : this.intensidadSeñalA)
        				 {
        					 if(nodo.getCuadrante() == n.getCuadrante())
        					 {
        						existe = true;
        						break;
        					 }
        				 }
        				 
        				 if(!existe)
        				 	intensidadSeñalA.add(n);
        			 }
        			
        		 }
    		 }
    		 else
    		 {
    			 for(NodoLista n: listaI)
        		 {
        			 if(!n.getVisitado())
        			 {
        				 boolean existe = false;
        				 for(NodoLista nodo : this.intensidadSeñalM)
        				 {
        					 if(nodo.getCuadrante() == n.getCuadrante())  //TODO Y PERTENECE AL CUADRANTE ACTUAL......
        					 {
        						existe = true;
        						break;
        					 }
        				 }
        				 
        				 if(!existe)
        				 	intensidadSeñalM.add(n);
        			 }
        			
        		 }
    			 grafoSubCuadrante = new Grafo(percepcion.getgps().getGrafoSubCuadrante().getListaNodos(), 
        				 percepcion.getgps().getGrafoSubCuadrante().getListaEnlaces());
    		 }

    		
    	 }
    	 else
    	 {
    		 ArrayList<Nodo> listaIB = percepcion.getantena().getIntensidadSeñal();

    		 for(Nodo n: listaIB)
    		 {
    			 if(!n.getVisitado())
    			 {
    				 boolean existe = false;
    				 for(Nodo nodo : this.intensidadSeñalB)
    				 {
    					 if(nodo.getId() == n.getId())
    					 {
    						existe = true;
    						break;
    					 }
    				 }
    				 
    				 if(!existe)
    				 	intensidadSeñalB.add(n);
    			 }
    			
    		 }
    		 
    		 grafoSubCuadrante = new Grafo(percepcion.getgps().getGrafoSubCuadrante().getListaNodos(), 
    				 percepcion.getgps().getGrafoSubCuadrante().getListaEnlaces());

    		 //Agrega el victimario
    		  victimario = identificarVictimario(percepcion.getcamara().getPersonasEnLugar());
    		 
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
    	this.energia = 1000;
    	
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "------ Estado Agente VANT -----\n";
        str += "Altura: "+this.altura+"\n";
        str += "Ubicación: ";
        if(altura != "A")
        	str += FuncionesAuxiliares.perteneceASubCuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        else
       		str += FuncionesAuxiliares.perteneceACuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        
        str += "\nIntensidad de señal\nNivel Alto \n";
        for(int i=0; i<intensidadSeñalA.size();i++)
        	str += "\tCuadrante: "+intensidadSeñalA.get(i).getCuadrante()+"\tIntensidad: "+intensidadSeñalA.get(i).getIntensidad()+", Fue visitado: "+ intensidadSeñalA.get(i).getVisitado()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSeñalM.size();i++)
        	str += "\tCuadrante: "+intensidadSeñalM.get(i).getCuadrante()+"\tIntensidad: "+intensidadSeñalM.get(i).getIntensidad()+", Fue visitado: "+ intensidadSeñalM.get(i).getVisitado()+"\n";
        str += "Nivel Bajo \n";
        for(int i=0; i<intensidadSeñalB.size();i++)
        	str += "\tPosición (x, y): "+intensidadSeñalB.get(i).getPosX()+" "+intensidadSeñalB.get(i).getPosY()+"\tCantidad de Personas: "+intensidadSeñalB.get(i).getPersonas().size()+", Fue visitado: "+ intensidadSeñalB.get(i).getVisitado()+"\n";
        str += "Dirección: "+this.direccion+"\n";
        str += "Victimario (ID): ";
        if(victimario != null)
        	str += victimario.getId();
        else
        	str += "No se encontró.";
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
    	 
    	 ArrayList<NodoLista> intensidadA = ((StateDrone) obj).getintensidadSeñalA();
    	 ArrayList<NodoLista> intensidadM = ((StateDrone) obj).getintensidadSeñalM();
    	 ArrayList<Nodo> intensidadB = ((StateDrone) obj).getintensidadSeñalB();
    	
    	 
    	 for( NodoLista nodo: this.intensidadSeñalA)
    	 {
    		if(!intensidadA.contains(nodo))
    			return false;
    	 }
    	 
    	 for( NodoLista nodo: this.intensidadSeñalM)
    	 {
    		if(!intensidadM.contains(nodo))
    			return false;
    	 }
    	 
    	 for( Nodo nodo: this.intensidadSeñalB)
    	 {
    		if(!intensidadB.contains(nodo))
    			return false;
    	 }
    	 
    	 if(((StateDrone) obj).getvictimario() != victimario)
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
     public Persona getvictimario(){
        return victimario;
     }
     public void setvictimario(Persona arg){
        victimario = arg;
     }

     public Grafo getGrafoSubCuadrante() {
 		return grafoSubCuadrante;
 	}

 	public void setGrafoSubCuadrante(Grafo grafoSubCuadrante) {
 		this.grafoSubCuadrante = grafoSubCuadrante;
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

	
	/**
	 * Dada la lista de personas identifica al victimario
	 * @param personas
	 * 		lista de personas en las cuales podria encontrarse el victimario
	 * @return
	 * 		retorna el victimario si lo encontro,en caso contrario retorna null
	 */
	private Persona identificarVictimario(ArrayList<Persona> personas)
	{
		for(Persona p: personas)
		{
			if(p.esVictimario())
				return p;
		}
		return null;
	}

	/**
	 * Marca como visitado el cuadrante pasado como parámetro
	 * **/
	public void visitarA(int cuadrante) {
		
		for(NodoLista n: getintensidadSeñalA())
		{
			if(n.getCuadrante() == cuadrante)
			{
System.out.println("\tVISITA cuadrante "+n.getCuadrante()+" intensidad "+n.getIntensidad());
				n.visitar();
			}
		}
	}

	/**
	 * Marca como visitado el cuadrante pasado como parámetro
	 * **/
	public void visitarM(int cuadrante) {
		
		for(NodoLista n: getintensidadSeñalM())
		{
			if(n.getCuadrante() == cuadrante)
			{
				n.visitar();
			}
		}
	}
	
}

