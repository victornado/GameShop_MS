package Presentacion.Provider;

import javax.swing.JPanel;

import Presentacion.View.IGUI;
import javafx.util.Pair;

@SuppressWarnings("serial")
public abstract class GUIProvider extends JPanel implements IGUI {
	private static GUIProvider _instance;
	
	public static GUIProvider getInstance() {
		if(_instance == null) {
			_instance = new GUIProviderImp();
			_instance.alignmentPanels();
		}
		return _instance;
	}
	
	protected abstract void alignmentPanels();
	public abstract void actualiza(Pair<Object, Integer> data);
}
