package Presentacion.Controller;

import javax.swing.JOptionPane;


import Presentacion.Command.Command;
import Presentacion.Command.CommandFactory;
import Presentacion.View.ViewDispatcher;
import utils.Pair;

public class ControllerImpl extends Controller {
	
	@Override
	public void action(Object data, Integer event) {
		Command command = CommandFactory.getInstance().parse(event);
		Pair<Object, Integer> retExecute = null;
		
		if(command != null) {
			try {
				retExecute = command.execute(data);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR 404: Cannot set the option...", "Fatal error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(command != null && retExecute != null) 
			ViewDispatcher.getInstance().createView(retExecute);
		else if(command == null)
			JOptionPane.showMessageDialog(null, "ERROR 404: null command...", "Fatal error", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "ERROR 404: Cannot update the view...", "Fatal error", JOptionPane.ERROR_MESSAGE);
	}


}