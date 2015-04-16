

package frsf.cidisi.exercise.default.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (true) //((intensidadSeñalA=empty) & (intensidadSeñalM=empty) & (intensidadSeñalB=empty))
        	{
            return true;
        	}
        return false;
	}
}