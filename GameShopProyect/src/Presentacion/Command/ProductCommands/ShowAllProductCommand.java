package Presentacion.Command.ProductCommands;

import java.util.List;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowAllProductCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		List<Object> products = (SAAbstractFactory.getInstance().createSAProduct()).readAllProducts();
		Integer evento = (products == null) ? Event.RES_READALL_PRODUCT_FAILED : Event.RES_READALL_PRODUCT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(products, evento);
		return p;
	}
}
