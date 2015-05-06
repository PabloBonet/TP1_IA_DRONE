package frsf.ia.tp.exceciones;

import javax.swing.JOptionPane;

public class FormatoDeArchivoNoValidoException extends Exception {
	   public FormatoDeArchivoNoValidoException(){
           super("Formato de Archivo Invalido");
           JOptionPane.showMessageDialog(null, "Formato de Archivo Invalido", "Informacion", 1);
          
   }





}




