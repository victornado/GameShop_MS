package Presentacion.Empleado;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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
	protected JTextField _nifText;
	protected JTextField _nameText;
	protected JComboBox<Object> _turnElection = new JComboBox<Object>();
	protected JSpinner _salaryElection = new JSpinner(new SpinnerNumberModel(1000, 0, 10000, 100));
	protected JButton _ok;
	private JButton _cancel;
	
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
		this.setBounds(new Rectangle(300, 140));
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
		_nifText = new JTextField();
		_nifText.setPreferredSize(new Dimension(220,20));
		_nifText.setMaximumSize(new Dimension(220,20));
		_nifText.setMinimumSize(new Dimension(220,20));
		
		_nameText = new JTextField();
		_nameText.setPreferredSize(new Dimension(220,20));
		_nameText.setMaximumSize(new Dimension(220,20));
		_nameText.setMinimumSize(new Dimension(220,20));
		
		_ok = new JButton("OK");
		_ok.setPreferredSize(new Dimension(70,20));
		_ok.setMaximumSize(new Dimension(70,20));
		_ok.setMinimumSize(new Dimension(70,20));
		
		_cancel = new JButton("Cancel");
		_cancel.setPreferredSize(new Dimension(90,20));
		_cancel.setMaximumSize(new Dimension(90,20));
		_cancel.setMinimumSize(new Dimension(90,20));
		
		this.add(_nif);
		this.add(Box.createRigidArea(new Dimension(16, 1)));
		this.add(_nifText);
		this.add(_name);
		this.add(_nameText);
		this.add(_turn);
		this.add(Box.createRigidArea(new Dimension(5, 1)));
		this.add(_turnElection);
		this.add(_salary);
		this.add(_salaryElection);
		this.add(_ok);
		this.add(_cancel);
	}
}