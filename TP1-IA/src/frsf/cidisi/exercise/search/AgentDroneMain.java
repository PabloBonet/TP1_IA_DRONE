package frsf.cidisi.exercise.search;


import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.ia.tp.paqueteGrafico.UIVentanaPrincipal;

public class AgentDroneMain {
	static UIVentanaPrincipal ventanaPrincipal;
    public static void main(String[] args) throws PrologConnectorException {
    	
    	ventanaPrincipal = new UIVentanaPrincipal();
    	
        AgentDrone agent = new AgentDrone();

        EnvironmentMap environment = new EnvironmentMap(ventanaPrincipal.getGrafo());

        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
