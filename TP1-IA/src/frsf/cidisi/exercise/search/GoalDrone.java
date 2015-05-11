package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
        if  (((StateDrone)agentState).getintensidadSeņalA().isEmpty() && 
        		((StateDrone)agentState).getintensidadSeņalM().isEmpty() && 
        		((StateDrone)agentState).getintensidadSeņalB().isEmpty())
        	{
        	
            return true;
        	}
        return false;
	}
}