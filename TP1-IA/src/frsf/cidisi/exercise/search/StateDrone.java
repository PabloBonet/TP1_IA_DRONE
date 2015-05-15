

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
     * de los que se recibieron se�al sin impotar la altura en la que se encuentra.
     * Estas listas ir�n actualizando su contenido seg�n el recorrido del agente
     * A medida que el agente recorre los nodos o cuadrantes estos se van quitando de la lista.
     */
    private ArrayList<NodoLista> intensidadSe�alA;
    private ArrayList<NodoLista> intensidadSe�alM;
    private ArrayList<Nodo> intensidadSe�alB;
    private String direccion;
    private ArrayList<Persona> victimarios;
    private int energia;
	

    public StateDrone(Point p, String a, String d, int e) {

    	ubicacionD = p;
    	altura = a;
    	intensidadSe�alA = new ArrayList<NodoLista>();
    	intensidadSe�alM = new ArrayList<NodoLista>();
    	intensidadSe�alB = new ArrayList<Nodo>();
    	direccion = d;
    	victimarios = new ArrayList<Persona>();
    	energia = e;
    }
    
    public StateDrone()
    {
    	ubicacionD = new Point();
		intensidadSe�alA = new ArrayList<NodoLista>();
		intensidadSe�alM = new ArrayList<NodoLista>();
		intensidadSe�alB = new ArrayList<Nodo>();
		victimarios = new ArrayList<Persona>();
   
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
    	ArrayList<Persona> nuevaListaVictimarios = new ArrayList<Persona>();
    	
    	for(NodoLista n: this.intensidadSe�alA)
    	{
    		nuevaIntensidadSe�alA.add(n);
    	}
    	
    	for(NodoLista n: this.intensidadSe�alM)
    	{
    		nuevaIntensidadSe�alM.add(n);
    	}
    	
    	for(Nodo n: this.intensidadSe�alB)
    	{
    		nuevaIntensidadSe�alB.add(n);
    	}
    	
    	for(Persona p: this.victimarios)
    	{
    		nuevaListaVictimarios.add(p);
    	}
    	
    	
    	nuevoEstado.setintensidadSe�alA(nuevaIntensidadSe�alA);
    	nuevoEstado.setintensidadSe�alB(nuevaIntensidadSe�alB);
    	nuevoEstado.setintensidadSe�alM(nuevaIntensidadSe�alM);
    	nuevoEstado.setvictimario(nuevaListaVictimarios);
    	
        return nuevoEstado;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
    	AgentDronePerception percepcion = (AgentDronePerception) p;
    	
    	 ubicacionD = percepcion.getposiciongps();
    	 altura = percepcion.getaltura();
    	 victimarios = new ArrayList<Persona>();
    	 energia = percepcion.getenergia();
    	 
    	 if(altura != "B")
    	 {
    		 ArrayList<NodoLista> listaI = percepcion.getantena().getIntensidadSe�al();
    		 if(altura == "A")
    		 {
    			 for(NodoLista n: listaI)
        		 {
        			 if(!n.getVisitado())
        			 	intensidadSe�alA.add(n);
        		 }
    		 }
    		 else
    		 {
    			 	 for(NodoLista n : listaI)
        			 {
        				 if(!n.getVisitado())
        					 intensidadSe�alM.add(n);
        			 }
    		 }
    	 }
         else
         {
    		 ArrayList<Nodo> listaIB = percepcion.getantena().getIntensidadSe�al();
    		 
        		 for(Nodo n : listaIB)
    			 {
    				
    					 intensidadSe�alB.add(n);
    			 }
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
    	this.energia = 100;
    	
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "------ Estado Agente VANT -----\n";
        str += "Altura: "+altura+"\n";
        str += "Ubicaci�n: ";
        if(altura== "B")
        	str += FuncionesAuxiliares.perteneceASubCuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        else
        	str += FuncionesAuxiliares.perteneceACuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        str += "Intensidad de se�al\n\t\tCuadrante Se�al\nNivel Alto \n";
        for(int i=0; i<intensidadSe�alA.size();i++)
        	str += "\t\t"+intensidadSe�alA.get(i).getCuadrante()+"\t"+intensidadSe�alA.get(i).getIntensidad()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSe�alM.size();i++)
        	str += "\t\t"+intensidadSe�alM.get(i).getCuadrante()+"\t"+intensidadSe�alM.get(i).getIntensidad()+"\n";
        str += "Nivel Bajo \tPosici�n (x, Y) Se�al\n";
        for(int i=0; i<intensidadSe�alB.size();i++)
        	str += "\t\t"+intensidadSe�alB.get(i).getPosX()+" "+intensidadSe�alB.get(i).getPosY()+"\t"+intensidadSe�alB.get(i).getPersonas().size()+"\n";
        str += "Direcci�n: "+direccion+"\n";
        str += "Victimarios (ID): ";
        for(int i=0; i<victimarios.size();i++)
        	str += victimarios.get(i).getId()+" ";
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
     public ArrayList<Persona> getvictimario(){
        return victimarios;
     }
     public void setvictimario(ArrayList<Persona> arg){
        victimarios = arg;
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
//POR AHORA NO SE USA UN M�TODO DES ESTOS PARA EL NIVEL ALTO....................................................................

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
	
}

