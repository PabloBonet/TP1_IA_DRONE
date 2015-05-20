package frsf.ia.tp.paqueteGrafico;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import frsf.ia.tp.libreriaclases.*;


public class UIMapa extends Canvas {

	
	private Image fondo;
	private Grafo grafo;
	

	/**el constructor recibiria como parametro el grafo*/
	public UIMapa(Grafo grafo) {
		this.grafo = grafo;
		inicializarImagenFondo();
		
	}

	private void inicializarImagenFondo() {
		fondo = new ImageIcon("imagenes/entornoMapa.png").getImage();
	}
	
	@Override
	/*Se reescribe el metodo paint que se hereda de Canvas*/
	public void paint(Graphics g) {
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		dibujarCuadrantes(g);
		dibujarNodos(g,grafo);
		dibujarArcosEntreNodos(g, grafo);
	}

	/**Metodo para graficar los nodos sobre el mapa*/
	private void dibujarNodos(Graphics g,Grafo grafo) {
		for(int i=0; i<grafo.getListaNodos().size(); i++){
			
			//cuenta las personas en el nodo
			
			int cantPersonas = grafo.getListaNodos().get(i).getPersonas().size();
			
			//coloca el color al nodo dependiendo si tiene victimarios
			if(grafo.getListaNodos().size() > 0)
			{
				if(hayVictimarios(grafo.getListaNodos().get(i).getPersonas()))
				{
					g.setColor(Color.red);
					//System.out.println("\npos x:" + grafo.getListaNodos().get(i).getPosX() + " pos y: "+ grafo.getListaNodos().get(i).getPosY());
				}
				else
				{
					if(grafo.getListaNodos().get(i).getPersonas().size()>0)
					{
						g.setColor(Color.green);
						/*System.out.println("\npos x:" + grafo.getListaNodos().get(i).getPosX() + " pos y: "+ grafo.getListaNodos().get(i).getPosY());

						System.out.println("\nPertenece a cuadrante: " +FuncionesAuxiliares.perteneceACuadrante(grafo.getListaNodos().get(i).getPosX(), grafo.getListaNodos().get(i).getPosY()));
						System.out.println("\nPertenece a subcuadrante: " +FuncionesAuxiliares.perteneceASubCuadrante(grafo.getListaNodos().get(i).getPosX(), grafo.getListaNodos().get(i).getPosY()));
					*/
					}
					else
					{
						g.setColor(Color.white);
					}
					
				}
			}
			g.fillOval(grafo.getListaNodos().get(i).getPosX()-10,grafo.getListaNodos().get(i).getPosY()-10,18,18);
			String esquina = Integer.toString(grafo.getListaNodos().get(i).getId());
			g.setColor(Color.black);
			g.drawString(esquina, grafo.getListaNodos().get(i).getPosX(),grafo.getListaNodos().get(i).getPosY());
			String personas = Integer.toString(cantPersonas);
			g.drawString(personas, grafo.getListaNodos().get(i).getPosX()+5,grafo.getListaNodos().get(i).getPosY()+5);
		}
	}
	
	public boolean hayVictimarios(List<Persona> personas)
	{
		for(Persona p: personas)
		{
			if(p.esVictimario())
				return true;
		}
	return false;
	}
	/**Metodo para graficar los enlaces entre nodos**/
	private void dibujarArcosEntreNodos(Graphics g, Grafo grafo){
		int idNodo1;
		int idNodo2;
		String costo;
		Nodo nodo1;
		Nodo nodo2;
		for(int i=0; i<grafo.getListaEnlaces().size(); i++){
			//variables auxiliares para guardar el id de cada nodo que compone el enlace
			idNodo1 = grafo.getListaEnlaces().get(i).getIdNodo1();
			idNodo2 = grafo.getListaEnlaces().get(i).getIdNodo2();
			nodo1 = grafo.buscarNodo(idNodo1);
			nodo2 = grafo.buscarNodo(idNodo2);
			
			g.setColor(Color.MAGENTA);
			g.drawLine(nodo1.getPosX(),nodo1.getPosY(),nodo2.getPosX(),nodo2.getPosY());
			
//			costo = Integer.toString(grafo.getListaEnlace().get(i).getPeso());
//			g.drawString(costo, grafo.getListaNodos().get(i).getPosX(),grafo.getListaNodos().get(i).getPosY());
		}
	}
	
	
	private void dibujarCuadrantes(Graphics g) {
		/** Trazado de Cuadrantes */
		 //Vista Nivel Superior
		 g.setColor(Color.RED);
		 //cuadrante1
		 g.drawRect(0, 0, 300, 300);
		 g.drawOval(146, 135, 20, 20);
		 g.drawString("A1", 150, 150);
		 
		 //cuadrante2
		 g.drawRect(300, 0, 300, 300);
		 g.drawOval(446, 135, 20, 20);
		 g.drawString("A2", 450, 150);
		 
		 //cuadrante3
		 g.drawRect(0, 300, 300, 300);
		 g.drawOval(146, 435, 20, 20);
		 g.drawString("A3", 150, 450);
		
		 //cuadrante4
		 g.drawRect(300, 300, 300, 300);
		 g.drawOval(446, 435, 20, 20);
		 g.drawString("A4", 450, 450);
	}
	


}
