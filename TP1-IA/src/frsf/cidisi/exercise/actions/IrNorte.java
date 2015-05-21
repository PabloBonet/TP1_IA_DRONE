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

public class IrNorte extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        StateDrone droneState = (StateDrone) s;
        
        // TODO: Use this conditions
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energía
        // hacia el norte (arriba) de su ubucación,
        // si está en el nivel bajo tiene que existir una esquina al norte de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energía
        // PostConditions: el agente se mantiene en el mismo nivel pero desplazado hacia el norte
        // se decrementa la energía
        
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
        
        Point sigPos = new Point();
        if(altura == "A")
        {
        	sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		boolean encontrado = false;
        		for(NodoLista n: droneState.getintensidadSeñalA())
        		{
        			if(cuadrante == n.getCuadrante())
        			{
        				encontrado = true;
        				break;
        			}
        		}
        		if(encontrado) //Si el cuadrante tiene señal, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		droneState.getintensidadSeñalA().remove(cuadrante);
            		return droneState;
        		}
        	}
        }
        else
        {
        	if(altura == "M")
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        		sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
            	
        		if(sigPos != null)
        		{
        			boolean encontrado = false;
            		for(NodoLista n: droneState.getintensidadSeñalM())
            		{
            			
            			if(cuadrante == n.getCuadrante())
            			{
            				encontrado = true;
            				break;
            			}
            		}
            		if(encontrado) //Si el cuadrante tiene señal, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			droneState.getintensidadSeñalM().remove(cuadrante);
            			return droneState;
            		}
        		}
        	}
        	else //altura == B
        	{
        		subGrafo = droneState.getGrafoSubCuadrante();
        		Nodo nodoSig = FuncionesAuxiliares.irNorteBajo(posicion, subGrafo);
        		
        		if(nodoSig != null)
        		{
        			if(droneState.getintensidadSeñalB().contains(nodoSig))
        			{
        				droneState.setenergia(energia - 1);
        			}
        			else
        			{
        				droneState.setenergia(energia - 2);
        			}
        			
        			sigPos.setLocation(nodoSig.getPosX(), nodoSig.getPosY());
                	droneState.setubicacionD(sigPos);
                	droneState.getintensidadSeñalB().remove(sigPos);
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

        // TODO: Use this conditions
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energía
        // hacia el norte (arriba) de su ubucación,
        // si está en el nivel bajo tiene que existir una esquina al norte de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energía
        // PostConditions: el agente se mantiene en el mismo nivel y se actualiza la ubicación del agente, tanto en el
        // agente como en el ambiente
        // se decrementa la energía
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = droneState.getGrafoSubCuadrante();
        boolean puedeIr = false;
        
        
        Point sigPos = new Point();
        if(altura == "A")
        {
        	sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		boolean encontrado = false;
        		for(NodoLista n: droneState.getintensidadSeñalA())
        		{
        			
        			if(cuadrante == n.getCuadrante())
        			{
        				encontrado = true;
        				break;
        			}
        		}
        		if(encontrado) //Si el cuadrante tiene señal, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		puedeIr = true;
            		environmentState.getintensidadSeñalA().remove(cuadrante);
        		}
        	}
        }
        else
        {
        	if(altura == "M")
        	{
        		sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
        		
        		if(sigPos != null)
        		{
        			int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        			boolean encontrado = false;
            		for(NodoLista n: droneState.getintensidadSeñalM())
            		{
            			
            			if(cuadrante == n.getCuadrante())
            			{
            				encontrado = true;
            				break;
            			}
            		}
            		if(encontrado) //Si el cuadrante tiene señal, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			puedeIr = true;
            			
            			environmentState.getintensidadSeñalM().remove(cuadrante);
            		}
        		}
        	}
        	else //altura == B
        	{
        		Nodo nodoSig = FuncionesAuxiliares.irNorteBajo(posicion, subGrafo);
        		
        		if(nodoSig != null)
        		{
        			if(droneState.getintensidadSeñalB().contains(nodoSig))
        			{
        				droneState.setenergia(energia - 1);
        			}
        			else
        			{
        				droneState.setenergia(energia - 2);
        			}
        			
                	droneState.setubicacionD(sigPos);
                	
                	puedeIr = true;
                	environmentState.getintensidadSeñalB().remove(nodoSig);
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
     * 
     * @return subGrafo: es el subgrafo que mantedra el agente en el nivel mas bajo
     * 					y lo usara para adquirir los nodos adyacentes.
     */
    public Grafo getSubgrafo(){
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