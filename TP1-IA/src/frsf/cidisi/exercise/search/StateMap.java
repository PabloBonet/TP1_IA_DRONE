package frsf.cidisi.exercise.search;


import java.awt.Point;
import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.*;

/**
 * This class represents the real world state.
 */
public class StateMap extends EnvironmentState {
	
	private ArrayList<NodoLista> intensidadSeñalA;
    private ArrayList<NodoLista> intensidadSeñalM;
    private ArrayList<Nodo> intensidadSeñalB;
    private Grafo grafoMapa;
    private int energiaAgente;
    private Point posicionAgente;
    private String alturaAgente;
	
    public StateMap(Grafo grafo) {
        
    	intensidadSeñalA = new ArrayList<NodoLista>();
		intensidadSeñalM = new ArrayList<NodoLista>();
		intensidadSeñalB = new ArrayList<Nodo>();
		grafoMapa = new Grafo();
		posicionAgente = new Point();
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {
    	
        this.energiaAgente = 1000;
        this.posicionAgente.setLocation(150, 150);
        this.alturaAgente  = "A";
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "----- Estado Ambiente Mapa -----\n";
        str += "Intensidad de señal\n\t\tCuadrante Señal\nNivel Alto \n";
        for(int i=0; i<intensidadSeñalA.size();i++)
        	str += "\t\t"+intensidadSeñalA.get(i).getCuadrante()+"\t"+intensidadSeñalA.get(i).getIntensidad()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSeñalM.size();i++)
        	str += "\t\t"+intensidadSeñalM.get(i).getCuadrante()+"\t"+intensidadSeñalM.get(i).getIntensidad()+"\n";
        str += "Nivel Bajo \tPosición (x, Y) Señal\n";
        for(int i=0; i<intensidadSeñalB.size();i++)
        	str += "\t\t"+intensidadSeñalB.get(i).getPosX()+" "+intensidadSeñalB.get(i).getPosY()+"\t"+intensidadSeñalB.get(i).getPersonas().size()+"\n";
        str += "Victimarios (ID): ";
        
        return str;
    }

    // The following methods are agent-specific:
	
     public ArrayList<NodoLista> getintensidadSeñalA(){
        return intensidadSeñalA;
     }
     public void setintensidadSeñalA(ArrayList<NodoLista> arg){
        intensidadSeñalA = arg;
     }
     public ArrayList<NodoLista> getintensidadSeñalM(){
        return intensidadSeñalM;
     }
     public void setintensidadSeñalM(ArrayList<NodoLista> arg){
        intensidadSeñalM = arg;
     }
     public ArrayList<Nodo> getintensidadSeñalB(){
        return intensidadSeñalB;
     }
     public void setintensidadSeñalB(ArrayList<Nodo> arg){
        intensidadSeñalB = arg;
     }
     public Grafo getgrafoMapa(){
        return grafoMapa;
     }
     public void setgrafoMapa(Grafo arg){
        grafoMapa = arg;
     }
     public int getenergiaAgente(){
        return energiaAgente;
     }
     public void setenergiaAgente(int arg){
        energiaAgente = arg;
     }
     public Point getposicionAgente(){
        return posicionAgente;
     }
     public void setposicionAgente(Point arg){
        posicionAgente = arg;
     }
	public String getAlturaAgente(){
		return alturaAgente;
	}
     public void setAlturaAgente(String a){
    	 this.alturaAgente = a;
     }
     
     /**
      * Función que recorre los nodos adyacentes al nodo donde se encuentra el agente y 
      * retorna las personas que son visibles al agente desde la posición donde se encuentra
      * */
     public ArrayList<Persona> getPersonasQueVe()
     {
    	 
    	 ArrayList<Persona> personasObservables = new ArrayList<Persona>();
    	 
    	//retorna los nodos adyacentes a la posicion pasada como parametro
    	 //Los nodos adyacentes serán los nodos que la cámara va a poder ver en todas las direcciones
    	 //el segundo parámetro indica si se va a retornar también el nodo acual
    	 ArrayList<Nodo> nodosAdyacentes = grafoMapa.nodosAdyacentesAPosicion(posicionAgente, true); 
    	 
    	 for(Nodo n: nodosAdyacentes)
    	 {
    		personasObservables.addAll(n.getPersonas());
    	 }
    	 
    	 return personasObservables;
     }

}

