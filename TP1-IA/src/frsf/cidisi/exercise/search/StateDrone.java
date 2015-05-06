

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
	
	//TODO: Setup Variables
    private Point ubicacionD;
    private String altura;
    private ArrayList<NodoLista> intensidadSeņalA;
    private ArrayList<NodoLista> intensidadSeņalM;
    private ArrayList<Nodo> intensidadSeņalB;
    private String direccion;
    private Vector<Persona> victimarios;
    private int energia;
	

    public StateDrone() {
    
    	//TODO: Complete Method
    	
			 ubicacionD = new Point();
			 altura = "A";
			 intensidadSeņalA = new ArrayList<NodoLista>();
			 intensidadSeņalM = new ArrayList<NodoLista>();
			 intensidadSeņalB = new ArrayList<Nodo>();
			 direccion = "N";
			 victimarios = new Vector<Persona>();
			 energia = 100;
        
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
		//TODO: Complete Method
		
        return null;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
        //TODO: Complete Method
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
	//TODO: Complete Method

    	//Ubica al agente en el cuadrante 1
    	ubicacionD.x = 150;
    	ubicacionD.y = 150;
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
       //TODO: Complete Method
        
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
     public ArrayList<NodoLista> getintensidadSeņalA(){
        return intensidadSeņalA;
     }
     public void setintensidadSeņalA( ArrayList<NodoLista> arg){
        intensidadSeņalA = arg;
     }
     public ArrayList<NodoLista> getintensidadSeņalM(){
    	 return intensidadSeņalM;
     }
     public void setintensidadSeņalM( ArrayList<NodoLista> arg){
        intensidadSeņalM = arg;
     }
     public  ArrayList<Nodo> getintensidadSeņalB(){
        return intensidadSeņalB;
     }
     public void setintensidadSeņalB(ArrayList<Nodo> arg){
        intensidadSeņalB = arg;
     }
     public String getdireccion(){
        return direccion;
     }
     public void setdireccion(String arg){
        direccion = arg;
     }
     public Vector<Persona> getvictimario(){
        return victimarios;
     }
     public void setvictimario(Vector<Persona> arg){
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
	
}

