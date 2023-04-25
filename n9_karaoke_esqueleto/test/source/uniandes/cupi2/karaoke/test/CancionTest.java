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

import junit.framework.TestCase;
import uniandes.cupi2.karaoke.mundo.Cancion;

/**
 * Esta es la clase usada para verificar la correcta implementaci�n de la clase Canci�n
 */
public class CancionTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Cancion cancion;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Canci�n
     * 
     */
    private void setupEscenario1( )
    {
        cancion = new Cancion( "nombre", 100, "letra", 5, "ruta" );
    }

    /**
     * Prueba 1 - Verifica que el nombre de la canci�n sea correcto
     */
    public void testDarNombre( )
    {
        setupEscenario1( );

        assertNotNull( "El nombre de la canci�n no fue inicializado", cancion.darNombre( ) );
        assertEquals( "El nombre de la canci�n no es el esperado", "nombre", cancion.darNombre( ) );
    }

    /**
     * Prueba 2 - Verifica que la duraci�n de la canci�n sea correcta
     */
    public void testDarDuracion( )
    {
        setupEscenario1( );

        assertEquals( "La duraci�n de la canci�n no es la esperada", 100, cancion.darDuracion( ) );
    }

    /**
     * Prueba 3 - Verifica que la dificultad de la canci�n sea correcta
     */
    public void testDarDificultad( )
    {
        setupEscenario1( );

        assertEquals( "La dificultad de la canci�n no es el esperada", 5, cancion.darDificultad( ) );
    }

    /**
     * Prueba 4 - Verifica que la letra de la canci�n sea correcta
     */
    public void testDarLetra( )
    {
        setupEscenario1( );

        assertNotNull( "La letra de la canci�n no fue inicializada", cancion.darLetra( ) );
        assertEquals( "La letra de la canci�n no es la esperada", "letra", cancion.darLetra( ) );
    }

    /**
     * Prueba 5 - Verifica que la ruta de la canci�n sea correcta
     */
    public void testDarRuta( )
    {
        setupEscenario1( );
        assertNotNull( "La ruta de la canci�n no fue inicializada", cancion.darRuta( ) );
        assertEquals( "La ruta de la canci�n no es la esperada", "ruta", cancion.darRuta( ) );
    }

    /**
     * <b>Prueba 6:</b> Verifica el m�todo cambiarSiguiente.<br>
     * <b>M�todos a probar:</b><br>
     * darSiguiente<br>
     * cambiarSiguiente<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay un siguiente al crear la canci�n. <br>
     * 2. La siguiente es correcta despu�s de haber sido asignada.
     */
    public void testCambiarSiguiente( )
    {
        setupEscenario1( );
        assertTrue( "No deber�a haber una siguiente", cancion.darSiguiente( ) == null );
        cancion.cambiarSiguiente( new Cancion( "C001", 123, "letra", 7, "ruta" ) );
        assertTrue( "No se est� cambiando el siguiente artista.", cancion.darSiguiente( ) != null );
        assertTrue( "La canci�n siguiente no es correcta.", cancion.darSiguiente( ).darNombre( ).equals( "C001" ) );
    }
}
