package frsf.cidisi.exercise.actions;


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
        
        // TODO: Use this conditions
        // PreConditions: el agente no tiene que estar en el nivel bajo, tiene que tener energía 
        //y debe existir intensisad de señal en el cuadrante donde se encuantra
        // PostConditions: desciende un nivel y se ubica en el centro de ese cuadrante
        
        String altura = agState.getaltura();
        int cuadrante, subCuadrante;
        if(altura != "B" && agState.getenergia()>1){ //ver si es 1 o 0 aca!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        	subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(agState.getubicacionD().x,agState.getubicacionD().y);
    		cuadrante = subCuadrante%10;
        	if(altura == "A" && agState.getintensidadSeñalA().size()>0){
        		for(NodoLista n: agState.getintensidadSeñalA()){
        			//si existe intensidad de señal en el subcuadrante inferior desde donde se encuantra el agente
        			if(subCuadrante == n.getCuadrante()){
        				agState.setaltura("M");
// FALTA SEGIR ESTE MÉTODO DE ABAJO #########################################################################################################################
                		agState.setubicacionD(FuncionesAuxiliares.centrarPosicionCuadrante(subCuadrante));
                		//elimina el nodo con el cuadrante actual de la lista de señal de nivel alto del agente
        				agState.removerCuadranteNivelA(cuadrante);
        				agState.setenergia(agState.getenergia()-1);
        				return agState;
        			}
        		}
        		//la lista de intensidad de señal de nivel alto está vacía 
        		return null;
        	}
        	//el agente está en nivel medio
        	else{
        		for(NodoLista n: agState.getintensidadSeñalM()){
        			//si existe intensidad de señal en el subcuadrante inferior desde donde se encuantra el agente
        			if(subCuadrante == n.getCuadrante()){
        				agState.setaltura("B");
//                		agState.setubicacionD(centrarPosicionCuadrante(subCuadrante));
                		//elimina el nodo de la lista de señal de nivel alto del agente
//        				agState.getintensidadSeñalM().removerNodoCuadrante(cuadrante);
        				agState.setenergia(agState.getenergia()-1);
        				return agState;
        			}
        		}
        		//la lista de intensidad de señal de nivel alto está vacía 
        		return null;
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
        return "Bajar";
    }
}