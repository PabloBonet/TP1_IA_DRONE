package frsf.ia.tp.paqueteGrafico;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;


public class UIMapa extends Canvas {

	
	private Image fondo;

	/**el constructor recibiria como parametro el grafo*/
	public UIMapa() {
		inicializarImagenFondo();
		inicializarComponente();
		inicializarAgente();

	}

	private void inicializarComponente() {

	}

	private void inicializarAgente() {

	}

	private void inicializarImagenFondo() {
		fondo = new ImageIcon("entornoMapa.png").getImage();
	}
	@Override
	/*Se reescribe el metodo paint que se hereda de Canvas*/
	public void paint(Graphics g) {
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		dibujarNodos(g);
		dibujarCuadrantes(g);
	}

	private void dibujarNodos(Graphics g) {
		/** Relevamiento de Nodos */

		// Linea 1
		g.setColor(Color.red);
		g.fillOval(8, 15, 20, 20); /* nodo1-1 */
		g.setColor(Color.black);
		g.drawString("1", 8, 15);
		g.fillOval(92, 15, 20, 20); /* nodo1-2 */
		g.fillOval(130, 15, 20, 20); /* nodo1-3 */
		g.fillOval(220, 15, 20, 20); /* nodo1-4 */
		g.fillOval(220, 2, 20, 20); /* nodo1-5 */
		g.fillOval(290, 2, 20, 20); /* nodo1-6 */
		g.fillOval(360, 2, 20, 20); /* nodo1-7 */
		g.fillOval(420, 2, 20, 20); /* nodo1-8 */
		g.fillOval(480, 2, 20, 20); /* nodo1-9 */
		g.fillOval(545, 2, 20, 20); /* nodo1-10 */

		// Linea 2
		g.setColor(Color.orange);
		g.fillOval(8, 65, 20, 20); /* nodo2-1 */
		g.fillOval(220, 65, 20, 20); /* nodo2-2 */
		g.fillOval(290, 65, 20, 20); /* nodo2-3 */
		g.fillOval(360, 65, 20, 20); /* nodo2-4 */
		g.fillOval(420, 65, 20, 20); /* nodo2-5 */
		g.fillOval(480, 65, 20, 20); /* nodo2-6 */
		g.fillOval(545, 65, 20, 20); /* nodo2-7 */

		// Linea 3
		g.setColor(Color.BLACK);
		g.fillOval(8, 110, 20, 20); /* nodo3-1 */
		g.fillOval(55, 110, 20, 20); /* nodo3-2 */
		g.fillOval(92, 110, 20, 20); /* nodo3-3 */
		g.fillOval(130, 110, 20, 20); /* nodo3-4 */
		g.fillOval(218, 110, 20, 20); /* nodo3-5 */

		// Linea 4
		g.setColor(Color.green);
		g.fillOval(8, 155, 20, 20); /* nodo4-1 */
		g.fillOval(92, 155, 20, 20); /* nodo4-2 */
		g.fillOval(218, 130, 20, 20); /* nodo4-3 */
		g.fillOval(290, 130, 20, 20); /* nodo4-4 */
		g.fillOval(360, 130, 20, 20); /* nodo4-5 */
		g.fillOval(420, 130, 20, 20); /* nodo4-6 */
		g.fillOval(480, 130, 20, 20); /* nodo4-7 */
		g.fillOval(545, 130, 20, 20); /* nodo4-8 */

		// Linea5
		g.setColor(Color.blue);
		g.fillOval(8, 205, 20, 20);
		g.fillOval(90, 205, 20, 20);
		g.fillOval(130, 205, 20, 20);
		g.fillOval(218, 205, 20, 20);
		g.fillOval(290, 205, 20, 20);
		g.fillOval(360, 205, 20, 20);
		g.fillOval(420, 205, 20, 20);
		g.fillOval(480, 205, 20, 20);
		g.fillOval(545, 205, 20, 20);

		// Line6
		g.setColor(Color.CYAN);
		g.fillOval(8, 290, 20, 20);
		g.fillOval(90, 290, 20, 20);
		g.fillOval(130, 290, 20, 20);
		g.fillOval(218, 290, 20, 20);
		g.fillOval(290, 275, 20, 20);
		g.fillOval(360, 275, 20, 20);
		g.fillOval(420, 275, 20, 20);
		g.fillOval(480, 275, 20, 20);
		g.fillOval(545, 275, 20, 20);

		// Linea7
		g.setColor(Color.black);
		g.fillOval(290, 340, 20, 20);
		g.fillOval(360, 340, 20, 20);
		g.fillOval(420, 340, 20, 20);
		g.fillOval(480, 340, 20, 20);
		g.fillOval(545, 340, 20, 20);

		// Linea8
		g.setColor(Color.red);
		g.fillOval(8, 368, 20, 20);
		g.fillOval(90, 368, 20, 20);
		g.fillOval(130, 368, 20, 20);
		g.fillOval(218, 368, 20, 20);
		g.fillOval(275, 368, 20, 20);
		g.fillOval(360, 368, 20, 20);

		// Linea9
		g.setColor(Color.magenta);
		g.fillOval(360, 405, 20, 20); // nodo13
		g.fillOval(420, 405, 20, 20);
		g.fillOval(480, 405, 20, 20);
		g.fillOval(545, 405, 20, 20);

		// Linea10
		g.setColor(Color.green);
		g.fillOval(8, 445, 20, 20);
		g.fillOval(90, 445, 20, 20);
		g.fillOval(130, 445, 20, 20);
		g.fillOval(215, 445, 20, 20);
		g.fillOval(275, 445, 20, 20);
		g.fillOval(325, 445, 20, 20);
		g.fillOval(360, 445, 20, 20);
		g.fillOval(420, 445, 20, 20);

		// Linea11
		g.setColor(Color.PINK);
		g.fillOval(420, 470, 20, 20);
		g.fillOval(480, 470, 20, 20);
		g.fillOval(545, 470, 20, 20);

		// Linea12
		g.setColor(Color.black);
		g.fillOval(8, 530, 20, 20);
		g.fillOval(90, 530, 20, 20);
		g.fillOval(130, 530, 20, 20);
		g.fillOval(215, 530, 20, 20);
		g.fillOval(275, 530, 20, 20);
		g.fillOval(360, 530, 20, 20);
		g.fillOval(420, 505, 20, 20);
		g.fillOval(420, 530, 20, 20);
		g.fillOval(480, 530, 20, 20);
		g.fillOval(545, 530, 20, 20);
	}

