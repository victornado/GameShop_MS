package Presentacion.Command.ConferenceCommands;

import java.util.List;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class UpdateComboBoxConference extends Command{
	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> tc = (SAAbstractFactory.getInstance().createSAConferencia().mostrarTodasLasConferencias());
		Integer evento = Event.UPDATE_LIST_CONFERENCE;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tc, evento);
		return p;
	}

}
