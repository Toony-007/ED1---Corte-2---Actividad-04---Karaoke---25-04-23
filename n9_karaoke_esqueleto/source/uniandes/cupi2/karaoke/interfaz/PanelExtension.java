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

package uniandes.cupi2.karaoke.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
@SuppressWarnings("serial")
public class PanelExtension extends JPanel implements ActionListener
{

	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------

	/**
	 * Comando Opci�n 1
	 */
	private static final String OPCION_1 = "OPCION_1";

	/**
	 * Comando Opci�n 2
	 */
	private static final String OPCION_2 = "OPCION_2";

	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicaci�n
	 */
	private InterfazKaraoke principal;

	//-----------------------------------------------------------------
	// Atributos de interfaz
	//-----------------------------------------------------------------

	/**
	 * Bot�n Opci�n 1
	 */
	private JButton btnOpcion1;

	/**
	 * Bot�n Opci�n 2
	 */
	private JButton btnOpcion2;

	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------

	/**
	 * Constructor del panel
	 * @param pVentana Ventana principal
	 */
	public PanelExtension( InterfazKaraoke pVentana )
	{
		principal = pVentana;

		setBorder( new TitledBorder( "Opciones" ) );
		setLayout( new GridLayout( 1, 2 ) );

		//Bot�n opci�n 1
		btnOpcion1 = new JButton("Reporte por categoria.");
		btnOpcion1.setActionCommand( OPCION_1 );
		btnOpcion1.addActionListener( this );
		add(btnOpcion1);

		//Bot�n opci�n 2
		btnOpcion2 = new JButton("Existen o no canciones.");
		btnOpcion2.setActionCommand( OPCION_2 );
		btnOpcion2.addActionListener( this );
		add(btnOpcion2);
	}

	//-----------------------------------------------------------------
	// M�todos
	//-----------------------------------------------------------------

	/**
	 * Manejo de los eventos de los botones
	 * @param pEvento Acci�n que gener� el evento.
	 */
	public void actionPerformed( ActionEvent pEvento )
	{
		if(OPCION_1.equals( pEvento.getActionCommand() ))
		{
			principal.reqFuncOpcion1();
		}
		else if(OPCION_2.equals( pEvento.getActionCommand() ))
		{
			principal.reqFuncOpcion2();
		}
	}

}
