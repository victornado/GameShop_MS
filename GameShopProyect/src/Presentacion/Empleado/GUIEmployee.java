package Presentacion.Empleado;

import javax.swing.JPanel;

import Presentacion.View.IGUI;
import javafx.util.Pair;

@SuppressWarnings("serial")
public abstract class GUIEmployee extends JPanel implements IGUI {
	
	private static GUIEmployee _instance;
	
	public static GUIEmployee getInstance() {
		if(_instance == null) {
			_instance = new GUIEmployeeImp();
			_instance.alignmentPanels();
		}
		return _instance;
	}
	
	protected abstract void alignmentPanels();
	public abstract void actualiza(Pair<Object, Integer> data);
}