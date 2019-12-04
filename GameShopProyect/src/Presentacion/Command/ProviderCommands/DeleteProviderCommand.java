package Presentacion.Command.ProviderCommands;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class DeleteProviderCommand extends Command{
	@Override
	public Pair<Object, Integer> execute(Object data) {
		int id = (Integer) data;
		boolean resDelete = (SAAbstractFactory.getInstance().createSAProvider()).deleteProvider(id);
		Integer evento = (!resDelete) ? Event.RES_UNSUBSCRIBE_PROVIDER_FAILED : Event.RES_UNSUBSCRIBE_PROVIDER_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(id, evento);
		return p;
	}
}
