package frsf.ia.tp.libreriaclases;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Clase de utilidad que convierte un archivo de texto con contenido CSV
 * (comma-separated values - valores separados por comas) en una lista de listas
 * de strings, que representa la matriz contenida en el archivo procesado.<br/>
 * <u><em>Importante:</em></u> Esta clase no debe ser modificada.
 * 
 * @author DIED
 */
public class LectorCsv {

	/** El archivo que contiene los datos. */
	private File archivo;

	/** El token por el cual se separan los datos. Por defecto es una coma. */
	private String token = ",";

	public LectorCsv(File archivo) {
		this.archivo = archivo;
	}

	public LectorCsv(String path) {
		this.archivo = new File(path);
	}

	public File getArchivo() {
		return archivo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Lee los datos contenidos en el archivo.
	 * 
	 * @return La lista conteniendo los datos leidos.
	 * @throws IOException
	 *           Cuando ocurre un error en la lectura del archivo.
	 */
	public List<List<String>> leerArchivo() throws IOException {
		List<List<String>> resultado = new ArrayList<List<String>>();
		InputStream is = new FileInputStream(this.archivo);
		DataInputStream dis = new DataInputStream(is);
		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		String linea;
		while ((linea = br.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(linea, token);
			List<String> fila = new ArrayList<String>();
			while (tokenizer.hasMoreTokens()) {
				fila.add(tokenizer.nextToken().trim());
			}
			resultado.add(fila);
		}
		dis.close();
		return resultado;
	}

}
