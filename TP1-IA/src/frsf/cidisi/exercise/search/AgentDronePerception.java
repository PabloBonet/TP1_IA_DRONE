package frsf.cidisi.exercise.search;


import java.awt.Point;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.Antena;
import frsf.ia.tp.libreriaclases.Camara;
import frsf.ia.tp.libreriaclases.Nodo;

public class AgentDronePerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	/*private int altura;
	private int posiciongps;
	private int camara;
	private int antena;
	private int energia;
	*/
	
	private String altura;
	private Point posiciongps;
	private int energia;
	private Camara camara;
	private Antena antena;
	
 

    public  AgentDronePerception() {
    	//TODO: Complete Method
    	
    	
    	
    }

    public AgentDronePerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
    	//TODO: Complete Method
        
        AgentDrone agente = (AgentDrone) agentIn;
        EnvironmentMap ambiente = (EnvironmentMap) environmentIn;
        StateMap estadoAmbiente =
                ambiente.getEnvironmentState();
       
        this.posiciongps = estadoAmbiente.getposicionAgente();
        this.altura = estadoAmbiente.getAlturaAgente();
        this.energia = estadoAmbiente.getenergiaAgente();
        
        if(estadoAmbiente.getAlturaAgente() == "B")
        {
        	Point posicionAgente = estadoAmbiente.getposicionAgente();
        	Nodo nodoAgente = (Nodo)(estadoAmbiente.getgrafoMapa()).nodoEnPosicion(posicionAgente);
        	
        	if(nodoAgente != null)
        	camara = new Camara(estadoAmbiente.getPersonasQueVe(), nodoAgente);
        	
        }
        
        
        /*
         * COMPLETAR PARTE ANTENA
         * 
         * */
        
       
        	
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
       str.append("\n\n-- Percepción del Agente VANT --\n");
       str.append("Altura: "+this.altura+"\n");
       str.append("Posición: "+this.posiciongps.getX()+" - "+this.posiciongps.getY()+"\n");
       str.append("Cámara: \n\tVictimas: ");
       for(int i=0;i<this.camara.getVictimas().size();i++)
    	   str.append("\n\t\t"+this.camara.getVictimas().get(i).getId());
       str.append("\n\tVictimarios: ");
       for(int i=0;i<this.camara.getVictimarios().size();i++)
    	   str.append("\n\t\t"+this.camara.getVictimarios().get(i).getId());
//str.append("\nAntena: "+this.antena.---); //Info de antena!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       str.append("\nEnergía: "+this.energia+"\n");

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	/*
     public int getaltura(){
        return altura;
     }
     public void setaltura(int arg){
        this.altura = arg;
     }
     public int getposiciongps(){
        return posiciongps;
     }
     public void setposiciongps(int arg){
        this.posiciongps = arg;
     }
     public int getcamara(){
        return camara;
     }
     public void setcamara(int arg){
        this.camara = arg;
     }
     public int getantena(){
        return antena;
     }
     public void setantena(int arg){
        this.antena = arg;
     }
    */
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        this.energia = arg;
     }
	
   
}
