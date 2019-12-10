package Presentacion.Empleado;

import Presentacion.View.ShowPanel;


import utils.Pair;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;
import Negocio.Transfers.TEmpleado;
import Presentacion.Controller.Event;
import Presentacion.View.GUIGameshopImp;
import Presentacion.View.OperationsPanel;

@SuppressWarnings("serial")
public class GUIEmployeeImp extends GUIEmployee {
	
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
		switch(data.getValue()) {
		case Event.RES_REGISTER_EMPLOYEE_OK:
			JOptionPane.showMessageDialog(null, "Employee " + data.getKey() + " has been correctly insertes into the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			this._leftPane.addInfoToComboBox();
			this._rightPane.update(this._leftPane.getElectionForm());
			break;
			
		case Event.RES_REGISTER_EMPLOYEE_FAILED:
			JOptionPane.showMessageDialog(this, "Error entering the Employee in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_UNSUBSCRIBE_EMPLOYEE_OK:
			JOptionPane.showMessageDialog(null, "The Employee " + data.getKey() + " has been successfully unsubscribed.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			this._leftPane.addInfoToComboBox();
			this._rightPane.update(this._leftPane.getElectionForm());
			break;
			
		case Event.RES_UNSUBSCRIBE_EMPLOYEE_FAILED:
			JOptionPane.showMessageDialog(this, "Error while removing the employee in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_MODIFY_EMPLOYEE_OK:
			JOptionPane.showMessageDialog(this, "Employee correctly updated in the database.","Success",JOptionPane.INFORMATION_MESSAGE);	
			_leftPane.addInfoToComboBox();
			_rightPane.update(this._leftPane.getElectionForm());
			break;
			
		case Event.RES_MODIFY_EMPLOYEE_FAILED:
			JOptionPane.showMessageDialog(this, "Error while modifying the employee in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_READ_EMPLOYEE_OK:
			TEmpleado tp = (TEmpleado)data.getKey();
			_rightPane.setInfoInScreen(tp.toString());
			break;
			
		case Event.RES_READ_EMPLOYEE_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing the employee in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_READALL_EMPLOYEE_OK:
			this._rightPane.update((List<Object>)data.getKey());
			break;
			
		case Event.RES_READALL_EMPLOYEE_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing all employees.","Failed",JOptionPane.ERROR_MESSAGE);			
			break;
			
		case Event.UPDATE_LIST_DEPARTMENT:
			if(this._leftPane != null) {
				this._leftPane.setElectionForm((List<Object>)data.getKey());
			}
			break;
			
		case Event.UPDATE_LIST_EMPLOYEE:
			if(this._leftPane != null) {
				this._leftPane.setElectionForm((List<Object>)data.getKey());
				this._rightPane.update((List<Object>)data.getKey());
			}
			break;
		}
	}
	
}