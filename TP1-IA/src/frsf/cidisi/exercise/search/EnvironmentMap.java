package frsf.cidisi.exercise.search;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.environment.Environment;
import frsf.ia.tp.libreriaclases.AntenaNB;
import frsf.ia.tp.libreriaclases.AntenaNMA;
import frsf.ia.tp.libreriaclases.Camara;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.Gps;
import frsf.ia.tp.libreriaclases.Grafo;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.NodoLista;

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
    public  AgentDronePerception getPercept() { //TODO de ac� llama el simulador para obtener una nueva percepci�n.
    	// Create a new perception to return
    	AgentDronePerception perception = new AgentDronePerception();

    	//TODO : Set the perceptions sensors
    	
    	String altura = this.getEnvironmentState().getAlturaAgente();
    	perception.setaltura(altura);
    	
    	//Asigna la camara
    	perception.setcamara(new Camara());
    	
    	Gps gps = new Gps();
    	gps.setPosiciongps(this.getEnvironmentState().getposicionAgente());
    	if(altura == "A")
    	{
    		//Asigna antena
    		AntenaNMA antena = new AntenaNMA(this.getEnvironmentState().getintensidadSe�alA());
    		perception.setantena(antena);
    	}
    	else
    	{
    		if(altura == "B")
    		{
    			gps.cargarGrafoSubCuadrante(this.getEnvironmentState().getgrafoMapa());
    			Nodo nodoAgente = this.getEnvironmentState().getgrafoMapa().nodoEnPosicion(this.getEnvironmentState().getposicionAgente());
    			Camara camara = new Camara(this.getEnvironmentState().getPersonasQueVe(nodoAgente, gps.getGrafoSubCuadrante()), nodoAgente);
    			perception.setcamara(camara);
    			AntenaNB antena = new AntenaNB(this.getEnvironmentState().getintensidadSe�alB());
        		perception.setantena(antena);
        		//si el agente esta en el nivel bajo se setea el grafo del gps con el correspondiente al subcuadrante donde se encuantra
        		
    		}
    		else
    		{
    			gps.cargarGrafoCuadrante(this.getEnvironmentState().getgrafoMapa());
    			AntenaNMA antena = new AntenaNMA(this.getEnvironmentState().getintensidadSe�alM());
    			int cuadranteActual = FuncionesAuxiliares.perteneceACuadrante(this.getEnvironmentState().getposicionAgente().x, this.getEnvironmentState().getposicionAgente().y);
//    			for(NodoLista nodo : this.getEnvironmentState().getintensidadSe�alM())
//        		{
//        			if(nodo.getCuadrante()/10 == cuadranteActual)
//        				antena.agregarIntensidadSe�al(nodo);
//        		}
        		perception.setantena(antena);
    		}
    	}
    	
    	
    	perception.setenergia(this.getEnvironmentState().getenergiaAgente());
    	
    	perception.setgps(gps);
    	
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
