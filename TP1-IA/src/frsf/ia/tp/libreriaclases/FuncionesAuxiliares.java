package frsf.ia.tp.libreriaclases;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import jpl.fli.functor_t;

public class FuncionesAuxiliares {

	public static int ANCHO_MAPA = 600; // ancho del MAPA
	public static int ALTO_MAPA = 600; // alto del MAPA
	public static int ANCHO_CUADRANTE = ANCHO_MAPA / 2;
	public static int ALTO_CUADRANTE = ALTO_MAPA / 2;
	public static int ANCHO_SUB_CUADRANTE = ANCHO_CUADRANTE / 2;
	public static int ALTO_SUB_CUADRANTE = ALTO_CUADRANTE / 2;

	public enum EstadoSimulacion {
		EJECUTANDO, PAUSADO, DETENIDO
	};

	/**
	 * Retorna el cuadrante al que pertenecen las coordenadas pasadas como
	 * parametros
	 * 
	 * @param x
	 *            coordenadaX
	 * @param y
	 *            coordenadaY
	 * 
	 * 
	 * @return cuadrante
	 * 		 cuadrante al que pertenece la posicion pasada como parametro
	 * 		Si la posicion pasada como parametro no corresponde a un cuadrante, retorna 0
	 * */

	public static int perteneceACuadrante(int x, int y) {
		int cuadrante = 0;

		//Controla que el punto pasado no este fuera del mapa
		if((x > ANCHO_MAPA || x < 0) || (y < 0 || y > ANCHO_MAPA))
			return 0;
		
		int cuadX = x / ANCHO_CUADRANTE;
		int cuadY = y / ALTO_CUADRANTE;

		if (cuadX == 0) {
			if (cuadY == 0) {
				cuadrante = 1;
			} else // cuadY == 1
			{
				cuadrante = 3;
			}
		} else // cuadX == 1
		{
			if (cuadY == 0) {
				cuadrante = 2;
			} else // cuadY == 1
			{
				cuadrante = 4;
			}
		}

		return cuadrante;
	}

	/**
	 * Retorna el sub cuadrante al que pertenecen las coordenadas pasadas como
	 * parametros
	 * 
	 * @param x
	 *            coordenadaX
	 * @param y
	 *            coordenadaY
	 * 
	 * 
	 * @return SubCuadrante
	 * 		 sub cuadrante al que pertenece la posicion pasada como parametro
	 * 		Si la posicion pasada como parametro no corresponde a un sub cuadrante, retorna 0
	 * */

	public static int perteneceASubCuadrante(int x, int y) {
		int cuadrante = 0;
		
		//Controla que el punto pasado no este fuera del mapa
		if((x > ANCHO_MAPA || x < 0) || (y < 0 || y > ANCHO_MAPA))
			return 0;
		
		// se llama a perteneceCuadrante para tener referencia a que cuadrante
		// de nivel superior pertenece
		
		cuadrante = perteneceACuadrante(x, y) * 10;

		int cuadX = (x % ANCHO_CUADRANTE) / ANCHO_SUB_CUADRANTE;
		int cuadY = (y % ALTO_CUADRANTE) / ALTO_SUB_CUADRANTE;

		if (cuadX == 0) {
			if (cuadY == 0) {
				cuadrante += 1;
			} else // cuadY == 1
			{
				cuadrante += 3;
			}
		} else // cuadX == 1
		{
			if (cuadY == 0) {
				cuadrante += 2;
			} else // cuadY == 1
			{
				cuadrante += 4;
			}
		}

		return cuadrante;
	}

