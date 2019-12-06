package Presentacion.Command.ConferenceCommands;

import Negocio.SA.SAAbstractFactory;

import Negocio.Transfers.TConferencia;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;


public class ShowOneConferenceCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		int id = (Integer) data;
		TConferencia tc = (TConferencia)(SAAbstractFactory.getInstance().createSAConferencia()).mostrarConferencia(id);
		Integer evento = (tc == null) ? Event.RES_READ_CONFERENCE_FAILED : Event.RES_READ_CONFERENCE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tc, evento);
		return p;
	}

}
