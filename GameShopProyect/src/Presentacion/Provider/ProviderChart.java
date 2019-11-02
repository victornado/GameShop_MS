package Presentacion.Provider;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

@SuppressWarnings("serial")
public class ProviderChart extends JPanel {
	
	private String _chartType;
	private String _tab;
	private Integer _providerId;
	
	public ProviderChart(String chartType, String tab, Object data) {
		_chartType = chartType.toLowerCase();
		_tab = tab;
		_providerId = (Integer)data;
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
		// Fuente de Datos
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset(); 
        defaultpiedataset.setValue("Programacion", new Double(41.200000000000003D)); 
        defaultpiedataset.setValue("Electronica", new Double(11D)); 
        defaultpiedataset.setValue("Hacking", new Double(19.5D)); 
        defaultpiedataset.setValue("SEO", new Double(30.5D)); 
        defaultpiedataset.setValue("Redes", new Double(2.0D)); 
 
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart3D("Tematicas Blog", defaultpiedataset, true, true, false); 
        PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
        pieplot3d.setDepthFactor(0.5); 
        pieplot3d.setStartAngle(290D); 
        pieplot3d.setDirection(Rotation.CLOCKWISE); 
        pieplot3d.setForegroundAlpha(0.5F); 
        
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        this.add(chartPanel);
	}
	
	public JPanel getChart() {
		return this;
	}
}
