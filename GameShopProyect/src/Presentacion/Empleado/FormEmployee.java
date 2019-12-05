package Presentacion.Empleado;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Negocio.Transfers.TComercial;
import Negocio.Transfers.TDepartamento;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TTecnico;
import Presentacion.Command.Command;
import Presentacion.Command.CommandFactory;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import javafx.util.Pair;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	// COMERCIAL
	private final JLabel _ventas = new JLabel("Sales:");
	protected JSpinner _numVentas = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
	// TECNICO
	private final JLabel _especialidad = new JLabel("Specialty:");
	protected JTextField _specialtyText;
	private final JLabel _sobresueldo = new JLabel("Sobresueldo:");
	protected JSpinner _sobresueldoText = new JSpinner(new SpinnerNumberModel(1000.0, 0.0, 10000.0, 100.0));
	
	public FormEmployee() {
		_turnElection.addItem("Morning");
		_turnElection.addItem("Afternoon");
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
		this.setBounds(new Rectangle(300, 60));
		this.setLocationRelativeTo(null);
		
		_typeElection.setPreferredSize(new Dimension(160,20));
		_typeElection.setMaximumSize(new Dimension(160,20));
		_typeElection.setMinimumSize(new Dimension(160,20));
		_typeElection.addItem(TEmpleado.Tecnico);
		_typeElection.addItem(TEmpleado.Comercial);
		
		_next = new JButton("Next");
		_next.setPreferredSize(new Dimension(80,20));
		this.add(new JLabel("Type"));
		this.add(_typeElection);
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
		
		//initComponents();
		//okButtonAction();
		//cancelButtonAction();
		addInfoToDepartmentElection();
		
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
					TEmpleado empleado;
					if(((String)_typeElection.getSelectedItem()).equalsIgnoreCase(TEmpleado.Comercial)) {
						Integer nVentas = (Integer)_numVentas.getValue();
						empleado = new TComercial(nif, nombre, turno, salarioBase, nVentas);
					}
					else {
						String especialidad = _specialtyText.getText();
						Double sobresueldo = (Double)_sobresueldoText.getValue();
						empleado = new TTecnico(nif, nombre, turno, salarioBase, sobresueldo, especialidad);
					}
					Controller.getInstance().action(empleado, Event.REGISTER_EMPLOYEE);
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
	
	private void initComponents() {
		this.setLayout(new FlowLayout());
		this.setBounds(new Rectangle(280, 220));
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
		//this.add(Box.createRigidArea(new Dimension(16, 1)));
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
		if(selected.equalsIgnoreCase(TEmpleado.Comercial)) {
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
	
	@SuppressWarnings("unchecked")
	private void addInfoToDepartmentElection() {
		try {
			Command command = CommandFactory.getInstance().parse(Event.READ_ALL_DEPARTMENT);
			Pair<Object, Integer> data = command.execute(null);
			List<Object> departments = (List<Object>)data.getKey();
			if(departments != null) {
				_departmentElection.setEditable(true);
				_departmentElection.setToolTipText("Available departments");
				for(Object o : departments)
					_departmentElection.addItem(((TDepartamento)o).getNombre());
			}
			else{
				_departmentElection.setToolTipText("Not available");
				_departmentElection.setEditable(false);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error when showing the departments.", "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}
}