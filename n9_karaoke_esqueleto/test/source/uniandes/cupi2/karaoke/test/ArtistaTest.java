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

package uniandes.cupi2.karaoke.test;

import java.util.ArrayList;
import java.util.Random;

import junit.framework.TestCase;
import uniandes.cupi2.karaoke.mundo.Artista;
import uniandes.cupi2.karaoke.mundo.Cancion;

/**
 * Esta es la clase usada para verificar la correcta implementaci�n de la clase Artista
 */
public class ArtistaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Artista artista;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Artista sin canciones
     */
    private void setupEscenario1( )
    {
        artista = new Artista( "CatPrueba", "nombre", "ruta" );
    }

    /**
     * Construye un nuevo Artista con 5 canciones
     */
    private void setupEscenario2( )
    {
        artista = new Artista( "CatPrueba", "nombre", "ruta" );
        try
        {
            artista.agregarCancion( "cancion4", 87, "letra4", 4, "ruta4" );
            artista.agregarCancion( "cancion1", 100, "letra1", 2, "ruta1" );
            artista.agregarCancion( "cancion3", 80, "letra3", 7, "ruta3" );
            artista.agregarCancion( "cancion2", 150, "letra2", 3, "ruta2" );
            artista.agregarCancion( "cancion5", 80, "letra5", 5, "ruta5" );
        }
        catch( Exception e )
        {
            fail( "Revisa el m�todo agregarCancion" );
        }
    }

    /**
     * Prueba 1 - Verifica que el nombre del artista sea correcto
     */
    public void testDarNombre( )
    {
        setupEscenario1( );

        assertNotNull( "El nombre del artista no ha sido inicializado", artista.darNombre( ) );
        assertEquals( "El nombre del artista no fue inicializado correctamente", "nombre", artista.darNombre( ) );
    }

    /**
     * Prueba 2 - Verifica que la categor�a del artista sea correcta
     */
    public void testDarCategoria( )
    {
        setupEscenario1( );

        assertNotNull( "La cateogor�a del artista no ha sido inicializada", artista.darCategoria( ) );
        assertEquals( "La categor�a del artista no fue inicializada correctamente", "CatPrueba", artista.darCategoria( ) );
    }

    /**
     * Prueba 3 - Verifica que la ruta de la imagen del artista sea correcta
     */
    public void testDarImagen( )
    {
        setupEscenario1( );

        assertNotNull( "La imagen del artista no ha sido inicializada", artista.darImagen( ) );
        assertEquals( "La imagen del artista no fue inicializada correctamente", "ruta", artista.darImagen( ) );
    }

    /**
     * <b>Prueba 4:</b> Verifica el m�todo cambiarAnterior.<br>
     * <b>M�todos a probar:</b><br>
     * darAnterior<br>
     * cambiarAnterior<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay un anterior al crear el artista. <br>
     * 2. El anterior es correcto despu�s de haber sido asignado.
     */
    public void testCambiarAnterior( )
    {
        setupEscenario1( );
        assertTrue( "No deber�a haber un anterior", artista.darAnterior( ) == null );
        artista.cambiarAnterior( new Artista( "C", "A001", "Imagen" ) );
        assertTrue( "No se est� cambiando el anterior artista.", artista.darAnterior( ) != null );
        assertTrue( "El artista anterior no es correcto.", artista.darAnterior( ).darNombre( ).equals( "A001" ) );
    }

    /**
     * <b>Prueba 5:</b> Verifica el m�todo cambiarSiguiente.<br>
     * <b>M�todos a probar:</b><br>
     * darSiguiente<br>
     * cambiarSiguiente<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay un siguiente al crear el artista. <br>
     * 2. El siguiente es correcto despu�s de haber sido asignado.
     */
    public void testCambiarSiguiente( )
    {
        setupEscenario1( );
        assertTrue( "No deber�a haber un siguiente", artista.darSiguiente( ) == null );
        artista.cambiarSiguiente( new Artista( "C", "A001", "Imagen" ) );
        assertTrue( "No se est� cambiando el siguiente artista.", artista.darSiguiente( ) != null );
        assertTrue( "El artista siguiente no es correcto.", artista.darSiguiente( ).darNombre( ).equals( "A001" ) );
    }

    /**
     * Prueba 6 - Prueba el m�todo agregarCancion <br>
     * <b> M�todos a probar: </b><br>
     * agregarCancion<br>
     * darCanciones<br>
     * <b> Resultado esperado: </b><br>
     * La canci�n es agregada correctamente a la lista de canciones del artista.
     */
    public void testAgregarCancion( )
    {
        setupEscenario1( );

        try
        {
            for( int i = 1; i < 1000; i++ )
            {
                int aleatorio = new Random( ).nextInt( Integer.MAX_VALUE );
                artista.agregarCancion( "cancion" + aleatorio, 100, "letra", 1, "ruta" );
                assertEquals( "La canci�n no se agreg� correctamente o el metodo darCanciones tiene errores.", i, artista.darCanciones( ).size( ) );
                Cancion c = artista.buscarCancion( "cancion" + aleatorio );
                assertNotNull( "La canci�n no se encontr�. No se agreg� bien o el m�todo buscarCancion tiene errores.", c );
            }

            ArrayList<Cancion> canciones = artista.darCanciones( );
            for( int i = 0; i < canciones.size( ) - 1; i++ )
            {
                Cancion c1 = canciones.get( i );
                Cancion c2 = canciones.get( i + 1 );
                assertTrue( "Las canciones no se agregaron en orden ascendente.", c1.darNombre( ).compareTo( c2.darNombre( ) ) <= 0 );
              

            }

        }
        catch( Exception e )
        {
        	e.printStackTrace();
            fail( "No deber�a lanzar excepci�n." );
        }

    }

    /**
     * Prueba 7 - Prueba el m�todo agregarCancion <br>
     * <b> M�todos a probar: </b> <br>
     * agregarCancion <br>
     * <b> Resultado esperado: </b><br>
     * La canci�n no es agregada ya que existe una canci�n del artista con el mismo nombre
     */
    public void testAgregarCancionError( )
    {
        setupEscenario2( );

        try
        {
            artista.agregarCancion( "cancion1", 100, "letra", 5, "ruta" );
            fail( "No se deber�a agregar la canci�n a la lista de canciones del artista" );
        }
        catch( Exception e )
        {
            // Debe generar excepci�n
        }
        assertEquals( "La canci�n fue agregada a la lista de canciones", 5, artista.darCanciones( ).size( ) );
    }

    /**
     * Prueba 8 - Prueba el m�todo eliminarCancion <br>
     * <b> M�todos a probar: </b> <br>
     * eliminarCancion <br>
     * <b> Resultado esperado: </b> <br>
     * Se elimin� la canci�n de la lista de canciones.
     */
    public void testEliminarCancion( )
    {

        setupEscenario2( );

        artista.eliminarCancion( "cancion1" );
        assertNull( "No se elimino correctamente la cancion.", artista.buscarCancion( "cancion1" ) );

        artista.eliminarCancion( "cancion5" );
        assertNull( "No se elimino correctamente la cancion.", artista.buscarCancion( "cancion5" ) );

        artista.eliminarCancion( "cancion3" );
        assertNull( "No se elimino correctamente la cancion.", artista.buscarCancion( "cancion3" ) );

        ArrayList<Cancion> canciones = artista.darCanciones( );
        assertEquals( "No se eliminaron correctamente las canciones.", 2, canciones.size( ) );

        assertTrue( "No se eliminaron correctamente las canciones.", canciones.get( 0 ).darNombre( ).equals( "cancion2" ) );
        assertTrue( "No se eliminaron correctamente las canciones.", canciones.get( 1 ).darNombre( ).equals( "cancion4" ) );

        assertTrue( "Las referencias no se asignaron correctamente.", canciones.get( 0 ).darSiguiente( ).darNombre( ).equals( "cancion4" ) );
        assertNull( "Las referencias no se asignaron correctamente.", canciones.get( 1 ).darSiguiente( ) );
    }

    /**
     * <b>Prueba 9:</b> Verifica el m�todo darCanciones.<br>
     * <b>M�todos a probar:</b><br>
     * darCanciones<br>
     * <b>Resultado esperado:</b><br>
     * 1. Se retornan correctamente las canciones ordenadas por nombre ascendentemente. <br>
     */
    public void testDarCanciones( )
    {
        setupEscenario1( );
        try
        {
            assertEquals( "La lista debe estar vacia.", 0, artista.darCanciones( ).size( ) );

            artista.agregarCancion( "cancion3", 100, "letra", 5, "ruta" );
            assertEquals( "No se retornaron todas las canciones correctamente. Verifique el metodo agregarCancion.", 1, artista.darCanciones( ).size( ) );

            artista.agregarCancion( "cancion5", 100, "letra", 5, "ruta" );
            assertEquals( "No se retornaron todas las canciones correctamente. Verifique el metodo agregarCancion.", 2, artista.darCanciones( ).size( ) );

            artista.agregarCancion( "cancion1", 100, "letra", 5, "ruta" );
            assertEquals( "No se retornaron todas las canciones correctamente. Verifique el metodo agregarCancion.", 3, artista.darCanciones( ).size( ) );

            artista.agregarCancion( "cancion4", 100, "letra", 5, "ruta" );
            assertEquals( "No se retornaron todas las canciones correctamente. Verifique el metodo agregarCancion.", 4, artista.darCanciones( ).size( ) );

            artista.agregarCancion( "cancion2", 100, "letra", 5, "ruta" );
            assertEquals( "No se retornaron todas las canciones correctamente. Verifique el metodo agregarCancion.", 5, artista.darCanciones( ).size( ) );

            artista.agregarCancion( "cancion6", 100, "letra", 5, "ruta" );
            assertEquals( "No se retornaron todas las canciones correctamente. Verifique el metodo agregarCancion.", 6, artista.darCanciones( ).size( ) );

            ArrayList<Cancion> canciones = artista.darCanciones( );
            for( int i = 0; i < 6; i++ )
            {
                assertTrue( "La lista no se retorno ordenada.", ( "cancion" + ( i + 1 ) ).equals( canciones.get( i ).darNombre( ) ) );

            }
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n. Revise el m�todo agregarCancion." );
        }
    }

    /**
     * Prueba 10 - Prueba el m�todo buscarCancion<br>
     * <b> M�todos a probar: </b><br>
     * buscarCancion<br>
     * <b> Resultado esperado: </b><br>
     * La canci�n es encontrada en la lista de canciones del artista
     */
    public void testBuscarCancion( )
    {
        setupEscenario2( );

        Cancion cancion = artista.buscarCancion( "cancion1" );

        assertNotNull( "La canci�n no deber�a ser nula", cancion );
        assertEquals( "La canci�n encontrada no es correcta", "cancion1", cancion.darNombre( ) );
    }

    /**
     * Prueba 11 - Prueba el m�todo buscarCancion <br>
     * <b> M�todos a probar: </b><br>
     * buscarCancion <br>
     * <b> Resultado esperado: </b><br>
     * La canci�n no es encontrada en la lista de canciones del artista
     */
    public void testBuscarCancion2( )
    {
        setupEscenario2( );

        Cancion cancion = artista.buscarCancion( "cancion6" );
        assertNull( "La canci�n deber�a ser nula", cancion );
    }

    /**
     * Prueba 12 - Prueba el m�todo darCancionMasDificil <b><br>
     * M�todos a probar: </b><br>
     * darCancionMasDificil
     */
    public void testDarCancionMasDificil( )
    {
        setupEscenario2( );

        Cancion dificil = artista.darCancionMasDificil( );
        assertNotNull( "La canci�n no deber�a ser nula", dificil );
        assertEquals( "La canci�n retornada no es la m�s dif�cil", "cancion3", dificil.darNombre( ) );
        assertEquals( "La canci�n retornada no es la m�s dif�cil", 7, dificil.darDificultad( ) );
    }

    /**
     * Prueba 13 - Prueba el m�todo darCancionMasFacil <b><br>
     * M�todos a probar: </b><br>
     * darCancionMasFacil
     */
    public void testDarCancionMasFacil( )
    {
        setupEscenario2( );

        Cancion facil = artista.darCancionMasFacil( );
        assertNotNull( "La canci�n no deber�a ser nula", facil );
        assertEquals( "La canci�n retornada no es la m�s f�cil", "cancion1", facil.darNombre( ) );
        assertEquals( "La canci�n retornada no es la m�s f�cil", 2, facil.darDificultad( ) );

    }

    /**
     * Prueba 14 - Prueba el m�todo darCancionMasLarga <b><br>
     * M�todos a probar: </b><br>
     * darCancionMasLarga
     */
    public void testDarCancionMasLarga( )
    {
        setupEscenario2( );

        Cancion larga = artista.darCancionMasLarga( );
        assertNotNull( "La canci�n no deber�a ser nula", larga );
        assertEquals( "La canci�n retornada no es la m�s larga", "cancion2", larga.darNombre( ) );
        assertEquals( "La canci�n retornada no es la m�s larga", 150, larga.darDuracion( ) );
    }

    /**
     * Prueba 15 - Prueba el m�todo darCancionMasCorta<br>
     * <b> M�todos a probar: </b><br>
     * darCancionMasCorta
     */
    public void testDarCancionMasCorta( )
    {
        setupEscenario2( );

        Cancion corta = artista.darCancionMasCorta( );
        assertNotNull( "La canci�n no deber�a ser nula", corta );
        assertEquals( "La canci�n retornada no es la m�s corta", "cancion3", corta.darNombre( ) );
        assertEquals( "La canci�n retornada no es la m�s corta", 80, corta.darDuracion( ) );
    }
}