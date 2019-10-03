
package Presentacion.Platform;

import javax.swing.JPanel;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Presentacion.Product.ShowAllProducts;
import Transfers.TEmployee;
import Transfers.TPlatform;
import Transfers.TProvider;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Negocio.SA.SAAbstractFactory;

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


@SuppressWarnings("serial")
public class ShowProductsFromPlatform extends ShowAllProducts {

	private JComboBox<Object> _election;
	private JButton _show;
	
	public ShowProductsFromPlatform(String nameIdentificator,List<Object> l) {
		super(nameIdentificator,l);
		initGUI();
	}

	private void initGUI() {
	
		initComponents();
		this.setVisible(true);
	}
	
	private void addShowButtonAction() {
		_show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(_election.getItemCount() > 0){
					Integer id = Integer.parseInt(_election.getSelectedItem().toString().split(" - ")[0]);
					TPlatform tpla = 
							SAAbstractFactory.getInstance().createSAPlatform()
							.readPlatform(id);
					Controller.getInstance().action(tpla.get_id(), Event.READ_ALL_PRODUCTS_FROM_PLATFORM);
				}
			}
		});
	}
	
	private void initComponents() {
		
		this.add(Box.createVerticalGlue());
		
		this.add(Box.createRigidArea(new Dimension(1, 10)));
		
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
		
		this.add(Box.createVerticalGlue());
		
		addShowButtonAction();
		fillList();
	}
	
	public void fillList() {
		_election.removeAllItems();
		for(Object tpla : SAAbstractFactory.getInstance().createSAPlatform().readAllPlatforms())
			_election.addItem(((TPlatform) tpla).get_id().toString() + " - " + ((TPlatform) tpla).get_name());
	}

	
}