package frsf.ia.tp.paqueteGrafico;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import frsf.ia.tp.libreriaclases.*;


public class UIMapa extends Canvas {

	
	private Image fondo;
	private Grafo grafo;
	

	/**el constructor recibiria como parametro el grafo*/
	public UIMapa(Grafo grafo) {
		this.grafo = grafo;
		inicializarImagenFondo();
//		inicializarComponente();
//		inicializarAgente();

	}

	private void inicializarComponente() {

	}

	private void inicializarAgente() {

	}

	private void inicializarImagenFondo() {
		fondo = new ImageIcon("imagenes/entornoMapa.png").getImage();
	}
	@Override
	/*Se reescribe el metodo paint que se hereda de Canvas*/
	public void paint(Graphics g) {
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		dibujarNodos(g,grafo);
		dibujarCuadrantes(g);
	}

	private void dibujarNodos(Graphics g,Grafo grafo) {
	
		/** Relevamiento de Nodos */
		for(int i=0; i<grafo.getListaNodos().size(); i++){
			g.fillOval(grafo.getListaNodos().get(i).getPosX(),grafo.getListaNodos().get(i).getPosY(),15,15);
		}
	}

	private void dibujarArcosEntreNodos(Graphics g){
		
	}
	private void dibujarCuadrantes(Graphics g) {
		/** Trazado de Cuadrantes */
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
