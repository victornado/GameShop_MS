package Presentacion.Command.EmployeeCommands;

import java.util.List;


import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;


public class ShowAllEmployeeCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> empleados = (SAAbstractFactory.getInstance().createSAEmpleado()).mostrarTodosLosEmpleados();
		Integer evento = (empleados == null) ? Event.RES_READALL_EMPLOYEE_FAILED : Event.RES_READALL_EMPLOYEE_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(empleados, evento);
		return p;
	}

}
