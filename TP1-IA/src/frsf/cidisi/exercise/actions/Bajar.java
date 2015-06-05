package frsf.cidisi.exercise.actions;


import java.awt.Point;
import java.io.IOException;

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
    		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(agState.getubicacionD().x, agState.getubicacionD().y);
    		if(altura == "A" )
    		{
    			
    			
    			//No baja si el cuadrante ya esta visitado
    			/*for(NodoLista n: agState.getintensidadSe�alA())
    			{
    				if(n.getCuadrante() == cuadrante && n.getVisitado())
    				{
    					return null;
    				}
    			}*/
    			
    			
    			
    			/*
    			 * Esta parte restringe para que no baje a lugares donde no hay se�al o que ya este visitado
    			 * 
    			 * */
        		boolean conSe�al = false;
        		for(NodoLista n: agState.getintensidadSe�alA())
    			{
    				if(n.getCuadrante() == cuadrante && !n.getVisitado())
    				{
    					conSe�al = true;
    					break;
    					//return null;
    					}
    			}
        		if(!conSe�al)
        		{
        			return null;
        		}
    			agState.setaltura("M");
    			agState.setubicacionD(FuncionesAuxiliares.bajarASubCuadranteM(cuadrante));
    			agState.setenergia(agState.getenergia()-1);
    			
    			System.out.println("bajar a M (en arbol)");
    			return agState;
    		}
        	//el agente est� en nivel medio
    		else
    		{
    			//No baja si el subcuadrante ya esta visitado
    			/*for(NodoLista n: agState.getintensidadSe�alM())
    			{
    				if(n.getCuadrante() == subCuadrante && n.getVisitado())
    				{
    					return null;
    				}
    			}*/
    			
    			
    			
    			/*
    			 * Esta parte restringe para que no baje a lugares donde no hay se�al o que ya este visitado
    			 * 
    			 * */
    			boolean conSe�al = false;
        		for(NodoLista n: agState.getintensidadSe�alM())
    			{
    				if(n.getCuadrante() == cuadrante && !n.getVisitado())
    				{
    					conSe�al = true;
    					break;
    					//return null;
    					}
    			}
        		if(!conSe�al)
        		{
        			return null;
        		}
    			
    			agState.setaltura("B");
    		
    			Point uAgente = null;
    			System.out.println("Sub Cuadrante: " + subCuadrante);
    			if(agState.getGrafoSubCuadrante().getListaNodos().size() > 0)
    			{
    				 uAgente = FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante());
				
    			}
    			else
    			{
    				//Si no tengo el grafo en nivel medio (Todavia no bajo)
    				//coloco como posicion el centro del cuadrante
    				 uAgente = FuncionesAuxiliares.centroSubcuadranteBajo(subCuadrante);
    			}
    			if(uAgente != null)
				{
    				System.out.println("Nivel M (en bajar): pos: " + uAgente.x + " " + uAgente.y);
    			
					agState.setubicacionD(uAgente);
					
					//                		
					//Point uAgente = new Point(370,215);
					//agState.setubicacionD(uAgente);
					//environmentState.setposicionAgente(uAgente);
					//elimina el nodo de la lista de se�al de nivel medio del agente
					//        				agState.removerCuadranteNivelM(subCuadrante);
					agState.setenergia(agState.getenergia()-1);
					
					
					System.out.println("BAja a nivel B (en ambiente)");
					return agState;
				}
//System.out.println("Esta en nivel medio (de bajar). Cuadrante: "+cuadrante+"; subCuadrante: " + subCuadrante);	
        		

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
    		int cuadrante = subCuadrante/10;
    		
    		
    		
        	if(altura == "A")
        	{
	
        		boolean conSe�al = false;
        		for(NodoLista n: agState.getintensidadSe�alA())
    			{
    				if(n.getCuadrante() == cuadrante )
    				{
    					conSe�al = true;
    					break;
    					//return null;
    					}
    			}
        		if(!conSe�al)
        		{
        			return null;
        		}
        				agState.setaltura("M");
        				environmentState.setAlturaAgente("M");
        				Point uAgente = FuncionesAuxiliares.bajarASubCuadranteM(cuadrante);
                		agState.setubicacionD(uAgente);
                		environmentState.setposicionAgente(uAgente);
//                		//elimina el nodo con el cuadrante actual de la lista de se�al de nivel alto del agente
//        				agState.removerCuadranteNivelA(cuadrante);
        				agState.setenergia(agState.getenergia()-1);
        				environmentState.setenergiaAgente(environmentState.getenergiaAgente()-1);
        				
        				System.out.println("BAja a nivel M (en ambiente)");
        				
        				return environmentState;
//        			}
//        		}
//        		
        	}
        	//el agente est� en nivel medio
        	else
        	{
        		
        		//No baja si el subcuadrante ya esta visitado
    			/*for(NodoLista n: agState.getintensidadSe�alM())
    			{
    				if(n.getCuadrante() == subCuadrante && n.getVisitado())
    				{
    					return null;
    				}
    			}*/
        		
        		
        		
        		/*
    			 * Esta parte restringe para que no baje a lugares donde no hay se�al o que ya este visitado
    			 * 
    			 * */
        		
        		boolean conSe�al = false;
        		for(NodoLista n: agState.getintensidadSe�alM())
    			{
    				if(n.getCuadrante() == cuadrante && !n.getVisitado())
    				{
    					conSe�al = true;
    					break;
    					//return null;
    					}
    			}
        		if(!conSe�al)
        		{
        			return null;
        		}
    			agState.setaltura("B");
				environmentState.setAlturaAgente("B");
    			
				Point uAgente = null;
    			if(agState.getGrafoSubCuadrante().getListaNodos().size() >0)
    			{
    				
    				 uAgente = FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante());
    				 System.out.println("Nivel M (en bajar con grafo size > 0): pos: " + uAgente.x + " " + uAgente.y);
				
    			}
    			else
    			{
    				//Si no tengo el grafo en nivel medio (Todavia no bajo)
    				//coloco como posicion el centro del cuadrante
    				
    				 uAgente = FuncionesAuxiliares.centroSubcuadranteBajo(subCuadrante);
    				
    			}
				if(uAgente != null)
				{
					agState.setubicacionD(uAgente);
					environmentState.setposicionAgente(uAgente);
					
					agState.setenergia(agState.getenergia()-1);
					environmentState.setenergiaAgente(environmentState.getenergiaAgente()-1);
					
					
					return environmentState;
				}
    			
    			

        			
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