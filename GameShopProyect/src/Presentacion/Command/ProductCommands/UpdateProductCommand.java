package Presentacion.Command.ProductCommands;

import Negocio.SA.SAAbstractFactory;

import Negocio.Transfers.TProduct;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class UpdateProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TProduct tProd = (TProduct) data;
		Boolean ok=SAAbstractFactory.getInstance().createSAProduct().updateProduct(tProd);
		Integer evento = (!ok) ? Event.RES_MODIFY_PRODUCT_FAILED : Event.RES_MODIFY_PRODUCT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tProd, evento);
		return p;
	}
}
