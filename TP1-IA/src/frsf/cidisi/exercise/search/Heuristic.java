package frsf.cidisi.exercise.search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class Heuristic implements IEstimatedCostFunction {

    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    @Override
    public double getEstimatedCost(NTree node) {
    	StateDrone agState = (StateDrone) node.getAgentState();
       
	
		if(agState.getvictimario() == null) // si no encontro al victimario
		{
			//No implementado
			//return calcularDistanciaAVictimario();
			
		}
		
        return 0;
    }
}
