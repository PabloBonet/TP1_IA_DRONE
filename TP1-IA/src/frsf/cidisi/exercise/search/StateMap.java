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
        //probar crear los nodos dentro del for.....
        //preguntar a q subcuad pertenece
        //y si �ste existe en la lista corresp, sumar la intensidad
        //sino agregar ese cuad.
        NodoLista NAc1 = new NodoLista(1, 0);
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
        
        //Se cargan las intensidades de se�al a cada lista del estado del ambiente
        for( Nodo n: grafoMapa.getListaNodos()){
        	
        	//NA: cuadrante que se ve desde nivel alto
        	int NA = FuncionesAuxiliares.perteneceACuadrante(n.getPosX(), n.getPosY());
        	
        	//NM: cuadrante que se ve desde nivel medio (DEBE IDENTIFICAR EN QUE NIVEL NA EST�))
        	int NM = FuncionesAuxiliares.perteneceASubCuadrante(n.getPosX(), n.getPosY());
        	
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

