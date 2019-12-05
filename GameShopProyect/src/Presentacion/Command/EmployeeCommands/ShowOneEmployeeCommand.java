package Presentacion.Command.EmployeeCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TEmpleado;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowOneEmployeeCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		int id = (Integer) data;
		TEmpleado tc = (TEmpleado)(SAAbstractFactory.getInstance().createSAEmpleado()).mostrarEmpleado(id);
		Integer evento = (tc == null) ? Event.RES_READ_EMPLOYEE_FAILED : Event.RES_READ_EMPLOYEE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(tc, evento);
		return p;
	}

}
