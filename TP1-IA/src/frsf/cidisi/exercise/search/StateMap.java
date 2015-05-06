package frsf.cidisi.exercise.search;


import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class StateMap extends EnvironmentState {
	
	//TODO: Setup Variables
    //private Other intensidadSe�alA;
    //private Other intensidadSe�alM;
    //private Other intensidadSe�alB;
    //private Other grafoMapa;
    private int energiaAgente;
    private int[] posicionAgente;
	
    public StateMap() {
        
        //TODO: Complete Method
    	/*
			// intensidadSe�alA = initData0;
			// intensidadSe�alM = initData1;
			// intensidadSe�alB = initData2;
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
	
//     public Other getintensidadSe�alA(){
//        return intensidadSe�alA;
//     }
//     public void setintensidadSe�alA(Other arg){
//        intensidadSe�alA = arg;
//     }
//     public Other getintensidadSe�alM(){
//        return intensidadSe�alM;
//     }
//     public void setintensidadSe�alM(Other arg){
//        intensidadSe�alM = arg;
//     }
//     public Other getintensidadSe�alB(){
//        return intensidadSe�alB;
//     }
//     public void setintensidadSe�alB(Other arg){
//        intensidadSe�alB = arg;
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

