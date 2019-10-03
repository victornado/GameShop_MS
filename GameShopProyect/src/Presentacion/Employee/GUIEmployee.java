package Presentacion.Employee;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Presentacion.View.GUIGameshop;
import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import Transfers.TEmployee;

@SuppressWarnings("serial")
public class GUIEmployee extends JPanel implements IGUI {
	
	private OperationsPanel _leftPane;
	private ShowPanel _rightPane;
	
	public GUIEmployee() {
		alignmentPanels();
	}

	private void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		_leftPane = new OperationsPanel(GUIGameshop.TAB_EMPLOYEE);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		_rightPane = new ShowPanel(GUIGameshop.TAB_EMPLOYEE);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Integer e, Object t) {
		switch(e){
		case Event.RES_REGISTER_EMPLOYEE_OK:
			Integer id = (Integer)t;
			JOptionPane.showMessageDialog(null, "Employee " + id + " has been correctly inserted into the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAEmployee()).readAllEmployees());
			_leftPane.addInfoToComboBox();
			break;
		case Event.RES_REGISTER_EMPLOYEE_FAILED:
			JOptionPane.showConfirmDialog(null, "Error inserting the employee in the database.", "Failed", JOptionPane.ERROR_MESSAGE);
			break;
		case Event.RES_UNSUBSCRIBE_EMPLOYEE_OK:
			Integer a = (Integer)t;
			JOptionPane.showMessageDialog(null, "The employee " + a + " has been properly unsubscribed.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAEmployee()).readAllEmployees());
			break;
			
		case Event.RES_UNSUBSCRIBE_EMPLOYEE_FAILED:
			JOptionPane.showMessageDialog(this, "Error deleting the employee in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_MODIFY_EMPLOYEE_FAILED:
			JOptionPane.showMessageDialog(this, "Error while modifying the employee in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_MODIFY_EMPLOYEE_OK:
			JOptionPane.showMessageDialog(this, "Employee successfully updated in the database.","Success",JOptionPane.INFORMATION_MESSAGE);		
			_rightPane.update((SAAbstractFactory.getInstance().createSAEmployee()).readAllEmployees());
			_leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_READ_EMPLOYEE_OK:
			TEmployee te = (TEmployee)t;
			_rightPane.setInfoInScreen(te.toString());
			break;
			
		case Event.RES_READ_EMPLOYEE_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing an employee in the database.","Failed",JOptionPane.ERROR_MESSAGE);
			break;
		
		case Event.RES_READALL_EMPLOYEES_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing all employees in the database.","Failed",JOptionPane.ERROR_MESSAGE);
			break;
			
		case Event.RES_READALL_EMPLOYEES_OK:
			_rightPane.update((List<Object>)t);
			break;
		}
	}
}