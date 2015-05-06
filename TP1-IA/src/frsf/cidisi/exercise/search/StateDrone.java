

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
    private ArrayList<NodoLista> intensidadSe�alA;
    private ArrayList<NodoLista> intensidadSe�alM;
    private ArrayList<Nodo> intensidadSe�alB;
    private String direccion;
    private Vector<Persona> victimarios;
    private int energia;
	

    public StateDrone(Point p, String a, String d, int e) {
    	
			 ubicacionD = p;
			 altura = a;
			 intensidadSe�alA = new ArrayList<NodoLista>();
			 intensidadSe�alM = new ArrayList<NodoLista>();
			 intensidadSe�alB = new ArrayList<Nodo>();
			 direccion = d;
			 victimarios = new Vector<Persona>();
			 energia = e;
        
        this.initState();
    }
    
    public StateDrone()
    {
    	ubicacionD = new Point();
		 altura = "A";
		 intensidadSe�alA = new ArrayList<NodoLista>();
		 intensidadSe�alM = new ArrayList<NodoLista>();
		 intensidadSe�alB = new ArrayList<Nodo>();
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
    	
    	ArrayList<NodoLista> nuevaIntensidadSe�alA = new ArrayList<NodoLista>();
    	ArrayList<NodoLista> nuevaIntensidadSe�alM = new ArrayList<NodoLista>();
    	ArrayList<Nodo> nuevaIntensidadSe�alB = new ArrayList<Nodo>();
    	Vector<Persona> nuevaListaVictimarios = new Vector<Persona>();
    	
    	for(NodoLista n: this.intensidadSe�alA)
    	{
    		nuevaIntensidadSe�alA.add(n);
    	}
    	
    	for(NodoLista n: this.intensidadSe�alM)
    	{
    		nuevaIntensidadSe�alM.add(n);
    	}
    	
    	for(Nodo n: this.intensidadSe�alB)
    	{
    		nuevaIntensidadSe�alB.add(n);
    	}
    	
    	for(Persona p: this.victimarios)
    	{
    		nuevaListaVictimarios.add(p);
    	}
    	
    	
    	nuevoEstado.setintensidadSe�alA(nuevaIntensidadSe�alA);
    	nuevoEstado.setintensidadSe�alB(nuevaIntensidadSe�alB);
    	nuevoEstado.setintensidadSe�alM(nuevaIntensidadSe�alM);
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

    	//Inicializa la posici�n
    	ubicacionD.x = 150;
    	ubicacionD.y = 150;
    	
    	//Inicializa la energ�a
    	this.energia = 100;
    	
    	
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "---- Estado Agente VANT ----\n";
        str += "Altura: "+altura+"\n";
        str += "Ubicaci�n: ";
        if(altura== "B")
        	str += FuncionesAuxiliares.perteneceASubCuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        else
        	str += FuncionesAuxiliares.perteneceACuadrante(ubicacionD.x, ubicacionD.y)+"\n";
        str += "Intensidad de se�al\n\t\tCuadrante Se�al\nNivel Alto \n";
        for(int i=0; i<intensidadSe�alA.size();i++)
        	str += "\t\t"+intensidadSe�alA.get(i).getCuadrante()+"\t"+intensidadSe�alA.get(i).getIntensidad()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSe�alM.size();i++)
        	str += "\t\t"+intensidadSe�alM.get(i).getCuadrante()+"\t"+intensidadSe�alM.get(i).getIntensidad()+"\n";
        str += "Nivel Bajo \tPosici�n (x, Y) Se�al\n";
        for(int i=0; i<intensidadSe�alB.size();i++)
        	str += "\t\t"+intensidadSe�alB.get(i).getPosX()+" "+intensidadSe�alB.get(i).getPosY()+"\t"+intensidadSe�alB.get(i).getPersonas().size()+"\n";
        str += "Direcci�n: "+direccion+"\n";
        str += "Victimarios (ID): ";
        for(int i=0; i<victimarios.size();i++)
        	str += victimarios.get(i).getId()+" ";
        str += "\nEnerg�a: "+energia+"\n\n";

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
     public ArrayList<NodoLista> getintensidadSe�alA(){
        return intensidadSe�alA;
     }
     public void setintensidadSe�alA( ArrayList<NodoLista> arg){
        intensidadSe�alA = arg;
     }
     public ArrayList<NodoLista> getintensidadSe�alM(){
    	 return intensidadSe�alM;
     }
     public void setintensidadSe�alM( ArrayList<NodoLista> arg){
        intensidadSe�alM = arg;
     }
     public  ArrayList<Nodo> getintensidadSe�alB(){
        return intensidadSe�alB;
     }
     public void setintensidadSe�alB(ArrayList<Nodo> arg){
        intensidadSe�alB = arg;
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

