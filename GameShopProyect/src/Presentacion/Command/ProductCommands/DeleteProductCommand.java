package Presentacion.Command.ProductCommands;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class DeleteProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		int id = (Integer) data;
		boolean resDelete = (SAAbstractFactory.getInstance().createSAProduct()).deleteProduct(id);
		Integer evento = (!resDelete) ? Event.RES_UNSUBSCRIBE_PRODUCT_FAILED : Event.RES_UNSUBSCRIBE_PRODUCT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(id, evento);
		return p;
	}
}
