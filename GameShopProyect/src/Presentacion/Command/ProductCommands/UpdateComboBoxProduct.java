package Presentacion.Command.ProductCommands;

import java.util.List;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class UpdateComboBoxProduct extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> tc = (SAAbstractFactory.getInstance().createSAProduct().readAllProducts());
		Integer evento = Event.UPDATE_LIST_PRODUCT;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tc, evento);
		return p;
	}

}
