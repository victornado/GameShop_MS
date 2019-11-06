package Integracion.Provider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Transacciones.Transaction;
import Integracion.Transacciones.TransactionManager;
import Transfers.TProvider;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class TestDAOProvider {
	
	public static	TransactionManager tm = TransactionManager.getInstance();
	public static Transaction t;
	private static DAOProvider dao = DAOAbstractFactory.getInstance().createDAOProvider();
	public static TProvider getValidProvider() {
		TProvider tpr = null;	
		boolean found = false;
		TestDAOProvider.t = TestDAOProvider.tm.newTransaction();
		try {
			TestDAOProvider.t.init();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for(int i = 1; tpr == null && !found && i < 100; i++) {
			try {
				tpr = (TProvider) dao.readProvider(i, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(tpr != null) {
				if(tpr.get_activated())
					found = true;
				else
					tpr = null;
			}else
				found = true;
		}
		TestDAOProvider.t.commit();
		TestDAOProvider.tm.deleteTransaction();
		return tpr;
	}
	public static TProvider getNotValidProvider() {
		TProvider tpr = null;	
		boolean found = false;
		TestDAOProvider.t = TestDAOProvider.tm.newTransaction();
		try {
			TestDAOProvider.t.init();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for(int i = 1; tpr == null && !found && i < 100; i++) {
			try {
				tpr = (TProvider)dao.readProvider(i,null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(tpr != null) {
				if(!tpr.get_activated())
					found = true;
				else
					tpr = null;
			}else
				found = true;
		}
		TestDAOProvider.t.commit();
		TestDAOProvider.tm.deleteTransaction();
		return tpr;
	}
	public static int nextId() {
		TestDAOProvider.t = TestDAOProvider.tm.newTransaction();
		try {
			TestDAOProvider.t.init();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for(int i = 1; true; i++) {
			try {
				if(dao.readProvider(i, null) == null) {
					TestDAOProvider.t.commit();
					TestDAOProvider.tm.deleteTransaction();
					return i;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testNextId() { //siguiente ID sin provider asignado
		int id = TestDAOProvider.nextId();
		try {
			assertTrue(dao.readProvider(id, null) == null);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void testGetValidProvider() {
		TProvider tp = TestDAOProvider.getValidProvider();
		TestDAOProvider.t = tm.newTransaction();
		try {
			TestDAOProvider.t.init();
			TProvider tp2 = (TProvider) dao.readProvider(tp.get_id(), null);
			assertTrue(tp.get_id() == tp2.get_id() && tp.get_nif() == tp2.get_nif() && tp.get_address() == tp2.get_address() 
					&& tp.get_phoneNumber() == tp2.get_phoneNumber() && tp.get_activated() == true);
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		} catch (Exception e) {
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	public void testGetNotValidProvider() {
		TProvider tp = TestDAOProvider.getValidProvider();
		TestDAOProvider.t = tm.newTransaction();
		try {
			TestDAOProvider.t.init();
			TProvider tp2 = (TProvider) dao.readProvider(tp.get_id(), null);
			assertTrue(tp.get_id() == tp2.get_id() && tp.get_nif() == tp2.get_nif() && tp.get_address() == tp2.get_address() 
					&& tp.get_phoneNumber() == tp2.get_phoneNumber() && tp.get_activated() == false);
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		} catch (Exception e) {
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	public void testReadProviderByNIF() {
		TestDAOProvider.t = tm.newTransaction();
		try {
			TestDAOProvider.t.init();
			String nif = "76664094B"; //Dni insertado con el script inicial de sql
			assertTrue(dao.readProviderByNIF(nif, null) != null);
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		} catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	void testCreateProviderOk() {
		TProvider tp = new TProvider("Y9969928V","calle55",622254999);
		TestDAOProvider.t = tm.newTransaction();
		try {
			TestDAOProvider.t.init();
			if(dao.readProviderByNIF("Y9969928V", null) == null)
				DAOAbstractFactory.getInstance().createDAOProvider().createProvider(tp);
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	void testCreateProviderFail() {
		TestDAOProvider.t = tm.newTransaction();
		try {
			TestDAOProvider.t.init();
			assertFalse(dao.createProvider(new TProvider("4822738172S","as",222222222)) > -1);// campo nif mal	
			assertFalse(dao.createProvider(new TProvider("48227381S",null,222222222)) > -1);// campo nulo/vacio		
			assertFalse(dao.createProvider(new TProvider("48227381S","calle siete ocho nueve diez once doce trece catorce quince dieciseis",222222222)) > -1);// campo address invalido(>50 chars)
			assertFalse(dao.createProvider(new TProvider("48227381S","calle otra",234234)) > -1);// telefono invalido sintacticamente (<9)
			assertFalse(dao.createProvider(new TProvider("48227381S","calle otra",1342342334)) > -1);//telefono invalido sintacticamente (>9))
			assertFalse(dao.createProvider(new TProvider(null,"calle",888999888)) > -1);//campo nulo
			// Proveedor existente
			TProvider tpr;
			if((tpr=TestDAOProvider.getValidProvider()) != null)
				assertFalse( dao.createProvider(tpr) > -1); //NIF EXISTENTE)]
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	public void testDeleteProviderOk() {
		try {
			TProvider tpr;
			if((tpr=TestDAOProvider.getValidProvider()) != null) {
				TestDAOProvider.t = tm.newTransaction();
				TestDAOProvider.t.init();
				assertTrue(dao.deleteProvider(tpr));
				TestDAOProvider.t.commit();
				TestDAOProvider.tm.deleteTransaction();
			}
		}catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	public void testDeleteProviderFail() {
		TProvider tpr = new TProvider("97276269T","calle otra",134567890); 
		TestDAOProvider.t = tm.newTransaction();
		try {
			TestDAOProvider.t.init();
			if(dao.readProviderByNIF(tpr.get_nif(), null) == null) {//Proveedor no existente
				assertFalse(dao.deleteProvider(tpr));
			}
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
			//Proveedor existente pero inactivo
			if((tpr=TestDAOProvider.getNotValidProvider()) != null) {
				TestDAOProvider.t = tm.newTransaction();
				TestDAOProvider.t.init();
				assertFalse(dao.deleteProvider(tpr));
			}
			// Proveedor id nulo
			assertFalse(dao.deleteProvider(null));
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	public void testUpdateProviderOk() {
		TProvider tpr = null; 
		try {
			// Proveedor existe y validez sintactica
			tpr = TestDAOProvider.getValidProvider();
			TestDAOProvider.t = tm.newTransaction();
			TestDAOProvider.t.init();
			if(tpr != null) {
				tpr.set_activated(true);
				assertTrue(dao.updateProvider(tpr));
			}
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	public void testUpdateProviderFail() {
		TProvider tpr = new TProvider("C69204840","calle otra",134567890); 
		TestDAOProvider.t = tm.newTransaction();
		try {
			tpr.set_activated(true);
			tpr.set_id(0);
			TestDAOProvider.t.init();
			if(dao.readProviderByNIF(tpr.get_nif(), null) == null) 
				assertFalse(dao.updateProvider(tpr));

			//Proveedor existe, invalidez sintactica	
			tpr = new TProvider("76664094B","",999888777);
			tpr.set_activated(false);
			TProvider tupdt = tpr; 
	
			if(dao.readProviderByNIF(tpr.get_nif(),null) != null) {
				TestDAOProvider.tm.deleteTransaction();
				tupdt.set_id(0);	//El id no corresponde con el nif || el nif para ese id no es valido porque ya existe en la BD
				assertFalse(dao.updateProvider(tupdt));
				tupdt = null;
				assertFalse(dao.updateProvider(tupdt)); // TProvider nulo
				tupdt = tpr;
				assertFalse(dao.updateProvider(tupdt)); //calle vacia
				tupdt.set_address("calle cualquiera");
				tupdt.set_phoneNumber(643544); //telefono incorrecto
				assertFalse(dao.updateProvider(tupdt));
				tupdt.set_phoneNumber(null);
				assertFalse(dao.updateProvider(tupdt)); //telefono nulo
				tupdt.set_phoneNumber(622237470);
				tupdt.set_nif("22222222V"); //nif incorrecto
				assertFalse(dao.updateProvider(tupdt)); 
				tupdt.set_nif(null); //nif nulo
				assertFalse(dao.updateProvider(tupdt));  
			}
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}

	@Test
	public void testReadProviderOk() {
		TProvider tpr = TestDAOProvider.getNotValidProvider(); 
		TestDAOProvider.t = tm.newTransaction();
		try {
			TestDAOProvider.t.init();
			//Proveedor existente
			TProvider tp;
			if(tpr != null) {
				assertTrue((tp = (TProvider) dao.readProvider(tpr.get_id(),null)) != null);
				assertTrue(tp.get_nif().equalsIgnoreCase(tpr.get_nif()));
			}
		}catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
	
	@Test
	public void testReadProviderFail() {
		TProvider tpr = new TProvider("65032991P","calle distinta",666666666);
		int id = TestDAOProvider.nextId();
		TestDAOProvider.t = tm.newTransaction();
		try {
			TestDAOProvider.t.init();
			//Proveedor no existente
			TProvider tp;
			tpr.set_id(1);
			assertFalse(dao.readProvider(id,null) != null);
			assertTrue((tp = (TProvider) dao.readProvider(tpr.get_id(),null)) != null);
			assertTrue(tp.get_nif() != tpr.get_nif()); //proveedor existente pero no el requerido
		}catch (Exception e) {
			//e.printStackTrace();
			TestDAOProvider.t.commit();
			TestDAOProvider.tm.deleteTransaction();
		}
	}
}