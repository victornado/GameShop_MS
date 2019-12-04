package Presentacion.Command.ProductCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TProduct;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
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
}
