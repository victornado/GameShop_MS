package Presentacion.Ticket;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import Negocio.SA.SAAbstractFactory;
import javafx.util.Pair;

@SuppressWarnings("serial")
public class TicketChart extends JDialog {
	private Pair<String, String> _dates;
	private DefaultPieDataset defaultpiedataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private PiePlot3D pieplot3d;
	private boolean _showThis;
	
	public TicketChart(String from, String to) {
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
		ArrayList<Object[]> data = (ArrayList<Object[]>) SAAbstractFactory.getInstance()
				.createSATicket().getBestProduct(_dates.getKey(), _dates.getValue());
		if (data != null) {
			defaultpiedataset = new DefaultPieDataset();
			chart = ChartFactory.createPieChart3D("Get product selled in a date range", defaultpiedataset, true, true, false);
			pieplot3d = (PiePlot3D) chart.getPlot();
			HashMap<String, Integer> setData = new HashMap<String, Integer>();
			for(int i = 0; i < data.size(); ++i) {
				if(setData.containsKey((String)data.get(i)[0])) {
					Integer antes = setData.get((String)data.get(i)[0]) + (Integer)data.get(i)[1];
					setData.remove((String)data.get(i)[0]);
					setData.put((String)data.get(i)[0], antes);
				}
				else
					setData.put((String)data.get(i)[0], (Integer)data.get(i)[1]);
			}
			for(Map.Entry<String, Integer> entry : setData.entrySet()) {
				defaultpiedataset.setValue(entry.getKey(), entry.getValue());
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
}
