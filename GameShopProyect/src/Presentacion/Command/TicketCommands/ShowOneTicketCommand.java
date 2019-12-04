package Presentacion.Command.TicketCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TProductQuantity;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowOneTicketCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		Integer id = (Integer) data;
		TProductQuantity ticket = (TProductQuantity)(SAAbstractFactory.getInstance().createSATicket().TOAReadTicket(id));
		Integer evento = (ticket == null) ? Event.RES_READ_TICKET_FAILED : Event.RES_READ_TICKET_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(ticket, evento);
		return p;
	}
}
