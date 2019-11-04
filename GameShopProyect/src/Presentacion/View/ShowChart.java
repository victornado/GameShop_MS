package Presentacion.View;

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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Product.ProductChart;
import Presentacion.Ticket.TicketChart;

@SuppressWarnings("serial")
public class ShowChart extends JPanel {
	public static final String PIE_CHART = "Pie chart";
	public static final String BAR_CHART = "Bar chart";
	private String _nameIdentificator;
	private JComboBox<Object> _election; // Para elegir el tipo de grafico
	private JComboBox<Object> _products; // Para elegir el producto
	private JButton _show;
	private JScrollPane _jsc; // Para almacenar el panel que contiene la grafica
	private JTextField _from;
	private JTextField _to;
	private JLabel _lFrom, _lTo;
	private JTextArea _info;

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
		if (_nameIdentificator.equalsIgnoreCase("Ticket")) {
			_lFrom = new JLabel("From: ");
			_lFrom.setAlignmentX(CENTER_ALIGNMENT);
			this.add(_lFrom);
			_from = new JTextField("yyyy-mm-dd [hh:mm:ss]");
			_to = new JTextField("yyyy-mm-dd [hh:mm:ss]");
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
		else if (_nameIdentificator.equalsIgnoreCase("Provider")) {
			this.add(new JLabel("Show the best provider of the shop:"));
			_info = new JTextArea();
			_info.setWrapStyleWord(true);
			_info.setLineWrap(true);
			_info.setPreferredSize(new Dimension(150, 150));
			_info.setMinimumSize(new Dimension(150, 150));
			_info.setMaximumSize(new Dimension(150, 150));
			_info.setEditable(false);
			_info.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
					"Best provider data", TitledBorder.LEFT, TitledBorder.TOP));
			_info.setFont(new Font("Arial", 0, 11));
			_info.setVisible(true);
			this.add(new JScrollPane(_info, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		}

		if (!_nameIdentificator.equalsIgnoreCase("Provider")) {
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
		}

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
	}

	private void addShowButtonAction() {
		_show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String chartElection;
				switch(_nameIdentificator){
				case "product":
					chartElection = (String)_election.getSelectedItem();
					new ProductChart(chartElection);
					break;
				case "ticket":
					chartElection = (String)_election.getSelectedItem();
					new TicketChart(chartElection, _from.getText(), _to.getText());
					break;
				case "provider": _info.setText(setBestProviderInfo());
					break;
				}
			}
		});
	}

	private String setBestProviderInfo(){
		List<Object> data = SAAbstractFactory.getInstance().createSAProvider().getBestProvider();
		StringBuilder ret = new StringBuilder("Product name: ");
		if(data != null) {
			int i = 0;
			while(i < data.size()) {
				ret.append(data.get(i));
				if(i == 0) ret.append("\nNIF: ");
				else if(i == 1) ret.append("\nCount: ");
				++i;
			}
			ret.append("\n");
			return ret.toString();
		}
		else return "Oooops... something was wrong...";
	}
}
