package Presentacion.View;

import utils.Pair;

public abstract class ViewDispatcher {
	private static ViewDispatcher _instance;
	
	public static ViewDispatcher getInstance() {
		if(_instance == null)
			_instance = new ViewDispatcherImp();
		return _instance;
	}	
	
	public abstract void createView(Pair<Object, Integer> data);
}
