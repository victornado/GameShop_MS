package Command.ProductCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Transfers.TProduct;
import javafx.util.Pair;

public class ShowOneProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		int id = (Integer) data;
		TProduct tProd = (TProduct) (SAAbstractFactory.getInstance().createSAProduct()).readProduct(id);
		Integer evento = (tProd == null) ? Event.RES_READ_PRODUCT_FAILED : Event.RES_READ_PRODUCT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tProd, evento);
		return p;
	}

	@Override
	public Command parse(Integer event) {
		if (Event.READ_PRODUCT == event)
			return new ShowOneProductCommand();

		return null;
	}

}
