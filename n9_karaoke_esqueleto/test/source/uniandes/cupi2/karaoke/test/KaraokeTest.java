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
import uniandes.cupi2.karaoke.mundo.Karaoke;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Karaoke est�n correctamente implementados
 */
public class KaraokeTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Karaoke karaoke;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Karaoke vac�o
     * 
     */
    private void setupEscenario1( )
    {
        karaoke = new Karaoke( );
    }

    /**
     * Construye un nuevo Karaoke con 3 artistas.
     */
    private void setupEscenario2( )
    {
        karaoke = new Karaoke( );

        try
        {
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 2 ], "artista1", "imagen1" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 2 ], "artista2", "imagen2" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 1 ], "artista3", "imagen3" );
        }
        catch( Exception e )
        {
            fail( "Revisa el m�todo agregarArtista." );
        }
    }

    /**
     * Construye un Karaoke con 8 artistas y 15 canciones
     */
    private void setupEscenario3( )
    {
        karaoke = new Karaoke( );
        try
        {

            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 1 ], "artista5", "imagen5" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 4 ], "artista7", "imagen7" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 2 ], "artista1", "imagen1" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 1 ], "artista3", "imagen3" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 3 ], "artista6", "imagen6" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 2 ], "artista2", "imagen2" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 1 ], "artista4", "imagen4" );
            karaoke.agregarArtista( Karaoke.CATEGORIAS[ 4 ], "artista8", "imagen8" );
        }
        catch( Exception e )
        {
            fail( "Revisa el m�todo agregarArtista." );
        }

        try
        {

            karaoke.agregarCancion( "artista7", "cancion1", 50, "letra1", 8, "ruta1" );
            karaoke.agregarCancion( "artista7", "cancion2", 80, "letra2", 6, "ruta2" );
            karaoke.agregarCancion( "artista4", "cancion3", 75, "letra3", 7, "ruta3" );
            karaoke.agregarCancion( "artista4", "cancion4", 150, "letra4", 9, "ruta4" );
            karaoke.agregarCancion( "artista5", "cancion5", 120, "letra5", 1, "ruta5" );
            karaoke.agregarCancion( "artista2", "cancion6", 80, "letra6", 3, "ruta6" );
            karaoke.agregarCancion( "artista7", "cancion7", 39, "letra7", 1, "ruta7" );
            karaoke.agregarCancion( "artista8", "cancion8", 68, "letra8", 7, "ruta8" );
            karaoke.agregarCancion( "artista8", "cancion9", 20, "letra9", 6, "ruta9" );
            karaoke.agregarCancion( "artista6", "cancion10", 450, "letra10", 5, "ruta10" );
            karaoke.agregarCancion( "artista6", "cancion11", 75, "letra11", 4, "ruta11" );
            karaoke.agregarCancion( "artista6", "cancion12", 20, "letra12", 9, "ruta12" );
            karaoke.agregarCancion( "artista5", "cancion13", 345, "letra13", 3, "ruta13" );
            karaoke.agregarCancion( "artista3", "cancion14", 90, "letra14", 2, "ruta14" );
            karaoke.agregarCancion( "artista3", "cancion15", 45, "letra15", 2, "ruta15" );
        }
        catch( Exception e )
        {
            fail( "Revisa el m�todo agregarCancion." );
        }
    }

    /**
     * Prueba 1 - Prueba el m�todo constructor <br>
     * M�todos a probar: <br>
     * Karaoke
     */
    public void testKaraoke( )
    {
        setupEscenario1( );

        assertEquals( "Categor�a 1 incorrecta", Karaoke.CATEGORIAS[ 0 ], "Rock" );
        assertEquals( "Categor�a 2 incorrecta", Karaoke.CATEGORIAS[ 1 ], "Pop" );
        assertEquals( "Categor�a 3 incorrecta", Karaoke.CATEGORIAS[ 2 ], "Reggae" );
        assertEquals( "Categor�a 4 incorrecta", Karaoke.CATEGORIAS[ 3 ], "Tropical" );
        assertEquals( "Categor�a 5 incorrecta", Karaoke.CATEGORIAS[ 4 ], "Electr�nica" );
    }

    /**
     * Prueba 2 - Prueba el m�todo agregarArtista <br>
     * <b> M�todos a probar: </b> <br>
     * agregarArtista<br>
     * buscarArtista <br>
     * darArtistasCategoria<br>
     * <b> Resultado esperado: </b> <br>
     * El artista es agregado a una categor�a del karaoke
     */
    public void testAgregarArtista( )
    {
        setupEscenario1( );
        try
        {
            Artista menor = null;
            for( int i = 1; i < 1000; i++ )
            {
                int aleatorio = new Random( ).nextInt( Integer.MAX_VALUE );
                int cat = new Random( ).nextInt( Karaoke.CATEGORIAS.length );
                int antes = karaoke.darArtistasCategoria( Karaoke.CATEGORIAS[ cat ] ).size( );
                karaoke.agregarArtista( Karaoke.CATEGORIAS[ cat ], "artista" + aleatorio, "imagen" );
                Artista c = karaoke.buscarArtista( "artista" + aleatorio );
                assertNotNull( "El artista no se encontr�. No se agreg� bien o el m�todo buscarArtista tiene errores.", c );
                assertEquals( "El artista no se agreg� correctamente o el metodo darArtistasCategoria tiene errores.", antes + 1, karaoke.darArtistasCategoria( Karaoke.CATEGORIAS[ cat ] ).size( ) );
                if( menor == null || c.darNombre( ).compareTo( menor.darNombre( ) ) < 0 )
                {
                    menor = c;
                }
            }

            ArrayList<Artista> artistas = new ArrayList<>( );
            for( Artista actual = menor; actual != null; actual = actual.darSiguiente( ) )
            {
                artistas.add( actual );
            }
            assertNull( "El primer artista no puede tener referencia anterior.", menor.darAnterior( ) );
            for( int i = 0; i < artistas.size( ) - 1; i++ )
            {
                Artista c1 = artistas.get( i );
                Artista c2 = artistas.get( i + 1 );
                assertTrue( "Los artistas no se agregaron en orden ascendente.", c1.darNombre( ).compareTo( c2.darNombre( ) ) <= 0 );
                Artista anterior = c2.darAnterior( );
                if( i > 0 )
                {
                    assertTrue( "No se construyeron bien las referencias al artista anterior", anterior.darNombre( ).equals( c1.darNombre( ) ) );
                }

            }

        }
        catch( Exception e )
        {	
        	
        	e.printStackTrace();
            fail( "No deber�a lanzar excepci�n." );
        }

    }

    /**
     * Prueba 3 - Prueba el m�todo agregarArtista <br>
     * <b> M�todos a probar: </b> <br>
     * agregarArtista <br>
     * <b> Resultado esperado: </b> <br>
     * El artista no es agregado ya que existe un artista con el mismo nombre
     */
    public void testAgregarArtistaError( )
    {
        setupEscenario2( );

        String c = Karaoke.CATEGORIAS[ 4 ];
        try
        {
            karaoke.agregarArtista( c, "artista1", "imagen" );
            fail( "No se deber�a agregar el artista a la categor�a del karaoke" );
        }
        catch( Exception e )
        {
            assertEquals( "El n�mero de artistas de la categor�a no deber�a cambiar", 0, karaoke.darArtistasCategoria( c ).size( ) );
        }

    }

    /**
     * Prueba 4 - Prueba el m�todo buscarArtista. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarArtista | <br>
     * <b> Resultado esperado: </b> <br>
     * El artista es encontrado en el karaoke.
     */
    public void testBuscarArtista( )
    {
        setupEscenario2( );

        Artista artista = karaoke.buscarArtista( "artista3" );

        assertNotNull( "El artista no deber�a ser nulo", artista );
        assertEquals( "El artista encontrado no es correcto", "artista3", artista.darNombre( ) );
    }

    /**
     * Prueba 5 - Prueba el m�todo buscarArtista <br>
     * <b> M�todos a probar: </b> <br>
     * buscarArtista <br>
     * <b> Resultado esperado: </b> <br>
     * El artista no es encontrado en alguna de las categor�as del karaoke
     */
    public void testBuscarArtista2( )
    {
        setupEscenario2( );

        Artista artista = karaoke.buscarArtista( "artista4" );
        assertNull( "El artista deber�a ser nulo", artista );
    }

    /**
     * Prueba 6 - Prueba el m�todo agregarCancion <br>
     * <b> M�todos a probar: </b> <br>
     * agregarCancion <br>
     * <b> Resultado esperado: </b> <br>
     * La canci�n es agregada a un artista del karaoke
     */
    public void testAgregarCancion( )
    {
        setupEscenario2( );

        try
        {
            karaoke.agregarCancion( "artista2", "canci�n", 20, "letra", 5, "ruta" );
            assertEquals( "El canci�n no fue agregada al artista", 1, karaoke.darCancionesArtista( "artista2" ).size( ) );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            fail( "Se deber�a agregar la canci�n al karaoke" );
        }
    }

    /**
     * Prueba 7 - Prueba el m�todo agregarCancion <br>
     * <b> M�todos a probar: </b> <br>
     * agregarCancion <br>
     * <b> Resultado esperado: </b> <br>
     * La canci�n no es agregada ya que existe una canci�n del artista con el mismo nombre
     */
    public void testAgregarCancionError( )
    {
        setupEscenario3( );

        try
        {
            karaoke.agregarCancion( "artista2", "cancion6", 80, "letra6", 3, "ruta6" );
            fail( "No se deber�a agregar la canci�n al karaoke" );
        }
        catch( Exception e )
        {

            String c = Karaoke.CATEGORIAS[ 2 ];
            assertEquals( "El n�mero de canciones de la categor�a no deber�a cambiar", 1, karaoke.darCancionesCategoria( c ).size( ) );
        }

    }

    /**
     * Prueba 8 - Prueba el m�todo eliminarArtista <br>
     * <b> M�todos a probar: </b> <br>
     * eliminarArtista <br>
     * <b> Resultado esperado: </b> <br>
     * Se elimina el artista del karaoke.
     */
    public void testEliminarArtista( )
    {
        setupEscenario3( );

        karaoke.eliminarArtista( "artista6" );
        assertNull( "No se elimino correctamente el artista.", karaoke.buscarArtista( "artista6" ) );

        karaoke.eliminarArtista( "artista1" );
        assertNull( "No se elimino correctamente el artista.", karaoke.buscarArtista( "artista1" ) );

        karaoke.eliminarArtista( "artista8" );
        assertNull( "No se elimino correctamente el artista.", karaoke.buscarArtista( "artista8" ) );

        ArrayList<Artista> artistas = new ArrayList<>( );
        for( Artista actual = karaoke.buscarArtista( "artista2" ); actual != null; actual = actual.darSiguiente( ) )
        {
            artistas.add( actual );
        }
        assertEquals( "No se elimino correctamente el artista.", 5, artistas.size( ) );
        assertNull( "El primer artista no puede tener referencia anterior.", karaoke.buscarArtista( "artista2" ).darAnterior( ) );
        for( int i = 0; i < artistas.size( ) - 1; i++ )
        {
            Artista c1 = artistas.get( i );
            Artista c2 = artistas.get( i + 1 );
            Artista anterior = c2.darAnterior( );
            if( i > 0 )
            {
                assertTrue( "No se construyeron bien las referencias al artista anterior", anterior.darNombre( ).equals( c1.darNombre( ) ) );
            }

        }

    }

    /**
     * Prueba 9 - Prueba el m�todo eliminarCancion <br>
     * <b> M�todos a probar: </b> <br>
     * eliminarCancion <br>
     * <b> Resultado esperado: </b> <br>
     * Se elimina la canci�n del karaoke.
     */
    public void testEliminarCancion( )
    {
        setupEscenario3( );

        karaoke.eliminarCancion( "artista7", "cancion2" );
        assertEquals( "No se elimino correctamente la canci�n.", 2, karaoke.buscarArtista( "artista7" ).darCanciones( ).size( ) );

        karaoke.eliminarCancion( "artista7", "cancion7" );
        assertEquals( "No se elimino correctamente la canci�n.", 1, karaoke.buscarArtista( "artista7" ).darCanciones( ).size( ) );

        karaoke.eliminarCancion( "artista7", "cancion1" );
        assertEquals( "No se elimino correctamente la canci�n.", 0, karaoke.buscarArtista( "artista7" ).darCanciones( ).size( ) );

    }

    /**
     * Prueba 10 - Prueba el m�todo darCancionMasDificil <br>
     * <b> M�todos a probar: </b> <br>
     * darCancionMasDificil
     */
    public void testDarCancionMasDificil( )
    {
        setupEscenario3( );

        Cancion dificil = karaoke.darCancionMasDificil( );
        assertNotNull( "La canci�n no deber�a ser nula", dificil );
        assertEquals( "La canci�n retornada no es la m�s dif�cil", "cancion4", dificil.darNombre( ) );
        assertEquals( "La canci�n retornada no es la m�s dif�cil", 9, dificil.darDificultad( ) );
    }

    /**
     * Prueba 11 - Prueba el m�todo darCancionMasFacil <br>
     * <b> M�todos a probar: </b> <br>
     * darCancionMasFacil
     */
    public void testDarCancionMasFacil( )
    {
        setupEscenario3( );

        Cancion facil = karaoke.darCancionMasFacil( );
        assertNotNull( "La canci�n no deber�a ser nula", facil );
        assertEquals( "La canci�n retornada no es la m�s f�cil", "cancion5", facil.darNombre( ) );
        assertEquals( "La canci�n retornada no es la m�s f�cil", 1, facil.darDificultad( ) );

    }

    /**
     * Prueba 12 - Prueba el m�todo darCancionMasLarga <br>
     * <b> M�todos a probar: </b> <br>
     * darCancionMasLarga
     */
    public void testDarCancionMasLarga( )
    {
        setupEscenario3( );

        Cancion larga = karaoke.darCancionMasLarga( );
        assertNotNull( "La canci�n no deber�a ser nula", larga );
        assertEquals( "La canci�n retornada no es la m�s larga", "cancion10", larga.darNombre( ) );
        assertEquals( "La canci�n retornada no es la m�s larga", 450, larga.darDuracion( ) );
    }

    /**
     * Prueba 13 - Prueba el m�todo darCancionMasCorta <br>
     * <b> M�todos a probar: </b> <br>
     * darCancionMasCorta
     */
    public void testDarCancionMasCorta( )
    {
        setupEscenario3( );

        Cancion corta = karaoke.darCancionMasCorta( );
        assertNotNull( "La canci�n no deber�a ser nula", corta );
        assertEquals( "La canci�n retornada no es la m�s corta", "cancion12", corta.darNombre( ) );
        assertEquals( "La canci�n retornada no es la m�s corta", 20, corta.darDuracion( ) );
    }

    /**
     * Prueba 14 - Prueba el m�todo darArtistaMasCanciones <br>
     * <b> M�todos a probar: </b> <br>
     * darArtistaMasCanciones
     */
    public void testDarArtistaMasCanciones( )
    {
        setupEscenario3( );

        Artista artista = karaoke.darArtistaMasCanciones( );
        assertNotNull( "El artista no deber�a ser nulo", artista );
        assertEquals( "El artista retornado no es el artista con mayor n�mero de canciones", "artista6", artista.darNombre( ) );
    }

    /**
     * Prueba 15 - Prueba el m�todo darCanciones <br>
     * <b> M�todos a probar: </b> <br>
     * darCanciones
     */
    public void testDarCancionesCategoria( )
    {
        setupEscenario3( );

        ArrayList canciones = karaoke.darCancionesCategoria( Karaoke.CATEGORIAS[ 4 ] );
        assertNotNull( "La lista de canciones no deber�a ser nula", canciones );
        assertEquals( "No se han incluido todas las canciones de la categor�a en la lista", 5, canciones.size( ) );

        for( int i = 0; i < canciones.size( ); i++ )
        {
            Cancion c = ( Cancion )canciones.get( i );
            int numCancion = i < 2 ? i + 1 : 5 + i;
            assertEquals( "La canci�n en la lista no es correcta", "cancion" + numCancion, c.darNombre( ) );
        }
    }

    /**
     * Prueba 16 - Prueba el m�todo darCancionesArtista <br>
     * <b> M�todos a probar: </b> <br>
     * darCancionesArtista
     */
    public void testDarCancionesArtista( )
    {
        setupEscenario3( );

        ArrayList canciones = karaoke.darCancionesArtista( "artista5" );
        assertNotNull( "La lista de canciones no deber�a ser nula", canciones );
        assertEquals( "No se han retornado todas las canciones del artista", 2, canciones.size( ) );

        Cancion c = ( Cancion )canciones.get( 0 );
        assertEquals( "La canci�n no es correcta", "cancion13", c.darNombre( ) );
        c = ( Cancion )canciones.get( 1 );
        assertEquals( "La canci�n no es correcta", "cancion5", c.darNombre( ) );
    }
}