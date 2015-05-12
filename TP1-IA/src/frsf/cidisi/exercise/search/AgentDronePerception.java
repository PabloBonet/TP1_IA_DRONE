package frsf.cidisi.exercise.search;


import java.awt.Point;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.Antena;
import frsf.ia.tp.libreriaclases.AntenaNB;
import frsf.ia.tp.libreriaclases.AntenaNMA;
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
        
        String altura = estadoAmbiente.getAlturaAgente();
        
        if(altura == "B")
        {
        	Point posicionAgente = estadoAmbiente.getposicionAgente();
        	Nodo nodoAgente = (Nodo)(estadoAmbiente.getgrafoMapa()).nodoEnPosicion(posicionAgente);
        	
        	//Percepci�n c�mara
        	if(nodoAgente != null)
        	camara = new Camara(estadoAmbiente.getPersonasQueVe(), nodoAgente);
        	
        	//Percepci�n Antena
        	antena = new AntenaNB(estadoAmbiente.getintensidadSe�alB());
        	
        }
        else // Si altura del agente es M o A
        {
        	camara = new Camara();
        	
        	if(altura == "M")
        	{
        		antena = new AntenaNMA(estadoAmbiente.getintensidadSe�alM());
        	}
        	else
        	{
        		antena = new AntenaNMA(estadoAmbiente.getintensidadSe�alA());
        	}
        		
        	
        }
        
       
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
       str.append("\n\n-- Percepci�n del Agente VANT --\n");
       str.append("Altura: "+this.altura+"\n");
       str.append("Posici�n: "+this.posiciongps.getX()+" - "+this.posiciongps.getY()+"\n");
       str.append("C�mara: \n\tVictimas: ");
    /*   for(int i=0;i<this.camara.getVictimas().size();i++)
    	   str.append("\n\t\t"+this.camara.getVictimas().get(i).getId());
       str.append("\n\tVictimarios: ");
       for(int i=0;i<this.camara.getVictimarios().size();i++)
    	   str.append("\n\t\t"+this.camara.getVictimarios().get(i).getId());*/
//str.append("\nAntena: "+this.antena.---); //Info de antena!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       str.append("\nEnerg�a: "+this.energia+"\n");

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
     public String getaltura(){
        return altura;
     }
     public void setaltura(String arg){
        this.altura = arg;
     }
     public Point getposiciongps(){
        return posiciongps;
     }
     public void setposiciongps(Point arg){
        this.posiciongps = arg;
     }
     public Camara getcamara(){
        return camara;
     }
     public void setcamara(Camara arg){
        this.camara = arg;
     }
     public Antena getantena(){
        return antena;
     }
     public void setantena(Antena arg){
        this.antena = arg;
     }
    
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        this.energia = arg;
     }
	
   
}
