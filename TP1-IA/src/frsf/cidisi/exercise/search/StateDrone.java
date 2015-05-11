

package frsf.cidisi.exercise.search;



import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.ia.tp.libreriaclases.*;


/**
 * Represent the internal state of the Agent.
 */
public class StateDrone extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private Point ubicacionD;
    private String altura;
    private ArrayList<NodoLista> intensidadSeñalA;
    private ArrayList<NodoLista> intensidadSeñalM;
    private ArrayList<Nodo> intensidadSeñalB;
    private String direccion;
    private Vector<Persona> victimarios;
    private int energia;
	

    public StateDrone(Point p, String a, String d, int e) {
    	
			 ubicacionD = p;
			 altura = a;
			 intensidadSeñalA = new ArrayList<NodoLista>();
			 intensidadSeñalM = new ArrayList<NodoLista>();
			 intensidadSeñalB = new ArrayList<Nodo>();
			 direccion = d;
			 victimarios = new Vector<Persona>();
			 energia = e;
        
        this.initState();
    }
    
    public StateDrone()
    {
    	ubicacionD = new Point();
		 altura = "A";
		 intensidadSeñalA = new ArrayList<NodoLista>();
		 intensidadSeñalM = new ArrayList<NodoLista>();
		 intensidadSeñalB = new ArrayList<Nodo>();
		 direccion = "N";
		 victimarios = new Vector<Persona>();
		 energia = 100;
   
   this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
	    	
    	StateDrone nuevoEstado = new StateDrone(this.ubicacionD, this.altura, this.direccion, this.energia);
    	
    	ArrayList<NodoLista> nuevaIntensidadSeñalA = new ArrayList<NodoLista>();
    	ArrayList<NodoLista> nuevaIntensidadSeñalM = new ArrayList<NodoLista>();
    	ArrayList<Nodo> nuevaIntensidadSeñalB = new ArrayList<Nodo>();
    	Vector<Persona> nuevaListaVictimarios = new Vector<Persona>();
    	
    	for(NodoLista n: this.intensidadSeñalA)
    	{
    		nuevaIntensidadSeñalA.add(n);
    	}
    	
    	for(NodoLista n: this.intensidadSeñalM)
    	{
    		nuevaIntensidadSeñalM.add(n);
    	}
    	
    	for(Nodo n: this.intensidadSeñalB)
    	{
    		nuevaIntensidadSeñalB.add(n);
    	}
    	
    	for(Persona p: this.victimarios)
    	{
    		nuevaListaVictimarios.add(p);
    	}
    	
    	
    	nuevoEstado.setintensidadSeñalA(nuevaIntensidadSeñalA);
    	nuevoEstado.setintensidadSeñalB(nuevaIntensidadSeñalB);
    	nuevoEstado.setintensidadSeñalM(nuevaIntensidadSeñalM);
    	nuevoEstado.setvictimario(nuevaListaVictimarios);
    	
        return nuevoEstado;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
        //TODO: Complete Method
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {

    	//Inicializa la posición
    	ubicacionD.x = 150;
    	ubicacionD.y = 150;
    	
    	//Inicializa la energía
    	this.energia = 100;
    	
    	
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "------ Estado Agente VANT -----\n";
        str += "Altura: "+altura+"\n";
        str += "Ubicación: ";
        if(altura== "B")
        	str += FuncionesAuxiliares.perteneceASubCuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        else
        	str += FuncionesAuxiliares.perteneceACuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        str += "Intensidad de señal\n\t\tCuadrante Señal\nNivel Alto \n";
        for(int i=0; i<intensidadSeñalA.size();i++)
        	str += "\t\t"+intensidadSeñalA.get(i).getCuadrante()+"\t"+intensidadSeñalA.get(i).getIntensidad()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSeñalM.size();i++)
        	str += "\t\t"+intensidadSeñalM.get(i).getCuadrante()+"\t"+intensidadSeñalM.get(i).getIntensidad()+"\n";
        str += "Nivel Bajo \tPosición (x, Y) Señal\n";
        for(int i=0; i<intensidadSeñalB.size();i++)
        	str += "\t\t"+intensidadSeñalB.get(i).getPosX()+" "+intensidadSeñalB.get(i).getPosY()+"\t"+intensidadSeñalB.get(i).getPersonas().size()+"\n";
        str += "Dirección: "+direccion+"\n";
        str += "Victimarios (ID): ";
        for(int i=0; i<victimarios.size();i++)
        	str += victimarios.get(i).getId()+" ";
        str += "\nEnergía: "+energia+"\n\n";

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
       //TODO: Complete Method
        
    	 if (!(obj instanceof StateDrone))
             return false;
    	
    	 Point ubicacion = ((StateDrone) obj).getubicacionD();
    	 String altura = ((StateDrone) obj).getaltura();
    	 int energia = ((StateDrone) obj).getenergia();
    	 
    	 
    	 if(ubicacion.x != this.ubicacionD.x || ubicacion.y != this.ubicacionD.y)
    		 return false;
    	 
    	 if(!altura.equals(this.altura))
    		 return  false;
    	 
    	 if(energia != this.energia)
    		 return false;
    	 
        return true;
    }

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     public Point getubicacionD(){
        return ubicacionD;
     }
     public void setubicacionD(Point arg){
        ubicacionD = arg;
     }
     public String getaltura(){
        return altura;
     }
     public void setaltura(String arg){
        altura = arg;
     }
     public ArrayList<NodoLista> getintensidadSeñalA(){
        return intensidadSeñalA;
     }
     public void setintensidadSeñalA( ArrayList<NodoLista> arg){
        intensidadSeñalA = arg;
     }
     public ArrayList<NodoLista> getintensidadSeñalM(){
    	 return intensidadSeñalM;
     }
     public void setintensidadSeñalM( ArrayList<NodoLista> arg){
        intensidadSeñalM = arg;
     }
     public  ArrayList<Nodo> getintensidadSeñalB(){
        return intensidadSeñalB;
     }
     public void setintensidadSeñalB(ArrayList<Nodo> arg){
        intensidadSeñalB = arg;
     }
     public String getdireccion(){
        return direccion;
     }
     public void setdireccion(String arg){
        direccion = arg;
     }
     public Vector<Persona> getvictimario(){
        return victimarios;
     }
     public void setvictimario(Vector<Persona> arg){
        victimarios = arg;
     }
     
     public void agregarVictimario(Persona p)
     {
    	 if(victimarios != null)
    	 {
    		 victimarios.add(p);
    	 }
     }
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        energia = arg;
     }
	
}

