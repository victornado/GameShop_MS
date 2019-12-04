package Presentacion.Departamento;

import javax.swing.JPanel;

import Presentacion.View.IGUI;
import javafx.util.Pair;

@SuppressWarnings("serial")
public abstract class GUIDepartment extends JPanel implements IGUI {
	private static GUIDepartment _instance;
	
	public static GUIDepartment getInstance() {
		if(_instance == null) {
			_instance = new GUIDepartmentImp();
			_instance.alignmentPanels();
		}
		return _instance;
	}
	
	protected abstract void alignmentPanels();
	public abstract void actualiza(Pair<Object, Integer> data);
}