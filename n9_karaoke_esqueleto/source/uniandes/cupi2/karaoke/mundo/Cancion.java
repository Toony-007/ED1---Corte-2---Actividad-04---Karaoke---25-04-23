/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_karaoke
 * Autor: Equipo Cupi2  2018-2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.karaoke.mundo;

/**
 * Representa una canci�n del karaoke
 */
public class Cancion {
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Nombre de la canci�n
	 */
	private String nombre;

	/**
	 * Duraci�n en segundos de la canci�n
	 */
	private int duracion;

	/**
	 * Letra de la canci�n
	 */
	private String letra;

	/**
	 * Dificultad de la canci�n
	 */
	private int dificultad;

	/**
	 * Ruta del archivo con la canci�n
	 */
	private String ruta;

	/**
	 * La siguiente canci�n de la lista.
	 */
	private Cancion siguiente;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea una Canci�n. Los atributos son inicializados con las par�metros
	 * recibidos
	 * 
	 * @param pNombre Nombre de la canci�n. pNombre != null y pNombre != ""
	 * @param pDuracion Duraci�n en segundos de la canci�n. pDuracion > 0
	 * @param pLetra Letra de la canci�n. pLetra != null y pLetra != ""
	 * @param pDificultad Dificultad de la canci�n. pDificultad >= 1 y pDificultad <= 10
	 * @param pRuta Ruta del archivo con la canci�n. pRuta != null y pRuta != ""
	 */
	public Cancion(String pNombre, int pDuracion, String pLetra, int pDificultad, String pRuta) {
		nombre = pNombre;
		duracion = pDuracion;
		letra = pLetra;
		dificultad = pDificultad;
		ruta = pRuta;

		siguiente = null;

	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Retorna el nombre de la canci�n
	 * @return Nombre de la canci�n
	 */
	public String darNombre() {
		return nombre;
	}

	/**
	 * Retorna la duraci�n en segundos de la canci�n
	 * @return Duraci�n de la canci�n
	 */
	public int darDuracion() {
		return duracion;
	}

	/**
	 * Retorna la letra de la canci�n
	 * @return Letra de la canci�n
	 */
	public String darLetra() {
		return letra;
	}

	/**
	 * Retorna la dificultad de la canci�n
	 * @return Dificultad de la canci�n
	 */
	public int darDificultad() {
		return dificultad;
	}

	/**
	 * Retorna la ruta de archivo con la canci�n
	 * @return Ruta del archivo
	 */
	public String darRuta() {
		return ruta;
	}

	/**
	 * Retorna la siguiente canci�n de la lista.
	 * @return Siguiente canci�n.
	 */
	public Cancion darSiguiente() {

		return siguiente;
	}

	/**
	 * Cambia la siguiente canci�n de la lista.
	 * @param pCancion Canci�n a asignar como siguiente.
	 */
	public void cambiarSiguiente(Cancion pCancion) {

		siguiente = pCancion;
	}

	/**
	 * Devuelve la representaci�n en String de la canci�n
	 * @return La representaci�n en String de la canci�n: nombre.
	 */
	public String toString() {
		return nombre;
	}
}