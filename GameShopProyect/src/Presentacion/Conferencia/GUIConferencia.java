package Presentacion.Conferencia;

import javax.swing.JPanel;


import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import utils.Pair;

@SuppressWarnings("serial")
public abstract class GUIConferencia extends JPanel implements IGUI {
	private static GUIConferencia _instance;
	protected ShowPanel _rightPane;
	protected OperationsPanel _leftPane;
	
	public static GUIConferencia getInstance() {
		if(_instance == null) {
			_instance = new GUIConferenciaImp();
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