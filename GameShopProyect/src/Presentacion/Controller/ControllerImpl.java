package Presentacion.Controller;

import javax.swing.JOptionPane;

import Command.Command;
import Command.CommandFactory;
import Presentacion.View.GUIGameShop;
import Presentacion.View.ViewDispatcher;
import javafx.util.Pair;

/** 
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ControllerImpl extends Controller {
	
	@Override
	public void action(Object data, Integer event) {
		Command command = CommandFactory.getInstance().parse(event);
		Pair<Object, Integer> retExecute = null;
		
		if(command != null) 
			retExecute = command.execute(data);
		
		if(command != null && retExecute != null) 
			ViewDispatcher.getInstance().createView(retExecute);
		else
			JOptionPane.showInputDialog(null, "ERROR 404: Cannot update the view...", "Fatal error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void initGameShop(Integer event) {
		GUIGameShop.getInstance().initGameShop(event);
	}

}