package frsf.cidisi.exercise.search;

import java.awt.Point;
import java.util.ArrayList;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.ia.tp.libreriaclases.Antena;
import frsf.ia.tp.libreriaclases.AntenaNB;
import frsf.ia.tp.libreriaclases.AntenaNMA;
import frsf.ia.tp.libreriaclases.Camara;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.Gps;
import frsf.ia.tp.libreriaclases.Grafo;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.NodoLista;

public class AgentDronePerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	private String altura;
	private Gps gps;
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
       
       
        gps = new Gps();
        
        this.gps.setPosiciongps(estadoAmbiente.getposicionAgente());
        this.altura = estadoAmbiente.getAlturaAgente();
        this.energia = estadoAmbiente.getenergiaAgente();
        
        String altura = estadoAmbiente.getAlturaAgente();
        
        int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(gps.getPosiciongps().x, gps.getPosiciongps().y);
		

        if(altura != "A")
        {
        	if(altura == "M")
        	{
        		//cargar el grafo perteneciente al cuadrante
System.out.println("Se carga el grafoCuadrante!!! Pos: "+gps.getPosiciongps().x+" "+gps.getPosiciongps().y+"(en AgentDronePerception)");
            	gps.cargarGrafoCuadrante(estadoAmbiente.getgrafoMapa());
            	
            	antena = new AntenaNMA();
        		
        		for(NodoLista nodo : estadoAmbiente.getintensidadSeñalM())
        		{
        			if(nodo.getCuadrante() == subCuadrante)
        				antena.agregarIntensidadSeñal(nodo);
        		}
        	}
        	else //altura == B
        	{
        		Point posicionAgente = estadoAmbiente.getposicionAgente();
            	Nodo nodoAgente = (Nodo)(estadoAmbiente.getgrafoMapa()).nodoEnPosicion(posicionAgente);
            	
            	//cargar el grafo perteneciente al subcuadrante
System.out.println("Se carga el grafoSubCuadrante!!! Pos: "+posicionAgente.x+" "+posicionAgente.y+"(en AgentDronePerception)");
            	gps.cargarGrafoSubCuadrante(estadoAmbiente.getgrafoMapa());
            	
            	//Percepción cámara
            	if(nodoAgente != null)
            		camara = new Camara(estadoAmbiente.getPersonasQueVe(nodoAgente, gps.getGrafoSubCuadrante()), nodoAgente);
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
        }
        else
        {
    		antena = new AntenaNMA(estadoAmbiente.getintensidadSeñalA());
        }
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
       str.append("-- Percepción del Agente VANT --\n");
       str.append("Altura: "+this.altura+"\n");
       str.append("Posición: "+this.gps.getPosiciongps().x+" - "+this.gps.getPosiciongps().y+"\n");
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
    	   str.append("No se puede ver personas a esta altura");
       }
    	   
       str.append("\nEnergía: "+this.energia);

       str.append("\nAntena: "); 
       if(this.altura == "B")
       {
    	  
    	   for(int i=0;i<this.antena.getIntensidadSeñal().size();i++)
    	   {
    		   if(this.antena.getIntensidadSeñal().get(i) instanceof AntenaNB){
    			   ArrayList<Nodo> intensidadesN = ((AntenaNB)this.antena.getIntensidadSeñal().get(i)).getIntensidadSeñal();

    			   for(Nodo n: intensidadesN)
    			   {
    				   str.append("\n\tNodo: "+ n.getId()+" Cantidad personas: "+n.getPersonas().size());
    			   }
    		   }
    	   }
    	   
    	   str.append("\nEsquinas en cuadrante: ");
    	   for(Nodo n: gps.getGrafoSubCuadrante().getListaNodos())
    	   {
    		   str.append("\nNodo: "+n.getId());
    	   }
	     
       }
       else
       {
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
     public Gps getgps(){
        return gps;
     }
     public void setgps(Gps arg){
        this.gps = arg;
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
