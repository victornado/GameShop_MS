package Presentacion.Command.DepartmentCommands;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TDepartamento;
import Presentacion.Command.Command;
import Presentacion.Controller.Event;
import utils.Pair;

public class CalcularNominaCommand extends Command {

	@Override
	public Pair<Object, Integer> execute(Object data) throws Exception {
		Integer idDep = (Integer)data;
		Double nomina = SAAbstractFactory.getInstance().createSADepartamento().calcularNomina(idDep);
		Integer event = Event.CALCULAR_NOMINA_DEPARTAMENTO_OK;//nomina <= 0.0 ? Event.CALCULAR_NOMINA_DEPARTAMENTO_FAILED : Event.CALCULAR_NOMINA_DEPARTAMENTO_OK;
		Pair<Object, Integer> p = new Pair<Object, Integer>(nomina, event);
		return p;
	}

}
