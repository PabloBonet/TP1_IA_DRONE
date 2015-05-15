package frsf.cidisi.exercise.actions;


import java.awt.Point;

import frsf.cidisi.exercise.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;

public class Subir extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        StateDrone agState = (StateDrone) s;
        
        /*PreConditions: no debe estar en el nivel alto
         * la lista de intensidad de energ�a del nivel actual debe ser vac�a para el cuadrante donde se encuentre 
         * el agente tiene que tener mas de 1 de energ�a
         */
        if(agState.getenergia() > 1){
        	Point ubucacion = agState.getubicacionD();
        	String altura = agState.getaltura();
        	int cuadranteActual = FuncionesAuxiliares.perteneceASubCuadrante(ubucacion.x, ubucacion.y);
        	//si no hay intensidad de se�al para ese cuadrante de nivel bajo
        	//entonces ya recorri� todas las posiciones de las personas para ese cuadrante y ya puede subir
        	if(altura == "B" && !agState.hayIntensidadSe�alBCuadrante(cuadranteActual)){
        		agState.setaltura("M");
        		agState.setenergia(agState.getenergia()-2);
        		return s;
        	}
        	//si no hay intensidad de se�al para ese cuadrante de nivel medio
        	//entonces ya recorri� todos los cuadrantes de ese nivel medio y ya puede subir
        	else if(altura == "M" && !agState.hayIntensidadSe�alMCuadrante(cuadranteActual)){ 
        		agState.setaltura("A");
        		agState.setenergia(agState.getenergia()-2);
        		return s;
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
         * la lista de intensidad de energ�a del nivel actual debe ser vac�a para el cuadrante donde se encuentre el agente
         * tiene que tener mas de 1 de energ�a
         */
        if(agState.getenergia() > 1){
        	Point ubucacion = agState.getubicacionD();
        	String altura = agState.getaltura();
        	int cuadranteActual = FuncionesAuxiliares.perteneceASubCuadrante(ubucacion.x, ubucacion.y);
        	//si no hay intensidad de se�al para ese cuadrante de nivel bajo
        	//entonces ya recorri� todas las posiciones de las personas para ese cuadrante y ya puede subir
        	if(altura == "B" && !agState.hayIntensidadSe�alBCuadrante(cuadranteActual)){
        		agState.setaltura("M");
        		agState.setenergia(agState.getenergia()-2);
        		environmentState.setenergiaAgente(environmentState.getenergiaAgente()-2);
        		return environmentState;
        	}
        	//si no hay intensidad de se�al para ese cuadrante de nivel medio
        	//entonces ya recorri� todos los cuadrantes de ese nivel medio y ya puede subir
        	else if(altura == "M" && !agState.hayIntensidadSe�alMCuadrante(cuadranteActual)){ 
        		agState.setaltura("A");
        		agState.setenergia(agState.getenergia()-2);
        		environmentState.setenergiaAgente(environmentState.getenergiaAgente()-2);
        		return environmentState;
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