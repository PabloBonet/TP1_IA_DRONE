package frsf.cidisi.exercise.actions;


import java.awt.Point;
import java.io.IOException;

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
        //System.out.println("EN EXECUTE(1)");
        
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

        	if(altura == "B")
    		{
    		//	System.out.println("Altura: " + altura);
//    			System.out.println("Cantidad de señales nivel M: " +  ((StateDrone)agState).getintensidadSeñalM().size());
    			//if(FuncionesAuxiliares.señalesVisitadas(((StateDrone)agState).getintensidadSeñalM()))
    			//{
    				boolean existe  = false;
    				//Marca el cuadrante de nivel superior como visitado
        			for(NodoLista n: agState.getintensidadSeñalM())
        			{
        		
        				if(n.getCuadrante() == subCuadranteActual)
        				{
        					for(Nodo nB: agState.getintensidadSeñalB())
        					{	
        						
        						
        						int subCuad = FuncionesAuxiliares.perteneceASubCuadrante(nB.getPosX(), nB.getPosY());
        					
        						if((subCuad) == subCuadranteActual && !nB.getVisitado())
        						{
        							return null;
        						}
        					
        					}
        				
        					existe = true;
        					n.visitar();
        					break;

        				}
        			}
        				if(!existe)
                		{
            				
            				
        					//Creo un nodoNuevo para indicar que el cuadrante fue visitado
        					NodoLista nodoNuevo = new NodoLista(subCuadranteActual, 0, true);
        					agState.getintensidadSeñalM().add(nodoNuevo);
                		}
 
        				System.out.println("Nivel Bajo (en Subir): " + cuadranteActual);
        				System.out.println("Posicion: " + ubucacion.x + " " + ubucacion.y);
           				System.out.println("Posicion estado: " + agState.getubicacionD().x + " " + agState.getubicacionD().y);
           				

        			agState.setaltura("M");
                	agState.setenergia(agState.getenergia()-2);

                /*System.out.println("Sube a nivel M (En arbol)");
                System.out.println("Estado: Posicion: " +agState.getubicacionD().x  + " " + agState.getubicacionD().y + "\nSeñales en nivel M:");
                
                System.out.println("Señales Alto");
                for(NodoLista n: agState.getintensidadSeñalA())
                {
                	System.out.println("Señal: " + n.getCuadrante() + " int: " + n.getIntensidad()+ " visitado: " + n.getVisitado());
                }
                
                System.out.println("Señales Medio");
                for(NodoLista n: agState.getintensidadSeñalM())
                {
                	System.out.println("Señal: " + n.getCuadrante() + " int: " + n.getIntensidad()+ " visitado: " + n.getVisitado());
                }
                
                System.out.println("Señales Bajo");
                for(Nodo n: agState.getintensidadSeñalB())
                {
                	System.out.println("Señal: " + n.getId()+ " perso: " + n.getPersonas().size()+ " visitado: " + n.getVisitado());
                }
                */
               
                		                    		return agState;	
        	
        	
        	
    		}
        	//si las intensidades de señal para ese cuadrante de nivel medio ya se visitaron
        	//entonces ya recorrió todos los cuadrantes de ese nivel medio y puede subir
        	else
        	{
        		if(altura == "M")
        		{
        		//	System.out.println("Altura: " + altura);
//        			System.out.println("Cantidad de señales nivel M: " +  ((StateDrone)agState).getintensidadSeñalM().size());
        			//if(FuncionesAuxiliares.señalesVisitadas(((StateDrone)agState).getintensidadSeñalM()))
        			//{
        				boolean existe  = false;
        				//Marca el cuadrante de nivel superior como visitado
            			for(NodoLista n: agState.getintensidadSeñalA())
            			{
            		
            				if(n.getCuadrante() == cuadranteActual)
            				{
            					for(NodoLista nM: agState.getintensidadSeñalM())
            					{
            						/*if(nM.getCuadrante() == cuadranteActual && nM.getVisitado())
            						{
            							existe = true;
                    					n.visitar();
                    					break;
            						}*/
            						int subCuad = nM.getCuadrante();
            						if((subCuad/10) == cuadranteActual && !nM.getVisitado())
            						{
//ACÁ AL GENERAR EL ARBOL (en nivel medio) podria subir estando en 23 y nM no visitado para q llegue a la meta
            							return null;
            						}
            					
            					}
            				
            					existe = true;
            					n.visitar();
            					break;

            				}
            			}
            				if(!existe)
                    		{
                				
                				
            					//Creo un nodoNuevo para indicar que el cuadrante fue visitado
            					NodoLista nodoNuevo = new NodoLista(cuadranteActual, 0, true);
            					agState.getintensidadSeñalA().add(nodoNuevo);
                    		}
     
            			agState.setaltura("A");
                    		agState.setenergia(agState.getenergia()-2);
                    		
                    		System.out.println("Sube a nivel A (En arbol)");
                    		                    		return agState;	
                			
        			//}
        			
        		}
        	}
        	

        }
        //System.out.println("Se termino la energia (en subir)");
        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        StateMap environmentState = (StateMap) est;
        StateDrone agState = ((StateDrone) ast);
        System.out.println("EN EXECUTE de SUBIR");
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
        	if(altura == "B")
    		{
    		//	System.out.println("Altura: " + altura);
//    			System.out.println("Cantidad de señales nivel M: " +  ((StateDrone)agState).getintensidadSeñalM().size());
    			//if(FuncionesAuxiliares.señalesVisitadas(((StateDrone)agState).getintensidadSeñalM()))
    			//{
        		System.out.println("En Altura B");
        		System.out.println("Tamaño intensidad M " + agState.getintensidadSeñalM().size());
    				boolean existe  = false;
    				//Marca el cuadrante de nivel superior como visitado
        			for(NodoLista n: agState.getintensidadSeñalM())
        			{
        				System.out.println("Cuadrante nodo. " + n.getCuadrante());
        				System.out.println("Sub Cuadrante actual: " + subCuadranteActual);
        				if(n.getCuadrante() == subCuadranteActual)
        				{
        					for(Nodo nB: agState.getintensidadSeñalB())
        					{	
        						
        						
        						int subCuad = FuncionesAuxiliares.perteneceASubCuadrante(nB.getPosX(), nB.getPosY());
        					
        						if((subCuad) == subCuadranteActual && !nB.getVisitado())
        						{
        							return null;
        						}
        					
        					}
        				
        					existe = true;
        					n.visitar();
        					break;

        				}
        			}
        				if(!existe)
                		{
            				
            				
        					//Creo un nodoNuevo para indicar que el cuadrante fue visitado
        					NodoLista nodoNuevo = new NodoLista(subCuadranteActual, 0, true);
        					agState.getintensidadSeñalM().add(nodoNuevo);
                		}
				
				
        		agState.setaltura("M");
        		agState.setenergia(agState.getenergia()-2);
        		environmentState.setenergiaAgente(environmentState.getenergiaAgente()-2);
        		environmentState.setAlturaAgente("M");

        		System.out.println("Sube a nivel M (En Ambiente)");
        		
        		return environmentState;
				
        	}
        	
        	//si no hay intensidad de señal para ese cuadrante de nivel medio
        	//entonces ya recorrió todos los cuadrantes de ese nivel medio y ya puede subir
        	else //if(altura == "M" && !agState.hayIntensidadSeñalMCuadrante(cuadranteActual)){ 
        	{

        		if(altura == "M")
        		{
        		//	System.out.println("Altura: " + altura);
//        			System.out.println("Cantidad de señales nivel M: " +  ((StateDrone)agState).getintensidadSeñalM().size());
        			//if(FuncionesAuxiliares.señalesVisitadas(((StateDrone)agState).getintensidadSeñalM()))
        			//{
        			System.out.println("En Altura M");
            		System.out.println("Tamaño intensidad M " + agState.getintensidadSeñalM().size());
        				boolean existe  = false;
        				//Marca el cuadrante de nivel superior como visitado
            			for(NodoLista n: agState.getintensidadSeñalA())
            			{
            		
            				if(n.getCuadrante() == cuadranteActual) ///VER DE !VISITADO
            				{
            					System.out.println("Cuadrante nodo. " + n.getCuadrante());
                				System.out.println("Sub Cuadrante actual: " + subCuadranteActual);
            					for(NodoLista nM: agState.getintensidadSeñalM())
            					{
            						/*if(nM.getCuadrante() == cuadranteActual && nM.getVisitado())
            						{
            							existe = true;
                    					n.visitar();
                    					break;
            						}*/
            						int subCuad = nM.getCuadrante();
            						if((subCuad/10) == cuadranteActual && !nM.getVisitado())
            						{
            							return null;
            						}
            					
            					}
            				
            					existe = true;
            					n.visitar();
            					break;

            				}
            			}
            				if(!existe)
                    		{
                				
                				
            					//Creo un nodoNuevo para indicar que el cuadrante fue visitado
            					NodoLista nodoNuevo = new NodoLista(cuadranteActual, 0, true);
            					agState.getintensidadSeñalA().add(nodoNuevo);
                    		}
     
            				agState.setaltura("M");
                    		agState.setenergia(agState.getenergia()-2);
                    		environmentState.setenergiaAgente(environmentState.getenergiaAgente()-2);
                    		environmentState.setAlturaAgente("M");
                    		
                    		System.out.println("Sube a nivel A  (En ambiente)");
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