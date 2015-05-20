package frsf.ia.tp.libreriaclases;

import java.awt.Point;
import java.util.ArrayList;

import jpl.fli.functor_t;

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
	 * Retorna el sub cuadrante al que pertenecen las coordenadas pasadas como parametros
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
	 * Devuelve la posición central del primer subcuadrante de nivel medio correspondiente 
	 * al cuadrante de nivel alto donde se encuentra el agente
	 * @param cuadrante Cuadrante de nivel alto donde se encuantra el agente
	 * 
	 * @return Point Posición donde se ubicará el agente dentro del primer subcuadrante
	 */
	public static Point bajarASubCuadranteM(int cuadrante) {
		// TODO el nro de subcuadrante es: cuadrante * 10 + 1 
		Point nuevaPos = new Point();
		switch(cuadrante){
		case 1:
			nuevaPos.setLocation(75, 75);
			break;
		case 2:
			nuevaPos.setLocation(375, 75);
			break;
		case 3:
			nuevaPos.setLocation(75, 375);
			break;
		case 4:
			nuevaPos.setLocation(375, 375);
			break;
		default:
			return null;
		}
		return nuevaPos;
	}
	
	public static Point irNorte(Point ubicacionActual, String altura)
	{
		Point posicion = null;
		
		if(altura != "B")
		{
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;
			
			if(altura == "A")
			{
				y -= ALTO_CUADRANTE;

				if(y <= 0)
				{
					return null;
				}
				else
				{
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			}
			else //altura == M
			{
				int nosNueva = posicion.y - ALTO_SUB_CUADRANTE;
				if(nosNueva >= 0)  //Si no sale fuera de la grilla
				{
					int auxY = posicion.y/ALTO_SUB_CUADRANTE+1;  ////cuad 1, 2, 3 o 4 (Ej: y=100->1, y=160->2, y=320->3,..)
					if(auxY == 2 || auxY == 4) //se puede mover hacia arriba si esta en el subcuadrante inferior dentro del cuadrante
					{
						posicion.x = x;
						posicion.y = nosNueva;
						return posicion;
					}
				}
				return null;
			}
		}
		else //altura == B
		{

		}


		return null;
	}

	/**
	 * Centra el agente en la esquina central del cuadrante de nivel bajo donde se encuentra
	 * 
	 * @param subCuadrante cuadrante de nivel bajo
	 * @return pocición de la esquina central para ese cuadrante
	 */
	public static Point centrarPosicionCuadrante(int subCuadrante) {
		//Posición central del subcuadrante (x, y)
		Point centroSubCuadrante = centroSubcuadranteBajo(subCuadrante);
		Point centroEsquina = new Point();
		//buscar los adyacentes a esa esquina
		
		//quitar los q no correspondan a ese subcuadrante
		// ...
		return null;
	}

	/**
	 * Posición central de un subcuadrante
	 * @param subCuadrante
	 * @return centro Centro del subcuadrante de nivel bajo
	 */
	private static Point centroSubcuadranteBajo(int subCuadrante) {
		Point centro = new Point();
		centro.x = (((((subCuadrante/10)%2)==1) ? 1 : 2) *ANCHO_CUADRANTE - (((subCuadrante%10)%2)+1)*ANCHO_SUB_CUADRANTE+ANCHO_SUB_CUADRANTE/2);
		centro.y =  ((((subCuadrante/10)<=2) ? 1 : 2) *ALTO_CUADRANTE - ((subCuadrante%10)>2?1:2)*ALTO_SUB_CUADRANTE + ANCHO_SUB_CUADRANTE/2);
		return centro;
	}
	
}
