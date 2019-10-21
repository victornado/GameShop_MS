package Presentacion.Ticket;

import javax.swing.JPanel;

import Presentacion.View.IGUI;

@SuppressWarnings("serial")
public abstract class GUITicket extends JPanel implements IGUI {
	private static GUITicket _instance;
	
	public static GUITicket getInstance() {
		if(_instance == null) {
			_instance = new GUITicketImp();
			_instance.alignmentPanels();
		}
		return _instance;
	}
	
	protected abstract void alignmentPanels();
	public abstract void actualiza(Integer event, Object id);
}
