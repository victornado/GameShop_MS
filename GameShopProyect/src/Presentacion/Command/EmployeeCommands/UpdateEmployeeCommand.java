package Presentacion.Command.EmployeeCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TEmpleado;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class UpdateEmployeeCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		TEmpleado tc = (TEmpleado) data;
		Boolean ok = SAAbstractFactory.getInstance().createSAEmpleado().modificarEmpleado(tc);
		Integer evento = (!ok) ? Event.RES_MODIFY_EMPLOYEE_FAILED : Event.RES_MODIFY_EMPLOYEE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tc, evento);
		return p;
	}

}
