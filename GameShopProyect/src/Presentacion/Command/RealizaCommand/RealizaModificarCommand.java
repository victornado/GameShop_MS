package Presentacion.Command.RealizaCommand;

import java.util.List;

import Negocio.Realiza.RealizaEmbeddable;
import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TRealiza;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class RealizaModificarCommand extends Command {

	@SuppressWarnings("unchecked")
	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<TRealiza> emps = (List<TRealiza>)data;
		RealizaEmbeddable re = null;
		for(int i = 0; i < emps.size(); ++i)
			re = SAAbstractFactory.getInstance().createSARealiza().updateRealiza(emps.get(i));
		Integer event = re != null ? Event.REALIZA_MODIFICAR_OK : Event.REALIZA_MODIFICAR_FAILED;
		Pair<Object, Integer> ret = new Pair<Object, Integer>(re, event);
		return ret;
	}

}
