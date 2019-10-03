package Presentacion.View;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Transfers.TEmployee;
import Transfers.TPlatform;
import Transfers.TProduct;
import Transfers.TProvider;
import Transfers.TTicket;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
@SuppressWarnings("serial")
public class ShowOne extends JPanel {
	
	private JComboBox<Object> _election;
	private JButton _show;
	private JTextArea _info;
	private String nameIdentificator;
	
	public ShowOne(String nameIdentificator) {
		this.nameIdentificator = nameIdentificator.toLowerCase();
		initGUI();
	}

	private void initGUI() {
		this.setPreferredSize(new Dimension(400, 300));
		this.setMinimumSize(new Dimension(400, 300));
		this.setMaximumSize(new Dimension(400, 300));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		initComponents();
		
		this.setVisible(true);
	}
	
	private void addShowButtonAction() {
		_show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(_election.getItemCount() > 0){
					Integer id = Integer.parseInt(_election.getSelectedItem().toString().split(" - ")[0]);
					switch(nameIdentificator){
					case "provider":
						Controller.getInstance().action(id,	Event.READ_PROVIDER);
						break;
					case "platform":
						Controller.getInstance().action(id, Event.READ_PLATFORM);
						break;
					case "employee":
						Controller.getInstance().action(id, Event.READ_EMPLOYEE);
						break;
					case "product":
						Controller.getInstance().action(id, Event.READ_PRODUCT);
						break;
					case "ticket":
						Controller.getInstance().action(id, Event.READ_TICKET);
						break;
					}
				}
			}
		});
	}
	

	private void initComponents() {
		this.add(Box.createVerticalGlue());
		
		_election = new JComboBox<Object>();
		_election.setPreferredSize(new Dimension(200, 20));
		_election.setMinimumSize(new Dimension(200, 20));
		_election.setMaximumSize(new Dimension(200, 20));
		_election.setEditable(false);
		_election.setVisible(true);
		this.add(_election);
		
		this.add(Box.createRigidArea(new Dimension(1, 10)));
		
		_show = new JButton("Show");
		_show.setAlignmentX(CENTER_ALIGNMENT);
		_show.setSize(new Dimension(100, 30));
		_show.setMinimumSize(new Dimension(100, 30));
		_show.setMaximumSize(new Dimension(100, 30));
		this.add(_show);
		_show.setVisible(true);
		
		this.add(Box.createRigidArea(new Dimension(1, 30)));
		
		_info = new JTextArea();
		_info.setWrapStyleWord(true);
		_info.setLineWrap(true);
		_info.setPreferredSize(new Dimension(300, 190));
		_info.setMinimumSize(new Dimension(300, 190));
		_info.setMaximumSize(new Dimension(300, 190));
		_info.setEditable(false);
		_info.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Detailed data",
				TitledBorder.LEFT, TitledBorder.TOP));
		_info.setFont(new Font("Arial", 0, 11));
		_info.setVisible(true);
		this.add(new JScrollPane(_info, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		this.add(Box.createVerticalGlue());
		
		addShowButtonAction();
		fillList();
	}

	public void fillList() {
		_election.removeAllItems();
		switch(nameIdentificator){
		case "provider":
			for(Object tpro : SAAbstractFactory.getInstance().createSAProvider().readAllProviders())
				_election.addItem(((TProvider) tpro).get_id() + " - " + ((TProvider) tpro).get_nif());
			break;
		case "platform":
			for(Object tpla : SAAbstractFactory.getInstance().createSAPlatform().readAllPlatforms())
				_election.addItem(((TPlatform) tpla).get_id().toString() + " - " + ((TPlatform) tpla).get_name());
			break;
		case "employee":
			for(Object temp : SAAbstractFactory.getInstance().createSAEmployee().readAllEmployees())
				_election.addItem(((TEmployee) temp).get_id() + " - " + ((TEmployee) temp).get_name());
			break;
		case "product":
			for(Object temp : SAAbstractFactory.getInstance().createSAProduct().readAllProducts())
				_election.addItem(((TProduct) temp).get_id() + " - " + ((TProduct)temp).get_type()+" - "+((TProduct)temp).get_name());
			break;
		case "ticket":
			for(Object tt : SAAbstractFactory.getInstance().createSATicket().readAllTickets())
				_election.addItem(((TTicket)tt).get_id() + " - " + ((TTicket)tt).get_date());
			break;
		}
	}
	
	public void update(List<Object> l) {
		fillList();
	}

	public void set_info(String text) {
		_info.setText(text);
	}
}