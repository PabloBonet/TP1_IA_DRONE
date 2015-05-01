package frsf.ia.tp.paqueteGrafico;



import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import javax.swing.UIManager;

public class UIVentanaGrafica extends JInternalFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UIMapa mapa;
	
	public UIVentanaGrafica()  {
		//setRootPaneCheckingEnabled(false);
		inicializar();
	}
	
	private void inicializar(){
		setSize(600, 600);
		setTitle("mapa");
		setAlignmentX(0); setAlignmentY(0);
		setResizable(false);
		setClosable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		mapa = new UIMapa();
		
		getContentPane().add(mapa);

		
		
	}

}
