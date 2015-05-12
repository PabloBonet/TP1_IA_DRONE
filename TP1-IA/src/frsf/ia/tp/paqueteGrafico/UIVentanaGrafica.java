package frsf.ia.tp.paqueteGrafico;



import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import javax.swing.UIManager;

import frsf.ia.tp.libreriaclases.Grafo;

public class UIVentanaGrafica extends JInternalFrame {

	UIMapa mapa;
	
	public UIVentanaGrafica(Grafo grafo)  {
		//setRootPaneCheckingEnabled(false);
		inicializar(grafo);
	}
	
	private void inicializar(Grafo grafo){
		setSize(600, 600);
		setTitle("mapa");
		setAlignmentX(0); setAlignmentY(0);
		setResizable(false);
		setClosable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		mapa = new UIMapa(grafo);
		
		getContentPane().add(mapa);
	}
}
