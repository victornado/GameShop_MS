package Presentacion.Command.RealizaCommand;

import Negocio.Realiza.RealizaEmbeddable;
import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TRealiza;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class RealizaModificarCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		TRealiza datos = (TRealiza)data;
		RealizaEmbeddable re = SAAbstractFactory.getInstance().createSARealiza().updateRealiza(datos);
		Integer event = re != null ? Event.REALIZA_MODIFICAR_OK : Event.REALIZA_MODIFICAR_FAILED;
		Pair<Object, Integer> ret = new Pair<Object, Integer>(re, event);
		return ret;
	}

}
