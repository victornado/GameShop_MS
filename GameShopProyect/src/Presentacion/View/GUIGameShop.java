package Presentacion.View;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class GUIGameShop extends JFrame {
	private static GUIGameShop _instance;
	
	public static GUIGameShop getInstance() {
		if(_instance == null)
			_instance = new GUIGameshopImp();
		return _instance;
	}
	
	public abstract void initGameShop(Integer event);
}
