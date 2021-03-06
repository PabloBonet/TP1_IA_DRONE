package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
        if  (	FuncionesAuxiliares.seņalesVisitadas(((StateDrone)agentState).getintensidadSeņalA()) && 
        		FuncionesAuxiliares.seņalesVisitadas(((StateDrone)agentState).getintensidadSeņalM()) && 
        		((StateDrone)agentState).getenergia() > 0 &&
        		FuncionesAuxiliares.seņalesVisitadasB(((StateDrone)agentState).getintensidadSeņalB())  ||
        		((StateDrone)agentState).getvictimario() != null)
        	{
        	
            return true;
        	}
        return false;
	}
}