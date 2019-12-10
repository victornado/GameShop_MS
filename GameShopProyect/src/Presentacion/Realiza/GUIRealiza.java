package Presentacion.Realiza;

import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import utils.Pair;

public abstract class GUIRealiza {
	private static GUIRealiza _instance;
	protected ShowPanel _rightPane;
	protected OperationsPanel _leftPane;
	
	public static GUIRealiza getInstance() {
		if(_instance == null) {
			_instance = new GUIRealizaImp();
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
