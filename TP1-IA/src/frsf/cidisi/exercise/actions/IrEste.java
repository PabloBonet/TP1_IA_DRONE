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

public class IrEste extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        StateDrone droneState = (StateDrone) s;
        
        // TODO: Use this conditions
        // PreConditions: null
        // PostConditions: null
        
        // TODO: Use this conditions
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energia
        // hacia el norte (arriba) de su ubucación,
        // si estaá en el nivel bajo tiene que existir una esquina al norte de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energía
        // PostConditions: el agente se mantiene en el mismo nivel pero desplazado hacia el norte
        // se decrementa la energía
        
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
              
       // Point siguientePos = irNorte(posicion,altura);
        Point sigPos = new Point();
        
        if(altura == "A"){
        	sigPos = FuncionesAuxiliares.irEste(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado=null;
        		for(NodoLista n: droneState.getintensidadSeñalA())
        		{
        			
        			if(cuadrante == n.getCuadrante())
        			{
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene señal, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		droneState.getintensidadSeñalA().remove(encontrado);
            		return droneState;
        		}
        	}
        }else{
        	if(altura == "M"){
        		int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        		sigPos = FuncionesAuxiliares.irEste(posicion, altura);
            	//ENERGIA ES -1??
        		if(sigPos != null)
        		{
        			NodoLista encontrado=null;
            		for(NodoLista n: droneState.getintensidadSeñalM())
            		{
            			
            			if(cuadrante == n.getCuadrante())
            			{
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene señal, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			droneState.getintensidadSeñalM().remove(encontrado);
            			return droneState;
            		}
        		}
        	}else //Altura "B"
        	{
        		{
                	//	sigPos = FuncionesAuxiliares.irNorte(posicion,  altura);
                    	//ENERGIA ES -1??
                		
                		//sigPos = FuncionesAuxiliares.irNorteBajo(posicion, subGrafo);
                		subGrafo = droneState.getGrafoSubCuadrante();
                		Nodo nodoSig = FuncionesAuxiliares.irEsteBajo(posicion, subGrafo);
                		
                		
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
                			
                        	droneState.setubicacionD(sigPos);  //aca no iría la pos de nodoSig??
                        	droneState.getintensidadSeñalB().remove(sigPos);
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
        StateDrone agState = ((StateDrone) ast);

        // TODO: Use this conditions
        // PreConditions: null
        // PostConditions: null
        
        if (true) {
            // Update the real world
            
            // Update the agent state
            
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
        return "IrEste";
    }
}