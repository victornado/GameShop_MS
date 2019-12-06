package Negocio.Empleado;

import Negocio.Transfers.TEmpleado;
import java.util.List;

import javax.persistence.EntityManager;

public class SAEmpleadoIMP implements SAEmpleado {
	

	public EntityManager em;
	public SAEmpleadoIMP(EntityManager em)
	{
		this.em=em;
	}
	
	
	public Integer registrarEmpleado(TEmpleado data) {
		return null;
	}

	public Boolean eliminarEmpleado(Integer id) {
		return null;
	}

	public Boolean modificarEmpleado(TEmpleado data) {
		return null;
	}

	public Object mostrarEmpleado(Integer id) {
		return null;
	}

	public List<Object> mostrarTodosLosEmpleados() {
		return null;
	}
}