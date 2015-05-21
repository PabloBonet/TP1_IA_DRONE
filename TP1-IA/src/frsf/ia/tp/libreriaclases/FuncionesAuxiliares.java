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
	public enum EstadoSimulacion{EJECUTANDO , PAUSADO, DETENIDO };
	
	
		
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
		
		int cuadX = x/ANCHO_CUADRANTE;
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
	
	/**
	 * Obtiene nueva ubicación al norte, sólo para nivel alto o medio.
	 * 
	 * @param ubicacionActual ubicación del agente
	 * @param altura altura del agente
	 * @return posición del agente al norte de ubicación actual si es que se puede mover
	 */
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
		


		return null;
	}
	
	
	
	public static Point irEste(Point ubicacionActual, String altura)
	{
		Point posicion = null;
		
		if(altura != "B")
		{
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;
			
			if(altura == "A")
			{
				x += ANCHO_CUADRANTE;

				if(x > ANCHO_MAPA)
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
				int posNueva = posicion.x + ANCHO_SUB_CUADRANTE;
				if(posNueva <= ANCHO_CUADRANTE)  //Si no sale fuera de la grilla
				{
					int auxX = posicion.x/ANCHO_SUB_CUADRANTE+1;  ////cuad 1, 2, 3 o 4 (Ej: x=100->1, x=160->2, x=320->3,..)
					if(auxX == 1 || auxX == 3) //se puede mover hacia la derecha si esta en el subcuadrante inferior dentro del cuadrante
					{
						posicion.x = posNueva;
						posicion.y = y;
						return posicion;
					}
				}
				return null;
			}
		}
		


		return null;
	}
	
	/**
	 * 
	 * @param ubicacionActual
	 * @param subGrafo
	 * @return
	 */
	public static Nodo irNorteBajo(Point ubicacionActual, Grafo subGrafo)
	{
		Nodo nodoActual  = subGrafo.nodoEnPosicion(ubicacionActual);
	
		
		for(Nodo n: subGrafo.buscarAdyacentes(nodoActual))
		{
			//verifica que haya un nodo mas al norte de la posicion actual y que este en un rango de +-10
			// devuelve el primer nodo que cumpla dichas condiciones
			if( estaAlNorte(nodoActual,n) && n.getPosX() >= ubicacionActual.x -10 &&  n.getPosX() <= ubicacionActual.x +10)
			{
				return n;
			}
		}
		return null;
	}
	
	public static Nodo irEsteBajo(Point ubicacionActual, Grafo subGrafo)
	{	
		Nodo nodoActual  = subGrafo.nodoEnPosicion(ubicacionActual);
	
		
		for(Nodo n: subGrafo.buscarAdyacentes(nodoActual))
		{
			//verifica que haya un nodo mas al norte de la posicion actual y que este en un rango de +-10
			// devuelve el primer nodo que cumpla dichas condiciones
			if( estaAlEste(nodoActual,n) && n.getPosX() >= ubicacionActual.y -10 &&  n.getPosX() <= ubicacionActual.y +10)
			{
				return n;
			}
		}
		return null;
	}

	/**
	 * Ubica el agente en la esquina mas cercana al centro del subcuadrante de nivel bajo donde se encuentra
	 * 
	 * @param subCuadrante número de subcuadrante de nivel bajo
	 * @param grafoSubCuadrante grafo que contiene los nodos y enlaces sólo del subcuadrante
	 * @return pocición de la esquina central para ese cuadrante
	 */
	public static Point centrarPosicionEsquina(int subCuadrante, Grafo grafoSubCuadrante) {
		//Posición central del subcuadrante (x, y)
		Point centroSubCuadrante = centroSubcuadranteBajo(subCuadrante);
		Point centroEsquina = new Point();
		
		//cálculo de la esquina central
		double d=ALTO_MAPA, auxD;
		for(Nodo n: grafoSubCuadrante.getListaNodos())
		{
			auxD = Math.hypot(n.getPosX()-centroSubCuadrante.x, n.getPosY()-centroSubCuadrante.y);
			if(auxD < d)
			{
				d = auxD;
				centroEsquina.setLocation(n.getPosX(), n.getPosY());
			}
		}

		if(centroEsquina.getLocation() != null)
			return centroEsquina;
		
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
	

// Dado un el nodo actual y un no
	/**
	 * @param nodoActual
	 *            : es el nodo con la posicion actual del agente
	 * @param n
	 *            : es un nodo adyacente
	 * @return true: si el nodo n esta al Norte de nodoActual
	 * @return false: caso contrario
	 */
	private static boolean estaAlNorte(Nodo nodoActual, Nodo n) {
		if (nodoActual.getPosY() - n.getPosY() > 0) {
			return true;
		} else
			return false;
	}

	/**
	 * @param nodoActual
	 *            : es el nodo con la posicion actual del agente
	 * @param n
	 *            : es un nodo adyacente
	 * @return true: si el nodo n esta al Este del nodoActual
	 * @return false: caso contrario
	 */
	private static boolean estaAlEste(Nodo nodoActual, Nodo n) {
		if (n.getPosX() - nodoActual.getPosX() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param nodoaActual
	 *            : es el nodo con la posicion actual del agente
	 * @param n
	 *            : es un nodo adyacente
	 * @return true: si el nodo n esta al Sur del nodoActual
	 * @retun false: caso contrario
	 */
	private static boolean estaAlSur(Nodo nodoaActual, Nodo n) {
		if (n.getPosY() - nodoaActual.getPosY() > 0) {
			return true;
		} else {
			return false;
		}
	}


/**
 * Obtiene nueva ubicación al sur, sólo para nivel alto o medio.
 * 
 * @param ubicacionActual ubicación del agente
 * @param altura altura del agente
 * @return posición del agente al sur de ubicación actual si es que se puede mover
 */
public static Point irSur(Point ubicacionActual, String altura) {
	Point posicion = null;

	if(altura != "B")
	{
		posicion = new Point();
		int x = ubicacionActual.x;
		int y = ubicacionActual.y;

		if(altura == "A")
		{
			y += ALTO_CUADRANTE;

			if(y >= ALTO_MAPA)
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
			int nosNueva = posicion.y + ALTO_SUB_CUADRANTE;
			if(nosNueva <= ALTO_MAPA)  //Si no sale fuera de la grilla
			{
				int auxY = posicion.y/ALTO_SUB_CUADRANTE+1;  ////cuad 1, 2, 3 o 4 (Ej: y=100->1, y=160->2, y=320->3,..)
				if(auxY == 1 || auxY == 3) //se puede mover hacia abajo si esta en el subcuadrante superior dentro del cuadrante
				{
					posicion.x = x;
					posicion.y = nosNueva;
					return posicion;
				}
			}
			return null;
		}
	}
	return null;
}

	public static Nodo irSurBajo(Point ubicacionActual, Grafo subGrafo) {
		Nodo nodoActual = subGrafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : subGrafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al sur de la posicion actual y que
			// este en un rango de +-10 en x
			// devuelve el primer nodo que cumpla dichas condiciones
			if (estaAlNorte(nodoActual, n)
					&& n.getPosX() >= ubicacionActual.x - 10
					&& n.getPosX() <= ubicacionActual.x + 10) {
				return n;
			}
		}
		return null;
	}
}

