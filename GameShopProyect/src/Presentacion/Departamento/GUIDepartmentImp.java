package Presentacion.Departamento;

import Presentacion.View.ShowPanel;
import utils.Pair;

import java.awt.BorderLayout;
import java.util.List;

import Presentacion.Controller.Event;
import Presentacion.View.GUIGameshopImp;
import Presentacion.View.OperationsPanel;

@SuppressWarnings("serial")
public class GUIDepartmentImp extends GUIDepartment {
	
	@Override
	protected void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshopImp.TAB_DEPARTMENT);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshopImp.TAB_DEPARTMENT);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Pair<Object, Integer> data) {
		switch(data.getValue()) {
		case Event.UPDATE_LIST_DEPARTMENT:
			this._leftPane.setElectionForm((List<Object>)data.getKey());
			break;
		case Event.UPDATE_LIST_EMPLOYEE:
			this._leftPane.setElectionForm((List<Object>)data.getKey());
			break;
		}
	}	
	
}