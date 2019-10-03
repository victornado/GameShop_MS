package Negocio.Ticket;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Ticket.DAOTicket;
import Negocio.SA.SAAbstractFactory;
import Transfers.TGame;
import Transfers.TPlatform;
import Transfers.TProduct;
import Transfers.TTicket;


public class TestSATicket {
	
	private SATicket st = SAAbstractFactory.getInstance().createSATicket();
	
	@Test
	public void testCreateTicketOk() {
		// validez sintactica,nombre no existente
		//TPlatform tp = new TPlatform("PS3");
		//assertEquals(4,sa.createPlatform(tp).intValue());
		ArrayList<Object> p = new ArrayList<Object>();
		p.add(new TGame("FORTNITE", 6, 20.0, 1, 2, "descripcion", "Shooter"));
		TTicket tt = new TTicket(1, p);
		assertEquals();
	}
	
	@Test
	public void testCreateTicketFail() {
		// invalidez sintactica
		//TPlatform tp = new TPlatform("");	//nombre vacio
		//assertNotEquals(5,sa.createPlatform(tp).intValue());
		//tp = new TPlatform("PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3");//nombre >45 chars
		//assertNotEquals(5,sa.createPlatform(tp).intValue());
		// nombre existe
		//tp = new TPlatform("XBOX360");
		//assertNotEquals(5,sa.createPlatform(tp).intValue());
		
		//assertNotEquals(5,sa.createPlatform(null).intValue());
	}

	@Test
	public void testUpdateTicketOk() {
		//Plataforma existe,validez sintactica(NOMBRE A CAMBIAR NO EXISTE)
		//TPlatform tp = new TPlatform("STEAM");
		//tp.set_id(3);
		//tp.set_activated(true);
		//assertTrue(sa.updatePlatform(tp));
	}
	
	@Test
	public void testUpdateTicketFail() {
		//TPlatform tp = new TPlatform();
		//invalidez sintactica
		//assertFalse(sa.updatePlatform(tp));	//campos nulos
		//tp.set_name("");
		//assertFalse(sa.updatePlatform(tp)); //name empty y campos nulos
		//tp.set_name("PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3PS3");
		//assertFalse(sa.updatePlatform(tp)); //nombre >45 chars y campos nulos
		//tp.set_name("STEAM"); //campos nulos menos nombre
		//assertFalse(sa.updatePlatform(tp)); //name empty y campos nulos
		//tp.set_activated(true);
		//assertFalse(sa.updatePlatform(tp)); //id nulo
		//Plataforma no existe
		//tp.set_id(8);
		//assertFalse(sa.updatePlatform(tp));
	}
	
	@Test
	public void testReadTicketOk() {
		//Plataforma existe
		//TPlatform tp = new TPlatform("XBOX360");
		//tp.set_id(1);
		//tp.set_activated(false);
		//assertEquals(tp.toString(),((TPlatform)sa.readPlatform(1)).toString());
	}
	
	@Test
	public void testReadTicketFail() {
		//Plataforma no existe
		//TPlatform tp = new TPlatform("XBOX360");
		//tp.set_id(8);
		//tp.set_activated(false);
		//assertNotEquals(tp,((TPlatform)sa.readPlatform(8)));
		//assertNotEquals(tp,sa.readPlatform(null));
	}
	
}