package Presentacion.Command.TicketCommands;

import java.util.List;


import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class ShowALLTicketCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		List<Object> tickets = (SAAbstractFactory.getInstance().createSATicket().readAllTickets());
		Integer evento = (tickets == null) ? Event.RES_READALL_TICKET_FAILED : Event.RES_READALL_TICKET_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tickets, evento);
		return p;
	}
}
