package frsf.cidisi.exercise.actions;


import java.awt.Point;

import frsf.cidisi.exercise.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
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
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energ�a
        // hacia el norte (arriba) de su ubucaci�n,
        // si esta� en el nivel bajo tiene que existir una esquina al norte de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energ�a
        // PostConditions: el agente se mantiene en el mismo nivel pero desplazado hacia el norte
        // se decrementa la energ�a
        
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        
       // Point siguientePos = irNorte(posicion,altura);
        Point sigPos = new Point();
        if(altura == "A")
        {
        	sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
        	//ENERGIA ES -1??
        	if(sigPos != null)
        	{
        		boolean encontrado = false;
        		for(NodoLista n: droneState.getintensidadSe�alA())
        		{
        			int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        			if(cuadrante == n.getCuadrante())
        			{
        				encontrado = true;
        				break;
        			}
        		}
        		if(encontrado) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
        		}
        	}
        }
        else
        {
        	if(altura == "M")
        	{
        		sigPos = FuncionesAuxiliares.irNorte(posicion, altura);
            	//ENERGIA ES -1??
        		if(sigPos != null)
        		{
        			boolean encontrado = false;
            		for(NodoLista n: droneState.getintensidadSe�alM())
            		{
            			int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
            			if(cuadrante == n.getCuadrante())
            			{
            				encontrado = true;
            				break;
            			}
            		}
            		if(encontrado) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            		}
        		}
        	}
        	else //altura == B
        	{
        		sigPos = FuncionesAuxiliares.irNorte(posicion,  altura);
            	//ENERGIA ES -1??
        		if(sigPos != null)
        		{
        			droneState.setenergia(energia - 1);
                	droneState.setubicacionD(sigPos);
        		}
        	}
        }
        	
        
        /*if(altura != "B")
        {
        	if(altura == "M")
        	{
        		
        	}
        	else //altura == alta
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(posicion.x, posicion.y);
        		int proxCuadrante = FuncionesAuxiliares.perteneceACuadrante(posicion.x, (posicion.y));
        		
        		if(cuadrante != 1 && cuadrante != 2)
        		{
        			
        			
        			posicion.setLocation(posicion.x, (posicion.y)+1);
        			
        			//DEBERIA DISMINUIR LA ENERGIA??
        			int energia = droneState.getenergia();
        			droneState.setenergia(energia -1);
        			return droneState;
        		}
        	}
        	
        	
        
        }*/
        
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
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energ�a
        // hacia el norte (arriba) de su ubucaci�n,
        // si esta� en el nivel bajo tiene que existir una esquina al norte de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energ�a
        // PostConditions: el agente se mantiene en el mismo nivel y se actualiza la ubicaci�n del agente, tanto en el
        // agente como en el ambiente
        // se decrementa la energ�a
        
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
        return "IrNorte";
    }
}