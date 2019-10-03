package Presentacion.Employee;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Transfers.TEmployee;

@SuppressWarnings("serial")
public class FormUpdateEmployee extends FormEmployee {
	
	private JCheckBox _reactivate;
	private TEmployee _employee;
	
	public FormUpdateEmployee(TEmployee t) {
		super();
		_employee = t;
		this.setTitle("Modify a Provider");
		this.setSize(new Dimension(300, 145));
		
		this._reactivate = new JCheckBox("Activated");
		this._reactivate.setBounds(50, 150, 140, 50); //this._reactivate.setBounds(200, 240, 140, 50);
		this.add(_reactivate);
		this._reactivate.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(_reactivate.isSelected()) {
					_employee.set_activated(true);
					_reactivate.setEnabled(false);
				}else
					_employee.set_activated(false);
			}
			
		});
		initForm();
		
		this.add(this._reactivate);
	}
	
	private void initForm() {
		_nifText.setText(_employee.get_nif());
		_nameText.setText(_employee.get_name());
		if(_employee.getTurn().equals("Early shift"))
			_turnElection.setSelectedIndex(0);
		else _turnElection.setSelectedIndex(1);
		
		if(_employee.get_activated()) {
			this._reactivate.setEnabled(false);
			this._reactivate.setSelected(true);
		}
	}
	
	@Override
	protected void okButtonAction(){ //LLAMAR/CREAR EVENT.UPDATE_EMPLOYEE
		_accept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				/* LO DE ANTES:
				_employee.set_nif(_nifText.getText());
				_employee.set_name(_nameText.getText());
				_employee.setTurn(String.valueOf(_turnElection.getSelectedItem()));
				Controller.getInstance().action(_employee, Event.MODIFY_EMPLOYEE);
				closeDialog();*/
				
				try {
					String name = _nameText.getText();
					String nif = _nifText.getText();
					String turn = String.valueOf(_turnElection.getSelectedItem());
					_employee.set_name(name);
					_employee.set_nif(nif);
					_employee.setTurn(turn);
					closeDialog();
					Controller.getInstance().action(_employee, Event.MODIFY_EMPLOYEE);
				} catch(Exception ex) {
					closeDialog();
					Controller.getInstance().action(null, Event.MODIFY_EMPLOYEE);
				}
			}
		});
	}
}
