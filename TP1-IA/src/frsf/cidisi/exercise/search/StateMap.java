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
       
        inicializarListasSeñales();
     /*   NodoLista NAc1 = new NodoLista(1, 0);
        NodoLista NAc2 = new NodoLista(2, 0);
        NodoLista NAc3 = new NodoLista(3, 0);
        NodoLista NAc4 = new NodoLista(4, 0);
        NodoLista NMc11 = new NodoLista(1, 0);
        NodoLista NMc12 = new NodoLista(2, 0);
        NodoLista NMc13 = new NodoLista(3, 0);
        NodoLista NMc14 = new NodoLista(4, 0);
        NodoLista NMc21 = new NodoLista(1, 0);
        NodoLista NMc22 = new NodoLista(2, 0);
        NodoLista NMc23 = new NodoLista(3, 0);
        NodoLista NMc24 = new NodoLista(4, 0);
        NodoLista NMc31 = new NodoLista(1, 0);
        NodoLista NMc32 = new NodoLista(2, 0);
        NodoLista NMc33 = new NodoLista(3, 0);
        NodoLista NMc34 = new NodoLista(4, 0);
        NodoLista NMc41 = new NodoLista(1, 0);
        NodoLista NMc42 = new NodoLista(2, 0);
        NodoLista NMc43 = new NodoLista(3, 0);
        NodoLista NMc44 = new NodoLista(4, 0);
       */ 
        //Se cargan las intensidades de señal a cada lista del estado del ambiente
  //      for( Nodo n: grafoMapa.getListaNodos()){
        	
        	//NA: cuadrante que se ve desde nivel alto
     //   	int NA = FuncionesAuxiliares.perteneceACuadrante(n.getPosX(), n.getPosY());
        	
        	//NM: cuadrante que se ve desde nivel medio (DEBE IDENTIFICAR EN QUE NIVEL NA ESTÁ))
    /*    	int NM = FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY());
        	
        	switch(NA){
        	case 1:
        		NAc1.setIntensidad(NAc1.getIntensidad()+n.getPersonas().size()*10);
        		switch(NM){
        		case 1:
        			NMc11.setIntensidad(NMc11.getIntensidad()+n.getPersonas().size()*10);
        			break;
        		case 2:
        			NMc12.setIntensidad(NMc12.getIntensidad()+n.getPersonas().size()*10);
        			break;
        		case 3:
        			NMc13.setIntensidad(NMc13.getIntensidad()+n.getPersonas().size()*10);
        			break;
        		case 4:
        			NMc14.setIntensidad(NMc14.getIntensidad()+n.getPersonas().size()*10);
        			break;
        		default:
        			break;
        		}
        		break;
        	case 2:
        		NAc2.setIntensidad(NAc2.getIntensidad()+n.getPersonas().size()*10);
        		break;
        	case 3:
        		NAc3.setIntensidad(NAc3.getIntensidad()+n.getPersonas().size()*10);
        		break;
        	case 4:
        		NAc4.setIntensidad(NAc4.getIntensidad()+n.getPersonas().size()*10);
        		break;
        	default:
        		break;
        	}

        }*/
    }

    private void inicializarListasSeñales() {
    	 //probar crear los nodos dentro del for.....
        //preguntar a q subcuad pertenece
        //y si éste existe en la lista corresp, sumar la intensidad
        //sino agregar ese cuad.
        
    	
    	for(Nodo n : this.getgrafoMapa().getListaNodos())
    	{
    		if(n.getPersonas().size() > 0) //Si el nodo tiene personas (Si tiene personas estas producen señales)
    		{
    			int intensidad = 0;
    			Point ubicacion = new Point();
    			ubicacion.x = n.getPosX();
    			ubicacion.y = n.getPosY();
    			
    			intensidadSeñalB.add(n);
    			
    			int subCuadrante = FuncionesAuxiliares.perteneceASubCuadrante(ubicacion.x, ubicacion.y);
    			int cuadrante = FuncionesAuxiliares.perteneceACuadrante(ubicacion.x, ubicacion.y);
    			
    			/*
    			 * Tratamiento para la lista intensidadSeñalM
    			 */
    			
    			//Busca en la lista intensidadSeñalM si el cuadrante ya se encuentra 
    			boolean existe = false;
    			for(NodoLista nodo : intensidadSeñalM)
    			{
    				if(nodo.getCuadrante() == subCuadrante){
    					existe = true;
    					break;
    				}
    					
    			}
    			
    			if(!existe)
    			{
    				 intensidad = n.getPersonas().size()*20; 
    				NodoLista nodo = new NodoLista(subCuadrante, intensidad);
    				intensidadSeñalM.add(nodo);
    			}
    			else
    			{
    				existe = false;
    				intensidad = 0;
    			}
    			
    			/*
    			 * Tratamiento para la lista intensidadSeñalA
    			 */
    			
    			//Busca en la lista intensidadSeñalA si el cuadrante ya se encuentra 
    			
    			for(NodoLista nodo : intensidadSeñalA)
    			{
    				if(nodo.getCuadrante() == cuadrante){
    					existe = true;
    					break;
    				}
    			}
    			
    			if(!existe)
    			{
    				 intensidad = n.getPersonas().size()*10; 
    				NodoLista nodo = new NodoLista(cuadrante, intensidad);
    				intensidadSeñalA.add(nodo);
    			}
    			else
    			{
    				existe = false;
    				intensidad = 0;
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

