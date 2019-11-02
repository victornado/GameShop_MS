package Presentacion.Ticket;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.data.general.DefaultPieDataset;

import Presentacion.View.ShowChart;
import javafx.util.Pair;

@SuppressWarnings("serial")
public class TicketChart extends JPanel {
	private String _chartType;
	private String _tab;
	private Pair<String, String> _dates;
	private List<Object[]> _solQuery; // SATicket > getBestProduct() > _solQuery = executeQuery();
	
	@SuppressWarnings("unchecked")
	public TicketChart(String chartType, String tab, String from, String to) {
		_chartType = chartType;
		_tab = tab;
		_dates = new Pair<String, String>(from, to);
		initGUI();
	}
	
	private void initGUI() {
		this.setPreferredSize(new Dimension(300, 150));
		this.setMinimumSize(new Dimension(300, 150));
		this.setMaximumSize(new Dimension(300, 150));
		
		createChart();
		
		this.setVisible(true);
	}
	
	private void createChart() {
		switch(_chartType) {
		case ShowChart.PIE_CHART: createPieChart();
			break;
		case ShowChart.BAR_CHART: createBarChart();
			break;
		}
	}
	
	public JPanel getChart() {
		return this;
	}
	
	private void createPieChart() {
		// Fuente de Datos
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        
	}
	
	private void createBarChart() {
		
	}
}
