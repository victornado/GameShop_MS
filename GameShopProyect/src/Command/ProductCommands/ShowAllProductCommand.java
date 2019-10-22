package Command.ProductCommands;

import java.util.List;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowAllProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		List<Object> products = (SAAbstractFactory.getInstance().createSAProduct()).readAllProducts();
		Integer evento = (products.isEmpty()) ? Event.RES_READALL_PRODUCT_FAILED : Event.RES_READALL_PRODUCT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(products, evento);
		return p;
	}
}
