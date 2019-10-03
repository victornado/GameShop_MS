package Presentacion.Product;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Presentacion.View.IGUI;
import Transfers.TAccessory;
import Transfers.TGame;
import Transfers.TPlatform;
import Transfers.TProduct;
import Transfers.TProvider;

import javax.swing.JSpinner;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class FormProduct extends JDialog {
	
	protected JTextField _nameText;
	protected JSpinner _stockInt;
	protected JComboBox<Object> _typeElection;
	protected JComboBox<Object> _providerElection;
	protected JComboBox<Object> _platformElection;
	protected JSpinner _pvpDoub;
	
	private JScrollPane jp;
	protected JTextArea _description;
	protected JTextField _brand;
	protected JTextField _genderText;
	protected JTextField _color;
	
	protected JButton _ok;
	private JButton _cancel;
	protected JButton _next;
	
	public FormProduct() {
		this.setTitle("Register a product");
		this.setIconImage(new ImageIcon("resources/GameShopLogo.png").getImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		this.setLayout(new FlowLayout());
		this.setBounds(new Rectangle(300, 60));
		this.setLocationRelativeTo(null);
		
		this._typeElection = new JComboBox<Object>();
		this._typeElection.setPreferredSize(new Dimension(160,20));
		this._typeElection.setMaximumSize(new Dimension(160,20));
		this._typeElection.setMinimumSize(new Dimension(160,20));
		this._typeElection.addItem(TProduct.accessory);
		this._typeElection.addItem(TProduct.game);
		this.add(this._typeElection);
		
		_next = new JButton("Next");
		_next.setPreferredSize(new Dimension(80,20));
		this.add(_next);
		_next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				remove(_next);
				initComponents(null);
			}
			
		});
		
		this.setVisible(true);
	}
	
	protected void initComponents(String select) {
		int index = this._typeElection.getSelectedIndex();
		String elected;
		if(select == null)
			elected = (String) this._typeElection.getSelectedItem();
		else
			elected = select;
		
		this.setResizable(true);
		if(elected.equals(TProduct.game))
			this.setBounds(new Rectangle(300, 380));
		else
			this.setBounds(new Rectangle(300, 400));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.add(new JLabel("Name:"));
		this._nameText = new JTextField();
		this._nameText.setPreferredSize(new Dimension(220,20));
		this.add(Box.createRigidArea(new Dimension(8, 1)));
		this.add(this._nameText);
		
		this.add(new JLabel("Type:"));
		this._typeElection.setPreferredSize(new Dimension(220,20));
		this._typeElection.setSelectedIndex(index);
		this._typeElection.setEnabled(false);
		this.add(Box.createRigidArea(new Dimension(10, 1)));
		this.add(this._typeElection);
		
		if(elected.equals(TProduct.game)) {
			this.add(new JLabel("Gender:"));
			this._genderText = new JTextField();
			this._genderText.setPreferredSize(new Dimension(220,20));
			this.add(Box.createRigidArea(new Dimension(1, 1)));
			this.add(this._genderText);
		}else {
			this.add(new JLabel("Brand:"));
			this.add(Box.createRigidArea(new Dimension(4,1)));
			this._brand = new JTextField();
			this._brand.setPreferredSize(new Dimension(220,20));
			this.add(this._brand);
			
			this.add(new JLabel("Color:"));
			this.add(Box.createRigidArea(new Dimension(5,1)));
			this._color = new JTextField();
			this._color.setPreferredSize(new Dimension(220,20));
			this.add(this._color);
		}
		this.add(Box.createRigidArea(new Dimension(300,20)));
		
		this.add(new JLabel("Stock:"));
		this._stockInt = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
		this._stockInt.setPreferredSize(new Dimension(60,20));
		this.add(this._stockInt);
		
		this.add(Box.createRigidArea(new Dimension(10,1)));
		
		this.add(new JLabel("Provider:"));
		this._providerElection = new JComboBox<Object>();
		this._providerElection.setPreferredSize(new Dimension(100,20));
		for(Object tpro : SAAbstractFactory.getInstance().createSAProvider().readAllProviders())
			this._providerElection.addItem(((TProvider) tpro).get_id() + " - "+ ((TProvider)tpro).get_nif());
		this.add(this._providerElection);
		
		this.add(new JLabel("PVP:"));
		this._pvpDoub = new JSpinner(new SpinnerNumberModel(0.0,0.0,5000.0,0.5));
		this._pvpDoub.setPreferredSize(new Dimension(70,20));
		this.add(this._pvpDoub);
		
		this.add(Box.createRigidArea(new Dimension(5,1)));
		
		this.add(new JLabel("Platform:"));
		this._platformElection = new JComboBox<Object>();
		this._platformElection.setPreferredSize(new Dimension(100,20));
		for(Object tpla : SAAbstractFactory.getInstance().createSAPlatform().readAllPlatforms())
			this._platformElection.addItem(((TPlatform) tpla).get_id() + " - " + ((TPlatform)tpla).get_name());
		this.add(this._platformElection);
		
		
			this.add(new JLabel("Description:"));
			this.add(Box.createRigidArea(new Dimension(220,1)));
			this._description = new JTextArea();
			this._description.setFont(new Font("Arial", 0, 11));
			this._description.setLineWrap(true);
			this._description.setWrapStyleWord(true);
			this._description.setPreferredSize(new Dimension(270,120));	
			jp = new JScrollPane(this._description);
			jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			this.add(jp);
		
		
		this.add(Box.createRigidArea(new Dimension(300,10)));
		this._ok = new JButton("Ok");
		this._ok.setPreferredSize(new Dimension(60,20));
		this.add(this._ok);
		
		this._cancel = new JButton("Cancel");
		this._cancel.setPreferredSize(new Dimension(80,20));
		this.add(this._cancel);
		
		okButtonAction(elected);
		cancelButtonAction();
		
		this.setVisible(true);
	}
	
	protected void okButtonAction(String elected){
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[] info1 = ((String)_providerElection.getSelectedItem()).split(" - ");
				String[] info2 = ((String)_platformElection.getSelectedItem()).split(" - "); 
				TProduct tprod;
				if(elected.equals(TProduct.game))				
					tprod = new TGame(_nameText.getText(), (Integer)_stockInt.getValue(), (Double) _pvpDoub.getValue(), (Integer)Integer.parseInt(info1[0]),
							(Integer)Integer.parseInt(info2[0]),_description.getText(),_genderText.getText());
				else
					tprod = new TAccessory(_nameText.getText(),(Integer)_stockInt.getValue(),(Double) _pvpDoub.getValue(), (Integer)Integer.parseInt(info1[0]),
							(Integer)Integer.parseInt(info2[0]),_brand.getText(),_color.getText(),
							_description.getText());
				
				Controller.getInstance().action(tprod, Event.REGISTER_PRODUCT);
				setVisible(false);
				dispose();
			}
		});
	}
	
	private void cancelButtonAction(){
		_cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
	
}