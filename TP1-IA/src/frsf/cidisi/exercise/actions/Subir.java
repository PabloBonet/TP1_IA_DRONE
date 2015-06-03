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
        	if(altura == "B" )
        	{
//System.out.println("Esta en nivel bajo (de subir). cuadrante: " + subCuadranteActual+" tam señB: "+agState.getintensidadSeñalB().size()+" Ag pos: "+agState.getubicacionD().x+"-"+agState.getubicacionD().y);

        		/*for(Nodo n: agState.getintensidadSeñalB())
        		{
        			if(FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY()) == subCuadranteActual)
        			{
        				if(!n.getVisitado())
        				{
System.out.println("nodo no visitado: " + n.getId());
        					return null;
        				}
        			}
        		}
*/
			if(FuncionesAuxiliares.señalesVisitadasB(((StateDrone)agState).getintensidadSeñalB()))
				{	
//        		System.out.println("TAmaño intensidadM: " + agState.getintensidadSeñalM().size());
        	
        		//Marca el cuadrante de nivel superior como visitado
    			for(NodoLista n: agState.getintensidadSeñalM())
    			{
//    				System.out.println("Nodo: "+ n.getCuadrante());
//    				System.out.println("sub cuadrante actual: " + subCuadranteActual);
    				if(n.getCuadrante() == subCuadranteActual) //agregando condicion !n.getVisitado() no veo cambios..
    				{
//System.out.println("marca cuadrante de nivel superior: " + subCuadranteActual + " como visitado. Ag pos: "+agState.getubicacionD().x+"-"+agState.getubicacionD().y);
					n.visitar();

//System.out.print("\t## bien: visita señalM\n");
    					break;
    				}
    			}
        		
        		agState.setaltura("M");
        		
        		agState.setenergia(agState.getenergia()-2);

//        		System.out.println("ALTURA NUEVA: " + agState.getaltura());
//        		System.out.println("Posicion: " + agState.getubicacionD().x + " " + agState.getubicacionD().y);
        		

        		return agState;
        	}
        	}
        	//si no hay intensidad de señal para ese cuadrante de nivel medio
        	//entonces ya recorrió todos los cuadrantes de ese nivel medio y ya puede subir
        	else
        	{
        		if(altura == "M")
        		{
//        			System.out.println("Altura: " + altura);
//        			System.out.println("Cantidad de señales nivel M: " +  ((StateDrone)agState).getintensidadSeñalM().size());
        			if(FuncionesAuxiliares.señalesVisitadas(((StateDrone)agState).getintensidadSeñalM()))
        			{
        				//Marca el cuadrante de nivel superior como visitado
            			for(NodoLista n: agState.getintensidadSeñalA())
            			{
            				if(n.getCuadrante() == cuadranteActual) ///VER DE !VISITADO
            				{
            					n.visitar();
            					break;
            				}
            			}
                			agState.setaltura("A");
                    		agState.setenergia(agState.getenergia()-2);
                    		
                    		for(NodoLista n: agState.getintensidadSeñalA())
                    		{
//    System.out.println("Nodo lista " + n.getCuadrante()+ " fue visitado: " + n.getVisitado());
                    		}
                    		
                    		return agState;	
                			
        			}
        		}
        	}
        	
        	/*
        	else //if(altura == "M" && !agState.hayIntensidadSeñalMCuadrante(cuadranteActual)){ 
        	{
        		if(altura == "M" )
        		{
<<<<<<< HEAD
        			
        				return 
System.out.println("Esta en nivel medio (de subir). cuadrante: " + subCuadranteActual);
        			for(NodoLista n: agState.getintensidadSeñalM())
            		{
            			int c = n.getCuadrante();
        				System.out.println("C: " + c);
        				System.out.println("CuadActual: " + cuadranteActual);
=======
System.out.println("Esta en nivel medio (de subir). cuadrante: " + subCuadranteActual+" Ag pos: "+agState.getubicacionD().x+"-"+agState.getubicacionD().y);
        			for(NodoLista n: agState.getintensidadSeñalM())
            		{
            			/*if(FuncionesAuxiliares.perteneceACuadrante(agState.getubicacionD().x, agState.getubicacionD().y) == cuadranteActual)
            			{
            				if(!n.getVisitado())
            					return null;
            			}*/
        		/*		int c = n.getCuadrante();
System.out.println("C: " + c);
System.out.println("CuadActual: " + cuadranteActual);
>>>>>>> origin/master
        				if(c/10 == cuadranteActual)
        				{
        					if(!n.getVisitado())
            					return null;
        				}
            		}
        			
        			
        			//Marca el cuadrante de nivel superior como visitado
        			for(NodoLista n: agState.getintensidadSeñalA())
        			{
        				if(n.getCuadrante() == cuadranteActual) ///VER DE !VISITADO
        				{
        					n.visitar();
        					break;
        				}
        			}
            			agState.setaltura("A");
                		agState.setenergia(agState.getenergia()-2);
                		
                		for(NodoLista n: agState.getintensidadSeñalA())
                		{
System.out.println("Nodo lista " + n.getCuadrante()+ " fue visitado: " + n.getVisitado());
                		}
                		return agState;	
            			
        			
        		}
        		
        	}*/
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
        	if(altura == "B" )
        	{
        		System.out.println("En altura baja");
        		for(Nodo n: agState.getintensidadSeñalB())
        		{
        			if(FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY()) == subCuadranteActual)
        			{
        				if(!n.getVisitado())
        					return null;
        			}
        		}

        		//Marca el cuadrante de nivel superior como visitado
    			for(NodoLista n: agState.getintensidadSeñalM())
    			{
    				if(n.getCuadrante() == subCuadranteActual)
    				{
    					n.visitar();
    					break;
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
        			System.out.println("En altura M");
        			for(NodoLista n: agState.getintensidadSeñalM())
            		{
        				if(FuncionesAuxiliares.perteneceACuadrante(agState.getubicacionD().x, agState.getubicacionD().y) == cuadranteActual)
            			{
            				if(!n.getVisitado())
            					return null;
            			}
            		}
        			
        			//Marca el cuadrante de nivel superior como visitado
        			for(NodoLista n: agState.getintensidadSeñalA())
        			{
        				if(n.getCuadrante() == cuadranteActual) //VER DE !VISITADO
        				{
        					System.out.println("marca el cuadrante: " + cuadranteActual + " como visitado");
        					n.visitar();
        					break;
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