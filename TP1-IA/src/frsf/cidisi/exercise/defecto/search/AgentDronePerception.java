package frsf.cidisi.exercise.defecto.search;


import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class AgentDronePerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	private int altura;
	private int posiciongps;
	private int camara;
	private int antena;
	private int energia;
	
 

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
        
        //AgentDrone agent = (AgentDrone) agentIn;
        //EnvironmentMap environment = (EnvironmentMap) environmentIn;
        //StateMap environmentState =
        //        environment.getEnvironmentState();
       
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	
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
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        this.energia = arg;
     }
	
   
}
