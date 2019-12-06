package Presentacion.Command.EmployeeCommands;

import Negocio.SA.SAAbstractFactory;

import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;


public class DeleteEmployeeCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		int id = (Integer) data;
		boolean resDelete = SAAbstractFactory.getInstance().createSAEmpleado().eliminarEmpleado(id);
		Integer evento = (!resDelete) ? Event.RES_UNSUBSCRIBE_EMPLOYEE_FAILED : Event.RES_UNSUBSCRIBE_EMPLOYEE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(id, evento);
		return p;
	}

}
