package frsf.cidisi.exercise.search;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.ia.tp.libreriaclases.Grafo;

public class EnvironmentMap extends Environment {

    public EnvironmentMap(Grafo grafo) {
        // Create the environment state
        this.environmentState = new StateMap(grafo);
    }


	public StateMap getEnvironmentState() {
        return (StateMap) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public  AgentDronePerception getPercept() { //TODO de acá llama el simulador para obtener una nueva percepción.
    	// Create a new perception to return
    	AgentDronePerception perception = new AgentDronePerception();

    	//TODO : Set the perceptions sensors
    	
//    	perception.setantena(this.getEnvironmentState().g)
    	perception.setaltura(this.getEnvironmentState().getAlturaAgente());
    	perception.setenergia(this.getEnvironmentState().getenergiaAgente());
    	perception.setposiciongps(this.getEnvironmentState().getposicionAgente());
//    	perception.setcamara(this.getEnvironmentState().getCamara());
    	// Return the perception
    	return perception;
    }

    
    public String toString() {
        return environmentState.toString();
    }

    
    public boolean agentFailed(Action actionReturned) {

        StateMap envState = this.getEnvironmentState();

        // TODO: Complete Method        

        return false;
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
    
    
}
