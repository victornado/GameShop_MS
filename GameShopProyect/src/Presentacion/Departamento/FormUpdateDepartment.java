package Presentacion.Departamento;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Transfers.TDepartamento;
import Negocio.Transfers.TProvider;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormUpdateDepartment extends FormDepartment {

	private JCheckBox _reactivate;
	private TDepartamento _department;
	
	public FormUpdateDepartment(TDepartamento td) {
		super();
		_department = td;
		this.setTitle("Modify a Provider");
		this.setSize(new Dimension(300, 145));
		
		this._reactivate = new JCheckBox("Activated");
		this._reactivate.setBounds(50, 150, 140, 50); //this._reactivate.setBounds(200, 240, 140, 50);
		this.add(_reactivate);
		this._reactivate.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(_reactivate.isSelected()) {
					//_department.set_activated(true);
					_reactivate.setEnabled(false);
				}
				//else
					//_department.set_activated(false);
			}
			
		});
		initForm();
		
		this.add(this._reactivate);
	}
	
	private void initForm() {
		/*this._nifText.setText(_provider.get_nif());
		this._addressText.setText(_provider.get_address());
		this._phoneText.setText(_provider.get_phoneNumber().toString());
		
		if(_provider.get_activated()) {
			this._reactivate.setEnabled(false);
			this._reactivate.setSelected(true);
		}*/
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