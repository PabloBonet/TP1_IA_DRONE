package frsf.cidisi.exercise.defecto.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (true)//(StateDrone.getintensidadSeņalA().isEmty() && StateDrone.getintensidadSeņalM().isEmty() && StateDrone.getintensidadSeņalB.isEmpty())
        	{
            return true;
        	}
        return false;
	}
}