package frsf.cidisi.exercise.defecto.search;


import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class StateDrone extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private int[] ubicacionD;
    private String altura;
    //private Other intensidadSeñalA;
    //private Other intensidadSeñalM;
    //private Other intensidadSeñalB;
    private String direccion;
    //private Other victimario;
    private int energia;
	

    public StateDrone() {
    
    	//TODO: Complete Method
    	/*
			// ubicacionD = initData0;
			// altura = initData1;
			// intensidadSeñalA = initData2;
			// intensidadSeñalM = initData3;
			// intensidadSeñalB = initData4;
			// direccion = initData5;
			// victimario = initData6;
			// energia = initData7;
        */
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
   	
     public int[] getubicacionD(){
        return ubicacionD;
     }
     public void setubicacionD(int[] arg){
        ubicacionD = arg;
     }
     public String getaltura(){
        return altura;
     }
     public void setaltura(String arg){
        altura = arg;
     }
//     public Other getintensidadSeñalA(){
//        return intensidadSeñalA;
//     }
//     public void setintensidadSeñalA(Other arg){
//        intensidadSeñalA = arg;
//     }
//     public Other getintensidadSeñalM(){
//        return intensidadSeñalM;
//     }
//     public void setintensidadSeñalM(Other arg){
//        intensidadSeñalM = arg;
//     }
//     public Other getintensidadSeñalB(){
//        return intensidadSeñalB;
//     }
//     public void setintensidadSeñalB(Other arg){
//        intensidadSeñalB = arg;
//     }
     public String getdireccion(){
        return direccion;
     }
     public void setdireccion(String arg){
        direccion = arg;
     }
//     public Other getvictimario(){
//        return victimario;
//     }
//     public void setvictimario(Other arg){
//        victimario = arg;
//     }
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        energia = arg;
     }
	
}

