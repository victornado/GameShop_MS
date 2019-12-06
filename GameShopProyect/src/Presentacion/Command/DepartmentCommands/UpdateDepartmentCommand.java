package Presentacion.Command.DepartmentCommands;

import Negocio.SA.SAAbstractFactory;

import Negocio.Transfers.TDepartamento;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;


public class UpdateDepartmentCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TDepartamento td = (TDepartamento) data;
		Boolean ok = SAAbstractFactory.getInstance().createSADepartamento().modificarDepartamento(td);
		Integer evento = (!ok) ? Event.RES_MODIFY_CONFERENCE_FAILED : Event.RES_MODIFY_CONFERENCE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(td, evento);
		return p;
	}

}
