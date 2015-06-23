package frsf.cidisi.exercise.search;

import java.util.Vector;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.NodoLista;

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
    	Vector<NTree> hermanos = new Vector<NTree>(); //contiene los nodos hijos de un mismo padre
    	NTree se�alA = new NTree();
    	
    	if(agState != null && agState.getvictimario() == null) // si no encontro al victimario
    	{
    		//ir al cuadrante con mayor intensidad de se�al
    			//comparar los hijos del nodo padre.

    	hermanos = node.getParent().getSons();
    	if(agState.getaltura()=="A")
    	{
    		se�alA = nodoMayorIntensidadA(hermanos);
    	}
    	else
    		if(agState.getaltura()=="M")
    		{
    			se�alA = nodoMayorIntensidadM(hermanos);
    		}
    		else
    			se�alA = nodoMayorIntensidadB(hermanos);

    	return se�alA.getCost();
    	}

    	return 0;
    }

    /**
     * Retorna el nodo con mayor intensidad de se�al para el nivel alto
     * @param getintensidadSe�alA
     * @return
     */
	private NTree nodoMayorIntensidadA(Vector<NTree> hermanos) {
		int se�al=0;
		NTree nodoL=new NTree();
		for(NTree t: hermanos)
		{
			for(NodoLista n: ((StateDrone) t.getAgentState()).getintensidadSe�alA()){
				if(se�al < n.getIntensidad()){
					se�al=n.getIntensidad();
					nodoL=t;
				}
			}
			t.setCost(100);
		}
		
		nodoL.setCost(50);
		return nodoL;
			
	}
	
	/**
     * Retorna el nodo con mayor intensidad de se�al para el nivel medio
     * @param getintensidadSe�alA
     * @return
     */
	private NTree nodoMayorIntensidadM(Vector<NTree> hermanos) {
		int se�al=0;
		NTree nodoL=new NTree();
		for(NTree t: hermanos)
		{
			for(NodoLista n: ((StateDrone) t.getAgentState()).getintensidadSe�alM()){
				if(se�al < n.getIntensidad()){
					se�al=n.getIntensidad();
					nodoL=t;
				}
			}
			t.setCost(75);
		}
		nodoL.setCost(30);
		return nodoL;
	}
	
	/**
     * Retorna el nodo con mayor intensidad de se�al para el nivel bajo
     * @param getintensidadSe�alA
     * @return
     */
	private NTree nodoMayorIntensidadB(Vector<NTree> hermanos) {
		int se�al=0;
		NTree nodoL=new NTree();
		for(NTree t: hermanos)
		{
			for(Nodo n: ((StateDrone) t.getAgentState()).getintensidadSe�alB()){
				if(se�al < n.getPersonas().size()){
					se�al=n.getPersonas().size();
					nodoL=t;
				}
			}
			t.setCost(30);
		}
		nodoL.setCost(5);
		return nodoL;
	}
}
