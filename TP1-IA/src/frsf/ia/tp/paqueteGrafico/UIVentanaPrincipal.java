package frsf.ia.tp.paqueteGrafico;
import java.beans.PropertyVetoException;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import frsf.ia.tp.exceciones.FormatoDeArchivoNoValidoException;
import frsf.ia.tp.libreriaclases.Converter;
import frsf.ia.tp.libreriaclases.ConverterEscenario;
import frsf.ia.tp.libreriaclases.FuncionesAuxiliares;
import frsf.ia.tp.libreriaclases.Grafo;
import frsf.ia.tp.libreriaclases.Nodo;
import frsf.ia.tp.libreriaclases.Persona;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.SystemColor;



public class UIVentanaPrincipal extends JFrame {
	
	
	//Atributos publicos para manejar la simulación
	private FuncionesAuxiliares.EstadoSimulacion estado;
	
	private static final long serialVersionUID = 1L;
	private Grafo grafo;
	private Converter datos;
	private ConverterEscenario nodosEscenario;
	private boolean datosCargados;
	
	JPanel panelGrafico;
	JPanel panelInformativo;
	UIVentanaGrafica ventanaGrafica;

	private JMenuBar menuBar;
	private JMenu menuArchivo;
	private JMenuItem mnItemCargarEscenario;
	private JMenu menuVer;
	private JMenu menuHelp;
	private JSeparator separator;
	private JMenuItem mnItemSalir;
	private JMenuItem mnItemVerCuadrantes;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JButton btnIniciar;
	private JButton btnPausar;
	private JButton btnDetener;

