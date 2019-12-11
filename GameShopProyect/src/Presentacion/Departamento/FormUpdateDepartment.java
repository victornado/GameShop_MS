package Presentacion.Departamento;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Transfers.TDepartamento;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormUpdateDepartment extends FormDepartment {

	private JCheckBox _reactivate;
	private TDepartamento td;
	
	public FormUpdateDepartment(TDepartamento td) {
		super();
		this.td = td;
		this.setTitle("Modify a department");
		this.setSize(new Dimension(300, 145));
		
		this._reactivate = new JCheckBox("Activated");
		this._reactivate.setBounds(50, 150, 140, 50);
		this.add(_reactivate);
		this._reactivate.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(_reactivate.isSelected()) {
					td.setActivo(true);
					_reactivate.setEnabled(false);
				}
				else
					td.setActivo(false);
			}
		});
		initForm();
		
		this.add(this._reactivate);
	}
	
	private void initForm() {
		_nameText.setText(td.getNombre());
		_nameText.setEnabled(false);
		_billingElection.setValue(((Double)td.getFactura()));
		_floorText.setText(td.getPlanta().toString());
		
		if(td.getActivo()) {
			_reactivate.setEnabled(false);
			_reactivate.setSelected(true);
		}
	}
	
	@Override
	protected void okButtonAction(){
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = _nameText.getText();
				Double fact = (Double)_billingElection.getValue();
				Integer planta = Integer.parseInt(_floorText.getText());
				TDepartamento newTd = new TDepartamento(nombre, fact, td.getEmpleados(), planta);
				newTd.setID(td.getID());
				newTd.setActivo(_reactivate.isSelected());
				Controller.getInstance().action(newTd, Event.MODIFY_DEPARTMENT);
				closeDialog();
			}
		});
	}
}