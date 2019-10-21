package Presentacion.Provider;

import javax.swing.JPanel;

import Presentacion.View.IGUI;

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
}
