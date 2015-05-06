package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (true)//(StateDrone.getintensidadSeñalA().isEmty() && StateDrone.getintensidadSeñalM().isEmty() && StateDrone.getintensidadSeñalB.isEmpty())
        	{
            return true;
        	}
        return false;
	}
}