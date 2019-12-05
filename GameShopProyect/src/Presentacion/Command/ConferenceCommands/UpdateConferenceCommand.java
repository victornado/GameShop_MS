package Presentacion.Command.ConferenceCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TConferencia;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class UpdateConferenceCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TConferencia tc = (TConferencia) data;
		Boolean ok = SAAbstractFactory.getInstance().createSAConferencia().modificarConferencia(tc);
		Integer evento = (!ok) ? Event.RES_MODIFY_CONFERENCE_FAILED : Event.RES_MODIFY_CONFERENCE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tc, evento);
		return p;
	}

}
