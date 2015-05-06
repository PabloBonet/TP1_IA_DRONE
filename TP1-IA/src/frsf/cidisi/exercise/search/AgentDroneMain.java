package frsf.cidisi.exercise.search;


import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class AgentDroneMain {

    public static void main(String[] args) throws PrologConnectorException {
        AgentDrone agent = new AgentDrone();

        EnvironmentMap environment = new EnvironmentMap();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
