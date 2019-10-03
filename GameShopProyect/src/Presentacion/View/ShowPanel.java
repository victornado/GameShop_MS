package Presentacion.View;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Presentacion.Employee.ShowAllEmployees;
import Presentacion.Platform.ShowAllPlatform;
import Presentacion.Platform.ShowProductsFromPlatform;
import Presentacion.Product.ShowAllProducts;
import Presentacion.Provider.ShowAllProvider;
import Presentacion.Ticket.ShowAllTickets;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
@SuppressWarnings("serial")
public class ShowPanel extends JPanel {
	
	private JTabbedPane _tabs;
	private String nameIdentificator;
	private ShowOne so;
	private ShowAll sa;
	private ShowProductsFromPlatform sp;
	
	public ShowPanel(String nameIdentificator) {
		sp = null;
		this.nameIdentificator = nameIdentificator.toLowerCase();
		this.so = new ShowOne(nameIdentificator);
		this.sp = new ShowProductsFromPlatform(nameIdentificator, null);
		switch(this.nameIdentificator) {
		case "provider":
			this.sa = new ShowAllProvider(nameIdentificator, null);
			break;
		case "employee":
			this.sa = new ShowAllEmployees(nameIdentificator, null);
			break;
		case "product":
			this.sa = new ShowAllProducts(nameIdentificator, null);
			break;
		case "platform":
			this.sa = new ShowAllPlatform(nameIdentificator, null);
			break;
		case "ticket":
			this.sa = new ShowAllTickets(nameIdentificator, null);
			break;
		}
		initPanel();
	}

	private void initPanel() {
		this.setPreferredSize(new Dimension(390, 700));
		this.setMinimumSize(new Dimension(390, 700));
		this.setMaximumSize(new Dimension(390, 700));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Data information",
				TitledBorder.LEFT, TitledBorder.TOP));
		
		initTabs();
		
		this.setVisible(true);
	}

	private void initTabs() {
		_tabs = new JTabbedPane(JTabbedPane.TOP);
		_tabs.setPreferredSize(new Dimension(400, 300));
		_tabs.setVisible(true);
		
		// TODO caso para PROVEEDOR
		_tabs.addTab("Show one", null, so, "Show the data of the selected " + nameIdentificator);
		_tabs.addTab("Show all", null, sa, "Show the data of all " + nameIdentificator);
		if(this.nameIdentificator.equalsIgnoreCase(GUIGameshop.TAB_PLATFORM)) 
			//AQUI DEBERIA AÑADIR NUEVA PESTAÑA CON TODAS LOS PRODUCTOS DE UNA PLATAFORMA
			_tabs.addTab("Show products from one", null, (this.sp = new ShowProductsFromPlatform(this.nameIdentificator,null)), 
					"Show all the products related to the selected platform");
		
		_tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Integer event = null;
				if(_tabs.getSelectedIndex() == 1) {
					switch(nameIdentificator) {
					case "provider": event = Event.READ_ALL_PROVIDERS; break;
					case "employee": event = Event.READ_ALL_EMPLOYEES; break;
					case "platform": event = Event.READ_ALL_PLATFORMS; break;
					case "ticket": event = Event.READ_ALL_TICKET; break;
					case "product": event = Event.READ_ALL_PRODUCT; break;
					}
					Controller.getInstance().action(null, event);
				}
			}
		});
		
		this.add(_tabs);		
	}
	
	public void setInfoInScreen(String text) {
		so.set_info(text);
	}
	
	public void update(List<Object> l) {
		sa.update(l);
		so.fillList();
		so.set_info("");
		sp.fillList();
	}
	
	public void updateProductFromPlatform(List<Object> l){
		sp.update(l);
		sp.fillList();
	}
	
}