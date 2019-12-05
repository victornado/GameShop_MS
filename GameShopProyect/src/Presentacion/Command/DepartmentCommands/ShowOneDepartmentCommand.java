package Presentacion.Command.DepartmentCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TDepartamento;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class ShowOneDepartmentCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		int id = (Integer) data;
		TDepartamento td = (TDepartamento)(SAAbstractFactory.getInstance().createSADepartamento()).mostrarDepartamento(id);
		Integer evento = (td == null) ? Event.RES_READ_DEPARTMENT_FAILED : Event.RES_READ_DEPARTMENT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(td, evento);
		return p;
	}

}
