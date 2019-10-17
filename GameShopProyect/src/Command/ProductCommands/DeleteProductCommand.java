package Command.ProductCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class DeleteProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		int id = (Integer) data;
		boolean resDelete = (SAAbstractFactory.getInstance().createSAProduct()).deleteProduct(id);
		Integer evento = (!resDelete) ? Event.RES_UNSUBSCRIBE_PRODUCT_FAILED : Event.RES_UNSUBSCRIBE_PRODUCT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(resDelete, evento);
		return p;
	}

	@Override
	public Command parse(Integer event) {
		if (Event.UNSUBSCRIBE_PRODUCT == event)
			return new DeleteProductCommand();

		return null;
	}

}
