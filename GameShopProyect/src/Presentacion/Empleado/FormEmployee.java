package Presentacion.Empleado;

import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Negocio.Departamento.Departamento;
import Negocio.Empleado.Empleado;
import Negocio.Transfers.TComercial;
import Negocio.Transfers.TDepartamento;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TTecnico;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FormEmployee extends JDialog {
	
	private final JLabel _nif = new JLabel("NIF:");
	private final JLabel _name = new JLabel("Name:");
	private final JLabel _turn = new JLabel("Turn:");
	private final JLabel _salary = new JLabel("Salary:");
	private final JLabel _department = new JLabel("Department:");
	protected JTextField _nifText;
	protected JTextField _nameText;
	protected JComboBox<Object> _turnElection = new JComboBox<Object>();
	protected JComboBox<Object> _typeElection = new JComboBox<Object>();
	protected JComboBox<Object> _departmentElection = new JComboBox<Object>();
	protected JSpinner _salaryElection = new JSpinner(new SpinnerNumberModel(1000.0, 0.0, 10000.0, 100.0));
	protected JButton _ok;
	private JButton _cancel;
	private JButton _next;
	
	public static final String SIN_DEPARTAMENTO = "Sin departamento";
	
	// COMERCIAL
	private final JLabel _ventas = new JLabel("Sales:");
	protected JSpinner _numVentas = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
	// TECNICO
	private final JLabel _especialidad = new JLabel("Specialty:");
	protected JTextField _specialtyText;
	private final JLabel _sobresueldo = new JLabel("Sobresueldo:");
	protected JSpinner _sobresueldoText = new JSpinner(new SpinnerNumberModel(1000.0, 0.0, 10000.0, 100.0));
	
	public FormEmployee() {

		this.setTitle("Register an employee");
		this.setIconImage(new ImageIcon("resources/GameShopLogo.png").getImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				closeDialog();
			}
		});
		
		this.setLayout(new FlowLayout());
		this.setBounds(new Rectangle(300, 100));
		this.setLocationRelativeTo(null);
		
		this._turnElection.setPreferredSize(new Dimension(160,20));
		this._turnElection.setMaximumSize(new Dimension(160,20));
		this._turnElection.setMinimumSize(new Dimension(160,20));
		this._turnElection.addItem("Morning");
		this._turnElection.addItem("Afternoon");
		
		_typeElection.setPreferredSize(new Dimension(160,20));
		_typeElection.setMaximumSize(new Dimension(160,20));
		_typeElection.setMinimumSize(new Dimension(160,20));
		_typeElection.addItem(Empleado.Tecnico);
		_typeElection.addItem(Empleado.Comercial);
		
		this._departmentElection.setPreferredSize(new Dimension(160,20));
		this._departmentElection.setMaximumSize(new Dimension(160,20));
		this._departmentElection.setMinimumSize(new Dimension(160,20));
		this._departmentElection.addItem(FormEmployee.SIN_DEPARTAMENTO);
		Controller.getInstance().action(null, Event.UPDATE_LIST_DEPARTMENT);
		List<Object> l = GUIEmployee.getInstance().getOpPanel().getElectionForm();
		if(l != null) {
		for(Object o : l ) {
			if(((TDepartamento)o).getActivo())
				this._departmentElection.addItem(((TDepartamento)o).getID() + " - " + ((TDepartamento)o).getNombre());
			}
		}
		_next = new JButton("Next");
		_next.setPreferredSize(new Dimension(80,20));
		this.add(new JLabel("Type:"));
		//this.add(this._typeElection);
		this.add(_typeElection);
		//this.add(this._departmentElection);
		this.add(_next);
		_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				remove(_next);
				initComponents();
				okButtonAction();
				cancelButtonAction();
			}
		});
		
		
		this.setVisible(true);
	}
	
	protected void okButtonAction(){
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nif = _nifText.getText();
					String nombre = _nameText.getText();
					String turno = (String)_turnElection.getSelectedItem();
					Double salarioBase = (Double)_salaryElection.getValue();
					
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
					Controller.getInstance().action(empleado, Event.REGISTER_EMPLOYEE);
					closeDialog();
				} catch(Exception ex) {
					closeDialog();
					Controller.getInstance().action(null, Event.REGISTER_EMPLOYEE);
				}
			}
		});
	}
	
	private void cancelButtonAction(){
		_cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
	}
	
	protected void closeDialog() {
		setVisible(false);
		dispose();
	}
	public void updateComboBox(List<Object> l){
		this._departmentElection.removeAllItems();
		for(Object i : l)
			this._departmentElection.addItem(((TDepartamento) i).getNombre());
	}
	
	private void initComponents() {
		this.setLayout(new FlowLayout());
		this.setBounds(new Rectangle(280, 300));
		this.setLocationRelativeTo(null);
		
		String selected = (String)_typeElection.getSelectedItem();
		
		_nifText = new JTextField();
		_nifText.setPreferredSize(new Dimension(150,20));
		_nifText.setMaximumSize(new Dimension(150,20));
		_nifText.setMinimumSize(new Dimension(150,20));
		
		_nameText = new JTextField();
		_nameText.setPreferredSize(new Dimension(150,20));
		_nameText.setMaximumSize(new Dimension(150,20));
		_nameText.setMinimumSize(new Dimension(150,20));
		
		_specialtyText = new JTextField();
		_specialtyText.setPreferredSize(new Dimension(150,20));
		_specialtyText.setMaximumSize(new Dimension(150,20));
		_specialtyText.setMinimumSize(new Dimension(150,20));
		
		_ok = new JButton("OK");
		_ok.setPreferredSize(new Dimension(70,20));
		_ok.setMaximumSize(new Dimension(70,20));
		_ok.setMinimumSize(new Dimension(70,20));
		
		_cancel = new JButton("Cancel");
		_cancel.setPreferredSize(new Dimension(90,20));
		_cancel.setMaximumSize(new Dimension(90,20));
		_cancel.setMinimumSize(new Dimension(90,20));
		
		_typeElection.setEnabled(false);
		this.add(Box.createRigidArea(new Dimension(50, 1)));
		this.add(_nif);
		this.add(_nifText);
		this.add(Box.createRigidArea(new Dimension(30, 1)));
		this.add(_name);
		this.add(_nameText);
		this.add(Box.createRigidArea(new Dimension(30, 1)));
		this.add(_turn);
		this.add(Box.createRigidArea(new Dimension(5, 1)));
		this.add(_turnElection);
		this.add(_salary);
		this.add(_salaryElection);
		this.add(_department);
		this.add(_departmentElection);
		this.add(Box.createRigidArea(new Dimension(100, 1)));
		if(selected.equalsIgnoreCase(Empleado.Comercial)) {
			this.add(_ventas);
			this.add(_numVentas);
		}
		else {
			this.add(_especialidad);
			this.add(_specialtyText);
			this.add(_sobresueldo);
			this.add(_sobresueldoText);
			this.add(Box.createRigidArea(new Dimension(100, 1)));
			TTecnico.SOBRESUELDO = (Double)_sobresueldoText.getValue();
		}
		this.add(_ok);
		this.add(_cancel);
		
		this.setVisible(true);
	}

}