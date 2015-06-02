package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
        if  (FuncionesAuxiliares.señalesVisitadas(((StateDrone)agentState).getintensidadSeñalA()) && 
        		FuncionesAuxiliares.señalesVisitadas(((StateDrone)agentState).getintensidadSeñalM()) && 
        		((StateDrone)agentState).getenergia() > 0 &&
        		FuncionesAuxiliares.señalesVisitadasB(((StateDrone)agentState).getintensidadSeñalB()) &&
        		((StateDrone)agentState).getaltura() == "B" ||
        		((StateDrone)agentState).getvictimario() != null)
        	{
        	
            return true;
        	}
        return false;
	}
}