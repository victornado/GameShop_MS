package Negocio.Departamento;

import Negocio.Transfers.TDepartamento;
import java.util.List;

import javax.persistence.EntityManager;

public class SADepartamentoIMP implements SADepartamento {
	

	public EntityManager em;
	public SADepartamentoIMP(EntityManager em)
	{
		this.em=em;
	}
	

	public Integer registrarDepartamento(TDepartamento data) {
		return null;
	}

	public Boolean eliminarDepartamento(Integer id) {
		return null;
	}
	
	public Boolean modificarDepartamento(TDepartamento data) {
		return null;
	}

	public Object mostrarDepartamento(Integer id) {
		return null;
	}

	public List<Object> mostrarTodosLosDepartamentos() {
		return null;
	}

	public Double calcularNomina() {
		return null;
	}
}