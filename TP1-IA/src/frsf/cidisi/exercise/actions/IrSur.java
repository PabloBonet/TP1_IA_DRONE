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

public class IrSur extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        StateDrone droneState = (StateDrone) s;
        
        // TODO: Use this conditions
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energía
        // hacia el sur (abajo) de su ubucación,
        // si está en el nivel bajo tiene que existir una esquina al sur de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energía
        // PostConditions: el agente se mantiene en el mismo nivel pero desplazado hacia el norte
        // se decrementa la energía
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
        Point sigPos = new Point();
        if(altura == "A" && droneState.getintensidadSeñalA().size()>0)
        {
        	sigPos = FuncionesAuxiliares.irSur(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		boolean encontrado = false;
        		for(NodoLista n: droneState.getintensidadSeñalA())
        		{
        			if(cuadrante == n.getCuadrante() && !n.getVisitado())
        			{
        				n.visitar();
        				encontrado = true;
        				break;
        			}
        		}
        		if(encontrado) //Si el cuadrante tiene señal, se mueve a ese cuadrante
        		{
        			//droneState.removerCuadranteNivelA(cuadrante);
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		return droneState;
        		}
        	}
        }
        else
        {
        	if(altura == "M" && droneState.getintensidadSeñalM().size()>0)
        	{
        		
        		sigPos = FuncionesAuxiliares.irSur(posicion, altura);
        		
        		
        		if(sigPos != null)
        		{
        			int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        			boolean encontrado = false;
            		for(NodoLista n: droneState.getintensidadSeñalM())
            		{
            			
            			if(cuadrante == n.getCuadrante() && !n.getVisitado())
            			{
            				n.visitar();
            				encontrado = true;
            				break;
            			}
            		}
            		if(encontrado) //Si el cuadrante tiene señal, se mueve a ese cuadrante
            		{
            		//	droneState.removerCuadranteNivelM(cuadrante);
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			return droneState;
            		}
        		}
        	}
        	else //altura == B
        	{
        		subGrafo = droneState.getGrafoSubCuadrante();
        		Nodo nodoSig = FuncionesAuxiliares.irSurBajo(posicion, subGrafo);
        		
        		if(nodoSig != null)
        		{
        			if(FuncionesAuxiliares.contieneNodoConID(droneState.getintensidadSeñalB(),nodoSig.getId()))
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
        // hacia el sur (abajo) de su ubucación,
        // si está en el nivel bajo tiene que existir una esquina al sur de la esquina donde se encuentra y un camino 
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
        if(altura == "A" && droneState.getintensidadSeñalA().size()>0)
        {
        	sigPos = FuncionesAuxiliares.irSur(posicion, altura);
System.out.println("EN IR SUR en A---");
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado = null;
        		for(NodoLista n: droneState.getintensidadSeñalA())
        		{
        			
        			if(cuadrante == n.getCuadrante() && !n.getVisitado())
        			{
        				n.visitar();
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene señal, se mueve a ese cuadrante
        		{
        			//droneState.removerCuadranteNivelA(cuadrante);
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		puedeIr = true;
        		}
        	}
        }
        else
        {
        	
        	if(altura == "M" && droneState.getintensidadSeñalM().size()>0)
        	{
        		sigPos = FuncionesAuxiliares.irSur(posicion, altura);
        		
        		if(sigPos != null)
        		{
        			int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        			NodoLista encontrado = null;
            		for(NodoLista n: droneState.getintensidadSeñalM())
            		{
            			
            			if(cuadrante == n.getCuadrante() && !n.getVisitado())
            			{
            				n.visitar();
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene señal, se mueve a ese cuadrante
            		{
            		//	droneState.removerCuadranteNivelM(cuadrante);
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			puedeIr = true;
            		}
        		}
        	}
        	else //altura == B
        	{
        		Nodo nodoSig = FuncionesAuxiliares.irSurBajo(posicion, subGrafo);
        		
        		if(nodoSig != null)
        		{
        			if(FuncionesAuxiliares.contieneNodoConID(droneState.getintensidadSeñalB(),nodoSig.getId()))
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
        			Point sig = new Point(nodoSig.getPosX(), nodoSig.getPosY());
                	droneState.setubicacionD(sig);
                	
                	puedeIr = true;
                	environmentState.getgrafoMapa().buscarNodo(nodoSig.getId()).visitar();
        		}
        	}
        }
        
        if (puedeIr) {
            environmentState.setposicionAgente(droneState.getubicacionD());
            environmentState.setenergiaAgente(droneState.getenergia());
System.out.println("FUE AL SUR!!\nPosicion agente: "+environmentState.getposicionAgente().getX()+" "+environmentState.getposicionAgente().getY());
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
        return "IrSur";
    }
}