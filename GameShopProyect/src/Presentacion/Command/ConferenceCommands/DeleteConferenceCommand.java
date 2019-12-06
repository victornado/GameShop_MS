package Presentacion.Command.ConferenceCommands;

import Negocio.SA.SAAbstractFactory;

import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;


public class DeleteConferenceCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		int id = (Integer) data;
		boolean resDelete = SAAbstractFactory.getInstance().createSADepartamento().eliminarDepartamento(id);
		Integer evento = (!resDelete) ? Event.RES_UNSUBSCRIBE_CONFERENCE_FAILED : Event.RES_UNSUBSCRIBE_CONFERENCE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(id, evento);
		return p;
	}

}
