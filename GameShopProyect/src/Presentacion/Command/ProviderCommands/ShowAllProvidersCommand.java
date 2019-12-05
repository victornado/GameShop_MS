package Presentacion.Command.ProviderCommands;

import java.util.List;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowAllProvidersCommand extends Command{
	
	@Override
	public Pair<Object, Integer> execute(Object data) {
		List<Object> providers = (SAAbstractFactory.getInstance().createSAProvider()).readAllProviders();
		Integer evento = (providers == null) ? Event.RES_READALL_PROVIDERS_FAILED : Event.RES_READALL_PROVIDERS_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(providers, evento);
		return p;
	}	
}
