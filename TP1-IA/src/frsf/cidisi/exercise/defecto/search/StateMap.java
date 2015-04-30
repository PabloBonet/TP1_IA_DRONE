package frsf.cidisi.exercise.defecto.search;


import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class StateMap extends EnvironmentState {
	
	//TODO: Setup Variables
    //private Other intensidadSeņalA;
    //private Other intensidadSeņalM;
    //private Other intensidadSeņalB;
    //private Other grafoMapa;
    private int energiaAgente;
    private int[] posicionAgente;
	
    public StateMap() {
        
        //TODO: Complete Method
    	/*
			// intensidadSeņalA = initData0;
			// intensidadSeņalM = initData1;
			// intensidadSeņalB = initData2;
			// grafoMapa = initData3;
			// energiaAgente = initData4;
			// posicionAgente = initData5;
        */
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

        //TODO: Complete Method
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

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
//     public Other getintensidadSeņalA(){
//        return intensidadSeņalA;
//     }
//     public void setintensidadSeņalA(Other arg){
//        intensidadSeņalA = arg;
//     }
//     public Other getintensidadSeņalM(){
//        return intensidadSeņalM;
//     }
//     public void setintensidadSeņalM(Other arg){
//        intensidadSeņalM = arg;
//     }
//     public Other getintensidadSeņalB(){
//        return intensidadSeņalB;
//     }
//     public void setintensidadSeņalB(Other arg){
//        intensidadSeņalB = arg;
//     }
//     public Other getgrafoMapa(){
//        return grafoMapa;
//     }
//     public void setgrafoMapa(Other arg){
//        grafoMapa = arg;
//     }
     public int getenergiaAgente(){
        return energiaAgente;
     }
     public void setenergiaAgente(int arg){
        energiaAgente = arg;
     }
     public int[] getposicionAgente(){
        return posicionAgente;
     }
     public void setposicionAgente(int[] arg){
        posicionAgente = arg;
     }
	

}

