package frsf.ia.tp.libreriaclases;

public class FuncionesAuxiliares {

	public static int ANCHO_CUDRANTE = 10; // ancho del cuadrante 10 unidades
	public static int ALTO_CUADRANTE = 10; // alto del cuadrante 10 unidades
	public static int ANCHO_SUB_CUADRANTE = 5; // ancho del subcuadrante 5 unidades
	public static int ALTO_SUB_CUADRANTE = 5; // alto del subcuadrante 5 unidades
	
	/**
	 * Retorna el cuadrante al que pertenece las coordenadas pasadas como parametros
	 * 
	 * @param x 
	 * 		coordenadaX
	 * @param y
	 * 		coordenadaY
	 * @param altura
	 * 		altura en la que se encuentra el cuadrante que se desea averiguar
	 * 		0: Bajo
	 * 		1: Medio
	 * 		2: Alto
	 * 
	 * @return cuadrante
	 * 		cuadrante al que pertenece la posicion pasada como parametro
	 * */
	
	public static int perteneceACuadrante(int x, int y, int altura)
	{
		int cuadrante = 0;
		
		int cuadX = x/ANCHO_CUDRANTE;
		int cuadY = y/ALTO_CUADRANTE;
		
		if(cuadX == 0)
		{
			if(cuadY == 0)
			{
				cuadrante = 1;
			}
			else //cuadY == 1
			{
				cuadrante = 3;
			}
		}
		else //cuadX == 1
		{
			if(cuadY == 0)
			{
				cuadrante = 2;
			}
			else //cuadY == 1
			{
				cuadrante = 4;
			}
		}
		
		if(altura == 1)
		{
			
		}
		
		return cuadrante;
	}
}
