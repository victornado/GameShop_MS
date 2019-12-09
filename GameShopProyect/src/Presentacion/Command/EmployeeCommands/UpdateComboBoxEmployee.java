package Presentacion.Command.EmployeeCommands;

import java.util.List;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class UpdateComboBoxEmployee extends Command{

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> te = (SAAbstractFactory.getInstance().createSAEmpleado().mostrarTodosLosEmpleados());
		Integer evento = Event.UPDATE_LIST_EMPLOYEE;
		Pair<Object, Integer> p = new Pair<Object, Integer>(te, evento);
		return p;
	}

}
