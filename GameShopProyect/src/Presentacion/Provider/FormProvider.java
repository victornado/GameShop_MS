package Presentacion.Provider;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Transfers.TProvider;

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
import javax.swing.JDialog;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
@SuppressWarnings("serial")
public class FormProvider extends JDialog {

	private final JLabel _nif = new JLabel("NIF:");
	private final JLabel _address = new JLabel("Address:");
	private final JLabel _phone = new JLabel("Phone:");
	protected JTextField _nifText;
	protected JTextField _addressText;
	protected JTextField _phoneText;
	protected JButton _ok;
	private JButton _cancel;
	
	public FormProvider(){
		this.setTitle("Register a provider");
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
				try {
					Integer phone = Integer.parseInt(_phoneText.getText());
					String nif = _nifText.getText();
					String address = _addressText.getText();
					TProvider tp = new TProvider(nif, address, phone);
					Controller.getInstance().action(tp, Event.REGISTER_PROVIDER);
					closeDialog();
				} catch(Exception ex) {
					closeDialog();
					Controller.getInstance().action(null, Event.REGISTER_PROVIDER);
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
		_addressText = new JTextField();
		_addressText.setPreferredSize(new Dimension(220,20));
		_addressText.setMaximumSize(new Dimension(220,20));
		_addressText.setMinimumSize(new Dimension(220,20));
		
		//_phone = new JLabel("Phone:");
		_phoneText = new JTextField();
		_phoneText.setPreferredSize(new Dimension(220,20));
		_phoneText.setMaximumSize(new Dimension(220,20));
		_phoneText.setMinimumSize(new Dimension(220,20));
		
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
		this.add(_address);
		this.add(_addressText);
		this.add(_phone);
		this.add(Box.createRigidArea(new Dimension(5, 1)));
		this.add(_phoneText);
		this.add(_ok);
		this.add(_cancel);
	}
	
}