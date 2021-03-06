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

import Presentacion.Conferencia.ShowAllConferences;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Presentacion.Departamento.CalcularNominasPanel;
import Presentacion.Departamento.ShowAllDepartments;
import Presentacion.Empleado.ShowAllEmployees;
import Presentacion.Product.ShowAllProducts;
import Presentacion.Provider.ShowAllProvider;
import Presentacion.Ticket.ShowAllTickets;

@SuppressWarnings("serial")
public class ShowPanel extends JPanel {

	private JTabbedPane _tabs;
	private String nameIdentificator;
	private ShowOne so;
	private ShowAll sa;
	private ShowChart sc;
	private CalcularNominasPanel cnp;

	public ShowPanel(String nameIdentificator) {
		this.nameIdentificator = nameIdentificator.toLowerCase();
		this.so = new ShowOne(nameIdentificator);
		switch (this.nameIdentificator) {
		case "provider":
			this.sa = new ShowAllProvider(null);
			break;
		case "product":
			this.sa = new ShowAllProducts(null);
			break;
		case "ticket":
			this.sa = new ShowAllTickets(null);
			break;
		case "conference":
			this.sa = new ShowAllConferences(null);
			break;
		case "department":
			this.sa = new ShowAllDepartments(null);
			this.cnp = new CalcularNominasPanel();
			break;
		case "employee":
			this.sa = new ShowAllEmployees(null);
			break;
		}
		this.sc = new ShowChart(nameIdentificator);
		initPanel();
	}

	private void initPanel() {
		this.setPreferredSize(new Dimension(390, 700));
		this.setMinimumSize(new Dimension(390, 700));
		this.setMaximumSize(new Dimension(390, 700));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
				"Data information", TitledBorder.LEFT, TitledBorder.TOP));

		initTabs();

		this.setVisible(true);
	}

	private void initTabs() {
		_tabs = new JTabbedPane(JTabbedPane.TOP);
		_tabs.setPreferredSize(new Dimension(400, 300));
		_tabs.setVisible(true);

		_tabs.addTab("Show one", null, so, "Show the data of the selected " + nameIdentificator);
		_tabs.addTab("Show all", null, sa, "Show the data of all " + nameIdentificator);

		if (nameIdentificator.equalsIgnoreCase("Provider")) {
			_tabs.addTab("Stats", null, sc, "Show the best " + nameIdentificator + " of the shop");
		} else if (nameIdentificator.equalsIgnoreCase("Ticket") || nameIdentificator.equalsIgnoreCase("Product")) {
			_tabs.addTab("Stats", null, sc, "Show the " + nameIdentificator + " chart");
		} else if (nameIdentificator.equalsIgnoreCase("department")) {
			_tabs.addTab("Nomina", null, cnp, "Muestra la nomina de un departmaneto");
		}

		_tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Integer event = null;
				if (_tabs.getSelectedIndex() == 1) {
					switch (nameIdentificator) {
					case "provider":
						event = Event.READ_ALL_PROVIDERS;
						break;
					case "ticket":
						event = Event.READ_ALL_TICKET;
						break;
					case "product":
						event = Event.READ_ALL_PRODUCT;
						break;
					case "conference":
						event = Event.READ_ALL_CONFERENCE;
						break;
					case "department":
						event = Event.READ_ALL_DEPARTMENT;
						break;
					case "employee":
						event = Event.READ_ALL_EMPLOYEE;
						break;
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
		so.fillList(l);
		if (cnp != null)
			cnp.fillList(l);
		so.set_info("");
	}

	public void updateBestProvider(String info) {
		sc.updateBestProviderInfo(info);
	}

	public void mostrarNomina(String info) {
		cnp.set_info(info);
	}

}