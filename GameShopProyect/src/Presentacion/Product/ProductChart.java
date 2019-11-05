package Presentacion.Product;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

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
public class ProductChart extends JDialog {
	private DefaultPieDataset defaultpiedataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private PiePlot3D pieplot3d;
	private boolean _showThis;

	public ProductChart() {
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
		ArrayList<Pair<String, Integer>> data = (ArrayList<Pair<String, Integer>>) SAAbstractFactory.getInstance()
				.createSAProduct().getProductsCount();
		if (data != null) {
			defaultpiedataset = new DefaultPieDataset();
			chart = ChartFactory.createPieChart3D("Get product types count", defaultpiedataset, true, true, false);
			pieplot3d = (PiePlot3D) chart.getPlot();
			int i = 0;
			while (i < data.size()) {
				defaultpiedataset.setValue(data.get(i).getKey(), data.get(i).getValue());
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
}
