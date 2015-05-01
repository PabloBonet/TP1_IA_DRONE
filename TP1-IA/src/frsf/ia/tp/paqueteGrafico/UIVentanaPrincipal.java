package frsf.ia.tp.paqueteGrafico;
import java.beans.PropertyVetoException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;



public class UIVentanaPrincipal extends JFrame{
	
	JPanel panelGrafico;
	JPanel panelInformativo;
	
	UIVentanaGrafica ventanaGrafica;
	private JMenuBar menuBar;
	private JMenu menuArchivo;
	private JMenuItem mnItemCargarMapa;
	private JMenu menuVer;
	private JMenu menuHelp;
	private JSeparator separator;
	private JMenuItem mnItemSalir;
	private JMenuItem mnItemVerCuadrantes;
	
	public UIVentanaPrincipal (){
		setResizable(false);
		inicializarVentanaPrincipal();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws PropertyVetoException 
	 */
	private void inicializarVentanaPrincipal(){
		
		setTitle("TP IA 2015 - Vehiculo Aéreo No Tripulado (VANT)");
		
		//tamaño de la ventana principal
		setSize(1200, 660);
		
		setAlwaysOnTop(true);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuArchivo = new JMenu("Archivo");
		menuArchivo.setActionCommand("Archivo");
		menuBar.add(menuArchivo);
		
		mnItemCargarMapa = new JMenuItem("Cargar Mapa");
		
		
		mnItemCargarMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//creacion de la ventana grafica
				ventanaGrafica = new UIVentanaGrafica();
				ventanaGrafica.setAutoscrolls(true);
				
				//se carga la ventana grafica a su correspondiente JPanel
				panelGrafico.removeAll();
				panelGrafico.add(ventanaGrafica);
				
			}
		});
		menuArchivo.add(mnItemCargarMapa);
		
		separator = new JSeparator();
		menuArchivo.add(separator);
		
		mnItemSalir = new JMenuItem("Salir");
		mnItemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				System.exit(0);
			}
		});
		menuArchivo.add(mnItemSalir);
		
		menuVer = new JMenu("Ver");
		menuBar.add(menuVer);
		
		mnItemVerCuadrantes = new JMenuItem("VisualizarCuadrantes");
		mnItemVerCuadrantes.setEnabled(false);
		menuVer.add(mnItemVerCuadrantes);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		// Se crea un Managmen Layout del tipo grilla para gestionar la ubicacion de los paneles
		GridLayout gestorDeFondo = new GridLayout(0, 2);
		getContentPane().setLayout(gestorDeFondo);
		
		//JPanel para alojar el InternalJFrame para el grafico del mapa
		panelGrafico = new JPanel();
		panelGrafico.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "Area Grafica", TitledBorder.CENTER, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelGrafico.setSize(600, 600);
		panelGrafico.setVisible(true);
		panelGrafico.setLayout(new BorderLayout(0, 0));
		
		
		
		//JPanel para alojar el JFrame para el informe de busqueda del agente
		panelInformativo = new JPanel();
		panelInformativo.setSize(600, 600);
		panelInformativo.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "Area Informativa", TitledBorder.CENTER, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelInformativo.setVisible(true);
		panelInformativo.setLayout(new BorderLayout(0, 0));
		
		
		
		
		
		//Se agregan los JPanels a la ventana principal
		getContentPane().add(panelGrafico);
		getContentPane().add(panelInformativo);
		
		

		
		setAlwaysOnTop(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void activarBotonVisualizarCuadrantes(){
		mnItemVerCuadrantes.setEnabled(false);
	}
	
}
