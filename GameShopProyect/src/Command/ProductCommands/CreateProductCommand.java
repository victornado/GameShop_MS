package Command.ProductCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Transfers.TProduct;
import javafx.util.Pair;

public class CreateProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TProduct tprod = (TProduct) data;
		int resRegister = (SAAbstractFactory.getInstance().createSAProduct()).createProduct(tprod);
		Integer evento = (resRegister != -1) ? Event.RES_REGISTER_PRODUCT_OK : Event.RES_REGISTER_PRODUCT_FAILED;
		Pair<Object, Integer> p = new Pair<Object, Integer>(resRegister, evento);
		return p;
	}
}
