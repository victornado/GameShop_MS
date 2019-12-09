package Presentacion.Empleado;

import javax.swing.JPanel;


import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import utils.Pair;

@SuppressWarnings("serial")
public abstract class GUIEmployee extends JPanel implements IGUI {
	
	private static GUIEmployee _instance;
	ShowPanel _rightPane;
	OperationsPanel _leftPane;
	
	public static GUIEmployee getInstance() {
		if(_instance == null) {
			_instance = new GUIEmployeeImp();
			_instance.alignmentPanels();
		}
		return _instance;
	}
	
	protected abstract void alignmentPanels();
	public abstract void actualiza(Pair<Object, Integer> data);
	public OperationsPanel getOpPanel() {
		return this._leftPane;
	}
	public ShowPanel getShowPanel() {
		return this._rightPane;
	}
}