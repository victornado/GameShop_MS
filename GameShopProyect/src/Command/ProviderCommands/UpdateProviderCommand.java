package Command.ProviderCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Transfers.TProvider;
import javafx.util.Pair;

public class UpdateProviderCommand extends Command{

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TProvider tProv = (TProvider) data;
		Boolean ok=SAAbstractFactory.getInstance().createSAProvider().updateProvider(tProv);
		Integer evento = (!ok) ? Event.RES_MODIFY_PROVIDER_FAILED : Event.RES_MODIFY_PROVIDER_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tProv, evento);
		return p;
	}
}
