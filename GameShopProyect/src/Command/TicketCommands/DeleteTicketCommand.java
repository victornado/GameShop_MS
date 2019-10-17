package Command.TicketCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class DeleteTicketCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		Integer id = (Integer) data;
		boolean resDeleteTi = (SAAbstractFactory.getInstance().createSATicket()).deleteTicket(id);
		Integer evento = (!resDeleteTi) ? Event.RES_UNSUBSCRIBE_TICKET_FAILED : Event.RES_UNSUBSCRIBE_TICKET_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(resDeleteTi, evento);
		return p;
	}

	@Override
	public Command parse(Integer event) {
		if(Event.UNSUBSCRIBE_TICKET==event) {
			return new DeleteTicketCommand();
		}
		else return null;
	}

}