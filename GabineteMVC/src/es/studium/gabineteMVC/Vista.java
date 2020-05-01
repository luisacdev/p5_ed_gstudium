package es.studium.gabineteMVC;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;

public class Vista extends Frame
{
	Frame frameAlta;
	Frame frameConsulta;
	Frame frameBaja;
	Frame frameModificar;

	private static final long serialVersionUID = 1L;

	/* Declaración de elementos gráficos del menú */
	MenuBar barraMenu = new MenuBar();
	Menu mnPsicologos = new Menu("Psicólogos");;
	MenuItem psicologosAlta = new MenuItem("Alta");
	MenuItem psicologosBaja = new MenuItem("Baja");
	MenuItem psicologosModificacion = new MenuItem("Modificación");
	MenuItem psicologosConsulta = new MenuItem("Consulta");

	/* Declaración de elementos gráficos */
	/* Labels | TextFields | Buttons */
	Label etiquetaNombreAlta = new Label("Nombre");
	TextField campoNombreAlta = new TextField(25);
	Label etiquetaNombreConsulta = new Label("Valor");
	TextField campoValorConsulta = new TextField(18);
	Button botonGuardar = new Button("Guardar");
	Button botonConsultar = new Button("Consultar");
	Button botonEliminar = new Button("Eliminar");
	Button botonModificar = new Button("Modificar");
	Label etiquetaResultado = new Label("Resultado:");
	TextField resultadoConsulta = new TextField(25);
	Label metodoConsulta = new Label("¿Cómo quieres buscar?");
	CheckboxGroup grupoCheck = new CheckboxGroup();
	Checkbox checkBoxID = new Checkbox("Por ID", grupoCheck, false);
	Checkbox checkBoxNombre = new Checkbox("Por Nombre", grupoCheck, false);
	Choice eliminarPsicologo = new Choice();
	Choice modificarPsicologo = new Choice();
	Label editarNombre = new Label("Cambiar");
	TextField campoNombre = new TextField(18);
	Label psicologoAModificar = new Label("Selecciona psicologo:");

	/* Constructor */
	public Vista()
	{
		/* Frame con menú inicial */
		setLayout(new FlowLayout());
		setTitle("Gestión del Gabinete");
		setMenuBar(barraMenu);
		barraMenu.add(mnPsicologos);
		mnPsicologos.add(psicologosAlta);
		mnPsicologos.add(psicologosBaja);
		mnPsicologos.add(psicologosModificacion);
		mnPsicologos.add(psicologosConsulta);
		setResizable(false);
		setVisible(true);
		setSize(350, 200);
		setLocationRelativeTo(null);

		/* Frame Alta Psicologo */
		frameAlta = new Frame("Alta Psicologo");
		frameAlta.setLayout(new FlowLayout());
		frameAlta.setResizable(false);
		frameAlta.setVisible(false);
		frameAlta.setSize(300, 100);
		frameAlta.setLocationRelativeTo(null);

		frameAlta.add(etiquetaNombreAlta);
		frameAlta.add(campoNombreAlta);
		frameAlta.add(botonGuardar);

		/* Frame Consulta Psicologo */
		frameConsulta = new Frame("Consulta Psicologo");
		frameConsulta.setLayout(new FlowLayout());
		frameConsulta.setResizable(false);
		frameConsulta.setVisible(false);
		frameConsulta.setSize(350, 125);
		frameConsulta.setLocationRelativeTo(null);
		frameConsulta.add(metodoConsulta);
		frameConsulta.add(checkBoxID);
		frameConsulta.add(checkBoxNombre);
		frameConsulta.add(etiquetaNombreConsulta);
		frameConsulta.add(campoValorConsulta);
		frameConsulta.add(botonConsultar);
		frameConsulta.add(etiquetaResultado);
		frameConsulta.add(resultadoConsulta);

		/* Frame Borrar Psicologo */
		frameBaja = new Frame("Borrar Psicologo");
		frameBaja.setLayout(new FlowLayout());
		frameBaja.setResizable(false);
		frameBaja.setVisible(false);
		frameBaja.setSize(350, 75);
		frameBaja.setLocationRelativeTo(null);
		frameBaja.add(eliminarPsicologo);
		frameBaja.add(botonEliminar);
		
		/* Frame Modificar Psicologo */
		frameModificar = new Frame("Modificar Psicologo");
		frameModificar.setLayout(new FlowLayout());
		frameModificar.setResizable(false);
		frameModificar.setVisible(false);
		frameModificar.setSize(250, 125);
		frameModificar.setLocationRelativeTo(null);
		frameModificar.add(psicologoAModificar);
		frameModificar.add(modificarPsicologo);
		frameModificar.add(editarNombre);
		frameModificar.add(campoNombre);
		frameModificar.add(botonModificar);
	}
}