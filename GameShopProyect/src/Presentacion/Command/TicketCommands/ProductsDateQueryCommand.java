package Presentacion.Command.TicketCommands;

import java.util.ArrayList;


import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class ProductsDateQueryCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		@SuppressWarnings("unchecked")
		Pair<String, String> dataParam = (Pair<String, String>)data;
		ArrayList<Object[]> queryData = (ArrayList<Object[]>) SAAbstractFactory.getInstance()
				.createSATicket().getBestProduct(dataParam.getKey(), dataParam.getValue());
		Integer event = (queryData != null) ? Event.SHOW_TICKET_QUERY_OK : Event.SHOW_TICKET_QUERY_FAILED;
		return new Pair<Object, Integer>(queryData, event);
	}

}
