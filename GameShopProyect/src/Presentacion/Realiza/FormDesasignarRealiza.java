package Presentacion.Realiza;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Negocio.Realiza.RealizaEmbeddable;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormDesasignarRealiza extends FormRealiza {
	
	public FormDesasignarRealiza() {
		super.setTitle("Desasigna los empleados de las conferencias");
		this.setSize(new Dimension(320, 120));
		elegirDuracion.setVisible(false);
		duracion.setVisible(false);
		add.setVisible(false);
		quitar.setVisible(false);
		jsp.setVisible(false);
		grid.setVisible(false);
	}
	
	@Override
	protected void okButtonAction() {
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idConf = Integer.parseInt(((String)conferencias.getSelectedItem()).split(" - ")[0]);
				Integer idEmp = Integer.parseInt(((String)empleados.getSelectedItem()).split(" - ")[0]);
				RealizaEmbeddable in = new RealizaEmbeddable(idEmp, idConf);
				Controller.getInstance().action(in, Event.REALIZA_DESASIGNAR);
			}
		});
	}
}
