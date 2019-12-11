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
import Negocio.Transfers.TComercial;
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
		this._typeElection.setSelectedItem(t.getTipo());
		
		if(t.getTipo().equalsIgnoreCase(Empleado.Comercial)) {
			this.add(_ventas);
			this.add(_numVentas);
			_numVentas.setValue((Integer)((TComercial)t).getnVentas());
			this.add(Box.createRigidArea(new Dimension(90, 1)));
			this.remove(_especialidad);
			this.remove(_specialtyText);
			this.remove(_sobresueldo);
			this.remove(_sobresueldoText);
		}else {
			this.add(Box.createRigidArea(new Dimension(300, 1)));
			this.add(_especialidad);
			this.add(_specialtyText);
			this._specialtyText.setText(((TTecnico)t).getEspecialidad());
			this.add(Box.createRigidArea(new Dimension(10, 1)));
			this.add(_sobresueldo);
			this.add(_sobresueldoText);
			this._sobresueldoText.setValue(((TTecnico)t).getSobresueldo());
			this.add(Box.createRigidArea(new Dimension(100, 1)));
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
		if(t.getDepartamento() != null) {
			Controller.getInstance().action(t.getDepartamento(), Event.READ_DEPARTMENT);
			this._departmentElection.setSelectedItem(_employee.getDepartamento() + " - " + ((TDepartamento)GUIEmployee.getInstance().getOpPanel().getEntityToUse()).getNombre());
		}else{
			this._departmentElection.setSelectedIndex(0);
		}
		this._nameText.setText(t.getNombre());
		this._nifText.setText(t.getNIF());
		this._nifText.setEnabled(false);
		this._salaryElection.setValue(t.getSueldobase());
		this._turnElection.setSelectedItem(t.getTurno());
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
				try {
					String nif = _nifText.getText();
					String nombre = _nameText.getText();
					String turno = (String)_turnElection.getSelectedItem();
					Double salarioBase = (Double)_salaryElection.getValue();
					boolean act = _reactivate.isSelected();
					
					// Para ver si ha seleccionado o no un departamento
					Integer departamento;
					if(((String)_departmentElection.getSelectedItem()).equalsIgnoreCase(FormEmployee.SIN_DEPARTAMENTO)){
						departamento = null;
					}
					else {
						String[] infoDpto = ((String)_departmentElection.getSelectedItem()).split(" - ");
						departamento = Integer.parseInt(infoDpto[0]);
					}
					
					// Guardar datos
					TEmpleado empleado;
					if(((String)_typeElection.getSelectedItem()).equalsIgnoreCase(Empleado.Comercial)) {
						Integer nVentas = (Integer)_numVentas.getValue();
						empleado = new TComercial(nif, nombre, turno, salarioBase, departamento, nVentas, Empleado.Comercial);
					}
					else {
						String especialidad = _specialtyText.getText();
						Double sobresueldo = (Double)_sobresueldoText.getValue();
						empleado = new TTecnico(nif, nombre, turno, salarioBase, departamento, sobresueldo, especialidad, Empleado.Tecnico);
					}			
					empleado.setActivo(act);
					empleado.setID(_employee.getID());
					Controller.getInstance().action(empleado, Event.MODIFY_EMPLOYEE);
					closeDialog();
				} catch(Exception ex) {
					closeDialog();
					Controller.getInstance().action(null, Event.MODIFY_EMPLOYEE);
				}
			}
		});
	}
}