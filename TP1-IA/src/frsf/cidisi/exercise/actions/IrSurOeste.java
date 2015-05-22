package frsf.cidisi.exercise.actions;


import java.awt.Point;

import frsf.cidisi.exercise.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.Grafo;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.NodoLista;

public class IrSurOeste extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        StateDrone droneState = (StateDrone) s;
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
        Point sigPos = new Point();
        if(altura == "A")
        {
        	sigPos = FuncionesAuxiliares.irSurOeste(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado = null;
        		for(NodoLista n: droneState.getintensidadSeņalA())
        		{
        			if(cuadrante == n.getCuadrante())
        			{
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene seņal, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		return droneState;
        		}
        	}
        }
        else
        {
        	if(altura == "M")
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        		sigPos = FuncionesAuxiliares.irSurOeste(posicion, altura);
            	
        		if(sigPos != null)
        		{
        			NodoLista encontrado = null;
            		for(NodoLista n: droneState.getintensidadSeņalM())
            		{
            			
            			if(cuadrante == n.getCuadrante())
            			{
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene seņal, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			return droneState;
            		}
        		}
        	}
        	else //altura == B
        	{
        		subGrafo = droneState.getGrafoSubCuadrante();
        		Nodo nodoSig = FuncionesAuxiliares.irSurOesteBajo(posicion, subGrafo);
        		
        		if(nodoSig != null)
        		{
        			if(droneState.getintensidadSeņalB().contains(nodoSig))
        			{
        				droneState.setenergia(energia - 1);
        			}
        			else
        			{
        				droneState.setenergia(energia - 2);
        			}
        			
        			sigPos.setLocation(nodoSig.getPosX(), nodoSig.getPosY());
                	droneState.setubicacionD(sigPos);
                	droneState.getintensidadSeņalB().remove(sigPos);
                	return droneState;
                	
        		}
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
         StateDrone droneState = ((StateDrone) ast);
         
    	String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
        boolean puedeIr = false;
              
      
        Point sigPos = new Point();
        
        if(altura == "A"){
        	sigPos = FuncionesAuxiliares.irSurOeste(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado=null;
        		for(NodoLista n: droneState.getintensidadSeņalA())
        		{
        			
        			if(cuadrante == n.getCuadrante())
        			{
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene seņal, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		puedeIr = true;
            		
        		}
        	}
        }else{
        	if(altura == "M"){
        		int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        		sigPos = FuncionesAuxiliares.irSurOeste(posicion, altura);
            	
        		if(sigPos != null)
        		{
        			NodoLista encontrado=null;
            		for(NodoLista n: droneState.getintensidadSeņalM())
            		{
            			
            			if(cuadrante == n.getCuadrante())
            			{
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene seņal, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			puedeIr = true;
            		}
        		}
        	}else //Altura "B"
        	{
        		subGrafo = droneState.getGrafoSubCuadrante();
        		Nodo nodoSig = FuncionesAuxiliares.irSurOesteBajo(posicion, subGrafo);

        		if(nodoSig != null)
        		{
        			if(droneState.getintensidadSeņalB().contains(nodoSig))
        			{
        				droneState.setenergia(energia - 1);
        			}
        			else
        			{
        				droneState.setenergia(energia - 2);
        			}

        			sigPos.setLocation(nodoSig.getPosX(), nodoSig.getPosY());
        			droneState.setubicacionD(sigPos);
        			droneState.getintensidadSeņalB().remove(nodoSig);
        			puedeIr = true;

        		}
        	}
        }
        
        
        if (puedeIr) {
            environmentState.setposicionAgente(droneState.getubicacionD());
            environmentState.setenergiaAgente(droneState.getenergia());
            
            return environmentState;
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
        return "IrSurOeste";
    }
}