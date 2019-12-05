package Negocio.Empleado;

import Negocio.Transfers.TEmpleado;
import java.util.List;

public interface SAEmpleado {
	public Integer registrarEmpleado(TEmpleado data);
	public Boolean eliminarEmpleado(Integer id);
	public Boolean modificarEmpleado(TEmpleado data);
	public Object mostrarEmpleado(Integer id);
	public List<Object> mostrarTodosLosEmpleados();
}