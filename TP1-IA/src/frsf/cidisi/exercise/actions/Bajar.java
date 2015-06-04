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
        
        // PreConditions: el agente no tiene que estar en el nivel bajo, tiene que tener energía  
        //y debe existir intensisad de señal en el cuadrante donde se encuantra
        // PostConditions: desciende un nivel y se ubica:
        // - Si descendió al nivel medio: en el centro del primer subcuadrante
        // - Si descendió al nivel bajo: en la esquina central del subcuadrante
        
        String altura = agState.getaltura();
        if(altura != "B" && agState.getenergia()>1)
        {
        	int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(agState.getubicacionD().x,agState.getubicacionD().y);
    		int cuadrante = FuncionesAuxiliares.perteneceACuadrante(agState.getubicacionD().x, agState.getubicacionD().y);
    		if(altura == "A" )
    		{
    			//No baja si el cuadrante ya esta visitado
boolean esta=false;
for(int i=0; i<agState.getintensidadSeñalA().size();i++){
if(agState.getintensidadSeñalA().get(i).getCuadrante()==cuadrante){
	esta=true;System.out.println("Hay señal A");}
}
if(!esta){System.out.println("No hay señal en el cuad donde estoy");
	return null;}
else{
    			for(NodoLista n: agState.getintensidadSeñalA())
    			{
    				if(n.getCuadrante() == cuadrante && n.getVisitado())
    				{
    					return null;
    				}
    			}
    			agState.setaltura("M");
    			agState.setubicacionD(FuncionesAuxiliares.bajarASubCuadranteM(cuadrante));
    			agState.setenergia(agState.getenergia()-1);
    			
System.out.println("bajar a M (en arbol)");
    			return agState;
    		}}
        	//el agente está en nivel medio
    		else
    		{
    			//No baja si el subcuadrante ya esta visitado
//System.out.println("Esta en nivel medio (de bajar). Cuadrante: "+cuadrante+"; subCuadrante: " + subCuadrante);	
    			boolean tieneSeñal = false;
    			//revisa si el cuadrante tiene señal
    			for(NodoLista n: agState.getintensidadSeñalM())
    			{
    				if(n.getCuadrante() == subCuadrante && !n.getVisitado())
    				{
//System.out.println("\t### y bajaría a bajo!!");
    					tieneSeñal = true;
    					break;
    				}
    			}

    			if(tieneSeñal)
    			{
    				agState.setaltura("B");
    				Point esquinaCentro = FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante());
//System.out.println("EsqCentro en Bajar: "+esquinaCentro.x+"-"+esquinaCentro.y);
    				if(esquinaCentro != null)
    				{
    					agState.setubicacionD(esquinaCentro);
    					agState.setenergia(agState.getenergia()-1);
//System.out.println("Retorna: Bajar");
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

        // PreConditions: el agente no tiene que estar en el nivel bajo, tiene que tener energía  
        //y debe existir intensisad de señal en el cuadrante donde se encuantra
        // PostConditions: desciende un nivel y se ubica:
        // - Si descendió al nivel medio: en el centro del primer subcuadrante
        // - Si descendió al nivel bajo: en la esquina central del subcuadrante
        // y actualiza la energía y altura de los estados de agente y entorno.
        
        String altura = agState.getaltura();
        if(altura != "B" && agState.getenergia()>1)
        { 
        	int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(agState.getubicacionD().x,agState.getubicacionD().y);
    		int cuadrante = subCuadrante/10;
    		
    		
    		
        	if(altura == "A")
        	{
//        		for(NodoLista n: agState.getintensidadSeñalA())
//        		{
//        			//si existe intensidad de señal en el subcuadrante inferior de donde se encuantra el agente
//        			if(cuadrante == n.getCuadrante())
//        			{
        		//No baja si el cuadrante ya esta visitado
        		for(NodoLista n: agState.getintensidadSeñalA())
        		{
        			if(n.getCuadrante() == cuadrante && n.getVisitado())
        			{
        				return null;
        			}
        		}
        		agState.setaltura("M");
        		environmentState.setAlturaAgente("M");
        		Point uAgente = FuncionesAuxiliares.bajarASubCuadranteM(cuadrante);
        		agState.setubicacionD(uAgente);
        		environmentState.setposicionAgente(uAgente);
        		agState.setenergia(agState.getenergia()-1);
        		environmentState.setenergiaAgente(environmentState.getenergiaAgente()-1);
        		return environmentState;
        	}
        	//el agente está en nivel medio
        	else
        	{
        		//No baja si el subcuadrante ya esta visitado
        		boolean tieneSeñal = false;
        		//revisa si el cuadrante tiene señal y no fué visitado
        		for(NodoLista n: agState.getintensidadSeñalM())
        		{
					if(n.getCuadrante() == subCuadrante && !n.getVisitado())
        			{
System.out.println("####### BAJO a bajo!!");
        				tieneSeñal = true;
        				break;
        			}
        		}

        		if(tieneSeñal)
        		{
        			agState.setaltura("B");
        			environmentState.setAlturaAgente("B");

        			Point uAgente = FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante());
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