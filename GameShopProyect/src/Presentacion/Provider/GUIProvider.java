package Presentacion.Provider;

import javax.swing.JPanel;


import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import utils.Pair;

@SuppressWarnings("serial")
public abstract class GUIProvider extends JPanel implements IGUI {
	private static GUIProvider _instance;	
	OperationsPanel _leftPane;
	ShowPanel _rightPane;
	
	public static GUIProvider getInstance() {
		if(_instance == null) {
			_instance = new GUIProviderImp();
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
