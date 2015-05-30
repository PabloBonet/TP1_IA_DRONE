package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
        if  (((StateDrone)agentState).getintensidadSe�alA().isEmpty() && 
        		((StateDrone)agentState).getintensidadSe�alM().isEmpty() && 
        		((StateDrone)agentState).getenergia() > 0 &&
        		((StateDrone)agentState).getintensidadSe�alB().isEmpty() &&
        		((StateDrone)agentState).getaltura() == "B" ||
        		((StateDrone)agentState).getvictimario() != null)
        	{
        	
            return true;
        	}
        return false;
	}
}