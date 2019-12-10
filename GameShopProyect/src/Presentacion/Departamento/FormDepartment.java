package Presentacion.Departamento;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Negocio.Transfers.TDepartamento;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

import javax.swing.JLabel;
import javax.swing.JSpinner;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class FormDepartment extends JDialog {
	
	private final JLabel _name = new JLabel("Name:");
	private final JLabel _billing = new JLabel("Billing:");
	private final JLabel _employees = new JLabel("Employees:");
	private final JLabel _floor = new JLabel("Floor:");
	protected JTextField _nameText;
	protected JSpinner _billingElection = new JSpinner(new SpinnerNumberModel(10000.0, 0.0, 1000000.0, 1000.0));
	protected JTextField _employeesText;
	protected JTextField _floorText;
	protected JButton _ok;
	private JButton _cancel;
	
	public FormDepartment(){
		this.setTitle("Register a department");
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
		this.setBounds(new Rectangle(300, 165));
		this.setLocationRelativeTo(null);
		
		initComponents();
		okButtonAction();
		cancelButtonAction();
		
		this.setVisible(true);
	}
	
	protected void okButtonAction(){
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = _nameText.getText();
					Double billing = (Double)_billingElection.getValue();
					Integer employees = Integer.parseInt(_employeesText.getText());
					Integer floor = Integer.parseInt(_floorText.getText());
					TDepartamento td = new TDepartamento(name, billing, employees, floor);
					Controller.getInstance().action(td, Event.REGISTER_DEPARTMENT);
					closeDialog();
				}catch(Exception ex) {
					closeDialog();
					Controller.getInstance().action(null, Event.REGISTER_DEPARTMENT);
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
		_nameText = new JTextField();
		_nameText.setPreferredSize(new Dimension(220,20));
		_nameText.setMaximumSize(new Dimension(220,20));
		_nameText.setMinimumSize(new Dimension(220,20));
		
		_billingElection.setPreferredSize(new Dimension(70,20));
		_billingElection.setMaximumSize(new Dimension(70,20));
		_billingElection.setMinimumSize(new Dimension(70,20));
		
		_employeesText = new JTextField();
		_employeesText.setPreferredSize(new Dimension(180,20));
		_employeesText.setMaximumSize(new Dimension(180,20));
		_employeesText.setMinimumSize(new Dimension(180,20));
		
		_floorText = new JTextField();
		_floorText.setPreferredSize(new Dimension(220,20));
		_floorText.setMaximumSize(new Dimension(220,20));
		_floorText.setMinimumSize(new Dimension(220,20));
		
		_ok = new JButton("OK");
		_ok.setPreferredSize(new Dimension(70,20));
		_ok.setMaximumSize(new Dimension(70,20));
		_ok.setMinimumSize(new Dimension(70,20));
		
		_cancel = new JButton("Cancel");
		_cancel.setPreferredSize(new Dimension(90,20));
		_cancel.setMaximumSize(new Dimension(90,20));
		_cancel.setMinimumSize(new Dimension(90,20));
		
		this.add(_name);
		this.add(_nameText);
		this.add(_billing);
		this.add(_billingElection);
		this.add(Box.createRigidArea(new Dimension(110, 1)));
		this.add(_employees);
		this.add(Box.createRigidArea(new Dimension(5, 1)));
		this.add(_employeesText);
		this.add(_floor);
		this.add(_floorText);
		this.add(_ok);
		this.add(_cancel);
	}
}