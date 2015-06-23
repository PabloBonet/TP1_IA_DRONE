package frsf.cidisi.exercise.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
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
    		if(estadoActual.getaltura() == "A") //altura A
    		{
    			if(estadoPadre.getaltura() == "M") // Si subo un nivel
    				costo += 200;
    			else //costo de moverse
    				costo += 10;
    		}
    		else
    		{
    			if(estadoActual.getaltura() == "M") //altura M
    			{
    				if(estadoPadre.getaltura() == "B")//Si subo un nivel
    					costo += 200;
    				else if(estadoPadre.getaltura() == "A")//Si bajo un nivel tiene la mitad de costo que subir
    					costo += 100;
    				if(estadoActual.getintensidadSeñalM().size() == 0) //No hay personas en subcuadrante
    					costo += 400;
    				else
    					costo += 10;
    			}
    			else //altura B
    			{
    				if(estadoPadre.getaltura() == "M") // Si bajo un nivel
    					costo += 100;
    				if(estadoActual.getintensidadSeñalB().size() == 0) //No hay personas en los nodos
    					costo += 400;
    				else
    				{
    					Nodo nodoActual = estadoActual.getGrafoSubCuadrante().nodoEnPosicion(estadoActual.getubicacionD());
    					if(nodoActual.getPersonas().size() > 0) //si hay personas en el nodo
    					{
    						for(Persona p: nodoActual.getPersonas())
    							//si el nodo tiene un victimario es excelente ir ahí
    							if(p.esVictimario()){
    								costo += -1000;
    								return costo;
    							}

    						costo += 10;
    					}
    					else //si no hay personas en el nodo
    						costo += 100;
    				}
    			}
    		}
    	}

    	return costo;
    }
}
