package Presentacion.Command.TicketCommands;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class DeleteTicketCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		Integer id = (Integer) data;
		boolean resDeleteTi = (SAAbstractFactory.getInstance().createSATicket()).deleteTicket(id);
		Integer evento = (!resDeleteTi) ? Event.RES_UNSUBSCRIBE_TICKET_FAILED : Event.RES_UNSUBSCRIBE_TICKET_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(id, evento);
		return p;
	}
}
