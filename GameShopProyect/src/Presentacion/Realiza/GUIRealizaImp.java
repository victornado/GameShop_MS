package Presentacion.Realiza;

import java.awt.BorderLayout;

import Presentacion.View.GUIGameshopImp;
import utils.Pair;

@SuppressWarnings("serial")
public class GUIRealizaImp extends GUIRealiza {

	@Override
	protected void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		panel = new PanelRealiza();
		this.add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
	}

	@Override
	public void actualiza(Pair<Object, Integer> data) {
	}

}
