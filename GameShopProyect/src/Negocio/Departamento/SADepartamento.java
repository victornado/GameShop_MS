package Negocio.Departamento;

import Negocio.Transfers.TDepartamento;
import java.util.List;

public interface SADepartamento {
	public Integer registrarDepartamento(TDepartamento data);

	public Boolean eliminarDepartamento(Integer id);

	public Boolean modificarDepartamento(TDepartamento data);

	public Object mostrarDepartamento(Integer id);

	public List<Object> mostrarTodosLosDepartamentos();

	public Double calcularNomina(Integer id);
}