	private void dibujarArcosEntreNodos(Graphics g){
		
	}
	private void dibujarCuadrantes(Graphics g) {
		/** Trazado de Cuadrantes */

//		 //Vista Nivel Medio
//		 //CuadranteA1
//		 g.setColor(Color.BLUE);
//		 g.drawRect(0, 0, 150, 150);
//		 g.drawRect(150, 0, 150, 150);
//		 g.drawRect(0, 150, 150, 150);
//		 g.drawRect(150, 150, 150, 150);
//					
//					
//		 //CuadranteA2
//		 g.setColor(Color.green);
//		 g.drawRect(300, 0, 150, 150);
//		 g.drawRect(450, 0, 150, 150);
//		 g.drawRect(300, 150, 150, 150);
//		 g.drawRect(450, 150, 150, 150);
//					
//		 //CuadranteA3
//		 g.drawRect(0, 300, 150, 150);
//		 g.drawRect(0, 450, 150, 150);
//		 g.drawRect(150, 300, 150, 150);
//		 g.drawRect(150, 450, 150, 150);
//					
//		 //CuadtanteA4
//		 g.setColor(Color.BLACK);
//		 g.drawRect(300, 300, 150, 150);
//		 g.drawRect(450, 300, 150, 150);
//		 g.drawRect(300, 450, 150, 150);
//		 g.drawRect(450, 450, 150, 150);
		
					
		 //Vista Nivel Superior
		 g.setColor(Color.RED);
		 //cuadrante1
		 g.drawRect(0, 0, 300, 300);
		 g.drawString("A1", 150, 150);
		 
		 //cuadrante2
		 g.drawRect(300, 0, 300, 300);
		 g.drawString("A2", 450, 150);
		 
		 //cuadrante3
		 g.drawRect(0, 300, 300, 300);
		 g.drawString("A3", 150, 450);
		
		 //cuadrante4
		 g.drawRect(300, 300, 300, 300);
		 g.drawString("A4", 450, 450);
	}

}
