/**
 * 
 */
package Presentacion.Employee;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Transfers.TEmployee;

import javax.swing.JComboBox;
import javax.swing.JDialog;

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
public class FormEmployee extends JDialog {
	
	private final String availableTurns[] = {"Early shift" , "Late shift"};
	//IMPORTANTE: El turno es un string , no un int
	private final JLabel _nif = new JLabel("NIF:");
	private final JLabel _name = new JLabel("Name:");
	private final JLabel _turn = new JLabel("Round:");
	protected JTextField _nifText;
	protected JTextField _nameText;
	protected JComboBox<String> _turnElection;
	protected JButton _accept;
	private JButton _cancel;
	
	public FormEmployee(){
		this.setTitle("Register a employee");
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
		this.setBounds(new Rectangle(300, 120));
		this.setLocationRelativeTo(null);
		
		initComponents();
		okButtonAction();
		cancelButtonAction();
		
		this.setVisible(true);
	}
	
	protected void okButtonAction(){
		this._accept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				/*TEmployee te = new TEmployee();//(_nifText.getText(), _addressText.getText(), Integer.parseInt(_phoneText.getText()));
				te.set_name(_nameText.getText());
				te.set_nif(_nifText.getText());
				te.setTurn((String)_turnElection.getSelectedItem());
				Controller.getInstance().action(te, Event.REGISTER_EMPLOYEE);
				closeDialog();*/
				try {
					String name = _nameText.getText();
					String nif = _nifText.getText();
					String turn = (String)_turnElection.getSelectedItem();
					TEmployee te = new TEmployee(name, nif, turn);
					closeDialog();
					Controller.getInstance().action(te, Event.REGISTER_EMPLOYEE);
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
		//_nif = new JLabel("NIF:");
		_nifText = new JTextField();
		_nifText.setPreferredSize(new Dimension(220,20));
		_nifText.setMaximumSize(new Dimension(220,20));
		_nifText.setMinimumSize(new Dimension(220,20));
		
		//_address = new JLabel("Address:");
		_nameText = new JTextField();
		_nameText.setPreferredSize(new Dimension(220,20));
		_nameText.setMaximumSize(new Dimension(220,20));
		_nameText.setMinimumSize(new Dimension(220,20));
		
		//_phone = new JLabel("Phone:");
		_turnElection = new JComboBox<String>();
		for(int i = 0; i < availableTurns.length; ++i)
			_turnElection.addItem(availableTurns[i]);
		_turnElection.setSize(new Dimension(150, 50));
		_turnElection.setBounds(new Rectangle(50, 150));
		_turnElection.setMinimumSize(new Dimension(150, 50));
		_turnElection.setMaximumSize(new Dimension(150, 50));
		_turnElection.setEditable(false);
		_turnElection.setVisible(true);
		
		_accept = new JButton("OK");
		_accept.setPreferredSize(new Dimension(70,20));
		_accept.setMaximumSize(new Dimension(70,20));
		_accept.setMinimumSize(new Dimension(70,20));
		
		_cancel = new JButton("Cancel");
		_cancel.setPreferredSize(new Dimension(70,20));
		_cancel.setMaximumSize(new Dimension(70,20));
		_cancel.setMinimumSize(new Dimension(70,20));
		
		this.add(_nif);
		this.add(Box.createRigidArea(new Dimension(7, 1)));
		this.add(_nifText);
		this.add(_name);
		this.add(_nameText);
		this.add(_turn);
		this.add(Box.createRigidArea(new Dimension(5, 1)));
		this.add(_turnElection);
		this.add(_accept);
		this.add(_cancel);
	}
}