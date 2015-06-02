package frsf.cidisi.exercise.actions;


import java.awt.Point;

import frsf.cidisi.exercise.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.NodoLista;

public class Subir extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        StateDrone agState = (StateDrone) s;
        
        /*PreConditions: no debe estar en el nivel alto
         * la lista de intensidad de energía del nivel actual debe ser vacía para el cuadrante donde se encuentre 
         * el agente tiene que tener mas de 1 de energía
         */
        if(agState.getenergia() > 1){
        	Point ubucacion = agState.getubicacionD();
        	String altura = agState.getaltura();
        	int subCuadranteActual = FuncionesAuxiliares.perteneceASubCuadrante(ubucacion.x, ubucacion.y);
        	int cuadranteActual = FuncionesAuxiliares.perteneceACuadrante(ubucacion.x, ubucacion.y);
        	//si no hay intensidad de señal para ese cuadrante de nivel bajo
        	//entonces ya recorrió todas las posiciones de las personas para ese cuadrante y ya puede subir
        	//if(altura == "B" && !agState.hayIntensidadSeñalBCuadrante(cuadranteActual)){
        	if(altura == "B" )
        	{
        		
        		for(Nodo n: agState.getintensidadSeñalB())
        		{
        			if(FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY()) == subCuadranteActual)
        			{
        				if(!n.getVisitado())
        					return null;
        			}
        		}
        		agState.setaltura("M");
        		agState.setenergia(agState.getenergia()-2);
        		return agState;
        	}
        	//si no hay intensidad de señal para ese cuadrante de nivel medio
        	//entonces ya recorrió todos los cuadrantes de ese nivel medio y ya puede subir
        	else //if(altura == "M" && !agState.hayIntensidadSeñalMCuadrante(cuadranteActual)){ 
        	{
        		if(altura == "M" )
        		{
        			for(NodoLista n: agState.getintensidadSeñalM())
            		{
            			if(n.getCuadrante() == cuadranteActual)
            			{
            				if(!n.getVisitado())
            					return null;
            			}
            		}
        			agState.setaltura("A");
            		agState.setenergia(agState.getenergia()-2);
            		return agState;
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

        /*PreConditions: no debe estar en el nivel alto
         * la lista de intensidad de energía del nivel actual debe ser vacía para el cuadrante donde se encuentre el agente
         * tiene que tener mas de 1 de energía
         */
        if(agState.getenergia() > 1){
        	Point ubucacion = agState.getubicacionD();
        	String altura = agState.getaltura();
        	int subCuadranteActual = FuncionesAuxiliares.perteneceASubCuadrante(ubucacion.x, ubucacion.y);
        	int cuadranteActual = FuncionesAuxiliares.perteneceACuadrante(ubucacion.x, ubucacion.y);
        	//si no hay intensidad de señal para ese cuadrante de nivel bajo
        	//entonces ya recorrió todas las posiciones de las personas para ese cuadrante y ya puede subir
        	if(altura == "B" )
        	{
        		
        		for(Nodo n: agState.getintensidadSeñalB())
        		{
        			if(FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY()) == subCuadranteActual)
        			{
        				if(!n.getVisitado())
        					return null;
        			}
        		}
        		agState.setaltura("M");
        		agState.setenergia(agState.getenergia()-2);
        		environmentState.setenergiaAgente(environmentState.getenergiaAgente()-2);
        		environmentState.setAlturaAgente("M");
        		return environmentState;
        	}
        	
        	//si no hay intensidad de señal para ese cuadrante de nivel medio
        	//entonces ya recorrió todos los cuadrantes de ese nivel medio y ya puede subir
        	else //if(altura == "M" && !agState.hayIntensidadSeñalMCuadrante(cuadranteActual)){ 
        	{
        		if(altura == "M" )
        		{
        			for(NodoLista n: agState.getintensidadSeñalM())
            		{
            			if(n.getCuadrante() == cuadranteActual)
            			{
            				if(!n.getVisitado())
            					return null;
            			}
            		}
        			agState.setaltura("A");
            		agState.setenergia(agState.getenergia()-2);
            		environmentState.setAlturaAgente("A");
            		environmentState.setenergiaAgente(environmentState.getenergiaAgente()-2);
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
        return "Subir";
    }
}