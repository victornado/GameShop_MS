package Presentacion.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Provider.ProviderChart;
import Presentacion.Ticket.TicketChart;
import Transfers.TProvider;

@SuppressWarnings("serial")
public class ShowChart extends JPanel {
	public static final String PIE_CHART = "Pie chart";
	public static final String BAR_CHART = "Bar chart";
	private String _nameIdentificator;
	private JComboBox<Object> _election; // Para elegir el tipo de grafico
	private JComboBox<Object> _providers; // Para elegir el proveedor
	private JButton _show;
	private JPanel _chart;
	private JScrollPane _jsc; // Para almacenar el panel que contiene la grafica
	private JTextField _from;
	private JTextField _to;
	private JLabel _lFrom, _lTo;
	
	public ShowChart(String tabName) { // data ==> Pair<> para ticket y Transfer para provider
		_nameIdentificator = tabName.toLowerCase();
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

	private void initComponents() {	
		this.add(Box.createVerticalGlue());
		if(_nameIdentificator.equalsIgnoreCase("Provider")) {
			_providers = new JComboBox<Object>();
			_providers.setPreferredSize(new Dimension(200, 20));
			_providers.setMinimumSize(new Dimension(200, 20));
			_providers.setMaximumSize(new Dimension(200, 20));
			fillProviderList();
			_providers.setEditable(false);
			_providers.setVisible(true);
			this.add(_providers);
			this.add(Box.createRigidArea(new Dimension(1, 5)));
		}
		else if(_nameIdentificator.equalsIgnoreCase("Ticket")) {
			_lFrom = new JLabel("From: ");
			_lFrom.setAlignmentX(CENTER_ALIGNMENT);
			this.add(_lFrom);
			_from = new JTextField("yyyy-mm-dd [hh:mm:ss]");
			_to =	new JTextField("yyyy-mm-dd [hh:mm:ss]");
			_from.setPreferredSize(new Dimension(150, 20));
			_to.setPreferredSize(new Dimension(150, 20));
			_from.setMinimumSize(new Dimension(150, 20));
			_to.setMinimumSize(new Dimension(150, 20));
			_from.setMaximumSize(new Dimension(150, 20));
			_to.setMaximumSize(new Dimension(150, 20));
			_from.setEditable(true);
			_to.setEditable(true);
			_from.setVisible(true);
			_to.setVisible(true);
			this.add(_from);
			_lTo = new JLabel("To: ");
			_lTo.setAlignmentX(CENTER_ALIGNMENT);
			this.add(_lTo);
			this.add(_to);
			this.add(Box.createRigidArea(new Dimension(1, 5)));
		}
		
		_election = new JComboBox<Object>();
		_election.setPreferredSize(new Dimension(200, 20));
		_election.setMinimumSize(new Dimension(200, 20));
		_election.setMaximumSize(new Dimension(200, 20));
		_election.removeAllItems();
		_election.addItem(PIE_CHART);
		_election.addItem(BAR_CHART);
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
		
		_chart = new JPanel();
		_chart.setBackground(Color.white);
		_chart.setPreferredSize(new Dimension(300, 150));
		_chart.setMinimumSize(new Dimension(300, 150));
		_chart.setMaximumSize(new Dimension(300, 150));
		_jsc = new JScrollPane(_chart);
		_jsc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
				"Chart", TitledBorder.LEFT, TitledBorder.TOP));
		this.add(_jsc);
		_jsc.setVisible(true);
		
		this.add(Box.createVerticalGlue());
		
		addShowButtonAction();
	}
	
	private void fillProviderList() {
		_providers.removeAllItems();
		for(Object tpro : SAAbstractFactory.getInstance().createSAProvider().readAllProviders())
			_providers.addItem(((TProvider) tpro).get_id() + " - " + ((TProvider) tpro).get_nif());
	}

	private void addShowButtonAction() {
		_show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String chartElection = (String)_election.getSelectedItem();
				switch(_nameIdentificator){
				case "provider":
					// Llamar a: _chart = ProviderChart(chartElection, nameidentificator, datos)
					Integer providerSelected = Integer.parseInt(((String)_providers.getSelectedItem()).split(" - ")[0]);
					_chart = new ProviderChart(chartElection, _nameIdentificator, providerSelected).getChart();
					_jsc.revalidate();
					_jsc.repaint();
					break;
				case "ticket":
					//Pair<String, String> datesTicket = new Pair<String, String>(_from.getText(), _to.getText());
					_chart = new TicketChart(chartElection, _nameIdentificator, _from.getText(), _to.getText()).getChart();
					_jsc.revalidate();
					_jsc.repaint();
					break;
				}
			}
		});
	}
}
