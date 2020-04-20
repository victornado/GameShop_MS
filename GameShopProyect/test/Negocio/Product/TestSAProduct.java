package Negocio.Product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Querys.LockModeType;
import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TProduct;

/**
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class TestSAProduct {
	
	private SAProduct sa = SAAbstractFactory.getInstance().createSAProduct();
	
	TProduct getValidProduct() {
		TProduct tpr = null;	
		boolean found = false;
		for(int i = 1; tpr == null && !found && i < 100; i++) {
			try {
				tpr = (TProduct) DAOAbstractFactory.getInstance().createDAOProduct().readProduct(i, LockModeType.PESSIMISTIC);
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
		return tpr;
	}
	TProduct getNotValidProduct() {
		TProduct tpr = null;	
		boolean found = false;
		for(int i = 1; tpr == null && !found && i < 100; i++) {
			try {
				tpr = (TProduct) DAOAbstractFactory.getInstance().createDAOProduct().readProduct(i, LockModeType.PESSIMISTIC);
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
		return tpr;
	}
	int nextId() {
		for(int i = 1; true; i++)
			try {
				if(DAOAbstractFactory.getInstance().createDAOProduct().readProduct(i, LockModeType.PESSIMISTIC) == null)
					return i;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void testCreateProductOk() {
		// Validez sintactica,proveedor no existente,
		TProduct tpr1 = new TProduct("76664094B","calle2",622237470);
		if(DAOAbstractFactory.getInstance().createDAOProduct().readProductByName(tpr1.get_name(), LockModeType.PESSIMISTIC) == null)
			assertTrue( sa.createProduct(tpr1) > -1 );
	}

	@Test
	public void testCreateProductFail() {
		// Invalidez Sintactica
		assertFalse(sa.createProduct(new TProduct("77788898G","calle otra",333337888)) > -1); //nif invalido
		assertFalse(sa.createProduct(new TProduct("48227381S","",222222222)) > -1);// campo nulo/vacio		
		assertFalse(sa.createProduct(new TProduct("48227381S",null,222222222)) > -1);// campo nulo/vacio		
		assertFalse(sa.createProduct(new TProduct("48227381S","calle siete ocho nueve diez once doce trece catorce quince dieciseis",222222222)) > -1);// campo address invalido(>50 chars)
		assertFalse(sa.createProduct(new TProduct("48227381S","calle otra",234234)) > -1);// telefono invalido sintacticamente (<9)
		assertFalse(sa.createProduct(new TProduct("48227381S","calle otra",1342342334)) > -1);//telefono invalido sintacticamente (>9))
		assertFalse(sa.createProduct(new TProduct(null,"calle",888999888)) > -1);//campo nulo
		// Proveedor existente
		TProduct tpr;
		if((tpr=this.getValidProduct()) != null)
			assertFalse( sa.createProduct(tpr) > -1); //NIF EXISTENTE)
	}
	
}