package frsf.ia.tp.libreriaclases;

import java.awt.Point;

public class FuncionesAuxiliares {

	public static int ANCHO_MAPA = 600; // ancho del MAPA 
	public static int ALTO_MAPA = 600; // alto del MAPA
	public static int ANCHO_CUADRANTE = ANCHO_MAPA / 2;
	public static int ALTO_CUADRANTE = ALTO_MAPA / 2;
	public static int ANCHO_SUB_CUADRANTE = ANCHO_CUADRANTE / 2;
	public static int ALTO_SUB_CUADRANTE = ALTO_CUADRANTE / 2;
	
		
	/**
	 * Retorna el cuadrante al que pertenecen las coordenadas pasadas como parametros
	 * 
	 * @param x 
	 * 		coordenadaX
	 * @param y
	 * 		coordenadaY
	 * 
	 * 
	 * @return cuadrante
	 * 		cuadrante al que pertenece la posicion pasada como parametro
	 * */
	
	public static int perteneceACuadrante(int x, int y)
	{
		int cuadrante = 0;
		
		int cuadX = x/ANCHO_MAPA;
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
		
		return cuadrante;
	}
	
	/**
	 * Retorna el sub cuadrante al que pertenece las coordenadas pasadas como parametros
	 * 
	 * @param x 
	 * 		coordenadaX
	 * @param y
	 * 		coordenadaY
	 * 
	 * 
	 * @return cuadrante
	 * 		sub cuadrante al que pertenece la posicion pasada como parametro
	 * */
	
	public static int perteneceASubCuadrante(int x, int y)
	{
		int cuadrante = 0;
		//se llama a perteneceCuadrante para tener referencia a que cuadrante de nivel superior pertenece
		cuadrante = perteneceACuadrante(x, y)*10;
		
		int cuadX = (x%ANCHO_CUADRANTE)/ANCHO_SUB_CUADRANTE;
		int cuadY = (y%ALTO_CUADRANTE) / ALTO_SUB_CUADRANTE;
		
		if(cuadX == 0)
		{
			if(cuadY == 0)
			{
				
					cuadrante += 1;
				
			}
			else //cuadY == 1
			{
				cuadrante += 3;
			}
		}
		else //cuadX == 1
		{
			if(cuadY == 0)
			{
				cuadrante += 2;
			}
			else //cuadY == 1
			{
					cuadrante += 4;
				
			}
		}
		
		return cuadrante;
	}

	/**
	 * Devuelve la posición central del cuadrante
	 */
	public static Point centrarPosicionCuadrante(int subCuadrante) {
		// TODO
		Point nuevaPos = new Point();
		
		return null;
	}
	
	
}
