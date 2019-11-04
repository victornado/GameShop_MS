package Presentacion.Ticket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import Negocio.SA.SAAbstractFactory;
import Presentacion.View.ShowChart;
import javafx.util.Pair;

@SuppressWarnings("serial")
public class TicketChart extends JDialog {
	private String _chartType;
	private Pair<String, String> _dates;
	private DefaultPieDataset defaultpiedataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private PiePlot3D pieplot3d;
	private DefaultCategoryDataset dataset;
	private CategoryPlot p;
	private boolean _showThis;
	
	public TicketChart(String chartType, String from, String to) {
		_chartType = chartType;
		_dates = new Pair<String, String>(from, to);
		_showThis = true;
		initGUI();
	}
	
	private void initGUI() {
		this.setPreferredSize(new Dimension(700, 470));
		this.setMinimumSize(new Dimension(700, 470));
		this.setMaximumSize(new Dimension(700, 470));
		this.setTitle("Product chart");
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
		this.setLocationRelativeTo(null);

		createChart();

		if(_showThis) this.setVisible(true);
	}
	
	private void createChart() {
		if (_chartType.equalsIgnoreCase(ShowChart.PIE_CHART))
			createPieChart();
		else
			createBarChart();
	}
	
	private void createPieChart() {
		ArrayList<Object[]> data = (ArrayList<Object[]>) SAAbstractFactory.getInstance()
				.createSATicket().getBestProduct(_dates.getKey(), _dates.getValue());
		if (data != null) {
			defaultpiedataset = new DefaultPieDataset();
			chart = ChartFactory.createPieChart3D("Get product selled in a date range", defaultpiedataset, true, true, false);
			pieplot3d = (PiePlot3D) chart.getPlot();
			int i = 0;
			while (i < data.size()) {
				defaultpiedataset.setValue((String)data.get(i)[0], (Integer)data.get(i)[1]);
				++i;
			}
			pieplot3d.setDepthFactor(0.5);
			pieplot3d.setStartAngle(290D);
			pieplot3d.setDirection(Rotation.CLOCKWISE);
			pieplot3d.setForegroundAlpha(0.5F);

			chartPanel = new ChartPanel(chart);
			this.add(chartPanel);
		} else {
			_showThis = false;
		}
	}
	
	private void createBarChart() {
		ArrayList<Object[]> data = (ArrayList<Object[]>) SAAbstractFactory.getInstance()
				.createSATicket().getBestProduct(_dates.getKey(), _dates.getValue());
		if (data != null) {
			dataset = new DefaultCategoryDataset();
			int i = 0;
			while (i < data.size()) {
				dataset.setValue((Integer)data.get(i)[1], (Integer)data.get(i)[1], (String)data.get(i)[0]);
				++i;
			}
			chart = ChartFactory.createBarChart3D("Get product selled in a date range", "Name", "Count", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			chart.setBackgroundPaint(Color.white);
			chart.getTitle().setPaint(Color.black);
			p = chart.getCategoryPlot(); 
	        p.setRangeGridlinePaint(Color.red);
	        chartPanel = new ChartPanel(chart);
			this.add(chartPanel);
	        
		} else {
			_showThis = false;
		}
	}
}
