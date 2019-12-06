package Presentacion.Command.EmployeeCommands;

import Negocio.SA.SAAbstractFactory;

import Negocio.Transfers.TEmpleado;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;


public class CreateEmployeeCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		TEmpleado te = (TEmpleado)data;
		int resRegister = SAAbstractFactory.getInstance().createSAEmpleado().registrarEmpleado(te);
		Integer event = resRegister < 0 ? Event.RES_REGISTER_EMPLOYEE_FAILED : Event.RES_REGISTER_EMPLOYEE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(resRegister, event);
		return p;
	}

}
