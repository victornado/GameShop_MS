
package Presentacion.Conferencia;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;

import Presentacion.Controller.Event;
import Presentacion.View.GUIGameshopImp;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import utils.Pair;

@SuppressWarnings("serial")
public class GUIConferenciaImp extends GUIConferencia {
	
	@Override
	protected void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshopImp.TAB_CONFERENCE);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshopImp.TAB_CONFERENCE);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Pair<Object, Integer> data) {
		switch(data.getValue()) {
		case Event.RES_REGISTER_CONFERENCE_OK:
			JOptionPane.showMessageDialog(null, "Conference " + data.getKey() + " has been correctly insertes into the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			this._leftPane.addInfoToComboBox();
			break;
		case Event.RES_REGISTER_CONFERENCE_FAILED:
			JOptionPane.showMessageDialog(this, "Error entering the conference in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		case Event.UPDATE_LIST_CONFERENCE:
			if(this._leftPane != null)
				this._leftPane.setElectionForm((List<Object>)data.getKey());
			break;
		case Event.UPDATE_LIST_EMPLOYEE:
			if(this._leftPane != null) 
				this._leftPane.setElectionForm((List<Object>)data.getKey());
			break;
		}
	}

}