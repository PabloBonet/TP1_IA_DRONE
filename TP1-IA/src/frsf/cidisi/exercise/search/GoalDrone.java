package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (((StateDrone)agentState).getintensidadSe�alA().isEmpty() && 
        		((StateDrone)agentState).getintensidadSe�alM().isEmpty() && 
        		((StateDrone)agentState).getintensidadSe�alB().isEmpty())
        	{
        	
            return true;
        	}
        return false;
	}
}