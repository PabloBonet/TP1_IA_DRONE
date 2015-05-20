package frsf.ia.tp.paqueteGrafico;



/**
 * @author Martin
 *
 */
public class Main {

	static private UIVentanaPrincipal frame;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		//	frame = new UIVentanaPrincipal();
		int aux, auxC, auxC1;
		int ANCHO_CUADRANTE = 300;
		int ALTO_SUB_CUADRANTE = ANCHO_CUADRANTE / 2; //150
		for(int i=1;i<7;i++){

				aux = (i*80)-ALTO_SUB_CUADRANTE;
				auxC=(i*80)/150+1; //cuad 1, 2, 3 o 4 (Ej: y=100->0, y=160->1, y=320->2,..) 
				if(aux >=0){
					//subCuadrante=aux;//%10 - ALTO_SUB_CUADRANTE ;
					
					System.out.println("Pos cuadrante "+i*80+": y= "+aux);
					System.out.println("Subcuadrante: "+(i*80)/150+" ~ "+aux/150);
					auxC1=aux/150;
					if(auxC==2 || auxC==4) 
					{
						System.out.println("\tSi!");
					}
				}

			
		}
//		aux = (1)/10;  //
//		subCuadrante = (1)%10 - ALTO_SUB_CUADRANTE ;
//System.out.println("Pos cuadrante 1: y= "+aux+" + "+subCuadrante);
		//int x = (x/10)*ANCHO_CUADRANTE - (((x%10)%2)+1)*ANCHO_SUB_CUADRANTE+ANCHO_SUB_CUADRANTE/2;
	}
}
