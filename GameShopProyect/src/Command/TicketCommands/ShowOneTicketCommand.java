package Command.TicketCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Transfers.TTicket;
import javafx.util.Pair;

public class ShowOneTicketCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		Integer id = (Integer) data;
		TTicket ticket = (TTicket)(SAAbstractFactory.getInstance().createSATicket().TOAReadTicket(id));
		Integer evento = (ticket == null) ? Event.RES_READ_TICKET_FAILED : Event.RES_READ_TICKET_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(ticket, evento);
		return p;
	}
}
