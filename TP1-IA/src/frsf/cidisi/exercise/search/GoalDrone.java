package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
        if  (((StateDrone)agentState).getintensidadSeñalA().isEmpty() && 
        		((StateDrone)agentState).getintensidadSeñalM().isEmpty() && 
        		((StateDrone)agentState).getenergia() > 0 &&
        		((StateDrone)agentState).getintensidadSeñalB().isEmpty() ||
        		((StateDrone)agentState).getvictimario() != null)
        	{
        	
            return true;
        	}
        return false;
	}
}