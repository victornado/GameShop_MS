package Command.ProductCommands;

import Command.Command;
import Presentacion.Controller.Event;
import Transfers.TProduct;
import javafx.util.Pair;

public class UpdateProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TProduct tProd = (TProduct) data;
		Integer evento = (tProd == null) ? Event.RES_MODIFY_PRODUCT_FAILED : Event.RES_MODIFY_PRODUCT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tProd, evento);
		return p;
	}

	@Override
	public Command parse(Integer event) {
		if (Event.MODIFY_PRODUCT == event)
			return new UpdateProductCommand();

		return null;
	}

}
