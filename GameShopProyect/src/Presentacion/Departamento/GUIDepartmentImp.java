package Presentacion.Departamento;

import Presentacion.View.ShowPanel;
import utils.Pair;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;

import Negocio.Transfers.TDepartamento;
import Negocio.Transfers.TEmpleado;
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
		case Event.RES_REGISTER_DEPARTMENT_OK:
			JOptionPane.showMessageDialog(null, "Department " + data.getKey() + " has been correctly insertes into the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			this._leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_REGISTER_DEPARTMENT_FAILED:
			JOptionPane.showMessageDialog(this, "Error entering the department in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_UNSUBSCRIBE_DEPARTMENT_OK:
			JOptionPane.showMessageDialog(null, "The Department " + data.getKey() + " has been successfully unsubscribed.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			this._leftPane.addInfoToComboBox();
			this._rightPane.update(this._leftPane.getElectionForm());
			break;
			
		case Event.RES_UNSUBSCRIBE_DEPARTMENT_FAILED:
			JOptionPane.showMessageDialog(this, "Error while removing the department in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_MODIFY_DEPARTMENT_OK:
			JOptionPane.showMessageDialog(this, "Department correctly updated in the database.","Success",JOptionPane.INFORMATION_MESSAGE);	
			_leftPane.addInfoToComboBox();
			_rightPane.update(this._leftPane.getElectionForm());
			break;
			
		case Event.RES_MODIFY_DEPARTMENT_FAILED:
			JOptionPane.showMessageDialog(this, "Error while modifying the department in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_READ_DEPARTMENT_OK:
			TDepartamento tp = (TDepartamento)data.getKey();
			this._leftPane.setEntityToUse(tp);
			_rightPane.setInfoInScreen(tp.toString());
			break;
			
		case Event.RES_READ_DEPARTMENT_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing the department in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_READALL_DEPARTMENT_OK:
			this._rightPane.update((List<Object>)data.getKey());
			break;
			
		case Event.RES_READALL_DEPARTMENT_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing all departments.","Failed",JOptionPane.ERROR_MESSAGE);			
			break;
			
		case Event.CALCULAR_NOMINA_DEPARTAMENTO_OK:
			_rightPane.mostrarNomina((String)data.getKey());
			break;
			
		case Event.CALCULAR_NOMINA_DEPARTAMENTO_FAILED:
			JOptionPane.showMessageDialog(this, "No se ha podido mostrar la nomina","Failed",JOptionPane.ERROR_MESSAGE);
			break;
			
		case Event.UPDATE_LIST_DEPARTMENT:
			if(this._leftPane != null) {
				this._leftPane.setElectionForm((List<Object>)data.getKey());
				this._rightPane.update((List<Object>)data.getKey());
			}
			break;
			
		case Event.UPDATE_LIST_EMPLOYEE:
			if(this._leftPane != null) {
				this._leftPane.setElectionForm((List<Object>)data.getKey());
			}
			break;
		}
	}	
	
}