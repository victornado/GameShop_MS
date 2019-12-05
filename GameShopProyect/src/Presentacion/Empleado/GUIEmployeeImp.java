package Presentacion.Empleado;

import Presentacion.View.ShowPanel;
import javafx.util.Pair;

import java.awt.BorderLayout;
import Presentacion.View.GUIGameshopImp;
import Presentacion.View.OperationsPanel;

@SuppressWarnings("serial")
public class GUIEmployeeImp extends GUIEmployee {
	
	private ShowPanel _rightPane;
	private OperationsPanel _leftPane;
	
	@Override
	protected void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshopImp.TAB_EMPLOYEE);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshopImp.TAB_EMPLOYEE);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Pair<Object, Integer> data) {
	}
}