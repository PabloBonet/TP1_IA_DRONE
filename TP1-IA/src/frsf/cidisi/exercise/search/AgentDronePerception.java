package frsf.cidisi.exercise.search;

import java.awt.Point;
import java.io.IOException;
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
            	gps.cargarGrafoCuadrante(estadoAmbiente.getgrafoMapa());
            	
//            	antena = new AntenaNMA();
            	//agrega todos los nodos de la lista de nivel medio ya que cada nodo se marc� como visitaso
            	//si es que ya se pas� por �ste
       //TODO AC� DEBE COPIAR SOLO LAS INTENSIDADES PARA ESE CUADRANTE DE NIVEL MEDIO??????????????????????
       //PODR�A COPIAR TODAS?????????????????????????????????????
            	antena = new AntenaNMA(estadoAmbiente.getintensidadSe�alM());
//        		for(NodoLista nodo : estadoAmbiente.getintensidadSe�alM())
//        		{
//        			if(nodo.getCuadrante() == subCuadrante)
//        				antena.agregarIntensidadSe�al(nodo);
//        		}
        	}
        	else //altura == B
        	{
        		Point posicionAgente = estadoAmbiente.getposicionAgente();
            	Nodo nodoAgente = (Nodo)(estadoAmbiente.getgrafoMapa()).nodoEnPosicion(posicionAgente);
            	
            	//cargar el grafo perteneciente al subcuadrante
            	gps.cargarGrafoSubCuadrante(estadoAmbiente.getgrafoMapa());
            	
            	//Percepci�n c�mara
            	if(nodoAgente != null)
            		camara = new Camara(estadoAmbiente.getPersonasQueVe(nodoAgente, gps.getGrafoSubCuadrante()), nodoAgente);
            	antena = new AntenaNB();
            	//Percepci�n Antena para nivel bajo
            	
            	for(Nodo n: estadoAmbiente.getintensidadSe�alB())
            	{
            		if(FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY()) == subCuadrante)
            		{
System.out.println("carg� intensisad en B| ");
            			antena.agregarIntensidadSe�al(n);
                    	
            		}
            	}
        	}
        	
        	
        }
        else
        {
    		antena = new AntenaNMA(estadoAmbiente.getintensidadSe�alA());
        }
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
       str.append("-- Percepci�n del Agente VANT --\n");
       str.append("Altura: "+this.altura+"\n");
       str.append("Posici�n: "+this.gps.getPosiciongps().x+" - "+this.gps.getPosiciongps().y+"\n");
       str.append("C�mara. Personas que ve:  ");
       if(this.altura == "B")
       {
    	     for(int i=0;i<this.camara.getPersonas().size();i++)
    	     {
    	    	 str.append("\n\tID: "+this.camara.getPersonas().get(i).getId()+ " es victima: "+this.camara.getPersonas().get(i).esVictima());
    	     }
       }
       else
       {
    	   str.append("No se puede ver personas a esta altura");
       }
    	   
       str.append("\nEnerg�a: "+this.energia);

       str.append("\nAntena: "); 
       if(this.altura == "B")
       {
    	   ArrayList<Nodo> intensidadesN = new ArrayList<Nodo>();
    	   for(int i=0;i<this.antena.getIntensidadSe�al().size();i++)
    	   {
    		   if(this.antena.getIntensidadSe�al().get(i) instanceof AntenaNB){
    			   intensidadesN = ((AntenaNB)this.antena.getIntensidadSe�al().get(i)).getIntensidadSe�al();
    		   }
    	   }
    	   for(Nodo n: intensidadesN)
    	   {
    		   str.append("\n\tPosici�n: "+ n.getPosX()+"-"+n.getPosY()+"\tPersonas: "+n.getPersonas().size());
    	   }
    	   str.append("\nEsquinas en cuadrante: ");
    	   for(Nodo n: gps.getGrafoSubCuadrante().getListaNodos())
    	   {
    		   str.append("\n\tNodo: "+n.getId());
    	   }
	     
       }
       else
       {
			ArrayList<NodoLista> intensidades = this.antena.getIntensidadSe�al();
    		for(NodoLista n: intensidades)
    		{
    			str.append("\n\tCuadrante: "+ n.getCuadrante()+ " Intensidad: "+n.getIntensidad());
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