	/**
	 * Devuelve la posición central del primer subcuadrante de nivel medio
	 * correspondiente al cuadrante de nivel alto donde se encuentra el agente
	 * 
	 * @param cuadrante
	 *            Cuadrante de nivel alto donde se encuantra el agente
	 * 
	 * @return Point Posición
	 * 			Posición donde se ubicará el agente dentro del primer subcuadrante
	 * 			Si el cuadrante no pertenece a ninguna posicion retorna null
	 */
	public static Point bajarASubCuadranteM(int cuadrante) {
System.out.print("bajarASubCuadranteM. Cuadrante: "+cuadrante);
		// TODO el nro de subcuadrante es: cuadrante * 10 + 1
		Point nuevaPos = new Point();
		switch (cuadrante) {
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
System.out.print(" NuevaPos: "+nuevaPos.x+"-"+nuevaPos.y+"\n");
		return nuevaPos;
	}

	/**
	 * Obtiene nueva ubicación al norte, sólo para nivel alto o medio.
	 * 
	 * @param ubicacionActual
	 *            ubicación del agente
	 * @param altura
	 *            altura del agente
	 * @return posición del agente al norte de ubicación actual si es que se puede mover sino retorna null
	 */
	public static Point irNorte(Point ubicacionActual, String altura) {
		Point posicion = null;

		//Controla que la altura no sea baja y que la posicion este dentro del mapa
		if (altura != "B" && (ubicacionActual.x >= 0 && ubicacionActual.x <= ANCHO_MAPA && ubicacionActual.y >= 0 && ubicacionActual.y <= ANCHO_MAPA)) {
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;

			if (altura == "A") {
				y -= ALTO_CUADRANTE;

				if (y < 0) {
					return null;
				} else {
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			} 
			else // altura == M
			{
				int posNueva = y - ALTO_SUB_CUADRANTE;
				if (posNueva >= 0) // Si no sale fuera de la grilla
				{
					int auxY = y / ALTO_SUB_CUADRANTE + 1; // //cuad 1,
					// 2, 3 o 4
					// (Ej:
					// y=100->1,
					// y=160->2,
					// y=320->3,..)
					if (auxY == 2 || auxY == 4) // se puede mover hacia arriba
					// si esta en el subcuadrante
					// inferior dentro del cuadrante
					{
						posicion.x = x;
						posicion.y = posNueva;
						return posicion;
					}
				}
				return null;
			}
		}

		return null;
	}

	public static Point irOeste(Point ubicacionActual, String altura) {
		Point posicion = null;

		//Controla que la altura no sea baja y que la posicion este dentro del mapa
		if (altura != "B" && (ubicacionActual.x >= 0 && ubicacionActual.x <= ANCHO_MAPA && ubicacionActual.y >= 0 && ubicacionActual.y <= ANCHO_MAPA)) {
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;

			if (altura == "A") {
				x -= ANCHO_CUADRANTE;

				if (x < 0) {
					return null;
				} else {
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			} 
			else // Altura == "M"
			{
				int posNueva = x - ANCHO_SUB_CUADRANTE;
				
				if(posNueva >= 0 && posNueva <= ANCHO_MAPA)  // Si no sale fuera de la grilla
				{
					int auxX = x / ANCHO_SUB_CUADRANTE + 1; // //cuad
					// 1, 2,
					// 3 o 4
					// (Ej:
					// x=100->1,
					// x=160->2,
					// x=320->3,..)
					if (auxX == 4 || auxX == 2) // se puede mover hacia la
					// izquierda si esta en el
					// subcuadrante inferior dentro
					// del cuadrante
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

	public static Point irEste(Point ubicacionActual, String altura) {
		Point posicion = null;

		//Controla que la altura no sea baja y que la posicion este dentro del mapa
		if (altura != "B" && (ubicacionActual.x >= 0 && ubicacionActual.x <= ANCHO_MAPA && ubicacionActual.y >= 0 && ubicacionActual.y <= ANCHO_MAPA)) {
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;

			if (altura == "A") {
				x += ANCHO_CUADRANTE;

				if (x > ANCHO_MAPA) {
					return null;
				} else {
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			} else // altura == M
			{
				int posNueva = x + ANCHO_SUB_CUADRANTE;
				//if (posNueva <= ANCHO_CUADRANTE) // Si no sale fuera de la grilla
				if(posNueva >= 0 && posNueva <= ANCHO_MAPA)
				{
					int auxX = x / ANCHO_SUB_CUADRANTE + 1; // cuad 1, 2, 3 o 4 (Ej: x=100->1, x=160->2, x=320->3,..)
					if (auxX == 1 || auxX == 3) // se puede mover hacia la derecha si esta en el subcuadrante inferior dentro del cuadrante
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
	public static Nodo irNorteBajo(Point ubicacionActual, Grafo subGrafo) {
		Nodo nodoActual = subGrafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : subGrafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al norte de la posicion actual y
			// que este en un rango de +-10
			// devuelve el primer nodo que cumpla dichas condiciones
			if (estaAlNorte(nodoActual, n)
					&& n.getPosX() >= ubicacionActual.x - 10
					&& n.getPosX() <= ubicacionActual.x + 10) {
				return n;
			}
		}
		return null;
	}

	public static Nodo irOesteBajo(Point ubicacionActual, Grafo subGrafo) {
		Nodo nodoActual = subGrafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : subGrafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al oeste de la posicion actual y
			// que este en un rango de +-10
			// devuelve el primer nodo que cumpla dichas condiciones
			if (estaAlOeste(nodoActual, n)
					&& n.getPosX() >= ubicacionActual.y - 10
					&& n.getPosX() <= ubicacionActual.y + 10) {
				return n;
			}
		}
		return null;

	}

	public static Nodo irEsteBajo(Point ubicacionActual, Grafo subGrafo) {

		Nodo nodoActual = subGrafo.nodoEnPosicion(ubicacionActual);
//System.out.println("En irEsteBajo--");
		for (Nodo n : subGrafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al este de la posicion actual y que
			// este en un rango de +-10
			// devuelve el primer nodo que cumpla dichas condiciones
			if (estaAlEste(nodoActual, n)
					&& n.getPosX() >= ubicacionActual.y - 10
					&& n.getPosX() <= ubicacionActual.y + 10) {
				return n;
			}
		}
		return null;
	}

	/**
	 * Ubica el agente en la esquina mas cercana al centro del subcuadrante de
	 * nivel bajo donde se encuentra
	 * 
	 * @param subCuadrante
	 *            número de subcuadrante de nivel bajo
	 * @param grafoSubCuadrante
	 *            grafo que contiene los nodos y enlaces sólo del subcuadrante
	 * @return pocición de la esquina central para ese cuadrante
	 */
	public static Point centrarPosicionEsquina(int subCuadrante,
			Grafo grafoSubCuadrante) {
		// Posición central del subcuadrante (x, y)
		Point centroSubCuadrante = centroSubcuadranteBajo(subCuadrante);
		Point centroEsquina = new Point();

//System.out.println("EN EL METODOS CENTRARPOSICIONESQUINA\n\nsubCuadrante: " +subCuadrante +"CentroSubCuadrante: "+centroSubCuadrante.x + " " + centroSubCuadrante.y);
		// cálculo de la esquina central
		double d = ALTO_MAPA;
		double auxD;
//System.out.println("cantidad de nodos: " + grafoSubCuadrante.getListaNodos().size());
		for (Nodo n : grafoSubCuadrante.getListaNodos()) {
			
			auxD = Math.hypot(Math.abs(n.getPosX() - centroSubCuadrante.x), Math.abs(n.getPosY()
					- centroSubCuadrante.y));
			
			
			
					//calcularDistanciaEntrePuntos();
			//System.out.println("Nodo: " + n.getId());
			//System.out.println("AUXD: " + auxD);
			if (auxD < d) {
				d = auxD;
				//centroEsquina.setLocation(n.getPosX(), n.getPosY());
				centroEsquina.x = n.getPosX();
				centroEsquina.y = n.getPosY();
			}
		}
//System.out.println("CENTRO ESQUINA: "+ centroEsquina.x + " " + centroEsquina.y+" para subCuad: "+subCuadrante);

//Solo para mostrar, selecciona el nodo que baja
/*for(Nodo n: grafoSubCuadrante.getListaNodos())
{
	if(n.getPosX() == centroEsquina.x && n.getPosY() == centroEsquina.y)
	{
		System.out.println("Nodo al que baja: " + n.getId());
		break;
	}
}*/
		if (centroEsquina.x >= 0 && centroEsquina.y >= 0) //si se seteó una posición centro
			return centroEsquina;

		return null;
	}

	/**
	 * Posición central de un subcuadrante
	 * 
	 * @param subCuadrante
	 * @return centro Centro del subcuadrante de nivel bajo
	 */
	private static Point centroSubcuadranteBajo(int subCuadrante) {
		Point centro = new Point();
		centro.x = (((((subCuadrante / 10) % 2) == 1) ? 1 : 2)
				* ANCHO_CUADRANTE - (((subCuadrante % 10) % 2) + 1)
				* ANCHO_SUB_CUADRANTE + ANCHO_SUB_CUADRANTE / 2);
		centro.y = ((((subCuadrante / 10) <= 2) ? 1 : 2) * ALTO_CUADRANTE
				- ((subCuadrante % 10) > 2 ? 1 : 2) * ALTO_SUB_CUADRANTE + ANCHO_SUB_CUADRANTE / 2);
		
System.out.println("CENTRO: " + centro.x + " " + centro.y);
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
	 * @param nodoaActual
	 *            : es el nodo con la posicion actual del agente
	 * @param n
	 *            : es un nodo adyacente
	 * @return true: si el nodo n esta al Oeste del nodoActual
	 * @retun false: caso contrario
	 */
	private static boolean estaAlOeste(Nodo nodoActual, Nodo n) {
		if (nodoActual.getPosX() - n.getPosX() > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**

	 * Obtiene nueva ubicación al sur, sólo para nivel alto o medio.
	 * 
	 * @param ubicacionActual
	 *            ubicación del agente
	 * @param altura
	 *            altura del agente
	 * @return posición del agente al sur de ubicación actual si es que se puede mover sino retorna null
	 */
	public static Point irSur(Point ubicacionActual, String altura) {
		Point posicion = null;
		
//System.out.println("En irSur (de Faux) Altra: " + altura);
//System.out.println("Ubicacion: " + ubicacionActual.x + " " + ubicacionActual.y);

		//Controla que la altura no sea baja y que la posicion este dentro del mapa
		if (altura != "B" && (ubicacionActual.x >= 0 && ubicacionActual.x <= ANCHO_MAPA && ubicacionActual.y >= 0 && ubicacionActual.y <= ANCHO_MAPA)) {
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;

			if (altura == "A") {
				y += ALTO_CUADRANTE;

				if (y > ALTO_MAPA) {
					return null;
				} else {
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			} else // altura == M
			{
System.out.print("\tnivel M\n");
				int posNueva = y + ALTO_SUB_CUADRANTE;
				if (posNueva <= ALTO_MAPA) // Si no sale fuera de la grilla
				
				{
					int auxY = y / ALTO_SUB_CUADRANTE + 1; // //cuad 1,
					// 2, 3 o 4
					// (Ej:
					// y=100->1,
					// y=160->2,
					// y=320->3,..)
					if (auxY == 1 || auxY == 3) // se puede mover hacia abajo si
					// esta en el subcuadrante
					// superior dentro del cuadrante
					{
						posicion.x = x;
						posicion.y = posNueva;
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
			if (estaAlSur(nodoActual, n)
					&& n.getPosX() >= ubicacionActual.x - 10
					&& n.getPosX() <= ubicacionActual.x + 10) {
				return n;
			}
		}
		return null;
	}


	public static Point irNorEste(Point ubicacionActual, String altura) {
		Point posicion = null;

		//Controla que la altura no sea baja y que la posicion este dentro del mapa
		if (altura != "B" && (ubicacionActual.x > 0 && ubicacionActual.x < ANCHO_MAPA && ubicacionActual.y > 0 && ubicacionActual.y < ANCHO_MAPA)) {
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;

			if (altura == "A") {
				y -= ALTO_CUADRANTE;
				x += ANCHO_CUADRANTE;
				if (y < 0 || x > ANCHO_MAPA) {
					return null;
				} else {
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			} else // altura == M
			{
				int posNuevaY = y - ALTO_SUB_CUADRANTE;
				int posNuevaX = x + ALTO_SUB_CUADRANTE;
				if (posNuevaY >= 0 && posNuevaX <= ANCHO_MAPA) // Si no sale
				// fuera de la
				// grilla

				{
					int auxY = y / ALTO_SUB_CUADRANTE + 1; // //cuad 1, 2, 3 o 4 (Ej: y=100->1, y=160->2, y=320->3,..)
					int auxX = x / ANCHO_SUB_CUADRANTE + 1;
					//if ((auxY == 2 || auxY == 4) && (auxX == 1 || auxX == 3)) // se puede mover hacia arriba y derecha si esta en el subcuadrante superior dentro del cuadrante
					if ((auxY == 2 && auxX == 1) || ( auxY == 2 && auxX == 3) || ( auxY == 4 && auxX == 1) || ( auxY == 4 && auxX == 3))
					{
						posicion.x = posNuevaX;
						posicion.y = posNuevaY;
						return posicion;
					}
				}
				return null;
			}
		}
		return null;
	}

	public static Nodo irNorEsteBajo(Point ubicacionActual, Grafo subGrafo) {
		Nodo nodoActual = subGrafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : subGrafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al noreste de la posicion actual y
			// que este en un rango de +-10 en x
			// devuelve el primer nodo que cumpla dichas condiciones
			if (estaAlNorte(nodoActual, n) && estaAlEste(nodoActual, n)
					&& n.getPosX() >= ubicacionActual.x + 10
					&& n.getPosY() <= ubicacionActual.y - 10) {
				return n;
			}
		}
		return null;
	}
	
	public static Point irNorOeste(Point ubicacionActual, String altura) {

		Point posicion = null;

		//Controla que la altura no sea baja y que la posicion este dentro del mapa
		if (altura != "B" && (ubicacionActual.x > 0 && ubicacionActual.x < ANCHO_MAPA && ubicacionActual.y > 0 && ubicacionActual.y < ANCHO_MAPA)) {
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;

			if (altura == "A") {
				y -= ALTO_CUADRANTE;
				x -= ANCHO_CUADRANTE;
				if (y < 0 || x < 0) {
					return null;
				} else {
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			} else // altura == M
			{
				int posNuevaY = y - ALTO_SUB_CUADRANTE;
				int posNuevaX = x - ANCHO_SUB_CUADRANTE;
				if (posNuevaY >= 0 && posNuevaX >= 0) // Si no sale fuera de la
				// grilla
				{
					int auxY = y / ALTO_SUB_CUADRANTE + 1; // //cuad 1, 2, 3 o 4 (Ej: y=100->1, y=160->2, y=320->3,..)
					int auxX = x / ANCHO_SUB_CUADRANTE + 1;
					//if ((auxY == 2 || auxY == 4) && (auxX == 2 || auxX == 4)) // se puede mover hacia arriba e izquierda si esta en el subcuadrante inferior dentro del cuadrante
					if ((auxY == 2 && auxX == 2) || ( auxY == 4 && auxX == 2) || ( auxY == 2 && auxX == 4) || ( auxY == 4 && auxX == 4))
					{
						posicion.x = posNuevaX;
						posicion.y = posNuevaY;
						return posicion;
					}
				}
				return null;

			}

		}
		return posicion;

	}

	
	public static Point irSurEste(Point ubicacionActual, String altura) {
		Point posicion = null;

		//Controla que la altura no sea baja y que la posicion este dentro del mapa
		if (altura != "B" && (ubicacionActual.x > 0 && ubicacionActual.x < ANCHO_MAPA && ubicacionActual.y > 0 && ubicacionActual.y < ANCHO_MAPA)) {
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;

			if (altura == "A") {
				y += ALTO_CUADRANTE;
				x += ANCHO_CUADRANTE;
				if (y > ALTO_MAPA || x > ANCHO_MAPA) {
					return null;
				} else {
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			} else // altura == M
			{
				//int posNuevaY = posicion.y + ALTO_SUB_CUADRANTE;
				//int posNuevaX = posicion.x + ALTO_SUB_CUADRANTE;
				int posNuevaY = y + ALTO_SUB_CUADRANTE;
				int posNuevaX = x + ANCHO_SUB_CUADRANTE;
				
				//if (posNuevaY <= ALTO_MAPA && posNuevaX >= ALTO_SUB_CUADRANTE) // Si no sale fuera de la grilla
				if (posNuevaY <= ALTO_MAPA && posNuevaX <= ANCHO_MAPA) // Si no sale fuera de la grilla
				
				{
					int auxY = y / ALTO_SUB_CUADRANTE + 1; 
					int auxX = x / ANCHO_SUB_CUADRANTE + 1;
					//if ((auxY == 1 || auxY == 3) && (auxX == 1 || auxX == 2))
					if ((auxY == 1 && auxX == 1) || ( auxY == 3 && auxX == 1) || ( auxY == 1 && auxX == 3) || ( auxY == 3 && auxX == 3))
					{
						posicion.x = posNuevaX;
						posicion.y = posNuevaY;
						return posicion;
					}
				}
				return null;
			}
		}
		return null;
	}

	public static Nodo irSurEsteBajo(Point ubicacionActual, Grafo subGrafo) {
		Nodo nodoActual = subGrafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : subGrafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al sureste de la posicion actual y
			// que este en un rango de +-10 en x
			// devuelve el primer nodo que cumpla dichas condiciones
			if (estaAlSur(nodoActual, n) && estaAlEste(nodoActual, n)
					&& n.getPosX() >= ubicacionActual.x + 10
					&& n.getPosY() >= ubicacionActual.y + 10) {
				return n;
			}
		}
		return null;
	}

	
	public static Nodo irNorOesteBajo(Point ubicacionActual, Grafo subGrafo) {
		Nodo nodoActual = subGrafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : subGrafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al NorOeste de la posicion actual y
			// que este en un rango de +-10 en x
			// devuelve el primer nodo que cumpla dichas condiciones
			if ( estaAlNorte(nodoActual,n) && estaAlOeste(nodoActual, n)
					&& n.getPosX() <= ubicacionActual.x - 10
					&& n.getPosY() <= ubicacionActual.y - 10){
				return n;
			}
		}
		return null;
	}

	
	public static Point irSurOeste(Point ubicacionActual, String altura) {

		Point posicion = null;
		//Controla que la altura no sea baja y que la posicion este dentro del mapa
		if (altura != "B" && (ubicacionActual.x >= 0 && ubicacionActual.x <= ANCHO_MAPA && ubicacionActual.y >= 0 && ubicacionActual.y <= ANCHO_MAPA)) {
			posicion = new Point();
			int x = ubicacionActual.x;
			int y = ubicacionActual.y;

			if(altura == "A"){
				y += ALTO_CUADRANTE;
				x -= ANCHO_CUADRANTE;
				if (y > ALTO_MAPA || x < 0) {
					return null;
				} else {
					posicion.x = x;
					posicion.y = y;
					return posicion;
				}
			}else //Altura == "M"
			{
				int posNuevaY = y + ALTO_SUB_CUADRANTE;
				int posNuevaX = x - ALTO_SUB_CUADRANTE;

				//if (posNuevaY <= ALTO_MAPA && posNuevaX >= ALTO_SUB_CUADRANTE) // Si no sale fuera de la grilla
				if (posNuevaY <= ALTO_MAPA && posNuevaX <= ANCHO_MAPA) // Si no sale fuera de la grilla
				{
					int auxY = y / ALTO_SUB_CUADRANTE + 1; 
					int auxX = x / ANCHO_SUB_CUADRANTE + 1;
					if ((auxY == 1 && auxX == 2) || ( auxY == 3 && auxX == 2) || ( auxY == 1 && auxX == 4) || ( auxY == 3 && auxX == 4)) 
					{
						posicion.x = posNuevaX;
						posicion.y = posNuevaY;
						return posicion;
					}
				}
				return null;
			}
		}
		return null;
	}

	
	public static Nodo irSurOesteBajo(Point ubicacionActual, Grafo subGrafo) {
		
		Nodo nodoActual = subGrafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : subGrafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al SurOeste de la posicion actual y
			// que este en un rango de +-10 en x
			// devuelve el primer nodo que cumpla dichas condiciones
			if ( estaAlNorte(nodoActual,n) && estaAlOeste(nodoActual, n)
					&& n.getPosX() <= ubicacionActual.x - 10
					&& n.getPosY() >= ubicacionActual.y + 10){
				return n;
			}
		}
		return null;
	}

	/**
	 * Retorna true si la lista contiene un nodo con el id pasado como parametro
	 * @param intensidadSeñal
	 * @param id
	 * @return
	 */
	public static boolean contieneNodoConID(ArrayList<Nodo> intensidadSeñal, int id) {
		
		for(Nodo n: intensidadSeñal)
		{
			if(n.getId() == id)
			{
				return true;	
			}
		}
		return false;
	}

	/**
	 * Retorna true en caso de que todos los nodos de la lista se hayan visitados
	 * 
	 * @param intensidadSeñales
	 * @return
	 */
	public static boolean señalesVisitadas( ArrayList<NodoLista> intensidadSeñales) {
		
		for(NodoLista n: intensidadSeñales){
			if(!n.getVisitado())
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Retorna true en caso de que todos los nodos de la lista se hayan visitados
	 * 
	 * @param intensidadSeñales
	 * @return
	 */
	public static boolean señalesVisitadasB( ArrayList<Nodo> intensidadSeñales) {

		for(Nodo n: intensidadSeñales){
			if(!n.getVisitado())
			{
				return false;
			}
		}
		return true;
	}

}
