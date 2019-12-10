package Presentacion.Realiza;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import Negocio.Realiza.RealizaEmbeddable;
import Presentacion.Controller.Event;
import utils.Pair;

@SuppressWarnings("serial")
public class GUIRealizaImp extends GUIRealiza {

	@Override
	protected void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		panel = new PanelRealiza();
		this.add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
	}

	@Override
	public void actualiza(Pair<Object, Integer> data) {
		
		switch(data.getValue()) {
		case Event.REALIZA_ASIGNAR_OK:
			RealizaEmbeddable a = (RealizaEmbeddable)data.getKey();
			JOptionPane.showMessageDialog(null, "La conferencia " + a.getConferencia() + " ha sido asiganda correctamente.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case Event.REALIZA_ASIGNAR_FAILED:
			JOptionPane.showMessageDialog(this, "Error asignando la conferencia.","Failed",JOptionPane.ERROR_MESSAGE);
			break;
		case Event.REALIZA_DESASIGNAR_OK:
			JOptionPane.showMessageDialog(null, "La conferencia ha sido desasiganda correctamente.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case Event.REALIZA_DESASIGNAR_FAILED:
			JOptionPane.showMessageDialog(this, "Error desasignando la conferencia.","Failed",JOptionPane.ERROR_MESSAGE);
			break;
		case Event.REALIZA_MODIFICAR_OK:
			RealizaEmbeddable c = (RealizaEmbeddable)data.getKey();
			JOptionPane.showMessageDialog(null, "La conferencia " + c.getConferencia() + " ha sido modificada correctamente.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case Event.REALIZA_MODIFICAR_FAILED:
			JOptionPane.showMessageDialog(this, "Error modificando la asignacion de la conferencia.","Failed",JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

}
