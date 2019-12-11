package Presentacion.Command.DepartmentCommands;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class ReadDepartmentToForm extends Command {
	
	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		int id = (Integer) data;
		Object res = SAAbstractFactory.getInstance().createSADepartamento().mostrarDepartamento(id);
		Integer evento = Event.READ_DEPARTMENT_FORM;
		Pair<Object, Integer> p = new Pair<Object, Integer>(res, evento);
		return p;
	}

}
