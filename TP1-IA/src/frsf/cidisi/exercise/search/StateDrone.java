

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
     * de los que se recibieron se�al sin impotar la altura en la que se encuentra.
     * Estas listas ir�n actualizando su contenido seg�n el recorrido del agente
     * A medida que el agente recorre los nodos o cuadrantes estos se van quitando de la lista.
     */
    private ArrayList<NodoLista> intensidadSe�alA;
    private ArrayList<NodoLista> intensidadSe�alM;
    private ArrayList<Nodo> intensidadSe�alB;
    private String direccion;
    private Persona victimario;
    private int energia;
    private Grafo grafoSubCuadrante;

	public StateDrone(Point p, String a, String d, int e) {

    	ubicacionD = p;
    	altura = a;
    	intensidadSe�alA = new ArrayList<NodoLista>();
    	intensidadSe�alM = new ArrayList<NodoLista>();
    	intensidadSe�alB = new ArrayList<Nodo>();
    	direccion = d;
    	victimario = null;
    	energia = e;
    	grafoSubCuadrante = new Grafo();
    }
    
    public StateDrone()
    {
    	ubicacionD = new Point();
		intensidadSe�alA = new ArrayList<NodoLista>();
		intensidadSe�alM = new ArrayList<NodoLista>();
		intensidadSe�alB = new ArrayList<Nodo>();
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
    	
    	ArrayList<NodoLista> nuevaIntensidadSe�alA = new ArrayList<NodoLista>();
    	ArrayList<NodoLista> nuevaIntensidadSe�alM = new ArrayList<NodoLista>();
    	ArrayList<Nodo> nuevaIntensidadSe�alB = new ArrayList<Nodo>();

    	for(NodoLista n: this.intensidadSe�alA)
    	{
    		NodoLista nodoNuevo = new NodoLista(n.getCuadrante(), n.getIntensidad(), n.getVisitado());
    		nuevaIntensidadSe�alA.add(nodoNuevo);
    	}
    	
    	for(NodoLista n: this.intensidadSe�alM)
    	{
    		NodoLista nodoNuevo = new NodoLista(n.getCuadrante(), n.getIntensidad(), n.getVisitado());
    		nuevaIntensidadSe�alM.add(nodoNuevo);
    	}
    	
    	for(Nodo n: this.intensidadSe�alB)
    	{
    		Nodo nodoNuevo = new Nodo(n.getId(), n.getPosX(), n.getPosY(), n.getVisitado());
    		for(Persona p: n.getPersonas())
    			nodoNuevo.agregarPersona(new Persona(p.getId(), p.getTipo()));
    		nuevaIntensidadSe�alB.add(nodoNuevo);
    	}
    	nuevoEstado.setintensidadSe�alA(nuevaIntensidadSe�alA);
    	nuevoEstado.setintensidadSe�alB(nuevaIntensidadSe�alB);
    	nuevoEstado.setintensidadSe�alM(nuevaIntensidadSe�alM);
    	nuevoEstado.setvictimario(victimario);
    	
    	ArrayList<Nodo> nodosNuevo = new ArrayList<Nodo>();
    	ArrayList<Enlace> enlacesNuevo = new ArrayList<Enlace>();
    	for(Nodo no: this.grafoSubCuadrante.getListaNodos())
    		nodosNuevo.add(new Nodo(no.getId(), no.getPosX(), no.getPosY(), no.getVisitado()));
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
    		 ArrayList<NodoLista> listaI = percepcion.getantena().getIntensidadSe�al();
    		 if(altura == "A")
    		 {
    			 for(NodoLista n: listaI)
        		 {
        			 if(!n.getVisitado())
        			 {
        				 boolean existe = false;
        				 for(NodoLista nodo : this.intensidadSe�alA)
        				 {
        					 if(nodo.getCuadrante() == n.getCuadrante())
        					 {
        						existe = true;
        						break;
        					 }
        				 }
         				//Si el nodo percibido no existe en la lista de intensidad A del agente, lo agrega
        				 if(!existe)
        				 	intensidadSe�alA.add(n);
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
        				 for(NodoLista nodo : this.intensidadSe�alM)
        				 {
        					 if(nodo.getCuadrante() == n.getCuadrante())
        					 {
        						existe = true;
        						break;
        					 }
        				 }
         				//Si el nodo percibido no existe en la lista de intensidad M del agente, lo agrega
        				 if(!existe)
        				 	intensidadSe�alM.add(n);
        			 }
        			
        		 }
    			 grafoSubCuadrante = new Grafo(percepcion.getgps().getGrafoSubCuadrante().getListaNodos(), 
        				 percepcion.getgps().getGrafoSubCuadrante().getListaEnlaces());
    		 }
    	 }
    	 else
    	 {
    		 ArrayList<Nodo> listaIB = percepcion.getantena().getIntensidadSe�al();

    		 for(Nodo n: listaIB)
    		 {
    			 if(!n.getVisitado())
    			 {
    				 boolean existe = false;
    				 for(Nodo nodo : this.intensidadSe�alB)
    				 {
    					 if(nodo.getId() == n.getId())
    					 {
    						existe = true;
    						break;
    					 }
    				 }
    				//Si el nodo percibido no existe en la lista de intensidad B del agente, lo agrega
    				 if(!existe)
    				 	intensidadSe�alB.add(n);
    			 }
    		 }
    		 
    		 grafoSubCuadrante = new Grafo(percepcion.getgps().getGrafoSubCuadrante().getListaNodos(), 
    				 percepcion.getgps().getGrafoSubCuadrante().getListaEnlaces());
    		 //Identifica al victimario
    		  victimario = identificarVictimario(percepcion.getcamara().getPersonasEnLugar());
    		 
    	 }	 
    	 
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {

    	//Inicializa la posici�n
    	ubicacionD.x = 150;
    	ubicacionD.y = 150;
    	
    	//Inicializa la altura
    	altura = "A";
    	
    	//Inicializa la direcci�n
    	direccion = "N";
    	
    	//Inicializa la energ�a
    	this.energia = 1000;
    	
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "------ Estado Agente VANT -----\n";
        str += "Altura: "+this.altura+"\n";
        str += "Ubicaci�n: ";
        if(altura== "B")
        {
        	str += (grafoSubCuadrante.nodoEnPosicion(ubicacionD)).getId();
        }
        	
        
        else
        {
        	if(altura == "M")
        		str += FuncionesAuxiliares.perteneceASubCuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        	else
        		str += FuncionesAuxiliares.perteneceACuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        }
        	
        
        str += "Intensidad de se�al\nNivel Alto \n";
        for(int i=0; i<intensidadSe�alA.size();i++)
        	str += "\tCuadrante: "+intensidadSe�alA.get(i).getCuadrante()+"\tIntensidad: "+intensidadSe�alA.get(i).getIntensidad()+", Fue visitado: "+ intensidadSe�alA.get(i).getVisitado()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSe�alM.size();i++)
        	str += "\tCuadrante: "+intensidadSe�alM.get(i).getCuadrante()+"\tIntensidad: "+intensidadSe�alM.get(i).getIntensidad()+", Fue visitado: "+ intensidadSe�alM.get(i).getVisitado()+"\n";
        str += "Nivel Bajo \n";
        for(int i=0; i<intensidadSe�alB.size();i++)
        	str += "\tPosici�n (x, y): "+intensidadSe�alB.get(i).getPosX()+" "+intensidadSe�alB.get(i).getPosY()+"\tCantidad de Personas: "+intensidadSe�alB.get(i).getPersonas().size()+", Fue visitado: "+ intensidadSe�alB.get(i).getVisitado()+"\n";
        str += "Direcci�n: "+this.direccion+"\n";
        str += "Victimario (ID): ";
        if(victimario != null)
        	str += victimario.getId();
        else
        	str += "No se encontr�.";
        str += "\nEnerg�a: "+energia+"\n\n";

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
    	 
    	 ArrayList<NodoLista> intensidadA = ((StateDrone) obj).getintensidadSe�alA();
    	 ArrayList<NodoLista> intensidadM = ((StateDrone) obj).getintensidadSe�alM();
    	 ArrayList<Nodo> intensidadB = ((StateDrone) obj).getintensidadSe�alB();
    	
    	 
    	 for( NodoLista nodo: this.intensidadSe�alA)
    	 {
    		if(!intensidadA.contains(nodo))
    			return false;
    	 }
    	 
    	 for( NodoLista nodo: this.intensidadSe�alM)
    	 {
    		if(!intensidadM.contains(nodo))
    			return false;
    	 }
    	 
    	 for( Nodo nodo: this.intensidadSe�alB)
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
     public ArrayList<NodoLista> getintensidadSe�alA(){
        return intensidadSe�alA;
     }
     public void setintensidadSe�alA( ArrayList<NodoLista> arg){
        intensidadSe�alA = arg;
     }
     public ArrayList<NodoLista> getintensidadSe�alM(){
    	 return intensidadSe�alM;
     }
     public void setintensidadSe�alM( ArrayList<NodoLista> arg){
        intensidadSe�alM = arg;
     }
     public  ArrayList<Nodo> getintensidadSe�alB(){
        return intensidadSe�alB;
     }
     public void setintensidadSe�alB(ArrayList<Nodo> arg){
        intensidadSe�alB = arg;
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

	public boolean hayIntensidadSe�alBCuadrante(int cuadranteActual) {
		for(Nodo n: intensidadSe�alB){
			if(cuadranteActual == FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(),n.getPosY()))
				return true;
		}
		return false;
	}

	public boolean hayIntensidadSe�alMCuadrante(int cuadranteActual) {
		for(NodoLista n: intensidadSe�alM){
			if(cuadranteActual == n.getCuadrante())
				return true;
		}
		return false;
	}

	/**
	 * Elimina un nodo de la lista de intensidad de se�al de nivel alto del agente
	 * @param cuadrante
	 */
	public void removerCuadranteNivelA(int cuadrante) {
		for(int i=0; i<intensidadSe�alA.size(); i++)
			if(cuadrante == intensidadSe�alA.get(i).getCuadrante()){
				intensidadSe�alA.remove(i);
				break;
			}
	}

	/**
	 * Elimina un nodo de la lista de intensidad de se�al de nivel medio del agente
	 * @param subCuadrante
	 */
	public void removerCuadranteNivelM(int subCuadrante) { //VER SI TIENE QUE RETORNAR ALGO EN CASO DE NO PODER BORRARLO####################################
		for(int i=0; i<intensidadSe�alM.size(); i++)
			if(subCuadrante == intensidadSe�alM.get(i).getCuadrante()){
				intensidadSe�alM.remove(i);
				break;
			}
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
	 * Marca como visitado el cuadrante pasado como par�metro
	 * **/
	public void visitarA(int cuadrante) {
		// TODO Auto-generated method stub
		
		for(NodoLista n: getintensidadSe�alA())
		{
			if(n.getCuadrante() == cuadrante)
			{
				n.visitar();
			}
		}
	}

	/**
	 * Marca como visitado el cuadrante pasado como par�metro
	 * **/
	public void visitarM(int cuadrante) {
		// TODO Auto-generated method stub
		
		for(NodoLista n: getintensidadSe�alM())
		{
			if(n.getCuadrante() == cuadrante)
			{
				n.visitar();
			}
		}
	}
	
}

