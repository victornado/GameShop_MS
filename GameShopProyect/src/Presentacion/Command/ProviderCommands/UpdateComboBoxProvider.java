package Presentacion.Command.ProviderCommands;

import java.util.List;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class UpdateComboBoxProvider extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> tc = (SAAbstractFactory.getInstance().createSAProvider().readAllProviders());
		Integer evento = Event.UPDATE_LIST_PROVIDER;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tc, evento);
		return p;
	}

}
