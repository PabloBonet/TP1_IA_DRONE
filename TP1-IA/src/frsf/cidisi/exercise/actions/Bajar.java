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
		if(altura != "B" && agState.getenergia()>1 && agState.getvictimario()==null)
		{
			int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(agState.getubicacionD().x,agState.getubicacionD().y);
			int cuadrante = FuncionesAuxiliares.perteneceACuadrante(agState.getubicacionD().x, agState.getubicacionD().y);
			if(altura == "A" )
			{

				//No baja si el cuadrante ya esta visitado
				// Esta parte restringe para que no baje a lugares donde no hay señal o que ya este visitado
				boolean conSeñal = false;
				for(NodoLista n: agState.getintensidadSeñalA())
				{
					if(n.getCuadrante() == cuadrante && !n.getVisitado())
					{
						conSeñal = true;
						break;
					}
				}
				if(!conSeñal)
				{
					return null;
				}
				agState.setaltura("M");
				agState.setubicacionD(FuncionesAuxiliares.bajarASubCuadranteM(cuadrante));
				agState.setenergia(agState.getenergia()-1);

				return agState;
			}
			//el agente está en nivel medio
			else
			{
				//No baja si el subcuadrante ya esta visitado
				//Esta parte restringe para que no baje a lugares donde no hay señal o que ya este visitado
				boolean conSeñal = false;
				for(NodoLista n: agState.getintensidadSeñalM())
				{
					if(n.getCuadrante() == subCuadrante && !n.getVisitado())
					{
						conSeñal = true;
						break;
					}
				}
				if(!conSeñal)
				{
					return null;
				}

				agState.setaltura("B");

				Point uAgente = null;
				if(agState.getGrafoSubCuadrante().getListaNodos().size() > 0)
				{
					uAgente = FuncionesAuxiliares.centrarPosicionEsquina(subCuadrante, agState.getGrafoSubCuadrante());
				}
				else
				{
					//Si no tengo el grafo en nivel medio (Todavia no bajo)
					//coloco como posicion el centro del subcuadrante
					uAgente = FuncionesAuxiliares.centroSubcuadranteBajo(subCuadrante);
				}
				if(uAgente != null) //es null cuando percibio estando en un cuadrante de nivel medio pero y también hay señal 
					//en otro subcuadrante de nivel medio, que ahora no lo puede recibir
				{
					agState.setubicacionD(uAgente);
					agState.setenergia(agState.getenergia()-1);
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

		// PreConditions: el agente no tiene que estar en el nivel bajo, tiene que tener energía  
		//y debe existir intensisad de señal en el cuadrante donde se encuantra
		// PostConditions: desciende un nivel y se ubica:
		// - Si descendió al nivel medio: en el centro del primer subcuadrante
		// - Si descendió al nivel bajo: en la esquina central del subcuadrante
		// y actualiza la energía y altura de los estados de agente y entorno.

		String altura = agState.getaltura();
		if(altura != "B" && agState.getenergia()>1 && agState.getvictimario()==null)
		{ 
			int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(agState.getubicacionD().x,agState.getubicacionD().y);
			int cuadrante = subCuadrante/10;

			if(altura == "A")
			{

				boolean conSeñal = false;
				for(NodoLista n: agState.getintensidadSeñalA())
				{
					if(n.getCuadrante() == cuadrante )
					{
						conSeñal = true;
						break;
					}
				}
				if(!conSeñal)
				{
					return null;
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
				//Esta parte restringe para que no baje a lugares donde no hay señal o que ya este visitado

				boolean conSeñal = false;
				for(NodoLista n: agState.getintensidadSeñalM())
				{
					if(n.getCuadrante() == subCuadrante && !n.getVisitado())
					{
						conSeñal = true;
						break;
					}
				}
				if(!conSeñal)
				{
					return null;
				}
				agState.setaltura("B");
				environmentState.setAlturaAgente("B");

				Point uAgente = null;
				if(agState.getGrafoSubCuadrante().getListaNodos().size() >0)
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