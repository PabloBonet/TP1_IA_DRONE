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



if(droneState.getenergia()>1){ System.out.println("\t\t-> Energ�a: "+energia);
		if(altura == "A" && droneState.getintensidadSe�alA().size()>0){
			Point sigPos = FuncionesAuxiliares.irEste(posicion, altura);

			if(sigPos != null)
			{
				int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
				boolean encontrado=false;
				for(NodoLista n: droneState.getintensidadSe�alA())
				{
					if(cuadrante == n.getCuadrante() && !n.getVisitado())
					{
						encontrado = true;
						break;
					}
				}
				if(encontrado) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
				{
					droneState.setenergia(energia - 1);
					droneState.setubicacionD(sigPos);	
					return droneState;
				}
			}
		}
		else{
			if(altura == "M" && droneState.getintensidadSe�alM().size()>0){

				Point sigPos = FuncionesAuxiliares.irEste(posicion, altura);
				if(sigPos != null)
				{
					int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(sigPos.x, sigPos.y);
					boolean encontrado=false;
					for(NodoLista n: droneState.getintensidadSe�alM())
					{

						if(subCuadrante == n.getCuadrante() && !n.getVisitado())
						{
							encontrado = true;
							break;
						}
					}
					if(encontrado) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
					{
						droneState.setenergia(energia - 1);
						droneState.setubicacionD(sigPos);
						return droneState;
					}
				}
			}
			else //Altura "B"
			{
				if(altura == "B" && droneState.getintensidadSe�alB().size()>0)
				{

					subGrafo = droneState.getGrafoSubCuadrante();
					Nodo nodoSig = FuncionesAuxiliares.irEsteBajo(posicion, subGrafo);
					
					//si existe el nodo en la direcci�n este 
					//y no se visitaron todos los nodos de la lista de intensidad de se�al baja del agente para ese subcuadrante
					if(nodoSig != null && !FuncionesAuxiliares.se�alesVisitadasB(droneState.getintensidadSe�alB()))
					{
//System.out.println("Este bajo! de "+droneState.getubicacionD().x+"-"+droneState.getubicacionD().y+" a "+nodoSig.getPosX()+"-"+nodoSig.getPosY());
						//si el nodo al este tiene intensidad de se�al y no se visit� se decrementa 1 en energ�a, 
						//sino se decrementa 2 y se marca como visitado en el subgrafo del agente
						if(FuncionesAuxiliares.contieneNodoConID(droneState.getintensidadSe�alB(),nodoSig.getId()))
						{
//System.out.println("\tNodo en lista B");
							FuncionesAuxiliares.visitarNodoIntensidadSe�alB(droneState.getintensidadSe�alB(), nodoSig.getId());
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

//FALTAR�A PREGUNTAR SI EL NODO YA FU� VISITADO EN EL SUBGRAFO PARA NO MARCARLO NUEVAMENTE!!!!!!!!!!!!!!!!!!!!!!

							(subGrafo.buscarNodo(nodoSig.getId())).visitar();
							droneState.setenergia(energia - 2);
							
						}

						Point sigPos = new Point(nodoSig.getPosX(), nodoSig.getPosY());
						droneState.setubicacionD(sigPos);
						return droneState;

					}
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

		// TODO: Use this conditions
		// PreConditions: null
		// PostConditions: null

		String altura = droneState.getaltura();
		Point posicion = droneState.getubicacionD();
		int energia = droneState.getenergia();
		Grafo subGrafo = new Grafo();

		boolean puedeIr = false;

		Point sigPos = new Point();
		if(droneState.getenergia()>1){
		if(altura == "A" && droneState.getintensidadSe�alA().size()>0){
			sigPos = FuncionesAuxiliares.irEste(posicion, altura);
			if(sigPos != null)
			{
				int cuadrante = FuncionesAuxiliares.perteneceACuadrante(sigPos.x, sigPos.y);
				NodoLista encontrado=null;
				for(NodoLista n: droneState.getintensidadSe�alA())
				{

					if(cuadrante == n.getCuadrante() && !n.getVisitado())
					{
						encontrado = n;
						break;
					}
				}
				if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
				{
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
							encontrado = n;
							break;
						}
					}
					if(encontrado != null) //Si el cuadrante tiene se�al, se mueve a ese cuadrante
					{
						droneState.setenergia(energia - 1);
						droneState.setubicacionD(sigPos);
						puedeIr = true;
					}
				}
			}
			else //Altura "B"
			{
				if(altura == "B" && droneState.getintensidadSe�alB().size()>0)
				{

					subGrafo = droneState.getGrafoSubCuadrante();
					Nodo nodoSig = FuncionesAuxiliares.irEsteBajo(posicion, subGrafo);

					if(nodoSig != null && !FuncionesAuxiliares.se�alesVisitadasB(droneState.getintensidadSe�alB()))
					{
						if(FuncionesAuxiliares.contieneNodoConID(droneState.getintensidadSe�alB(),nodoSig.getId()))
						{
							//visita el nodo de la lista de intensidad de se�al baja del agente
							FuncionesAuxiliares.visitarNodoIntensidadSe�alB(droneState.getintensidadSe�alB(), nodoSig.getId());
							if(subGrafo.buscarNodo(nodoSig.getId()).getVisitado())
							{
								droneState.setenergia(energia - 2);
							}
							else
							{
								//visitar el nodo del subgrafo del agente
								(subGrafo.buscarNodo(nodoSig.getId())).visitar();
								droneState.setenergia(energia - 1);	
							}

						}
						else
						{
							(subGrafo.buscarNodo(nodoSig.getId())).visitar();
							droneState.setenergia(energia - 2);
						}

						sigPos.setLocation(nodoSig.getPosX(), nodoSig.getPosY());
						droneState.setubicacionD(sigPos);
						puedeIr = true;
						//visita el nodo de la lista de intensidad de se�al baja y el nodo del mapa (del ambiente)
						FuncionesAuxiliares.visitarNodoIntensidadSe�alB(environmentState.getintensidadSe�alB(), nodoSig.getId());
						environmentState.getgrafoMapa().buscarNodo(nodoSig.getId()).visitar();
					}
				}
			}
		}

		if (puedeIr) {
			environmentState.setposicionAgente(droneState.getubicacionD());
			environmentState.setenergiaAgente(droneState.getenergia());

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
		return "IrEste";
	}
}