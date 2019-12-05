package Presentacion.Command.ConferenceCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TConferencia;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class CreateConferenceCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TConferencia tc = (TConferencia)data;
		int resRegister = SAAbstractFactory.getInstance().createSAConferencia().registrarConferencia(tc);
		Integer event = resRegister < 0 ? Event.RES_REGISTER_CONFERENCE_FAILED : Event.RES_REGISTER_CONFERENCE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(resRegister, event);
		return p;
	}

}
