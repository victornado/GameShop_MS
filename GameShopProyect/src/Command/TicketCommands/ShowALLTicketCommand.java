package Command.TicketCommands;

import java.util.List;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowALLTicketCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		List<Object> tickets = (SAAbstractFactory.getInstance().createSATicket().readAllTickets());
		Integer evento = (tickets.isEmpty()) ? Event.RES_READALL_TICKET_FAILED : Event.RES_READALL_TICKET_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tickets, evento);
		return p;
	}

	@Override
	public Command parse(Integer event) {
		if(Event.READ_ALL_TICKET==event) {
			return new ShowALLTicketCommand();
		}
		else return null;
	}

}
