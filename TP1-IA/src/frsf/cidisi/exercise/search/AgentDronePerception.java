package frsf.cidisi.exercise.search;


import java.awt.Point;
import java.util.ArrayList;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.Antena;
import frsf.ia.tp.libreriaclases.AntenaNB;
import frsf.ia.tp.libreriaclases.AntenaNMA;
import frsf.ia.tp.libreriaclases.Camara;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.NodoLista;

public class AgentDronePerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	/*private int altura;
	private int posiciongps;
	private int camara;
	private int antena;
	private int energia;
	*/
	
	private String altura;
	private Point posiciongps;
	private int energia;
	private Camara camara;
	private Antena antena;
	
 

    public  AgentDronePerception() {
    	//TODO: Complete Method
    	
    	
    	
    }

    public AgentDronePerception(Agent agent, Environment environment) {
        super(agent, environment);
        
        
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
    	//TODO: Complete Method
        
        AgentDrone agente = (AgentDrone) agentIn;
        EnvironmentMap ambiente = (EnvironmentMap) environmentIn;
        StateMap estadoAmbiente =
                ambiente.getEnvironmentState();
       
        this.posiciongps = estadoAmbiente.getposicionAgente();
        this.altura = estadoAmbiente.getAlturaAgente();
        this.energia = estadoAmbiente.getenergiaAgente();
        
        String altura = estadoAmbiente.getAlturaAgente();
        
        int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(posiciongps.x, posiciongps.y);
		
        if(altura == "B")
        {
        	Point posicionAgente = estadoAmbiente.getposicionAgente();
        	Nodo nodoAgente = (Nodo)(estadoAmbiente.getgrafoMapa()).nodoEnPosicion(posicionAgente);
        	
        	//Percepción cámara
        	if(nodoAgente != null)
        	camara = new Camara(estadoAmbiente.getPersonasQueVe(), nodoAgente);
        	antena = new AntenaNB();
        	//Percepción Antena para nivel bajo
        	
        	for(Nodo n: estadoAmbiente.getintensidadSeñalB())
        	{
        		if(FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY()) == subCuadrante)
        		{
        			antena.agregarIntensidadSeñal(n);
                	
        		}
        	}
        	
        	
        }
        else // Si altura del agente es Media(M) o Alta (A)
        {
        	camara = new Camara();
        	
        	
        	if(altura == "M")
        	{
        		antena = new AntenaNMA();
        		
        		for(NodoLista nodo : estadoAmbiente.getintensidadSeñalM())
        		{
        			System.out.println("ESTADO AMBIENTE: " + nodo.getIntensidad());
        			if(nodo.getCuadrante() == subCuadrante)
        				antena.agregarIntensidadSeñal(nodo);
        		}
        	}
        	else
        	{
        		System.out.println("ESTADO AMBIENTE: TAMAÑO: "+estadoAmbiente.getintensidadSeñalA().size());
        		antena = new AntenaNMA(estadoAmbiente.getintensidadSeñalA());
        	}
        		
        	
        }
        
       
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
       str.append("\n\n-- Percepción del Agente VANT --\n");
       str.append("Altura: "+this.altura+"\n");
       str.append("Posición: "+this.posiciongps.getX()+" - "+this.posiciongps.getY()+"\n");
       str.append("Cámara: ");
       if(this.altura == "B")
       {
    	     for(int i=0;i<this.camara.getPersonas().size();i++)
    	     {
    	    	 str.append("Personas que ve: ");
    	    	 str.append("\n\tID: "+this.camara.getPersonas().get(i).getId()+ " es victima: "+this.camara.getPersonas().get(i).esVictima());
    	     }
    	    	 
    	    
       }
       else
       {
    	   str.append("No se puede ver personas a estas alturas");
       }
    	   
       str.append("\nEnergía: "+this.energia+"\n");

       str.append("\nAntena: "); 
       if(this.altura == "B")
       {
    	   System.out.println("intensidades: "+this.antena.getIntensidadSeñal().size());
    	   for(int i=0;i<this.antena.getIntensidadSeñal().size();i++)
    	   {
    		   ArrayList<Nodo> intensidades = ((AntenaNB)this.antena.getIntensidadSeñal().get(i)).getIntensidadSeñal();
    		   
    		   for(Nodo n: intensidades)
    		   {
    			   str.append("\n\tNodo: "+ n.getId());
    		   }
    	   }
	     
       }
       else
       {System.out.println("intensidades: "+this.antena.getIntensidadSeñal().size());
    	   for(int i=0;i<this.antena.getIntensidadSeñal().size();i++)
    	   {
    		   ArrayList<NodoLista> intensidades = this.antena.getIntensidadSeñal();
    		   for(NodoLista n: intensidades)
    		   {
    			   str.append("\n\tCuadrante: "+ n.getCuadrante()+ " Intensidad: "+n.getIntensidad());
    		   }
    	   }
       }
      
        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
     public String getaltura(){
        return altura;
     }
     public void setaltura(String arg){
        this.altura = arg;
     }
     public Point getposiciongps(){
        return posiciongps;
     }
     public void setposiciongps(Point arg){
        this.posiciongps = arg;
     }
     public Camara getcamara(){
        return camara;
     }
     public void setcamara(Camara arg){
        this.camara = arg;
     }
     public Antena getantena(){
        return antena;
     }
     public void setantena(Antena arg){
        this.antena = arg;
     }
    
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        this.energia = arg;
     }
	
   
}
