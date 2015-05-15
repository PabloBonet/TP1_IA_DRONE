package frsf.cidisi.exercise.search;


import java.awt.Point;
import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.tp.libreriaclases.*;

/**
 * This class represents the real world state.
 */
public class StateMap extends EnvironmentState {
	
	private ArrayList<NodoLista> intensidadSe�alA;
    private ArrayList<NodoLista> intensidadSe�alM;
    private ArrayList<Nodo> intensidadSe�alB;
    private Grafo grafoMapa;
    private int energiaAgente;
    private Point posicionAgente;
    private String alturaAgente;
	
    public StateMap(Grafo grafo) {
        
    	intensidadSe�alA = new ArrayList<NodoLista>();
		intensidadSe�alM = new ArrayList<NodoLista>();
		intensidadSe�alB = new ArrayList<Nodo>();
		grafoMapa = new Grafo();
		posicionAgente = new Point();
		this.setgrafoMapa(grafo);
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
       
        inicializarListasSe�ales();
    }

    /**
     * Inicializa la lista de intensidad de se�ales
     */
    private void inicializarListasSe�ales() {
        /*pregunta a que subcuadrante pertenece
         * y si �ste existe en la lista correspondiente, suma la intensidad
         * sino agrega ese cuadrante en la lista correspondiente
         */
        
    	int intensidad = 0;
    	boolean existe = false;
    	for(Nodo n : this.getgrafoMapa().getListaNodos())
    	{
    		if(n.getPersonas().size() > 0) //Si el nodo tiene personas (Si tiene personas estas producen se�ales)
    		{
    			Point ubicacion = new Point();
    			ubicacion.x = n.getPosX();
    			ubicacion.y = n.getPosY();

    			intensidadSe�alB.add(n);

    			int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(ubicacion.x, ubicacion.y);
    			int cuadrante = FuncionesAuxiliares.perteneceACuadrante(ubicacion.x, ubicacion.y);

    			/*
    			 * Tratamiento para la lista intensidadSe�alM
    			 */

    			//Busca en la lista intensidadSe�alM si el cuadrante ya se encuentra 
    			for(NodoLista nodo : intensidadSe�alM)
    			{
    				if(nodo.getCuadrante() == subCuadrante){
    					//adiciona a la intensidad de se�al del subCuadrante ya existente
    					intensidad = n.getPersonas().size()*20;
    					nodo.setIntensidad(nodo.getIntensidad()+intensidad);
    					existe = true;
    					break;
    				}
    			}

    			if(!existe)
    			{
    				//crea un nodo para el subCuadrante y lo agrega a la lista de nivel medio
    				intensidad = n.getPersonas().size()*20; 
    				NodoLista nodo = new NodoLista(subCuadrante, intensidad);
    				intensidadSe�alM.add(nodo);
    			}

    			existe = false;
    			intensidad = 0;

    			/*
    			 * Tratamiento para la lista intensidadSe�alA
    			 */

    			//Busca en la lista intensidadSe�alA si el cuadrante ya se encuentra 

    			for(NodoLista nodo : intensidadSe�alA)
    			{
    				if(nodo.getCuadrante() == cuadrante){
    					//adiciona a la intensidad de se�al del cuadrante ya existente
    					intensidad = n.getPersonas().size()*10;
    					nodo.setIntensidad(nodo.getIntensidad()+intensidad);
    					existe = true;
    					break;
    				}
    			}

    			if(!existe)
    			{
    				//crea un nodo para el cuadrante y lo agrega a la lista de nivel alto
    				intensidad = n.getPersonas().size()*10; 
    				NodoLista nodo = new NodoLista(cuadrante, intensidad);
    				intensidadSe�alA.add(nodo);
    			}
    		}
    	}

    }

	/**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "----- Estado Ambiente Mapa -----\n";
        str += "Intensidad de se�al\n\t\tCuadrante Se�al\nNivel Alto \n";
        for(int i=0; i<intensidadSe�alA.size();i++)
        	str += "\t\t"+intensidadSe�alA.get(i).getCuadrante()+"\t"+intensidadSe�alA.get(i).getIntensidad()+"\n";
        str += "Nivel Medio \n";
        for(int i=0; i<intensidadSe�alM.size();i++)
        	str += "\t\t"+intensidadSe�alM.get(i).getCuadrante()+"\t"+intensidadSe�alM.get(i).getIntensidad()+"\n";
        str += "Nivel Bajo \tPosici�n (x, Y) Se�al\n";
        for(int i=0; i<intensidadSe�alB.size();i++)
        	str += "\t\t"+intensidadSe�alB.get(i).getPosX()+" "+intensidadSe�alB.get(i).getPosY()+"\t"+intensidadSe�alB.get(i).getPersonas().size()+"\n";
        str += "Victimarios (ID): ";
        
        return str;
    }

    // The following methods are agent-specific:
	
     public ArrayList<NodoLista> getintensidadSe�alA(){
        return intensidadSe�alA;
     }
     public void setintensidadSe�alA(ArrayList<NodoLista> arg){
        intensidadSe�alA = arg;
     }
     public ArrayList<NodoLista> getintensidadSe�alM(){
        return intensidadSe�alM;
     }
     public void setintensidadSe�alM(ArrayList<NodoLista> arg){
        intensidadSe�alM = arg;
     }
     public ArrayList<Nodo> getintensidadSe�alB(){
        return intensidadSe�alB;
     }
     public void setintensidadSe�alB(ArrayList<Nodo> arg){
        intensidadSe�alB = arg;
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
      * Funci�n que recorre los nodos adyacentes al nodo donde se encuentra el agente y 
      * retorna las personas que son visibles al agente desde la posici�n donde se encuentra
      * */
     public ArrayList<Persona> getPersonasQueVe()
     {
    	 
    	 ArrayList<Persona> personasObservables = new ArrayList<Persona>();
    	 
    	//retorna los nodos adyacentes a la posicion pasada como parametro
    	 //Los nodos adyacentes ser�n los nodos que la c�mara va a poder ver en todas las direcciones
    	 //el segundo par�metro indica si se va a retornar tambi�n el nodo acual
    	 ArrayList<Nodo> nodosAdyacentes = grafoMapa.nodosAdyacentesAPosicion(posicionAgente, true); 
    	 
    	 for(Nodo n: nodosAdyacentes)
    	 {
    		personasObservables.addAll(n.getPersonas());
    	 }
    	 
    	 return personasObservables;
     }

}

