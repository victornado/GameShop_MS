package Presentacion.Empleado;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TProvider;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormUpdateEmployee extends FormEmployee {
	
	private JCheckBox _reactivate;
	private TEmpleado _employee;
	
	public FormUpdateEmployee(TEmpleado t) {
		super();
		_employee = t;
		this.setTitle("Modify an employee");
		this.setSize(new Dimension(300, 145));
		
		this._reactivate = new JCheckBox("Activated");
		this._reactivate.setBounds(50, 150, 140, 50); //this._reactivate.setBounds(200, 240, 140, 50);
		this._reactivate.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(_reactivate.isSelected()) {
					_employee.setActivo(true);
					_reactivate.setEnabled(false);
				}
				else
					_employee.setActivo(false);
			}
			
		});
		initForm();
		
		this.add(this._reactivate);
	}
	
	private void initForm() {
		if(this._employee.getActivo()) {
			this._reactivate.setEnabled(false);
			this._reactivate.setSelected(true);
		}
	}
	
	@Override
	protected void okButtonAction(){
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}