package Negocio.Provider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import Negocio.SA.SAAbstractFactory;
import Transfers.TProvider;

/**
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class TestSAProvider {
	
	private SAProvider sa = SAAbstractFactory.getInstance().createSAProvider();
	
	//EN LA BASE DE DATOS EXISTE CON ID=1(PARA MODIFICAR) 
	//										values (1,'calle igual','76664094B',622237470,true);
	//(OTRO PROVEEDOR CON ID=3(PARA LEER)
	//										values(3,'calle igual','16374546H',999888777,true);
	
	
	@Test
	public void testCreateProviderOk() {
		// Validez sintactica,proveedor no existente,
		int result = sa.createProvider(new TProvider("88889999J","calle tuya",666777888));
		assertEquals(5, result); 
	}

	@Test
	public void testCreateProviderFail() {
		// Invalidez Sintactica
		int result = sa.createProvider(new TProvider("77788898G","calle otra",333337888)); //nif invalido
		assertNotEquals(6,result);
		result = sa.createProvider(new TProvider("77788898T","",222222222));// campo nulo/vacio
		assertNotEquals(6,result);																   
		result = sa.createProvider(new TProvider("77788898T","calle siete ocho nueve diez once doce trece catorce quince dieciseis",222222222));// campo address invalido(>50 chars)
		assertNotEquals(6,result);
		result = sa.createProvider(new TProvider("77788898T","calle otra",234234));// telefono invalido sintacticamente (<9)
		assertNotEquals(6,result);
		result = sa.createProvider(new TProvider("77788898T","calle otra",1342342334));//telefono invalido sintacticamente (>9)
		assertNotEquals(6,result);
		result = sa.createProvider(new TProvider("77788898T",null,888999888));//campo nulo
		// Proveedor existente
		result = sa.createProvider(new TProvider("76664094B","calle otra",222237470)); //NIF EXISTENTE
		assertNotEquals(6, result);
	}
	
	@Test
	public void testDeleteProviderOk() {
		// Proveedor existente y activo
		assertTrue(sa.deleteProvider(1)); 
	}
	
	@Test
	public void testDeleteProviderFail() {
		//Proveedor no existente
		assertFalse(sa.deleteProvider(6));
		//Proveedor existente pero inactivo
		assertFalse(sa.deleteProvider(4));
		// Proveedor id nulo
		assertFalse(sa.deleteProvider(null));
	}

	@Test
	public void testUpdateProviderOk() {
		// Proveedor existe y validez sintactica
		TProvider tpr = new TProvider("16374546H","calle distinta",987654321); //modifica la calle
		tpr.set_id(2);				//id del provider
		tpr.set_activated(true);	//modifica el activated
		assertTrue(sa.updateProvider(tpr));
	}
	
	@Test
	public void testUpdateProviderFail() {
		//Proveedor no existe
		TProvider tpr = new TProvider("77664094B","calle",999888777);
		tpr.set_id(1);	//El id no corresponde con el nif || el nif para ese id no es valido porque ya existe en la BD
		tpr.set_activated(false);
		assertFalse(sa.updateProvider(tpr));
		//Proveedor existe, invalidez sintactica	
		tpr = new TProvider("76664094B","",999888777);
		tpr.set_id(1);	//El id no corresponde con el nif || el nif para ese id no es valido porque ya existe en la BD
		tpr.set_activated(false);
		assertFalse(sa.updateProvider(tpr));
		tpr = null;
		assertFalse(sa.updateProvider(tpr));
		tpr = new TProvider("76664094B","",999888777);
		tpr.set_id(1);
		tpr.set_activated(false);
		assertFalse(sa.updateProvider(tpr));
		tpr = new TProvider("16374546H","calle distinta",999888777);
		tpr.set_id(1);
		tpr.set_activated(false);
		assertFalse(sa.updateProvider(tpr));
	}

	@Test
	public void testReadProviderOk() {
		//Proveedor existente
		TProvider tpr = (new TProvider("99296921X","calle igual",222333444));
		tpr.set_id(3);
		tpr.set_activated(false);
		assertEquals(((TProvider)tpr).toString(), ((TProvider)sa.readProvider(3)).toString());	
	}
	
	@Test
	public void testReadProviderFail() {
		//Proveedor no existente
		TProvider tpr = new TProvider("76664094B","calle distinta",666666666);
		tpr.set_id(5);
		tpr.set_activated(true);
		assertNotEquals(((TProvider)sa.readProvider(6)), tpr);
		assertNotEquals(((TProvider)sa.readProvider(1)),null);
	}


}