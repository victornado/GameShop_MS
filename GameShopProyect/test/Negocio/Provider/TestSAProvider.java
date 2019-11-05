package Negocio.Provider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Querys.LockModeType;
import Negocio.SA.SAAbstractFactory;
import Transfers.TProvider;

/**
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class TestSAProvider {
	
	private SAProvider sa = SAAbstractFactory.getInstance().createSAProvider();
	
	TProvider getValidProvider() {
		TProvider tpr = null;	
		boolean found = false;
		for(int i = 1; tpr == null && !found && i < 100; i++) {
			tpr = (TProvider) DAOAbstractFactory.getInstance().createDAOProvider().readProvider(i, LockModeType.PESSIMISTIC);
			if(tpr != null) {
				if(tpr.get_activated())
					found = true;
				else
					tpr = null;
			}else
				found = true;
		}
		return tpr;
	}
	TProvider getNotValidProvider() {
		TProvider tpr = null;	
		boolean found = false;
		for(int i = 1; tpr == null && !found && i < 100; i++) {
			tpr = (TProvider) DAOAbstractFactory.getInstance().createDAOProvider().readProvider(i, LockModeType.PESSIMISTIC);
			if(tpr != null) {
				if(!tpr.get_activated())
					found = true;
				else
					tpr = null;
			}else
				found = true;
		}
		return tpr;
	}
	int nextId() {
		for(int i = 1; true; i++)
			if(DAOAbstractFactory.getInstance().createDAOProvider().readProvider(i, LockModeType.PESSIMISTIC) == null)
				return i;
	}
	
	@Test
	public void testCreateProviderOk() {
		// Validez sintactica,proveedor no existente,
		TProvider tpr1 = new TProvider("76664094B","calle2",622237470);
		if(DAOAbstractFactory.getInstance().createDAOProvider().readProviderByNIF(tpr1.get_nif(), LockModeType.PESSIMISTIC) == null)
			assertTrue( sa.createProvider(tpr1) > -1 );
	}

	@Test
	public void testCreateProviderFail() {
		// Invalidez Sintactica
		assertFalse(sa.createProvider(new TProvider("77788898G","calle otra",333337888)) > -1); //nif invalido
		assertFalse(sa.createProvider(new TProvider("48227381S","",222222222)) > -1);// campo nulo/vacio		
		assertFalse(sa.createProvider(new TProvider("48227381S",null,222222222)) > -1);// campo nulo/vacio		
		assertFalse(sa.createProvider(new TProvider("48227381S","calle siete ocho nueve diez once doce trece catorce quince dieciseis",222222222)) > -1);// campo address invalido(>50 chars)
		assertFalse(sa.createProvider(new TProvider("48227381S","calle otra",234234)) > -1);// telefono invalido sintacticamente (<9)
		assertFalse(sa.createProvider(new TProvider("48227381S","calle otra",1342342334)) > -1);//telefono invalido sintacticamente (>9))
		assertFalse(sa.createProvider(new TProvider(null,"calle",888999888)) > -1);//campo nulo
		// Proveedor existente
		TProvider tpr;
		if((tpr=this.getValidProvider()) != null)
			assertFalse( sa.createProvider(tpr) > -1); //NIF EXISTENTE)
	}
	
	@Test
	public void testDeleteProviderOk() {
		// Proveedor existente y activo
		TProvider tpr;
		if((tpr=this.getValidProvider()) != null) 
			assertTrue(sa.deleteProvider(tpr.get_id())); 
	}
	
	@Test
	public void testDeleteProviderFail() {
		TProvider tpr = new TProvider("48227381S","calle otra",134567890); 
		if(DAOAbstractFactory.getInstance().createDAOProvider().readProviderByNIF(tpr.get_nif(), LockModeType.PESSIMISTIC) == null)
			//Proveedor no existente
			assertFalse(sa.deleteProvider(tpr.get_id()));
		
		//Proveedor existente pero inactivo
		if((tpr=this.getNotValidProvider()) != null)
			assertFalse(sa.deleteProvider(tpr.get_id()));
		// Proveedor id nulo
		assertFalse(sa.deleteProvider(null));
	}

	@Test
	public void testUpdateProviderOk() {
		// Proveedor existe y validez sintactica
		TProvider tpr = this.getValidProvider();
		if(tpr != null) {
			tpr.set_activated(true);
			assertTrue(sa.updateProvider(tpr));
		}
	}
	
	@Test
	public void testUpdateProviderFail() {
		//Proveedor no existe
		TProvider tpr = new TProvider("48227381S","calle otra",134567890); 
		tpr.set_activated(true);
		tpr.set_id(0);
		if(DAOAbstractFactory.getInstance().createDAOProvider().readProviderByNIF(tpr.get_nif(), LockModeType.PESSIMISTIC) == null)
			assertFalse(sa.updateProvider(tpr));
		//Proveedor existe, invalidez sintactica	
		tpr = new TProvider("76664094B","",999888777);
		tpr.set_activated(false);
		TProvider tupdt = tpr; 
		if(DAOAbstractFactory.getInstance().createDAOProvider().readProviderByNIF(tpr.get_nif(), LockModeType.PESSIMISTIC) != null) {
			tupdt.set_id(0);	//El id no corresponde con el nif || el nif para ese id no es valido porque ya existe en la BD
			assertFalse(sa.updateProvider(tupdt));
			tupdt = null;
			assertFalse(sa.updateProvider(tupdt)); // TProvider nulo
			tupdt = tpr;
			assertFalse(sa.updateProvider(tupdt)); //calle vacia
			tupdt.set_address("calle cualquiera");
			tupdt.set_phoneNumber(643544); //telefono incorrecto
			assertFalse(sa.updateProvider(tupdt));
			tupdt.set_phoneNumber(null);
			assertFalse(sa.updateProvider(tupdt)); //telefono nulo
			tupdt.set_phoneNumber(622237470);
			tupdt.set_nif("22222222V"); //nif incorrecto
			assertFalse(sa.updateProvider(tupdt)); 
			tupdt.set_nif(null); //nif nulo
			assertFalse(sa.updateProvider(tupdt));  
		}
	}
	
	@Test
	public void testReadProviderOk() {
		//Proveedor existente
		TProvider tpr = this.getNotValidProvider();
		TProvider tp;
		assertTrue((tp = (TProvider) sa.readProvider(tpr.get_id())) != null && tp.get_nif().equalsIgnoreCase(tpr.get_nif()));	
	}
	
	@Test
	public void testReadProviderFail() {
		//Proveedor no existente
		TProvider tpr = new TProvider("65032991P","calle distinta",666666666);
		TProvider tp;
		tpr.set_id(1);
		assertFalse(sa.readProvider(this.nextId()) != null);
		assertTrue((tp = (TProvider) sa.readProvider(tpr.get_id())) != null && tp.get_nif() != tpr.get_nif()); //proveedor existente pero no el requerido
	}
}