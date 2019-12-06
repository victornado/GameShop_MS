package Presentacion.Command.DepartmentCommands;

import java.util.List;

import utils.Pair;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;

public class UpdateComboBox extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> td = (SAAbstractFactory.getInstance().createSADepartamento().mostrarTodosLosDepartamentos());
		Integer evento = Event.UPDATE_LIST_EMPLOYEE;
		Pair<Object, Integer> p = new Pair<Object, Integer>(td, evento);
		return p;
	}
}
