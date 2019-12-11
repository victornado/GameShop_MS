package Presentacion.Command.ConferenceCommands;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class ReadConferenceToForm extends Command{
	
	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		int id = (Integer) data;
		Object res = SAAbstractFactory.getInstance().createSAConferencia().mostrarConferencia(id);
		Integer evento = Event.READ_CONFERENCE_FORM;
		Pair<Object, Integer> p = new Pair<Object, Integer>(res, evento);
		return p;
	}

}
