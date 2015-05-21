package frsf.cidisi.exercise.actions;


import java.awt.Point;

import frsf.cidisi.exercise.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.NodoLista;

public class Bajar extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        StateDrone agState = (StateDrone) s;
        
        // PreConditions: el agente no tiene que estar en el nivel bajo, tiene que tener energ�a  
        //y debe existir intensisad de se�al en el cuadrante donde se encuantra
        // PostConditions: desciende un nivel y se ubica:
        // - Si descendi� al nivel medio: en el centro del primer subcuadrante
        // - Si descendi� al nivel bajo: en la esquina central del subcuadrante
        
        String altura = agState.getaltura();
        if(altura != "B" && agState.getenergia()>1)
        {
        	int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(agState.getubicacionD().x,agState.getubicacionD().y);
    		int cuadrante = subCuadrante%10;
        	if(altura == "A" && agState.getintensidadSe�alA().size()>0)
        	{
        		for(NodoLista n: agState.getintensidadSe�alA())
        		{
        			//si existe intensidad de se�al en el subcuadrante inferior de donde se encuantra el agente
        			if(cuadrante == n.getCuadrante())
        			{
        				agState.setaltura("M");
                		agState.setubicacionD(FuncionesAuxiliares.bajarASubCuadranteM(cuadrante));
                		//elimina el nodo con el cuadrante actual de la lista de se�al de nivel alto del agente
        				agState.removerCuadranteNivelA(cuadrante);
        				agState.setenergia(agState.getenergia()-1);
        				return agState;
        			}
        		}
        		//la lista de intensidad de se�al de nivel alto est� vac�a 
        		return null;
        	}
        	//el agente est� en nivel medio
        	else
        	{
        		for(NodoLista n: agState.getintensidadSe�alM())
        		{
        			//si existe intensidad de se�al en el subcuadrante inferior de donde se encuantra el agente
        			if(subCuadrante == n.getCuadrante())
        			{
        				agState.setaltura("B");
                		agState.setubicacionD(FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante()));
                		//elimina el nodo de la lista de se�al de nivel medio del agente
        				agState.removerCuadranteNivelM(subCuadrante);
        				agState.setenergia(agState.getenergia()-1);
        				return agState;
        			}
        		}
        		//la lista de intensidad de se�al de nivel alto est� vac�a 
        		return null;
        	}
        }

        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        StateMap environmentState = (StateMap) est;
        StateDrone agState = ((StateDrone) ast);

        // PreConditions: el agente no tiene que estar en el nivel bajo, tiene que tener energ�a  
        //y debe existir intensisad de se�al en el cuadrante donde se encuantra
        // PostConditions: desciende un nivel y se ubica:
        // - Si descendi� al nivel medio: en el centro del primer subcuadrante
        // - Si descendi� al nivel bajo: en la esquina central del subcuadrante
        // y actualiza la energ�a y altura de los estados de agente y entorno.
        
        String altura = agState.getaltura();
        if(altura != "B" && agState.getenergia()>1)
        { 
        	int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(agState.getubicacionD().x,agState.getubicacionD().y);
    		int cuadrante = subCuadrante%10;
        	if(altura == "A" && agState.getintensidadSe�alA().size()>0)
        	{
        		for(NodoLista n: agState.getintensidadSe�alA())
        		{
        			//si existe intensidad de se�al en el subcuadrante inferior de donde se encuantra el agente
        			if(cuadrante == n.getCuadrante())
        			{
        				agState.setaltura("M");
        				environmentState.setAlturaAgente("M");
        				Point uAgente = FuncionesAuxiliares.bajarASubCuadranteM(cuadrante);
                		agState.setubicacionD(uAgente);
                		environmentState.setposicionAgente(uAgente);
                		//elimina el nodo con el cuadrante actual de la lista de se�al de nivel alto del agente
        				agState.removerCuadranteNivelA(cuadrante);
        				agState.setenergia(agState.getenergia()-1);
        				environmentState.setenergiaAgente(environmentState.getenergiaAgente()-1);
        				return environmentState;
        			}
        		}
        		//la lista de intensidad de se�al de nivel alto est� vac�a 
        		return null;
        	}
        	//el agente est� en nivel medio
        	else
        	{
        		for(NodoLista n: agState.getintensidadSe�alM())
        		{
        			//si existe intensidad de se�al en el subcuadrante inferior de donde se encuantra el agente
        			if(subCuadrante == n.getCuadrante())
        			{
        				agState.setaltura("B");
        				environmentState.setAlturaAgente("B");
        				Point uAgente = FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante());
                		agState.setubicacionD(uAgente);
        				environmentState.setposicionAgente(uAgente);
                		//elimina el nodo de la lista de se�al de nivel medio del agente
        				agState.removerCuadranteNivelM(subCuadrante);
        				agState.setenergia(agState.getenergia()-1);
        				environmentState.setenergiaAgente(environmentState.getenergiaAgente()-1);
        				return environmentState;
        			}
        		}
        		//la lista de intensidad de se�al de nivel alto est� vac�a 
        		return null;
        	}
        }

        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "Bajar";
    }
}