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

import java.io.*;
//import java.text.*;
//import java.util.*;
import java.util.ArrayList;


import javax.swing.JOptionPane;

/**
 * Representa un Karaoke
 */
public class Karaoke
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Arreglo de constantes necesarios para el manejo de las categor�as del karaoke.
	 */
	public static final String[] CATEGORIAS = new String[] {"Rock","Pop","Reggae","Tropical","Electronica"};

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Primer artista de la lista
	 */
	public Artista primerArtista;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del karaoke. <br>
	 */
	public Karaoke( )
	{
		primerArtista = null;

	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Agrega un artista a la categor�a del karaoke.<br>
	 * El artista se agrega en la posici�n que le corresponda de tal manera que la lista quede ordenada por nombre de manera ascendente.<br>
	 * <b> post: </b> Se ha agregado un nuevo artista del karaoke
	 * @param pCategoria Nombre de la categor�a. pCategoria pertenece a Karaoke.CATEGORIAS
	 * @param pNombre Nombre del artista. pNombre != null y pNombre != ""
	 * @param pImagen Ruta del archivo con la imagen del artista. pImagen != null y pImagen != ""
	 * @throws Exception Si ya existe un artista con el mismo nombre.
	 */
	public void agregarArtista( String pCategoria, String pNombre, String pImagen ) throws Exception
	{
		if(buscarArtista(pNombre)!=null)
			throw new Exception("Ya existe un artista con el nombre "+pNombre);

		Artista nuevoArtista = new Artista(pCategoria, pNombre, pImagen);

		if(primerArtista==null) {

			primerArtista=nuevoArtista;
		}
		else if(primerArtista.darNombre().compareTo(nuevoArtista.darNombre())>0) {

			nuevoArtista.cambiarSiguiente(primerArtista);
			primerArtista.cambiarAnterior(nuevoArtista);
			primerArtista=nuevoArtista;
		}

		else
		{
			Artista osArtista = primerArtista;

			while(osArtista.darSiguiente()!=null && osArtista.darSiguiente().darNombre().compareTo(nuevoArtista.darNombre())<0) 
			{
				osArtista = osArtista.darSiguiente();
			}

			if(osArtista.darSiguiente()==null)
			{
				osArtista.cambiarSiguiente(nuevoArtista);
				nuevoArtista.cambiarAnterior(osArtista);	
			}

			else 
			{	   			
				nuevoArtista.cambiarAnterior(osArtista);
				nuevoArtista.cambiarSiguiente(osArtista.darSiguiente());
				osArtista.darSiguiente().cambiarAnterior(nuevoArtista);
				osArtista.cambiarSiguiente(nuevoArtista);    		
			}
		}

	}

	/**
	 * Busca un artista con el nombre.
	 * @param pNombre Nombre del artista. pNombre != null y pNombre != ""
	 * @return El artista con el nombre dado. Si no existe un artista con ese nombre se retorna null
	 */
	public Artista buscarArtista( String pNombre )
	{
		Artista osArtista = primerArtista;
		while(osArtista!=null && !osArtista.darNombre().equals(pNombre))
		{
			osArtista = osArtista.darSiguiente();
		}
		return osArtista;
	}

	/**
	 * Agrega una nueva canci�n a un artista del karaoke <br>
	 * <b> pre: </b> El artista existe.<br>
	 * <b> post: </b> Se ha agregado una nueva canci�n al un artista dado
	 * @param pArtista Nombre del artista int�rprete de la canci�n. pArtista != null y pArtista != ""
	 * @param pNombre Nombre de la canci�n. pNombre != null y pNombre != ""
	 * @param pDuracion Duraci�n en segundos de la canci�n. pDuracion > 0
	 * @param pLetra Letra de la canci�n. pLetra != null y pLetra != ""
	 * @param pDificultad Dificultad de la canci�n. pDificultad >= 1 y pDificultad <= 10
	 * @param pRuta Ruta del archivo con la canci�n. pRuta != null y pRuta != ""
	 * @throws Exception Si el artista ya tiene una canci�n con ese nombre.
	 */
	public void agregarCancion( String pArtista, String pNombre, int pDuracion, String pLetra, int pDificultad, String pRuta ) throws Exception
	{
		buscarArtista(pArtista).agregarCancion(pNombre, pDuracion, pLetra, pDificultad, pRuta);
	}

	/**
	 * Elimina al artista con nombre dado del karaoke.<br>
	 * <b>pre:</b>El artista existe.<br>
	 * <b>post</b>Se elimin� el artista del karaoke.
	 * @param pNombre Nombre del artista. pNombre !=null && pNombre!="".
	 */
	public void eliminarArtista( String pNombre )
	{
		if(primerArtista.darNombre().equals(pNombre)) {
			primerArtista = primerArtista.darSiguiente();
			primerArtista.cambiarAnterior(null);
		}

		else {

			Artista auxiliar = primerArtista;
			while(!auxiliar.darNombre().equals(pNombre)) {

				auxiliar = auxiliar.darSiguiente();
			}

			auxiliar.darAnterior().cambiarSiguiente(auxiliar.darSiguiente());

			if(auxiliar.darSiguiente()!=null)
			{
				auxiliar.darSiguiente().cambiarAnterior(auxiliar.darAnterior());
			}
			auxiliar = null;

		}
	}

	/**
	 * Elimina la canci�n con el nombre dado del artista con nombre dado.<br>
	 * <b>pre: </b>Tanto el artista como la canci�n existen.<br>
	 * <b>post:</b> Se elimin� la canci�n de la lista de canciones del artista.
	 * @param pNombreArtista Nombre del artista. pNombreArtista!=null && pNombreArtista!="".
	 * @param pNombreCancion Nombre de la canci�n a eliminar. pNombreCancion != null && pNombreCancion != "".
	 */
	public void eliminarCancion( String pNombreArtista, String pNombreCancion )
	{
		buscarArtista(pNombreArtista).eliminarCancion(pNombreCancion);
	}

	/**
	 * Retorna una lista con los artistas que pertenecen a una categor�a.
	 * @param pCategoria Categor�a de la cual se quieren los artistas. pCategoria pertenece a Karaoke.CATEGORIAS.
	 * @return Lista con los artistas de la categor�a dada.
	 */
	public ArrayList<Artista> darArtistasCategoria( String pCategoria )
	{
		Artista auxiliar = primerArtista;
		ArrayList<Artista>artistasCategoria = new ArrayList<Artista>();
		while(auxiliar!=null)
		{
			if(auxiliar.darCategoria().equals(pCategoria))
				artistasCategoria.add(auxiliar);

			auxiliar = auxiliar.darSiguiente();
		}
		return artistasCategoria;
	}

	/**
	 * Retorna la lista de canciones de un artista con el nombre recibido por par�metro.<br>
	 * <b> pre: </b> El artista existe.
	 * @param pNombre Nombre del artista. pNombre != null y pNombre != ""
	 * @return La lista de canciones del artista
	 */
	public ArrayList<Cancion> darCancionesArtista( String pNombre )
	{
		return buscarArtista(pNombre).darCanciones();
	}

	/**
	 * Busca la canci�n con mayor dificultad. Si existen varias canciones con la misma dificultad retorna la primera canci�n encontrada.
	 * @return La canci�n con mayor dificultad. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasDificil( )
	{
		Cancion osCancion = null;
		int dificultad = 0;
		for(Artista actual = primerArtista; actual!=null; actual=actual.darSiguiente()) {

			Cancion temporal = actual.darCancionMasDificil();
			if(temporal!=null && temporal.darDificultad()>dificultad)
			{
				osCancion = temporal;
				dificultad = temporal.darDificultad();
			}
		}

		return osCancion;

	}

	/**
	 * Busca la canci�n con menor dificultad. Si existen varias canciones con la misma dificultad retorna la primera canci�n encontrada.
	 * @return La canci�n con menor dificultad. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasFacil( )
	{
		Cancion osCancion = null;
		int dificultad = 11;
		for(Artista actual = primerArtista; actual!=null; actual=actual.darSiguiente()) {

			Cancion temporal = actual.darCancionMasFacil();
			if(temporal!=null && temporal.darDificultad()<dificultad)
			{
				osCancion = temporal;
				dificultad = temporal.darDificultad();
			}
		}

		return osCancion;
	}

	/**
	 * Busca la canci�n con mayor duraci�n. Si existen varias canciones con la misma duraci�n retorna la primera canci�n encontrada.
	 * @return La canci�n con mayor duraci�n. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasLarga( )
	{
		Cancion larga = null;
		int duracion = 0;
		for(Artista actual = primerArtista; actual!=null; actual=actual.darSiguiente()) {

			Cancion temporal = actual.darCancionMasLarga();
			if(temporal!=null && temporal.darDuracion()>duracion)
			{
				larga = temporal;
				duracion = temporal.darDuracion();
			}
		}
		return larga;
	}

	/**
	 * Busca la canci�n con menor duraci�n. Si existen varias canciones con la misma duraci�n retorna la primera canci�n encontrada.
	 * @return La canci�n con menor duraci�n. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasCorta( )
	{
		Cancion corta = null;
		int duracion = 999999;
		for(Artista actual = primerArtista; actual!=null; actual=actual.darSiguiente()) {

			Cancion temporal = actual.darCancionMasCorta();
			if(temporal!=null && temporal.darDuracion()<duracion)
			{
				corta = temporal;
				duracion = temporal.darDuracion();
			}
		}
		return corta;
	}

	/**
	 * Busca el artista con mayor n�mero de canciones. Si existen varios artistas con el mismo n�mero de canciones retorna el primer artista encontrado.
	 * @return El artista de alguna categor�a con mayor n�mero de canciones. Si no hay artistas se retorna null
	 */
	public Artista darArtistaMasCanciones( )
	{
		int cantCanciones = 0;
		Artista osArtista =null;
		Artista actual = primerArtista;
		while(actual!=null)
		{
			if(actual.darCanciones().size()>cantCanciones)
			{
				osArtista = actual;
				cantCanciones = osArtista.darCanciones().size();
			}
			actual = actual.darSiguiente();
		}
		return osArtista;
	}

	/**
	 * Retorna una lista con todas las canciones de la categor�a con el nombre dado.<br>
	 * <b> pre: </b> La categor�a existe.
	 * @param pNombre Nombre de la categor�a. pertenece a Karaoke.CATEGORIAS
	 * @return Lista con todas las canciones de una categor�a
	 */
	public ArrayList<Cancion> darCancionesCategoria( String pNombre )
	{
		ArrayList<Cancion>cancionesCategoria = new ArrayList<Cancion>();

		for(Artista actual = primerArtista; actual!=null; actual= actual.darSiguiente()) {

			if(actual.darCategoria().equals(pNombre))
			{
				cancionesCategoria.addAll(actual.darCanciones());
			}
		}

		return cancionesCategoria;
	}

	//------------------------------------------------------------------
	// Trabajo de los estudiantes
	//------------------------------------------------------------------

	// -----------------------------------------------------------------
	// Trabajo del estudiante - N9L3 - 18/04/23
	// Antony Salcedo
	// -----------------------------------------------------------------

	/**
	 * Este método devuelve la cantidad total de canciones registradas en el karaoke
	 * que pertenecen a las categorías "Rock" y "Pop".
	 * @return la cantidad de canciones en las categorías "Rock" y "Pop"
	 * @throws NoHayArtistasException si no hay artistas registrados en la categoría ingresada.
	 * @throws NoHayCancionesException si no hay canciones registradas para algún artista de la categoría ingresada.
	 * @throws Exception si ocurre algún otro error durante la ejecución del método.
	 */
	public int darCantidadCancionesRockYPop() throws NoHayArtistasException, NoHayCancionesException, Exception {

		// Inicializa la cantidad de canciones a cero.
		int cantidadCanciones = 0;

		// Obtiene el primer artista registrado en el karaoke.
		Artista actual = primerArtista;

		// Si no hay artistas registrados, lanza una excepción.
		if(actual==null)
		{
			throw new NoHayArtistasException("No Existen artistas registrados en el Karaoke.");
		}

		// Recorre la lista de artistas desde el principio hasta el final.
		for(Artista actualArtista=primerArtista; actualArtista!=null; actualArtista=actualArtista.darSiguiente())
		{
			// Si el artista pertenece a las categorías "Rock" o "Pop", cuenta sus canciones.
			if(actualArtista.darCategoria().equalsIgnoreCase(CATEGORIAS[0]) || actualArtista.darCategoria().equalsIgnoreCase(CATEGORIAS[1]))
			{
				// Obtiene la primera canción del artista
				Cancion actualCancion = actualArtista.darPrimeraCancion();
				// Si no hay canciones en el artista, lanza una excepción.
				if(actualCancion==null)
				{
					throw new NoHayCancionesException("No Existen canciones en el artista: " + actualArtista.darNombre());
				}
				else
				{
					// Cuenta todas las canciones del artista.
					while(actualCancion!=null)
					{
						cantidadCanciones++;
						actualCancion = actualCancion.darSiguiente();
					}
				}
			}
		}
		// Retorna la cantidad total de canciones en las categorías "Rock" y "Pop".
		return cantidadCanciones;
	}

	// -----------------------------------------------------------------
	// Trabajo del estudiante - N9L3 - 25/04/23
	// Antony Salcedo
	// -----------------------------------------------------------------

	/**
	 * Genera un informe en un archivo de texto con los artistas registrados en una categoría dada y sus respectivas canciones.
	 * @param nCategoriaUsuario el nombre de la categoría de la que se desea generar el informe.
	 * @throws FileNotFoundException si el archivo de salida no puede ser creado.
	 * @throws IOException si ocurre un error al escribir en el archivo de salida.
	 * @throws NoHayCategoriaException si la categoría ingresada no existe.
	 * @throws NoHayArtistasException si no hay artistas registrados en la categoría ingresada.
	 * @throws NoHayCancionesException si no hay canciones registradas para algún artista de la categoría ingresada.
	 * @throws IllegalArgumentException si el nombre de la categoría ingresado es nulo o vacío.
	 * @throws Exception si ocurre algún otro error durante la ejecución del método.
	 */
	public void generarInformeArtistasPorCategoria(String nCategoriaUsuario) throws FileNotFoundException, IOException, NoHayCategoriaException, NoHayArtistasException, NoHayCancionesException,  IllegalArgumentException, Exception {
		// Verifica si el nombre de la categoría ingresado es válido.
		if (nCategoriaUsuario == null || nCategoriaUsuario.trim().isEmpty()) 
		{
			throw new IllegalArgumentException("Debe ingresar un nombre de categoria valido.");
		}
		// Verifica si la categoría ingresada existe.
		boolean existeCategoria = false;
		for (String categoria : CATEGORIAS) {
			if (categoria.equalsIgnoreCase(nCategoriaUsuario)) {
				existeCategoria = true;
				break;
			}
		}
		if (!existeCategoria) 
		{
			throw new NoHayCategoriaException("No existe la categoría: " + nCategoriaUsuario);
		}
		// Verifica si hay artistas registrados en la categoría ingresada.
		boolean existenArtistas = false;
		for (Artista actualArtista = primerArtista; actualArtista != null; actualArtista = actualArtista.darSiguiente()) 
		{
			if (actualArtista.darCategoria().equalsIgnoreCase(nCategoriaUsuario)) {
				existenArtistas = true;
				break;
			}
		}
		if (!existenArtistas) 
		{
			throw new NoHayArtistasException("No existen artistas registrados en la categoría: " + nCategoriaUsuario);
		}
		// Crea el archivo de salida para el informe.
		File archivo = new File("./data/informeDeArtistasYSusCancionesPorCategoría.txt");
		PrintWriter pluma = new PrintWriter(archivo);
		// Escribe la cabecera del informe en el archivo de salida.
		pluma.println("|-----------------------------------------------------------------------------");
		pluma.println("|Informe de artistas y sus canciones dada la categoría: " + nCategoriaUsuario + ".");
		pluma.println("|-----------------------------------------------------------------------------");
		// Escribe la información del artista de la categoría en el archivo de salida.
		for (Artista miArtista : darArtistasCategoria(nCategoriaUsuario)) 
		{
			pluma.println("Nombre del artista: " + miArtista.darNombre() + ".");
			pluma.println("Categoria del artista: " + miArtista.darCategoria() + ".");
			pluma.println("|--------------------------------------------------");
			// Escribe la información de cada cancion del artista de la categoría en el archivo de salida.
			Cancion actualCancion = miArtista.darPrimeraCancion();
			if (actualCancion == null) {
				throw new NoHayCancionesException("No existen canciones en el artista: " + miArtista.darNombre());
			} else {
				while (actualCancion != null) {
					pluma.println("Nombre de la cancion: " + actualCancion.darNombre() + ".");
					pluma.println("Dificultad de la cancion: " + actualCancion.darDificultad() + ".");
					pluma.println("Duracion de la cancion: " + actualCancion.darDuracion() + ".");
					pluma.println("|--------------------------------------------------");
					// Se pasa a la siguiente cancion dado el caso exista mas de una canncion.
					actualCancion = actualCancion.darSiguiente();
				}
			}
			pluma.println("");
			// Se cierra la pluma.
			pluma.close();
		}
	}
	
	// -----------------------------------------------------------------
	// Trabajo del estudiante - N9L3 - 25/04/23
	// Antony Salcedo
	// -----------------------------------------------------------------

	/**
	 * Este método verifica si hay o no canciones cortas y difíciles en los artistas de dos categorías dadas por el usuario. 
	 * Devuelve true si existen dichas canciones y false en caso contrario.
	 * @param nCategoriaUsuario_1 nombre de la primera categoría a verificar.
	 * @param nCategoriaUsuario_2 nombre de la segunda categoría a verificar.
	 * @return si hay canciones cortas y difíciles en los artistas de ambas categorías dadas returna verdadero, en caso contrario retorna falso.
	 * @throws NoHayCategoriaException si la categoría ingresada no existe.
	 * @throws NoHayArtistasException si no hay artistas registrados en la categoría ingresada.
	 * @throws NoHayCancionesException si no hay canciones registradas para algún artista de la categoría ingresada.
	 * @throws IllegalArgumentException si el nombre de la categoría ingresado es nulo o vacío.
	 * @throws Exception si ocurre algún otro error durante la ejecución del método.
	 */
	public boolean existenONoCancionesCortasYDificilesDeArtistasDe2Categorias(String nCategoriaUsuario_1, String nCategoriaUsuario_2) throws NoHayCategoriaException, NoHayArtistasException, NoHayCancionesException,  IllegalArgumentException, Exception
	{
		// Verifica si el nombre de la categoría 1 ingresado es válido.
		if (nCategoriaUsuario_1 == null || nCategoriaUsuario_1.trim().isEmpty()) {
			throw new IllegalArgumentException("Debe ingresar un nombre de categoria 1 valido.");
		}
		// Verifica si el nombre de la categoría 2 ingresado es válido.
		if (nCategoriaUsuario_2 == null || nCategoriaUsuario_2.trim().isEmpty()) {
			throw new IllegalArgumentException("Debe ingresar un nombre de categoria 2 valido.");
		}
		// Crear variables para verificar las excepciones
		boolean existenCacionesCortasYDificiles = false;
		boolean existeCategoria_1 = false;
		boolean existeCategoria_2 = false;
		boolean existenArtistas_1 = false;
		boolean existenArtistas_2 = false;
		boolean existenCancionesCategoria_1 = false;
		boolean existenCancionesCategoria_2 = false;

		// Verifica si las categorias 1 y 2 ingresadas por el usuario existen.
		for (String categoria : CATEGORIAS) {
			if (categoria.equalsIgnoreCase(nCategoriaUsuario_1)) {
				existeCategoria_1 = true;
			}
			if (categoria.equalsIgnoreCase(nCategoriaUsuario_2)) {
				existeCategoria_2 = true;
			}
		}
		// Laza excepcion segun existan o no las categorias 1 y 2 ingresadas por el usuario.
		if (!existeCategoria_1 && !existeCategoria_2) {
			throw new NoHayCategoriaException("No existen las categorías: " + nCategoriaUsuario_1 + "," + nCategoriaUsuario_2);
		}
		if (!existeCategoria_1) {
			throw new NoHayCategoriaException("No existe la categoría: " + nCategoriaUsuario_1);
		}

		if (!existeCategoria_2) {
			throw new NoHayCategoriaException("No existe la categoría: " + nCategoriaUsuario_2);
		}
		// Verifica si existen artistas en las categorias 1 y 2 ingresadas por el usuario.
		for (Artista actualArtista = primerArtista; actualArtista != null; actualArtista = actualArtista.darSiguiente()) {
			if (actualArtista.darCategoria().equalsIgnoreCase(nCategoriaUsuario_1)) {
				existenArtistas_1 = true;
			}
			if (actualArtista.darCategoria().equalsIgnoreCase(nCategoriaUsuario_2)) {
				existenArtistas_2 = true;
			}

			if (existenArtistas_1 && existenArtistas_2) {
				break;
			}
		}
		// Laza excepcion segun exista o no artistas en las categorias 1 y 2 ingresadas por el usuario.
		if (!existenArtistas_1 && !existenArtistas_2) {
			throw new NoHayArtistasException("No existen artistas registrados en las categorías: " + nCategoriaUsuario_1 +", " + nCategoriaUsuario_2);
		}
		if (!existenArtistas_1) {
			throw new NoHayArtistasException("No existen artistas registrados en la categoría: " + nCategoriaUsuario_1);
		}

		if (!existenArtistas_2) {
			throw new NoHayArtistasException("No existen artistas registrados en la categoría: " + nCategoriaUsuario_2);
		}
		// Verifica si existen canciones en los artistas de la categoria 1 ingresada por el usuario.
		for(Artista miArtista_1 = primerArtista; miArtista_1!=null; miArtista_1 = miArtista_1.darSiguiente())
		{
			if(miArtista_1.darCategoria().equalsIgnoreCase(nCategoriaUsuario_1))
			{
				Cancion actualCancion = miArtista_1.darPrimeraCancion();
				if (actualCancion == null) 
				{
					existenCancionesCategoria_1 = true;
				} else {
					existenCancionesCategoria_1 = false;
				}
			}
		}
		// Verifica si existen canciones en los artistas de la categoria 2 ingresada por el usuario.
		for(Artista miArtista_2 = primerArtista; miArtista_2!=null; miArtista_2 = miArtista_2.darSiguiente())
		{
			if(miArtista_2.darCategoria().equalsIgnoreCase(nCategoriaUsuario_2))
			{
				Cancion actualCancion = miArtista_2.darPrimeraCancion();
				if (actualCancion == null) 
				{
					existenCancionesCategoria_2 = true;
				} else {
					existenCancionesCategoria_2 = false;
				}
			}
		}
		// Laza excepcion segun existan o no canciones en los artistas de las categorias 1 y 2 ingresadas por el usuario.
		if(existenCancionesCategoria_1==true && existenCancionesCategoria_2==true)
		{
			throw new NoHayCancionesException("No existen canciones en los artistas de las categorias dadas.");
		}

		if(existenCancionesCategoria_1==true && !existenCancionesCategoria_2)
		{
			throw new NoHayCancionesException("No existen canciones en los artistas de la primera categoria dada.");
		}

		if(!existenCancionesCategoria_1 && existenCancionesCategoria_2==true)
		{
			throw new NoHayCancionesException("No existen canciones en los artistas de la segunda categoria dada.");
		}

		// verifica si hay o no canciones cortas y difíciles en los artistas de dos categorías dadas por el usuario. 
		for(Artista actualArtista = primerArtista; actualArtista!=null; actualArtista = actualArtista.darSiguiente())
		{
			if(actualArtista.darCategoria().equalsIgnoreCase(nCategoriaUsuario_1) || actualArtista.darCategoria().equalsIgnoreCase(nCategoriaUsuario_2))
			{
				Cancion actualCancion = actualArtista.darPrimeraCancion();
				while (actualCancion != null) {
					if(actualCancion.darDuracion()<180 && actualCancion.darDificultad()>=10)
					{
						existenCacionesCortasYDificiles = true;
					}
					actualCancion = actualCancion.darSiguiente();
				}
			}
		}
		// Retorna la variable de verificacion del metodo.
		return existenCacionesCortasYDificiles;
	}


	// -----------------------------------------------------------------
	// Puntos de Extensi�n
	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// Trabajo del estudiante - N9L3 - 18/04/23
	// Antony Salcedo
	// -----------------------------------------------------------------

	/**
	 * Metodo que solicite al usuario si desea conocer la cantidad total de canciones registradas en el karaoke
	 * que pertenecen a las categorías "Rock" y "Pop".
	 * En caso de que el usuario confirme la opcion de obtener la cantidad total de canciones registradas en el karaoke
	 * que pertenecen a las categorías "Rock" y "Pop", se invoca al metodo darCantidadCancionesRockYPop() para obtener la cantidad total.
	 * Si el usuario elige no saber la cantidad total la cantidad total de canciones registradas en el karaoke
	 * que pertenecen a las categorías "Rock" y "Pop", se devuelve un mensaje de despedida.
	 * En caso de que ocurra alguna excepción, se captura y se devuelve un mensaje de error correspondiente.
	 * @return la respuesta correspondiente según la opción seleccionada por el usuario.
	 */

	/*
	public String metodo1( )
	{
		try {
			// Solicitar al usuario si desea conocer la cantidad total de canciones registradas.
			int opcion = JOptionPane.showConfirmDialog(null, "¿Desea conocer la cantidad canciones del genero Rock 0 Pop?", "Cantidad de canciones", JOptionPane.YES_NO_OPTION);
			// Verificar la opción seleccionada por el usuario.
			if (opcion == JOptionPane.YES_OPTION) {
				// Si el usuario desea conocer la cantidad de pacientes, se invoca el método darCantidadCancionesRockYPop() para obtener la cantidad total.
				return "La cantidad de canciones es: "  + darCantidadCancionesRockYPop();
			} else {
				// Si el usuario no desea conocer la cantidad de pacientes, se devuelve un mensaje de despedida.
				return "Vuelva pronto.";
			}
		} catch (NoHayArtistasException e) {
			// Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
			return "Error: " + "\n" + "(" + e.getMessage() + ")";
		} catch (NoHayCancionesException e) {
			// Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
			return "Error: " + "\n" + "(" + e.getMessage() + ")";
		} catch (Exception e) {
			// Capturar cualquier otra excepción que ocurra durante la ejecución del método.
			return "Ocurrió un error durante la ejecución del método" + "\n" + "Error: " + "(" + e.getMessage() + ")";
		}
	}
	 */


	// -----------------------------------------------------------------
	// Trabajo del estudiante - N9L3 - 25/04/23
	// Antony Salcedo
	// -----------------------------------------------------------------

	/**
	 * Método que solicita al usuario una categoría para generar un informe de artistas y canciones.
	 * Si la categoría es válida, se llama al método generarInformeArtistasPorCategoria con el dato ingresado y se devuelve un mensaje de éxito.
	 * Si el usuario no ingresa ningún dato, se devuelve un mensaje de despedida.
	 * En caso de que ocurra alguna excepción, se captura y se devuelve un mensaje de error correspondiente.
	 * @return la respuesta correspondiente según la opción seleccionada por el usuario.
	 */
	public String metodo1()
	{
		try {
			// Solicitar al usuario que ingrese la categoría para generar el informe.
			String datoUsuario = JOptionPane.showInputDialog(null, "ingrese la categoria de la cual desea general el reporte.");
			if(datoUsuario != null)
			{
				// Si el usuario ingresó una categoría válida, llamar al método generarInformeArtistasPorCategoria con el dato ingresado.
				generarInformeArtistasPorCategoria(datoUsuario);
				// Devolver mensaje de éxito.
				return "El reporte ha sido generado.";
			} else {
				// Si el usuario no ingresó ningún dato, devolver mensaje de despedida.
				return "Vuelva pronto.";
			}
		} catch (FileNotFoundException e) {
			// Capturar la excepción FileNotFoundException en caso de que no se pueda encontrar el archivo.
			return "Error:" + "\n" + "(" + e.getMessage() + ")";
		} catch (IOException e) {
			// Capturar la excepción IOException en caso de que ocurra un error de lectura o escritura.
			return "Error:" + "\n" + "(" + e.getMessage() + ")";
		} catch (NoHayCategoriaException e) {
			// Capturar la excepción NoHayCategoriaException en caso de que no haya categorías disponibles.
			return "Error: " + "\n" + "(" + e.getMessage() + ")";
		} catch (NoHayArtistasException e) {
			// Capturar la excepción NoHayArtistasException en caso de que no haya artistas disponibles en la categoría ingresada.
			return "Error: " + "\n" + "(" + e.getMessage() + ")";
		} catch (NoHayCancionesException e) {
			// Capturar la excepción NoHayCancionesException en caso de que no haya canciones disponibles en la categoría ingresada.
			return "Error: " + "\n" + "(" + e.getMessage() + ")";
		} catch (IllegalArgumentException e) {
			// Capturar la excepción IllegalArgumentException en caso de que se ingrese un nombre de categoria inválido.
			return "Error:" + "\n" + "(" + e.getMessage() + ")";
		} catch (Exception e) {
			// Capturar cualquier otra excepción que ocurra durante la ejecución del método.
			return "Ocurrió un error durante la ejecución del método." + "\n" + "Error: " + "(" + e.getMessage() + ")";
		}
	}

	// -----------------------------------------------------------------
	// Trabajo del estudiante - N9L3 - 25/04/23
	// Antony Salcedo
	// -----------------------------------------------------------------

	/**
	 * Este método muestra una ventana emergente de entrada de texto para que el usuario ingrese
	 * dos categorías. Luego, llama a otro método llamado existenONoCancionesCortasYDificilesDeArtistasDe2Categorias
	 * para verificar si existen o no canciones cortas y difíciles de artistas en esas categorías.
	 * Finalmente, devuelve un mensaje indicando si se encontraron o no canciones cortas y difíciles
	 * en alguno de los artistas de las categorías ingresadas por el usuario.
	 * En caso de que ocurra alguna excepción, se captura y se devuelve un mensaje de error correspondiente.
	 * @return la respuesta correspondiente según la opción seleccionada por el usuario.
	 */
	public String metodo2( )
	{
		try {
			// Se solicita al usuario que ingrese el nombre de la primera categoria.
			String datoUsuario_1 = JOptionPane.showInputDialog(null, "ingrese la categoria 1 para la verificacion");
			// Dependiendo de la eleccion del usuario en "Aceptar" o "Cancelar", se veerifica si la categoria 1 ingresada no sea nula o vacia.
			if(datoUsuario_1 != null && !datoUsuario_1.trim().isEmpty())
			{
				// Se solicita al usuario que ingrese el nombre de la primera categoria.
				String datoUsuario_2 = JOptionPane.showInputDialog(null, "ingrese la categoria 2 para la verificacion");
				// Dependiendo de la eleccion del usuario en "Aceptar" o "Cancelar", se veerifica si la categoria 2 ingresada no sea nula o vacia.
				if(datoUsuario_2 != null && !datoUsuario_2.trim().isEmpty())
				{
					// Se llama al método existenONoCancionesCortasYDificilesDeArtistasDe2Categorias(String nCategoriaUsuario_1, String nCategoriaUsuario_2) para verificar 
					// si hay o no canciones cortas y difíciles en los artistas de dos categorías dadas por el usuario.
					boolean existeONo = existenONoCancionesCortasYDificilesDeArtistasDe2Categorias(datoUsuario_1, datoUsuario_2);
					// Si existe al menos una canción corta y difícil en alguno de los artistas de las categorías dadas, se retorna el siguiente mensaje.
					if(existeONo==true)
					{
						return "Existen canciones cortas y dificiles en alguno de los artistas de las categorias dadas.";
					} 
					// Si no existen canciones cortas y difíciles en alguno de los artistas de las categorías dadas, se retorna el siguiente mensaje:
					else {
						return "No existen canciones cortas y dificiles en alguno de los artistas de las categorias dadas.";
					}
				} 
				// Si el usuario no ingresó la categoría 2, se asigna "null" a datoUsuario_22 y se llama al metodo 
				// existenONoCancionesCortasYDificilesDeArtistasDe2Categorias(String nCategoriaUsuario_1, String nCategoriaUsuario_2)
				// con los parámetros datoUsuario_1 y datoUsuario_22, y se retorna el siguiente mensaje:
				else {
					String datoUsuario_22 = null;
					existenONoCancionesCortasYDificilesDeArtistasDe2Categorias(datoUsuario_1, datoUsuario_22);
					return "Vuelva pronto.";
				}
			} 
			// Si el usuario no ingresó la categoría 1, se asigna "null" a datoUsuario_11 y datoUsuario_22 y se llama al metodo
			// existenONoCancionesCortasYDificilesDeArtistasDe2Categorias(String nCategoriaUsuario_1, String nCategoriaUsuario_2) 
			// con los parámetros datoUsuario_11 y datoUsuario_22, y se retorna el siguiente mensaje:
			else {
				String datoUsuario_11 = null;
				String datoUsuario_22 = null;
				existenONoCancionesCortasYDificilesDeArtistasDe2Categorias(datoUsuario_11, datoUsuario_22);
				return "Vuelva Pronto";
			}
		} catch (NoHayCategoriaException e) {
			// Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
			return "Error: " + "\n" + "(" + e.getMessage() + ")";
		} catch (NoHayArtistasException e) {
			// Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
			return "Error: " + "\n" + "(" + e.getMessage() + ")";
		} catch (NoHayCancionesException e) {
			// Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
			return "Error: " + "\n" + "(" + e.getMessage() + ")";
		} catch (IllegalArgumentException e) { 
			// Capturar la excepción IllegalArgumentException en caso de que el usuario no ingresó un nombre de categoria válido.
			return "Error:" + "\n" + "(" + e.getMessage() + ")";
		} catch (Exception e) {
			// Capturar cualquier otra excepción que ocurra durante la ejecución del método.
			return "Ocurrió un error durante la ejecución del método." + "\n" + "Error: " + "(" + e.getMessage() + ")";
		}
	}

}