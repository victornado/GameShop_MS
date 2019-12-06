package Presentacion.Command.TicketCommands;

import Negocio.SA.SAAbstractFactory;

import Negocio.Transfers.TTicket;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

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
