package Presentacion.Realiza;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormModificarRealiza extends FormRealiza {

	private JButton modificarTiempo = new JButton("Modificar");

	public FormModificarRealiza() {
		super.setTitle("Modifica asignaciones de empleados a conferencias");
		duracion.setEnabled(false);
		quitar.setVisible(false);
		empleados.setVisible(false);
		elegirEmpleado.setVisible(false);

		modificarTiempo.setPreferredSize(new Dimension(75, 20));
		modificarTiempo.setMinimumSize(new Dimension(75, 20));
		modificarTiempo.setMaximumSize(new Dimension(75, 20));
		modificarTiempo.setAlignmentX(Component.CENTER_ALIGNMENT);
		modificarTiempo.setVisible(true);
		modificarTiempo.setEnabled(false);
		this.add(modificarTiempo);

		modificarButtonAction();
	}

	@Override
	protected void okButtonAction() {
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (grid.getRowCount() > 0) {

					closeDialog();

					Controller.getInstance().action(empleadosEnConferencia, Event.REALIZA_MODIFICAR);
				}
			}
		});
	}

	@Override
	protected void addButtonAction() {
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				add.setEnabled(false);
				Integer idConf = Integer.parseInt(((String) conferencias.getSelectedItem()).split(" - ")[0]);
				conferencias.setEnabled(false);

				modificarTiempo.setEnabled(true);
				duracion.setEnabled(true);

				Controller.getInstance().action(idConf, Event.READ_CONFERENCE_FORM);
				empleadosEnConferencia = GUIRealiza.getInstance().getPanel().get_entityToUse()
						.getEmpleadosEnConferencias();
				model.fireTableDataChanged();
			}
		});
	}

	private void modificarButtonAction() {
		modificarTiempo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer empleadoEnTabla = grid.getSelectedRow();
				if (empleadoEnTabla != -1) {
					Integer nuevaDuracion = Integer.parseInt((String) duracion.getText());
					empleadosEnConferencia.get(empleadoEnTabla).setDuracion(nuevaDuracion);
					model.fireTableDataChanged();
				}
			}
		});
	}
}
