package Command.TicketCommands;

import Command.Command;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Transfers.TTicket;
import javafx.util.Pair;

public class CreateTicketCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TTicket tti = (TTicket)data;
		int resRegisterTicket = (SAAbstractFactory.getInstance().createSATicket().createTicket(tti));
		Integer evento = (resRegisterTicket != -1) ? Event.RES_REGISTER_TICKET_OK : Event.RES_REGISTER_TICKET_FAILED;
		Pair<Object, Integer> p = new Pair<Object, Integer>(resRegisterTicket, evento);
		return p;
	}
}