	public UIVentanaPrincipal() {
		
		inicializarVentanaPrincipal();
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws PropertyVetoException
	 */
	private void inicializarVentanaPrincipal() {

		datosCargados = false;
		setTitle("TP IA 2015 - Vehiculo Aéreo No Tripulado (VANT)");

		// tamaño de la ventana principal
		setSize(1200, 660);
		setResizable(false);
		setAlwaysOnTop(false);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuArchivo = new JMenu("Archivo");
		menuArchivo.setActionCommand("Archivo");
		menuBar.add(menuArchivo);

		mnItemCargarEscenario = new JMenuItem("Cargar Escenario");
		menuArchivo.add(mnItemCargarEscenario);

		// mnItemCargarMapa.addActionListener(new ActionListener() {
		// // public void actionPerformed(ActionEvent arg0) {
		// //
		// // //creacion de la ventana grafica
		// // ventanaGrafica = new UIVentanaGrafica();
		// // ventanaGrafica.setAutoscrolls(true);
		// //
		// // //se carga la ventana grafica a su correspondiente JPanel
		// // panelGrafico.removeAll();
		// // panelGrafico.add(ventanaGrafica);
		// //
		// // }
		// });

		
		
		mnItemCargarEscenario.addActionListener(new ControlerCargarEscenario());

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

		// Se crea un Managment Layout del tipo grilla para gestionar la
		// ubicacion de los paneles
		GridLayout gestorDeFondo = new GridLayout(0, 2);
		getContentPane().setLayout(gestorDeFondo);

		// JPanel para alojar el InternalJFrame para el grafico del mapa
		panelGrafico = new JPanel();
		panelGrafico.setBorder(new TitledBorder(new LineBorder(new Color(64,
				64, 64), 2, true), "Area Grafica", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.DARK_GRAY));
		panelGrafico.setSize(600, 600);
		panelGrafico.setVisible(true);
		panelGrafico.setLayout(new BorderLayout(0, 0));

		// JPanel para alojar el JFrame para el informe de busqueda del agente
		panelInformativo = new JPanel();
		panelInformativo.setSize(600, 600);
		panelInformativo.setBorder(new TitledBorder(new LineBorder(new Color(
				64, 64, 64), 2, true), "Area Informativa", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.DARK_GRAY));
		panelInformativo.setVisible(true);
		panelInformativo.setLayout(new BorderLayout(0, 0));

		// Se agregan los JPanels a la ventana principal
		getContentPane().add(panelGrafico);
		getContentPane().add(panelInformativo);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panelInformativo.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("PANEL DE CONTROL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnIniciar);
		
		btnPausar = new JButton("PAUSAR");
		panel_1.add(btnPausar);
		
		btnDetener = new JButton("DETENER");
		panel_1.add(btnDetener);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Carga el grafo ubicado en el archivo: "archivosCSV/nodosYenlaces.csv"
		cargarGrafo();
	}
	
	/**
	 * Fucnción que carga el archivo "archivosCSV/nodosYenlaces.csv"
	 * Este archivo contiene toda la información de los nodos y enlaces
	 * */
	private void cargarGrafo()
	{
		try {
			
			//File archivoElegido = fc.getSelectedFile();
			
			File archivoElegido = new File("archivosCSV" + "\\"+"nodosYenlaces.csv");
			
			//if (new EvaluaExtension().accept(archivoElegido, ".csv")) {
			//	System.out.println("Formato de Archivo Correcto");
				
			/**Se crea una instancia de la Clase Converter*/
			datos = new Converter(archivoElegido);
			
			/**controlo que se impriman los datos que cargue en en arhivo ".csv"**/
			//System.out.println(datos.getListaDeDatos());						
			
			
			/**Se carga el escenario con los elementos devueltos por Converter*/
			grafo = new Grafo(datos.getListaNodos(),datos.getListaEnlaces());
			
			
				
				
				
				/*System.out.println("IMPRIMIENDO LA LISTA DE NODOS");
										
				for(int i=0; i<datos.getListaNodos().size(); i++){
					System.out.println("Esquina: " + grafo.getListaNodos().get(i).getId()+" Posicion X: "+grafo.getListaNodos().get(i).getPosX()+" PosicionY: "+ grafo.getListaNodos().get(i).getPosY()+"\n");
				}
				
				
				System.out.println("IMPRIMIENDO Lista de Enlaces");
				
				for(int i=0; i<datos.getListaEnlaces().size(); i++){
					System.out.println("Enlace["+grafo.getListaEnlaces().get(i).getIdNodo1()+"-"+grafo.getListaEnlaces().get(i).getIdNodo2()+"] Costo["+grafo.getListaEnlaces().get(i).getPeso()+"]");
				}
				*/
				//creacion de la ventana grafica tomando los datos del grafo
				ventanaGrafica = new UIVentanaGrafica(grafo);
				ventanaGrafica.setAutoscrolls(true);
				panelGrafico.add(ventanaGrafica);
				ventanaGrafica.setVisible(true);
				ventanaGrafica.repaint();
				
				
				/*luego tomar los datos del grafo y setearlos en la tabla que se muestra en pantalla**/

				//Completar


		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	
	//Clase que se utilizar para controlar los eventos del boton "Cargar Escenario"
	class ControlerCargarEscenario implements ActionListener {

		public void actionPerformed(ActionEvent e) {
				itmCargarArchivoActionEvent(e);
		}

		private void itmCargarArchivoActionEvent(ActionEvent e) {
			
			// Crear un objeto FileChooser
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter fiCsv = new FileNameExtensionFilter(".CSV","csv");
			

			fc.setFileFilter(fiCsv);
			fc.setVisible(true);
			fc.getAcceptAllFileFilter();
			//fc.setSelectedFile(new File("archivosCSV/nodosYenlaces.csv"));
			

			// Mostrar la ventana para abrir archivo y recoger la respuesta
			// En el parámetro del showOpenDialog se indica la ventana
			// al que estará asociado. Con el valor this se asocia a la
			// ventana que la abre.

			int respuesta = fc.showOpenDialog(null);
			

			// Comprobar si se ha pulsado Aceptar
			if (respuesta == JFileChooser.APPROVE_OPTION) {

				// Crear un objeto File con el archivo elegido
				try {
					
					File archivoElegido = fc.getSelectedFile();
					
				
					if (new EvaluaExtension().accept(archivoElegido, ".csv")) {
						System.out.println("Formato de Archivo Correcto");
						
						/**Se crea una instancia de la Clase ConverterEscenario*/
						nodosEscenario = new ConverterEscenario(archivoElegido);
						
						/**controlo que se impriman los datos que cargue en en arhivo ".csv"**/
						System.out.println(datos.getListaDeDatos());						
						
						/**Se cargan las personas al grafo con los elementos devueltos por ConverterEscenario*/
						
						for(Nodo nodo: nodosEscenario.getListaNodos())
						{
							for(Persona p: nodo.getPersonas())
							{
								(grafo.buscarNodo(nodo.getId())).agregarPersona(p);	
								
								System.out.println("\nid nodo: "+nodo.getId()+" persona: " + p.getId()+" es: " +p.esVictimario());
							}
							
						}
												
						
						
						/*System.out.println("IMPRIMIENDO LA LISTA DE NODOS");
												
						for(int i=0; i<datos.getListaNodos().size(); i++){
							System.out.println("Esquina: " + grafo.getListaNodos().get(i).getId()+" Posicion X: "+grafo.getListaNodos().get(i).getPosX()+" PosicionY: "+ grafo.getListaNodos().get(i).getPosY()+"\n");
						}
						
						
						System.out.println("IMPRIMIENDO Lista de Enlaces");
						
						for(int i=0; i<datos.getListaEnlaces().size(); i++){
							System.out.println("Enlace["+grafo.getListaEnlaces().get(i).getIdNodo1()+"-"+grafo.getListaEnlaces().get(i).getIdNodo2()+"] Costo["+grafo.getListaEnlaces().get(i).getPeso()+"]");
						}
						*/
						//creacion de la ventana grafica tomando los datos del grafo
						ventanaGrafica = new UIVentanaGrafica(grafo);
						ventanaGrafica.setAutoscrolls(true);
						panelGrafico.add(ventanaGrafica);
						ventanaGrafica.setVisible(true);
						ventanaGrafica.repaint();
						
						datosCargados = true;
						/*luego tomar los datos del grafo y setearlos en la tabla que se muestra en pantalla**/

						//Completar

					} else {
						throw new FormatoDeArchivoNoValidoException();
					}

				} catch (Exception exception) {
					exception.getMessage();
				}
			}
		}
		
		private class EvaluaExtension implements java.io.FilenameFilter {
			public boolean accept(File dir, String extension) {
				return dir.getName().endsWith(extension);
			}
		}
}
	public Grafo getGrafo() {
		return grafo;
	}
	
	public FuncionesAuxiliares.EstadoSimulacion getEstado()
	{
		return estado;
	}
	
	public boolean datosCargados()
	{
		return datosCargados;
	}
}






