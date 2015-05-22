package frsf.cidisi.exercise.search;



import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;

/**
 * This class can be used in any search strategy like
 * Uniform Cost.
 */
public class CostFunction implements IStepCostFunction {

    /**
     * This method calculates the cost of the given NTree node.
     */
    @Override
    public double calculateCost(NTree node) {
        
        //TODO: Complete Method
    	NTree padre=node.getParent();
	   	StateDrone estadoPadre;
	    StateDrone estadoActual=(StateDrone)node.getAgentState();
	   
	   	if(padre==null){
	   		  return 0;
	   		}
	   	else
	   		{
	   		/*Retornaria 2 si no hay personas en la posicionActual
	   		 * Retornaria 1 si hay personas en la posicion actual
	   		 * */
	   		}
	   		
        return 0;
    }
}
