package Presentacion.Command.RealizaCommand;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TRealiza;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class RealizaDesasignarCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		TRealiza datos = (TRealiza)data;
		Boolean re = SAAbstractFactory.getInstance().createSARealiza().deleteRealiza(datos.getIdEmp());
		Integer event = re == true  ? Event.REALIZA_DESASIGNAR_OK : Event.REALIZA_DESASIGNAR_FAILED;
		Pair<Object, Integer> ret = new Pair<Object, Integer>(re, event);
		return ret;
	}

}
