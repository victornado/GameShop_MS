package Command.ProviderCommands;

import java.util.List;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class BestProviderQueryCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> queryData = SAAbstractFactory.getInstance().createSAProvider().getBestProvider();
		StringBuilder ret = new StringBuilder("Product name: ");
		Integer event = (queryData != null) ? Event.SHOW_PROVIDER_QUERY_OK : Event.SHOW_PROVIDER_QUERY_FAILED;
		if(event == Event.SHOW_PROVIDER_QUERY_OK) {
			int i = 0;
			while(i < queryData.size()) {
				ret.append(queryData.get(i));
				if(i == 0) ret.append("\nNIF: ");
				else if(i == 1) ret.append("\nCount: ");
				++i;
			}
			ret.append("\n");
			return new Pair<Object, Integer>(ret.toString(), event);
		}
		else
			return new Pair<Object, Integer>("Error showing the selected query.", event);
	}

}
