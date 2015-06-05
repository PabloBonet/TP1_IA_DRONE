package frsf.cidisi.exercise.actions;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.exercise.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.Grafo;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.NodoLista;
import frsf.ia.tp.libreriaclases.Persona;

public class IrNorte extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        StateDrone droneState = (StateDrone) s;
        
        // TODO: Use this conditions
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energ�a
        // hacia el norte (arriba) de su ubucaci�n,
        // si est� en el nivel bajo tiene que existir una esquina al norte de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energ�a
        // PostConditions: el agente se mantiene en el mismo nivel pero desplazado hacia el norte
        // se decrementa la energ�a
        
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
        
        Point sigPos = new Point();
        if(altura == "A" && droneState.getintensidadSe�alA().size()>0)
        {
        	sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado = null;
        		for(NodoLista n: droneState.getintensidadSe�alA())
        		{
        			if(cuadrante == n.getCuadrante() && !n.getVisitado())
        			{
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		return droneState;
        		}
        	}
        }
        else
        {
        	if(altura == "M" && droneState.getintensidadSe�alM().size()>0)
        	{
        		
        		sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
            	
        		if(sigPos != null)
        		{
        			int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        			NodoLista encontrado = null;
            		for(NodoLista n: droneState.getintensidadSe�alM())
            		{
            			
            			if(cuadrante == n.getCuadrante() && !n.getVisitado())
            			{
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			return droneState;
            		}
        		}
        	}
        	else //altura == B
        	{
        		if(altura == "B" && droneState.getintensidadSe�alB().size()>0)
            	{

        		subGrafo = droneState.getGrafoSubCuadrante();
        		Nodo nodoSig = FuncionesAuxiliares.irNorteBajo(posicion, subGrafo);
        		if(nodoSig != null && !FuncionesAuxiliares.se�alesVisitadasB(droneState.getintensidadSe�alB()))
        		{
        			if(FuncionesAuxiliares.contieneNodoConID(droneState.getintensidadSe�alB(),nodoSig.getId()))
            			{
            				if(subGrafo.buscarNodo(nodoSig.getId()).getVisitado())
            				{
            					droneState.setenergia(energia - 2);
            				}
            				else
            				{
            					(subGrafo.buscarNodo(nodoSig.getId())).visitar();
                				droneState.setenergia(energia - 1);	
            				}
            				
            			}
        			else
        			{
        				droneState.setenergia(energia - 2);
        			}
        			
        			sigPos.setLocation(nodoSig.getPosX(), nodoSig.getPosY());
                	droneState.setubicacionD(sigPos);
                	return droneState;
                	
        		}
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

        // TODO: Use this conditions
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energ�a
        // hacia el norte (arriba) de su ubucaci�n,
        // si est� en el nivel bajo tiene que existir una esquina al norte de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energ�a
        // PostConditions: el agente se mantiene en el mismo nivel y se actualiza la ubicaci�n del agente, tanto en el
        // agente como en el ambiente
        // se decrementa la energ�a
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = droneState.getGrafoSubCuadrante();
        boolean puedeIr = false;
        
        
        Point sigPos = new Point();
        if(altura == "A" && droneState.getintensidadSe�alA().size()>0)
        {
        	sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado = null;
        		for(NodoLista n: droneState.getintensidadSe�alA())
        		{
        			
        			if(cuadrante == n.getCuadrante() && !n.getVisitado())
        			{
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);
            		puedeIr = true;
            		
        		}
        	}
        }
        else
        {
        	if(altura == "M" && droneState.getintensidadSe�alM().size()>0)
        	{
        		sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
        		int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);

        		if(sigPos != null)
        		{
        			NodoLista encontrado = null;
            		for(NodoLista n: droneState.getintensidadSe�alM())
            		{
            			
            			if(cuadrante == n.getCuadrante() && !n.getVisitado())
            			{
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			puedeIr = true;
            			
            		}
        		}
        	}
        	else //altura == B
        	{
        		if(altura == "B" && droneState.getintensidadSe�alB().size()>0)
            	{
        		Nodo nodoSig = FuncionesAuxiliares.irNorteBajo(posicion, subGrafo);

        		if(nodoSig != null && !FuncionesAuxiliares.se�alesVisitadasB(droneState.getintensidadSe�alB()))
        		{
        			if(FuncionesAuxiliares.contieneNodoConID(droneState.getintensidadSe�alB(),nodoSig.getId()))
        			{
        				if(subGrafo.buscarNodo(nodoSig.getId()).getVisitado())
        				{
        					droneState.setenergia(energia - 2);
        				}
        				else
        				{
        					(subGrafo.buscarNodo(nodoSig.getId())).visitar();
        					
            				droneState.setenergia(energia - 1);	
        				}
        				
        			}
        			else
        			{
        				droneState.setenergia(energia - 2);
        			}
        			
        			sigPos.setLocation(nodoSig.getPosX(), nodoSig.getPosY());
                	droneState.setubicacionD(sigPos);
                	puedeIr = true;
                	environmentState.getgrafoMapa().buscarNodo(nodoSig.getId()).visitar();
        		}
        	}
        }
        
        if (puedeIr) {
            environmentState.setposicionAgente(droneState.getubicacionD());
            environmentState.setenergiaAgente(droneState.getenergia());
            
            return environmentState;
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
        return "IrNorte";
    }
}