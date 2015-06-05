package frsf.cidisi.exercise.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

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
	   double costo =0 ;
	   
	   	if(padre==null){
	   		  return 0;
	   		}
	   	else
	   		{
	   		costo += 1;
	   		estadoPadre = (StateDrone) padre.getAgentState();
	   		if(estadoActual.getaltura() == "A")
	   		{
	   			if(estadoPadre.getaltura() == "M") // SI subo al nivel alto
	   			{
	   				costo += 20;
	   			}
	   			if(estadoActual.getintensidadSeñalA().size() == 0) //No hay personas
	   			{
	   				costo += 40; //estaba en un cuadrante sin señal
	   			}
	   		}
	   		else
	   		{
	   			if(estadoActual.getaltura() == "M")
	   			{
	   				if(estadoPadre.getaltura() == "B")//Si subo al nivel medio
	   				{
	   					costo += 20;
	   				}
	   				if(estadoActual.getintensidadSeñalM().size() == 0) //No hay personas
		   			{
		   				costo += 40;
		   			}
	   			}
	   			else //altura == B
	   			{
	   				if(estadoActual.getintensidadSeñalB().size() == 0) //No hay personas
		   			{
		   				costo += 40;
		   			}
	   			}
	   		}
	   		
	   		
	   		/*
	   		costo = 1;
	   		estadoPadre = (StateDrone) padre.getAgentState();
	   		
	   		//Comprueba si el agente sube un nivel. 
	   		if(estadoPadre.getaltura() == "B" && estadoActual.getaltura() == "M")
	   		{
	   			//Si sube. el costo es doble (2)
	   			costo = 2;
	   			
	   		}
	   		else
	   		{
	   			if(estadoPadre.getaltura() == "M" && estadoActual.getaltura() == "A")
	   			{
	   			//Si sube. el costo es doble (2)
		   			costo = 2;
	   			}
	   		}*/
	   		/*Retornaria 2 si no hay personas en la posicionActual
	   		 * Retornaria 1 si hay personas en la posicion actual
	   		 * */
	   		}
	   		
        return costo;
    }
}
