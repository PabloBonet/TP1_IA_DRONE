package frsf.cidisi.exercise.defecto.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (true)//(StateDrone.getintensidadSe�alA().isEmty() && StateDrone.getintensidadSe�alM().isEmty() && StateDrone.getintensidadSe�alB.isEmpty())
        	{
            return true;
        	}
        return false;
	}
}