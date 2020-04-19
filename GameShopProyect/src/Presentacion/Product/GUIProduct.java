package Presentacion.Product;

import javax.swing.JPanel;


import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import utils.Pair;

@SuppressWarnings("serial")
public abstract class GUIProduct extends JPanel implements IGUI {
	private static GUIProduct _instance;
	OperationsPanel _leftPane;
	ShowPanel _rightPane;
	
	public static GUIProduct getInstance() {
		if(_instance == null) {
			_instance = new GUIProductImp();
			_instance.alingmentPanels();
		}
		return _instance;
	}
	
	protected abstract void alingmentPanels();
	public abstract void actualiza(Pair<Object, Integer> data);
	public OperationsPanel getOpPanel() {
		return this._leftPane;
	}
	public ShowPanel getShowPanel() {
		return this._rightPane;
	}
}
