/**
 * 
 */
package Negocio.Employee;

import java.util.List;

import Transfers.TEmployee;

public interface SAEmployee {
	public Integer createEmployee(TEmployee te);
	public Boolean deleteEmployee(Integer id);
	public Boolean updateEmployee(TEmployee te);
	public Object readEmployee(Integer id);
	public List<Object> readAllEmployees();
}