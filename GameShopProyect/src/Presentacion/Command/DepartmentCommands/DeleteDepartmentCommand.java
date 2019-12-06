package Presentacion.Command.DepartmentCommands;

import Negocio.SA.SAAbstractFactory;

import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;


public class DeleteDepartmentCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		int id = (Integer) data;
		boolean resDelete = SAAbstractFactory.getInstance().createSADepartamento().eliminarDepartamento(id);
		Integer evento = (!resDelete) ? Event.RES_UNSUBSCRIBE_DEPARTMENT_FAILED : Event.RES_UNSUBSCRIBE_DEPARTMENT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(id, evento);
		return p;
	}

}
