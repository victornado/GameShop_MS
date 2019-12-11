package Presentacion.View;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public abstract class ShowAll extends JPanel {

	protected JTable _grid;

	public ShowAll() {
		initGUI();
	}

	private void initGUI() {
		this.setPreferredSize(new Dimension(400, 300));
		this.setMinimumSize(new Dimension(400, 300));
		this.setMaximumSize(new Dimension(400, 300));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.setVisible(true);
	}

	public abstract void update(List<Object> l);

}