package Presentacion.Empleado;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Empleado.Empleado;
import Negocio.Transfers.TDepartamento;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TProvider;
import Negocio.Transfers.TTecnico;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormUpdateEmployee extends FormEmployee {
	
	private JCheckBox _reactivate;
	private TEmpleado _employee;
	
	public FormUpdateEmployee(TEmpleado t) {
		super();
		_employee = t;
		this.setTitle("Modify employee");
		
		if(t.getTipo().equalsIgnoreCase(Empleado.Comercial)) {
			this.add(_ventas);
			this.add(_numVentas);
			this.add(Box.createRigidArea(new Dimension(90, 1)));
		}else {
			this.add(Box.createRigidArea(new Dimension(300, 1)));
			this.add(_especialidad);
			this.add(_specialtyText);
			this.add(Box.createRigidArea(new Dimension(10, 1)));
			this.add(_sobresueldo);
			this.add(_sobresueldoText);
			this.add(Box.createRigidArea(new Dimension(100, 1)));
			TTecnico.SOBRESUELDO = (Double)_sobresueldoText.getValue();
		}

		this.add(_ok);
		this.add(_cancel);
		
		this._reactivate = new JCheckBox("Activated");
		this._reactivate.setBounds(50, 150, 140, 50);
		if(this._employee.getActivo()) {
			this._reactivate.setEnabled(false);
			this._reactivate.setSelected(true);
		}
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
		
		this.add(this._reactivate);
		this.setVisible(true);
	}
	
	@Override
	protected void initForm() {
		_typeElection.setPreferredSize(new Dimension(160,20));
		_typeElection.setMaximumSize(new Dimension(160,20));
		_typeElection.setMinimumSize(new Dimension(160,20));
		_typeElection.addItem(Empleado.Tecnico);
		_typeElection.addItem(Empleado.Comercial);
		Controller.getInstance().action(null, Event.UPDATE_LIST_DEPARTMENT);
		List<Object> l = GUIEmployee.getInstance().getOpPanel().getElectionForm();
		if(l != null) {
		for(Object o : l ) {
			if(((TDepartamento)o).getActivo())
				this._departmentElection.addItem(((TDepartamento)o).getID() + " - " + ((TDepartamento)o).getNombre());
			}
		}
		this.add(new JLabel("Type:"));
		this.add(_typeElection);
		
		super.initComponents();
		
		okButtonAction();
		super.cancelButtonAction();
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