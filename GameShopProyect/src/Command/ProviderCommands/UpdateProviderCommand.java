package Command.ProviderCommands;

import Command.Command;
import Command.ProductCommands.UpdateProductCommand;
import Presentacion.Controller.Event;
import Transfers.TProvider;
import javafx.util.Pair;

public class UpdateProviderCommand extends Command{

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TProvider tProv = (TProvider) data;
		Integer evento = (tProv == null) ? Event.RES_MODIFY_PROVIDER_FAILED : Event.RES_MODIFY_PROVIDER_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tProv, evento);
		return p;
	}

	@Override
	public Command parse(Integer event) {
		if (Event.MODIFY_PROVIDER == event)
			return new UpdateProviderCommand();

		return null;
	}
}
