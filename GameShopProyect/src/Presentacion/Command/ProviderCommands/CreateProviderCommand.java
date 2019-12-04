package Presentacion.Command.ProviderCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TProvider;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class CreateProviderCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {

		TProvider tprov = (TProvider) data;
		int resRegister = (SAAbstractFactory.getInstance().createSAProvider()).createProvider(tprov);
		Integer evento = (resRegister != -1) ? Event.RES_REGISTER_PROVIDER_OK : Event.RES_REGISTER_PROVIDER_FAILED;
		Pair<Object, Integer> p = new Pair<Object, Integer>(resRegister, evento);
		return p;
	}

}