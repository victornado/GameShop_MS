package Presentacion.Command.DepartmentCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TDepartamento;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import javafx.util.Pair;

public class CreateDepartmentCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) {
		TDepartamento td = (TDepartamento)data;
		int resRegister = SAAbstractFactory.getInstance().createSADepartamento().registrarDepartamento(td);
		Integer event = resRegister < 0 ? Event.RES_REGISTER_DEPARTMENT_FAILED : Event.RES_REGISTER_DEPARTMENT_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(resRegister, event);
		return p;
	}

}
