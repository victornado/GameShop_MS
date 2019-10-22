package Command.ProviderCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Transfers.TProvider;
import javafx.util.Pair;

public class ShowOneProviderCommand extends Command{

	@Override
	public Pair<Object, Integer> execute(Object data) {
		int id = (Integer) data;
		TProvider tProv = (TProvider) (SAAbstractFactory.getInstance().createSAProvider()).readProvider(id);
		Integer evento = (tProv == null) ? Event.RES_READ_PROVIDER_FAILED : Event.RES_READ_PROVIDER_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tProv, evento);
		return p;
	}
}
