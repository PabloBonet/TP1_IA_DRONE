package frsf.cidisi.exercise.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.state.AgentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.Persona;

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
    	double costo = 0;

    	if(padre==null){
    		return 0;
    	}
    	else
    	{
    		estadoPadre = (StateDrone) padre.getAgentState();
    		if(estadoActual.getaltura() == "A")
    		{
    			if(estadoPadre.getaltura() == "M") // SI subo al nivel alto
    				costo += 200;
    			//sino no tiene costo
    		}
    		else
    		{
    			if(estadoActual.getaltura() == "M")
    			{
    				if(estadoPadre.getaltura() == "B")//Si subo al nivel medio: es mejor que subir al nivel alto
    					costo += 150;
    				if(estadoActual.getintensidadSeñalM().size() == 0) //No hay personas
    					costo += 400;
    			}
    			else //altura == B
    			{
    				if(estadoActual.getintensidadSeñalB().size() == 0) //No hay personas
    					costo += 400;
    				Nodo nodoActual = estadoActual.getGrafoSubCuadrante().nodoEnPosicion(estadoActual.getubicacionD());
    				if(nodoActual.getPersonas().size() > 0)
    				{
    					for(Persona p: nodoActual.getPersonas())
    						//si el nodo tiene un victimario es excelente ir ahí
    						if(p.esVictimario()){
    							costo += -1000;
    						}
    				}
    			}
    		}
    	}

    	return costo;
    }
}
