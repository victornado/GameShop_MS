package Presentacion.Command.ConferenceCommands;

import java.util.List;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowAllConferenceCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> conferences = (SAAbstractFactory.getInstance().createSAConferencia()).mostrarTodasLasConferencias();
		Integer evento = (conferences == null) ? Event.RES_READALL_CONFERENCE_FAILED : Event.RES_READALL_CONFERENCE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(conferences, evento);
		return p;
	}

}
