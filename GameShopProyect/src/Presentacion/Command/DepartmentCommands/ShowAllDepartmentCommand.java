package Presentacion.Command.DepartmentCommands;

import java.util.List;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowAllDepartmentCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		List<Object> departments = (SAAbstractFactory.getInstance().createSADepartamento()).mostrarTodosLosDepartamentos();
		Integer evento = (departments == null) ? Event.RES_READALL_DEPARTMENT_FAILED : Event.RES_READALL_DEPARTMENT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(departments, evento);
		return p;
	}

}
