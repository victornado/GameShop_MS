package Presentacion.Command.ProductCommands;

import java.util.ArrayList;


import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;

import utils.Pair;

public class ProductCountQueryCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		ArrayList<Pair<String, Integer>> queryData = (ArrayList<Pair<String, Integer>>) SAAbstractFactory.getInstance()
				.createSAProduct().getProductsCount();
		Integer event = (queryData != null) ? Event.SHOW_PRODUCT_QUERY_OK : Event.SHOW_PRODUCT_QUERY_FAILED;
		return new Pair<Object, Integer>(queryData, event);
	}

}
