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
        // PreConditions: Si el agente esta en el nivel alto o medio tiene que existir un cuadrante con energia
        // hacia el este (derecha) de su ubucaci�n,
        // si esta� en el nivel bajo tiene que existir una esquina al este de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energ�a
        // PostConditions: el agente se mantiene en el mismo nivel pero desplazado hacia el este
        // se decrementa la energ�a
        
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
      
        
        
        if(altura == "A" && droneState.getintensidadSe�alA().size()>0){
        	Point sigPos = FuncionesAuxiliares.irEste(posicion, altura);

        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado=null;
//System.out.println("Ir Este (arbol)---- Se�al A: ");
//ystem.out.println("Posicion: "+ droneState.getubicacionD().x + " " + droneState.getubicacionD().y);
        		for(NodoLista n: droneState.getintensidadSe�alA())
        		{
System.out.println(n.getCuadrante()+"-"+n.getIntensidad()+"-"+n.getVisitado()+"; ");
        			if(cuadrante == n.getCuadrante() && !n.getVisitado())
        			{
        			//	n.visitar();
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
        		{
        			//droneState.removerCuadranteNivelA(cuadrante);
            		
        			droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		return droneState;
        		}
        	}
        }
        else{
        	if(altura == "M" && droneState.getintensidadSe�alM().size()>0){
        		
        		Point sigPos = FuncionesAuxiliares.irEste(posicion, altura);
        		System.out.println("Ir Este (arbol)---- Se�al B: ");
        		System.out.println("Posicion: "+ droneState.getubicacionD().x + " " + droneState.getubicacionD().y);
        		if(sigPos != null)
        		{
        			int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        			NodoLista encontrado=null;
            		for(NodoLista n: droneState.getintensidadSe�alM())
            		{
            			
            			if(cuadrante == n.getCuadrante() && !n.getVisitado())
            			{
            			//	n.visitar();
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
            		{
            			//droneState.removerCuadranteNivelM(cuadrante);
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			return droneState;
            		}
        		}
        	}
        	else //Altura "B"
        	{
        			subGrafo = droneState.getGrafoSubCuadrante();
        			Nodo nodoSig = FuncionesAuxiliares.irEsteBajo(posicion, subGrafo);

        		if(nodoSig != null && !FuncionesAuxiliares.se�alesVisitadasB(droneState.getintensidadSe�alB()))
        		{
        			if(FuncionesAuxiliares.contieneNodoConID(droneState.getintensidadSe�alB(),nodoSig.getId()))
        			//if(droneState.getintensidadSe�alB().contains(nodoSig))
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

        			Point sigPos = new Point(nodoSig.getPosX(), nodoSig.getPosY());
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
        // PreConditions: null
        // PostConditions: null
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
        boolean puedeIr = false;
              
         Point sigPos = new Point();
        
        if(altura == "A" && droneState.getintensidadSe�alA().size()>0){
        	sigPos = FuncionesAuxiliares.irEste(posicion, altura);
System.out.println("EN IR ESTE en A---");
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado=null;
        		for(NodoLista n: droneState.getintensidadSe�alA())
        		{
        			
        			if(cuadrante == n.getCuadrante() && !n.getVisitado())
        			{
       // 				n.visitar();
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
        		{
        			//droneState.removerCuadranteNivelA(cuadrante);
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		puedeIr = true;
            		
        		}
        	}
        }
        else{
        	if(altura == "M" && droneState.getintensidadSe�alM().size()>0){
        		
        		sigPos = FuncionesAuxiliares.irEste(posicion, altura);
        		if(sigPos != null)
        		{
        			int cuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
        			NodoLista encontrado=null;
            		for(NodoLista n: droneState.getintensidadSe�alM())
            		{
            			
            			if(cuadrante == n.getCuadrante() && !n.getVisitado())
            			{
        //    				n.visitar();
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
            		{
            			//droneState.removerCuadranteNivelM(cuadrante);
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			puedeIr = true;
            		}
        		}
        	}
        	else //Altura "B"
        	{
        		if(droneState.getintensidadSe�alB().isEmpty())
        		{
        			return null;
        		}
        		subGrafo = droneState.getGrafoSubCuadrante();
        		Nodo nodoSig = FuncionesAuxiliares.irEsteBajo(posicion, subGrafo);

        		if(nodoSig != null && !FuncionesAuxiliares.se�alesVisitadasB(droneState.getintensidadSe�alB()))
        		{
        			if(FuncionesAuxiliares.contieneNodoConID(droneState.getintensidadSe�alB(),nodoSig.getId()))
            			//if(droneState.getintensidadSe�alB().contains(nodoSig))
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
       // 			environmentState.getgrafoMapa().buscarNodo(nodoSig.getId()).visitar();
        		}
        	}
        }
        
        if (puedeIr) {
            environmentState.setposicionAgente(droneState.getubicacionD());
            environmentState.setenergiaAgente(droneState.getenergia());
            
            
System.out.println("FUE AL ESTE!!\nPosicion agente: "+environmentState.getposicionAgente().getX()+" "+environmentState.getposicionAgente().getY());
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