package Presentacion.Realiza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormModificarRealiza extends FormRealiza {
	// Modificar tiene lo mismo que el normal pero ya esta todo relleno y solo tenemos que quitar la parte que pide la duracion
	
	public FormModificarRealiza() {
		super.setTitle("Modifica asignaciones de empleados a conferencias");
		elegirDuracion.setVisible(false);
		duracion.setVisible(false);
		add.setVisible(false);
		quitar.setVisible(false);
	}
	
	// NO TENEMOS NI EL BOTON AÑADIR NI EL TEXTFIELD DE DURACION, LO DEMAS ES IGUAL
	@Override
	protected void okButtonAction() {
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(empleadosEnConferencia.size() > 0) {
					closeDialog();
					
					Controller.getInstance().action(empleadosEnConferencia, Event.REALIZA_MODIFICAR);
				}
			}
		});
	}
}
