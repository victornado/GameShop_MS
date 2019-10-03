/**
 * 
 */
package Negocio.Employee;

import java.util.List;

import org.junit.jupiter.api.Test;

import Negocio.SA.SAAbstractFactory;
import Transfers.TEmployee;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSAEmployee {

	private SAEmployee sa = SAAbstractFactory.getInstance().createSAEmployee();
	
	@Test
	public void testCreateEmployeeOk() {
		//Empleado no existente(nif),validez sintactica
		TEmployee te = new TEmployee("PACO","27684587Q","Early shift");
		assertEquals(4, sa.createEmployee(te).intValue());
	}
	
	@Test
	public void testCreateEmployeeFail() {
		//Empleado existe
		TEmployee te = new TEmployee("PEDRO","76664094B","Late shift");
		assertNotEquals(5,sa.createEmployee(te).intValue());
		//Invalidez sintactica
		te = new TEmployee("","29178317B","Late shift");
		assertNotEquals(5,sa.createEmployee(te).intValue());
		te = new TEmployee("PEDRO",null,"Late shift");
		assertNotEquals(5,sa.createEmployee(te).intValue());
		te = new TEmployee("PEDRO","29178317B","Late shift late shift");
		assertNotEquals(5,sa.createEmployee(te).intValue());
		assertNotEquals(5,sa.createEmployee(null).intValue());
	}
	
	@Test
	public void testDeleteEmployeeOk() {
		//Empleado existe y esta activo
		assertTrue(sa.deleteEmployee(1));
	}	
	
	@Test
	public void testDeleteEmployeeFail() {
		//Empleado no existe
		assertFalse(sa.deleteEmployee(7));
		//Empleado existe y esta inactivo
		assertFalse(sa.deleteEmployee(2));
		
		assertFalse(sa.deleteEmployee(null));
	}

	@Test
	public void testUpdateEmployeeOk() {
		//Empleado existe y validez sintactica
		TEmployee te = new TEmployee("joaquin","76664094B","Late shift");
		te.set_activated(true);
		te.set_id(1);
		assertTrue(sa.updateEmployee(te));
	}
	
	@Test
	public void testUpdateEmployeeFail() {
		TEmployee te = new TEmployee("joaquin","76664094B","Late shift");
		te.set_id(9);
		te.set_activated(true);
		//Empleado no existe
		assertFalse(sa.updateEmployee(null));
		assertFalse(sa.updateEmployee(te));
		
		//Invalidez sintactica,empleado existe
		te.set_activated(null);
		assertFalse(sa.updateEmployee(te));
		te.set_activated(true);
		te.set_id(1);
		te.set_name("");																					
		assertFalse(sa.updateEmployee(te));
		te.set_name("Antonio");
		te.setTurn("");
		assertFalse(sa.updateEmployee(te));
	}

	@Test
	public void testReadEmployeeOk() {
		//Empleado existe
		TEmployee te = new TEmployee("pablo","74769725Z","Early shift");
		te.set_id(3);
		te.set_activated(true);
		assertEquals(te.toString(),(sa.readEmployee(te.get_id()).toString()));
	}
	
	@Test
	public void testReadEmployeeFail() {
		//Empleado no existe
		TEmployee te = new TEmployee("pablo","74769725Z","Early shift");
		te.set_id(8);
		te.set_activated(true);
		assertNotEquals(te,sa.readEmployee(te.get_id()));
		assertNotEquals(te,sa.readEmployee(null));
	}
}