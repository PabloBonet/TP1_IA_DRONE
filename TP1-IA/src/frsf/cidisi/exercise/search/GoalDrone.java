package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;

public class GoalDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
        if  (FuncionesAuxiliares.se�alesVisitadas(((StateDrone)agentState).getintensidadSe�alA()) && 
        		FuncionesAuxiliares.se�alesVisitadas(((StateDrone)agentState).getintensidadSe�alM()) && 
        		((StateDrone)agentState).getenergia() > 0 &&
        		FuncionesAuxiliares.se�alesVisitadasB(((StateDrone)agentState).getintensidadSe�alB()) &&
        		((StateDrone)agentState).getaltura() == "B" ||
        		((StateDrone)agentState).getvictimario() != null)
        	{
        	
            return true;
        	}
        return false;
	}
}