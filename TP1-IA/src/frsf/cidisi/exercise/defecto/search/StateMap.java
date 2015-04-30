package frsf.cidisi.exercise.defecto.search;


import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class StateMap extends EnvironmentState {
	
	//TODO: Setup Variables
    //private Other intensidadSeñalA;
    //private Other intensidadSeñalM;
    //private Other intensidadSeñalB;
    //private Other grafoMapa;
    private int energiaAgente;
    private int[] posicionAgente;
	
    public StateMap() {
        
        //TODO: Complete Method
    	/*
			// intensidadSeñalA = initData0;
			// intensidadSeñalM = initData1;
			// intensidadSeñalB = initData2;
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
	
//     public Other getintensidadSeñalA(){
//        return intensidadSeñalA;
//     }
//     public void setintensidadSeñalA(Other arg){
//        intensidadSeñalA = arg;
//     }
//     public Other getintensidadSeñalM(){
//        return intensidadSeñalM;
//     }
//     public void setintensidadSeñalM(Other arg){
//        intensidadSeñalM = arg;
//     }
//     public Other getintensidadSeñalB(){
//        return intensidadSeñalB;
//     }
//     public void setintensidadSeñalB(Other arg){
//        intensidadSeñalB = arg;
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

