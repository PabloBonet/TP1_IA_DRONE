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
    		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(agState.getubicacionD().x, agState.getubicacionD().y);
    		if(altura == "A")
    		{
    			agState.setaltura("M");
    			agState.setubicacionD(FuncionesAuxiliares.bajarASubCuadranteM(cuadrante));
    			agState.setenergia(agState.getenergia()-1);
    			
System.out.println("bajar a M (en arbol)");
    			return agState;
    		}
        	//el agente est� en nivel medio
    		else
    		{
System.out.println("Esta en nivel medio (de bajar). Cuadrante: "+cuadrante+"; subCuadrante: " + subCuadrante);	
        		boolean tieneSe�al = false;
        		//revisa si el cuadrante tiene se�al
        		for(NodoLista n: agState.getintensidadSe�alM())
        		{
System.out.println("n.getCuadrante(): "+n.getCuadrante()+" if("+n.getCuadrante()+"=="+subCuadrante+")");
        			//if(n.getCuadrante()/10 == cuadrante && !n.getVisitado())
					if(n.getCuadrante() == subCuadrante && !n.getVisitado())
        			{
System.out.println("\t### y bajar�a a bajo!!");
        				tieneSe�al = true;
        				break;
        			}
        		}
System.out.println("Despues de for");	
//        		if(!FuncionesAuxiliares.se�alesVisitadasB(agState.getintensidadSe�alB()))
//        		{
//System.out.println("EL AGENTE ESTA EN NIVEL MEDIO - Subcuadrante: " +subCuadrante);
//        		for(NodoLista n: agState.getintensidadSe�alM())
//        		{
//        			//si existe intensidad de se�al en el subcuadrante inferior de donde se encuantra el agente
//        			if(subCuadrante == n.getCuadrante())
        		if(tieneSe�al)
        		{
        			agState.setaltura("B");
        			Point esquinaCentro = FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante());
System.out.println("EsqCentro en Bajar: "+esquinaCentro.x+"-"+esquinaCentro.y);
        			if(esquinaCentro != null)
        			{
        				agState.setubicacionD(esquinaCentro);
        				agState.setenergia(agState.getenergia()-1);
System.out.println("Retorna: Bajar");
        					return agState;
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
//        		for(NodoLista n: agState.getintensidadSe�alA())
//        		{
//        			//si existe intensidad de se�al en el subcuadrante inferior de donde se encuantra el agente
//        			if(cuadrante == n.getCuadrante())
//        			{
        				agState.setaltura("M");
        				environmentState.setAlturaAgente("M");
        				Point uAgente = FuncionesAuxiliares.bajarASubCuadranteM(cuadrante);
                		agState.setubicacionD(uAgente);
                		environmentState.setposicionAgente(uAgente);
//                		//elimina el nodo con el cuadrante actual de la lista de se�al de nivel alto del agente
//        				agState.removerCuadranteNivelA(cuadrante);
        				agState.setenergia(agState.getenergia()-1);
        				environmentState.setenergiaAgente(environmentState.getenergiaAgente()-1);
        				return environmentState;
//        			}
//        		}
//        		//la lista de intensidad de se�al de nivel alto est� vac�a 
//        		return null;
        	}
        	//el agente est� en nivel medio
        	else
        	{
        		boolean tieneSe�al = false;
        		//revisa si el cuadrante tiene se�al y no fu� visitado
//        		for(NodoLista n: agState.getintensidadSe�alM())
//        		{
//        			if(FuncionesAuxiliares.perteneceACuadrante(agState.getubicacionD().x, agState.getubicacionD().y) == cuadrante)
//        			{
//        				tieneSe�al = true;
//        				break;
//        			}
//        		}
        		for(NodoLista n: agState.getintensidadSe�alM())
        		{
        			//if(n.getCuadrante()/10 == cuadrante && !n.getVisitado())
					if(n.getCuadrante() == subCuadrante && !n.getVisitado())
        			{
System.out.println("####### BAJO a bajo!!");
        				tieneSe�al = true;
        				break;
        			}
        		}

//        		for(NodoLista n: agState.getintensidadSe�alM())
//        		{
//        			//si existe intensidad de se�al en el subcuadrante inferior de donde se encuantra el agente
//        			if(subCuadrante == n.getCuadrante())
//        			{
        			if(tieneSe�al)
        			{
        				
        				        				
        		agState.setaltura("B");
        				environmentState.setAlturaAgente("B");
        				
        				//VER 
        				Point uAgente = FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante());
        				if(uAgente != null)
        				{
        					agState.setubicacionD(uAgente);
        					environmentState.setposicionAgente(uAgente);
        					//                		
        					//Point uAgente = new Point(370,215);
        					//agState.setubicacionD(uAgente);
        					//environmentState.setposicionAgente(uAgente);
        					//elimina el nodo de la lista de se�al de nivel medio del agente
        					//        				agState.removerCuadranteNivelM(subCuadrante);
        					agState.setenergia(agState.getenergia()-1);
        					environmentState.setenergiaAgente(environmentState.getenergiaAgente()-1);
        					return environmentState;
        				}
//        			}
//        		}
//        		//la lista de intensidad de se�al de nivel alto est� vac�a 
//        		return null;
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