
package Presentacion.Conferencia;

import java.awt.BorderLayout;

import Presentacion.View.GUIGameshopImp;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import javafx.util.Pair;

@SuppressWarnings("serial")
public class GUIConferenciaImp extends GUIConferencia {

	private OperationsPanel _leftPane;
	private ShowPanel _rightPane;
	
	@Override
	protected void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshopImp.TAB_PROVIDER);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshopImp.TAB_PROVIDER);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}
	@Override
	public void actualiza(Pair<Object, Integer> data) {
		
	}
}