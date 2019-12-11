package Presentacion.Realiza;

import javax.swing.JPanel;

import Presentacion.View.IGUI;
import utils.Pair;

@SuppressWarnings("serial")
public abstract class GUIRealiza extends JPanel implements IGUI {
	private static GUIRealiza _instance;
	protected PanelRealiza panel;

	public static GUIRealiza getInstance() {
		if (_instance == null) {
			_instance = new GUIRealizaImp();
			_instance.alignmentPanels();
		}
		return _instance;
	}

	protected abstract void alignmentPanels();

	public abstract void actualiza(Pair<Object, Integer> data);

	public PanelRealiza getPanel() {
		return panel;
	}
}
