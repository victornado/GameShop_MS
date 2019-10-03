package Negocio.Platform;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Negocio.SA.SAAbstractFactory;
import Transfers.TPlatform;


public class TestSAPlatform  {
	
	private SAPlatform sa = SAAbstractFactory.getInstance().createSAPlatform();

	@Test
	public void testCreatePlatformOk() {
		// validez sintactica,nombre no existente
		TPlatform tp = new TPlatform("PS3");
		assertEquals(4,sa.createPlatform(tp).intValue());
	}
	
	@Test
	public void testCreatePlatformFail() {
		// invalidez sintactica
		TPlatform tp = new TPlatform("");	//nombre vacio
		assertNotEquals(5,sa.createPlatform(tp).intValue());
		tp = new TPlatform("PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3");//nombre >45 chars
		assertNotEquals(5,sa.createPlatform(tp).intValue());
		// nombre existe
		tp = new TPlatform("XBOX360");
		assertNotEquals(5,sa.createPlatform(tp).intValue());
		
		assertNotEquals(5,sa.createPlatform(null).intValue());
	}

	@Test
	public void testDeletePlatformOk() {
		//Plataforma existe y esta activa
		assertTrue(sa.deletePlatform(2));	//TPlatform("NINTENDO-SWITCH");
	}
	
	@Test
	public void testDeletePlatformFail() {
		//Plataforma inexistente
		assertFalse(sa.deletePlatform(5));
		assertFalse(sa.deletePlatform(null));
		//Plataforma existe y esta inactiva
		assertFalse(sa.deletePlatform(1));
	}

	@Test
	public void testUpdatePlatformOk() {
		//Plataforma existe,validez sintactica(NOMBRE A CAMBIAR NO EXISTE)
		TPlatform tp = new TPlatform("STEAM");
		tp.set_id(3);
		tp.set_activated(true);
		assertTrue(sa.updatePlatform(tp));
	}
	
	@Test
	public void testUpdatePlatformFail() {
		TPlatform tp = new TPlatform();
		//invalidez sintactica
		assertFalse(sa.updatePlatform(tp));	//campos nulos
		tp.set_name("");
		assertFalse(sa.updatePlatform(tp)); //name empty y campos nulos
		tp.set_name("PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3");
		assertFalse(sa.updatePlatform(tp)); //nombre >45 chars y campos nulos
		tp.set_name("STEAM"); //campos nulos menos nombre
		assertFalse(sa.updatePlatform(tp)); //name empty y campos nulos
		tp.set_activated(true);
		assertFalse(sa.updatePlatform(tp)); //id nulo
		//Plataforma no existe
		tp.set_id(8);
		assertFalse(sa.updatePlatform(tp));
	}
	
	@Test
	public void testReadPlatformOk() {
		//Plataforma existe
		TPlatform tp = new TPlatform("XBOX360");
		tp.set_id(1);
		tp.set_activated(false);
		assertEquals(tp.toString(),((TPlatform)sa.readPlatform(1)).toString());
	}
	
	@Test
	public void testReadPlatformFail() {
		//Plataforma no existe
			TPlatform tp = new TPlatform("XBOX360");
			tp.set_id(8);
			tp.set_activated(false);
			assertNotEquals(tp,((TPlatform)sa.readPlatform(8)));
			assertNotEquals(tp,sa.readPlatform(null));
	}


	@Test
	public void testReadAllProductsOfAPlatformOk() {
		//Plataforma existe y juegos tambien
		assertTrue(sa.readAllProductsOfAPlatform(1)!=null);
	}
	
	@Test
	public void testReadAllProductsOfAPlatformFail() {
		//Plataforma no existe
		assertTrue(sa.readAllProductsOfAPlatform(8) == null);
		//Plataforma no contiene juegos
		assertEquals(sa.readAllProductsOfAPlatform(3),new ArrayList<Object>()); 
	}
	
	
}