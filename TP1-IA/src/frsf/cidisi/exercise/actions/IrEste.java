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
        // hacia el norte (arriba) de su ubucaci�n,
        // si esta� en el nivel bajo tiene que existir una esquina al norte de la esquina donde se encuentra y un camino 
        // directo que lo lleve hasta ella
        // debe tener energ�a
        // PostConditions: el agente se mantiene en el mismo nivel pero desplazado hacia el norte
        // se decrementa la energ�a
        
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
              
      
        Point sigPos = new Point();
        
        if(altura == "A"){
        	sigPos = FuncionesAuxiliares.irEste(posicion, altura);
        	
        	//System.out.println("EN IR ESTE---\n");
        	if(sigPos != null)
        	{
        		//System.out.print("Siguiente Posicion: "+sigPos.getX() + " "+sigPos.getY()+"\n");
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado=null;
        		for(NodoLista n: droneState.getintensidadSe�alA())
        		{
        			
        			if(cuadrante == n.getCuadrante())
        			{
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
        		{
        			System.out.println("puede ir");
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		//droneState.getintensidadSe�alA().remove(encontrado);
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
            		for(NodoLista n: droneState.getintensidadSe�alM())
            		{
            			
            			if(cuadrante == n.getCuadrante())
            			{
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			//droneState.getintensidadSe�alM().remove(encontrado);
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
                			if(droneState.getintensidadSe�alB().contains(nodoSig))
                			{
                				droneState.setenergia(energia - 1);
                			}
                			else
                			{
                				droneState.setenergia(energia - 2);
                			}
                			
                        	droneState.setubicacionD(sigPos);  //aca no ir�a la pos de nodoSig??
                        	droneState.getintensidadSe�alB().remove(nodoSig);
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
        
        System.out.println("En EXECUTE CON AGENTE Y ESTADO");
        // TODO: Use this conditions
        // PreConditions: null
        // PostConditions: null
        
        String altura = droneState.getaltura();
        Point posicion = droneState.getubicacionD();
        int energia = droneState.getenergia();
        Grafo subGrafo = new Grafo();
        
        boolean puedeIr = false;
              
       // Point siguientePos = irNorte(posicion,altura);
        Point sigPos = new Point();
        
        if(altura == "A"){
        	sigPos = FuncionesAuxiliares.irEste(posicion, altura);
        	
        	if(sigPos != null)
        	{
        		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
        		NodoLista encontrado=null;
        		for(NodoLista n: droneState.getintensidadSe�alA())
        		{
        			
        			if(cuadrante == n.getCuadrante())
        			{
        				encontrado = n;
        				break;
        			}
        		}
        		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
        		{
            		droneState.setenergia(energia - 1);
            		droneState.setubicacionD(sigPos);	
            		//droneState.getintensidadSe�alA().remove(encontrado);
            		puedeIr = true;
            		
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
            		for(NodoLista n: droneState.getintensidadSe�alM())
            		{
            			
            			if(cuadrante == n.getCuadrante())
            			{
            				encontrado = n;
            				break;
            			}
            		}
            		if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
            		{
            			droneState.setenergia(energia - 1);
            			droneState.setubicacionD(sigPos);
            			//droneState.getintensidadSe�alM().remove(encontrado);
            			puedeIr = true;
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
                			if(droneState.getintensidadSe�alB().contains(nodoSig))
                			{
                				droneState.setenergia(energia - 1);
                			}
                			else
                			{
                				droneState.setenergia(energia - 2);
                			}
                			
                        	droneState.setubicacionD(sigPos);  //aca no ir�a la pos de nodoSig??
                        	droneState.getintensidadSe�alB().remove(nodoSig);
                        	puedeIr = true;
                        	
                		}
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