package es.studium.gabineteMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controlador implements ActionListener, WindowListener
{
	Vista objVista = null;
	Modelo objModelo = null;

	public Controlador(Vista objVista, Modelo objModelo)
	{
		this.objVista = objVista;
		this.objModelo = objModelo;
		objVista.addWindowListener(this);
		objVista.frameAlta.addWindowListener(this);
		objVista.frameConsulta.addWindowListener(this);
		objVista.frameBaja.addWindowListener(this);
		objVista.frameModificar.addWindowListener(this);
		objVista.psicologosAlta.addActionListener(this);
		objVista.psicologosBaja.addActionListener(this);
		objVista.psicologosModificacion.addActionListener(this);
		objVista.psicologosConsulta.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evento)
	{
		/* ALTA */
		if (objVista.psicologosAlta.equals(evento.getSource()))
		{
			objVista.frameAlta.setVisible(true);
			objVista.botonGuardar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new Modelo().Alta(objVista.campoNombreAlta.getText());
				}
			});
		}

		/* BAJA */
		if (objVista.psicologosBaja.equals(evento.getSource()))
		{
			objVista.frameBaja.setVisible(true);
			ArrayList<String> psicologos = null;
			try
			{
				psicologos = objModelo.ConsultarPsicologos();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			for (String nombre : psicologos)
				objVista.eliminarPsicologo.add(nombre);
			objVista.botonEliminar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String nombrePsicologo = objVista.eliminarPsicologo.getSelectedItem();
					new Modelo().Eliminar(nombrePsicologo.toString());
				}
			});
		}

		/* MODIFICACIÓN */
		if (objVista.psicologosModificacion.equals(evento.getSource()))
		{
			objVista.frameModificar.setVisible(true);
			ArrayList<String> psicologos = null;
			try
			{
				psicologos = objModelo.ConsultarPsicologos();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			for (String nombre : psicologos)
				objVista.modificarPsicologo.add(nombre);
			objVista.botonModificar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String nuevoNombre = objVista.campoNombre.getText();
					String nombrePsicologo = objVista.modificarPsicologo.getSelectedItem();
					try
					{
						new Modelo().Modificar(nuevoNombre, nombrePsicologo);
					} catch (SQLException e1)
					{
						e1.printStackTrace();
					}
				}
			});
		}

		/* CONSULTA */
		if (objVista.psicologosConsulta.equals(evento.getSource()))
		{
			objVista.frameConsulta.setVisible(true);
			objVista.botonConsultar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (objVista.checkBoxID.getState())
					{
						String idPsicologo = objVista.campoValorConsulta.getText();
						String nombreResultado = null;
						try
						{
							nombreResultado = objModelo.ConsultaXID(idPsicologo);
						} catch (SQLException e1)
						{
							e1.printStackTrace();
						}
						objVista.resultadoConsulta.setText("#" + idPsicologo + " - " + nombreResultado);
					} else
					{
						String nombrePsicologo = objVista.campoValorConsulta.getText();
						String idResultado = null;
						try
						{
							idResultado = objModelo.ConsultaXNombre(nombrePsicologo);
						} catch (SQLException e1)
						{
							e1.printStackTrace();
						}
						objVista.resultadoConsulta.setText("#" + idResultado + " - " + nombrePsicologo);
					}

				}
			});
		}
	}

	public void windowActivated(WindowEvent we)
	{
	}

	public void windowClosed(WindowEvent we)
	{
	}

	public void windowClosing(WindowEvent we)
	{
		if (we.getWindow().equals(objVista.frameAlta))
		{
			objVista.frameAlta.setVisible(false);
		}
		if (we.getWindow().equals(objVista.frameConsulta))
		{
			objVista.frameConsulta.setVisible(false);
		}
		if (we.getWindow().equals(objVista.frameBaja))
		{
			objVista.frameBaja.setVisible(false);
		}
		if (we.getWindow().equals(objVista.frameModificar))
		{
			objVista.frameModificar.setVisible(false);
		}
		if (we.getWindow().equals(objVista))
		{
			System.exit(0);
		}
	}

	public void windowDeactivated(WindowEvent we)
	{
	}

	public void windowDeiconified(WindowEvent we)
	{
	}

	public void windowIconified(WindowEvent we)
	{
	}

	public void windowOpened(WindowEvent we)
	{
	}
}