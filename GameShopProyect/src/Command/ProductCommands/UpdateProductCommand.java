package Command.ProductCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Transfers.TProduct;
import javafx.util.Pair;

public class UpdateProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TProduct tProd = (TProduct) data;
		Boolean ok=SAAbstractFactory.getInstance().createSAProduct().updateProduct(tProd);
		Integer evento = (!ok) ? Event.RES_MODIFY_PRODUCT_FAILED : Event.RES_MODIFY_PRODUCT_OK;
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
