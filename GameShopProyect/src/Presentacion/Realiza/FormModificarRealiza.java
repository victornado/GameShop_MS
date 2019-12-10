package Presentacion.Realiza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Negocio.Transfers.TRealiza;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormModificarRealiza extends FormRealiza {
	// Modificar tiene lo mismo que el normal pero ya esta todo relleno y solo tenemos que quitar la parte que pide la duracion
	
	public FormModificarRealiza() {
		super.setTitle("Modifica asignaciones de empleados a conferencias");
		elegirDuracion.setVisible(false);
		duracion.setVisible(false);
		quitar.setVisible(false);
		empleados.setVisible(false);
		elegirEmpleado.setVisible(false);
	}
	
	@Override
	protected void okButtonAction() {
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(grid.getRowCount() > 0) {
					empleadosEnConferencia.clear();
					Integer idEmp, idConf, duracion;
					TRealiza in = null;
					
					for(int i = 0; i < grid.getRowCount(); ++i) {
						idEmp = (Integer)grid.getValueAt(i, 0);
						idConf = (Integer)grid.getValueAt(i, 1);
						duracion = (Integer)grid.getValueAt(i, 2);
						in = new TRealiza(idEmp, idConf, duracion);
						empleadosEnConferencia.add(in);
					}
					
					closeDialog();
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
				Integer idConf = Integer.parseInt(((String)conferencias.getSelectedItem()).split(" - ")[0]);
				conferencias.setEnabled(false);
				
				// Ahora necesitamos rellenar la tabla con todo lo que haya en la BBDD.Realiza con ese "idConf"
				// TODO Controller.getInstance().action(idConf, Event.GET_ALL_REALIZA);
			}
		});
	}
}
