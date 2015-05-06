package frsf.cidisi.exercise.defecto.search;


import java.awt.Point;
import java.util.Vector;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.ia.tp.libreriaclases.Persona;

/**
 * Represent the internal state of the Agent.
 */
public class StateDrone extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private Point ubicacionD;
    private String altura;
    //private Other intensidadSeņalA;
    //private Other intensidadSeņalM;
    //private Other intensidadSeņalB;
    private String direccion;
    private Vector<Persona> victimario;
    private int energia;
	

    public StateDrone() {
    
    	//TODO: Complete Method
    	
			// ubicacionD = initData0;
			// altura = initData1;
			// intensidadSeņalA = initData2;
			// intensidadSeņalM = initData3;
			// intensidadSeņalB = initData4;
			// direccion = initData5;
			 victimario = new Vector<Persona>();
			// energia = initData7;
        
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
//     public Other getintensidadSeņalA(){
//        return intensidadSeņalA;
//     }
//     public void setintensidadSeņalA(Other arg){
//        intensidadSeņalA = arg;
//     }
//     public Other getintensidadSeņalM(){
//        return intensidadSeņalM;
//     }
//     public void setintensidadSeņalM(Other arg){
//        intensidadSeņalM = arg;
//     }
//     public Other getintensidadSeņalB(){
//        return intensidadSeņalB;
//     }
//     public void setintensidadSeņalB(Other arg){
//        intensidadSeņalB = arg;
//     }
     public String getdireccion(){
        return direccion;
     }
     public void setdireccion(String arg){
        direccion = arg;
     }
     public Vector<Persona> getvictimario(){
        return victimario;
     }
     public void setvictimario(Vector<Persona> arg){
        victimario = arg;
     }
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        energia = arg;
     }
	
}

