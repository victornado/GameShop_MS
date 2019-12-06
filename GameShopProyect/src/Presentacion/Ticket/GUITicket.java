package Presentacion.Ticket;

import javax.swing.JPanel;


import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import utils.Pair;

@SuppressWarnings("serial")
public abstract class GUITicket extends JPanel implements IGUI {
	private static GUITicket _instance;
	OperationsPanel _leftPane;
	ShowPanel _rightPane;
	
	
	public static GUITicket getInstance() {
		if(_instance == null) {
			_instance = new GUITicketImp();
			_instance.alignmentPanels();
		}
		return _instance;
	}
	
	protected abstract void alignmentPanels();
	public abstract void actualiza(Pair<Object, Integer> data);
	public OperationsPanel getOpPanel() {
		return this._leftPane;
	}
}
