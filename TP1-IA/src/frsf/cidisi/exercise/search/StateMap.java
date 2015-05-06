package frsf.cidisi.exercise.search;


import java.awt.Point;
import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.*;

/**
 * This class represents the real world state.
 */
public class StateMap extends EnvironmentState {
	
	private ArrayList<NodoLista> intensidadSeņalA;
    private ArrayList<NodoLista> intensidadSeņalM;
    private ArrayList<Nodo> intensidadSeņalB;
    private Grafo grafoMapa;
    private int energiaAgente;
    private Point posicionAgente;
	
    public StateMap() {
        
    	intensidadSeņalA = new ArrayList<NodoLista>();
		intensidadSeņalM = new ArrayList<NodoLista>();
		intensidadSeņalB = new ArrayList<Nodo>();
		grafoMapa = new Grafo();
		energiaAgente = 0;
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
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

    // The following methods are agent-specific:
	
     public ArrayList<NodoLista> getintensidadSeņalA(){
        return intensidadSeņalA;
     }
     public void setintensidadSeņalA(ArrayList<NodoLista> arg){
        intensidadSeņalA = arg;
     }
     public ArrayList<NodoLista> getintensidadSeņalM(){
        return intensidadSeņalM;
     }
     public void setintensidadSeņalM(ArrayList<NodoLista> arg){
        intensidadSeņalM = arg;
     }
     public ArrayList<Nodo> getintensidadSeņalB(){
        return intensidadSeņalB;
     }
     public void setintensidadSeņalB(ArrayList<Nodo> arg){
        intensidadSeņalB = arg;
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
	

}

