package Negocio.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import Negocio.SA.SAAbstractFactory;
import Transfers.TProduct;
import Transfers.TAccessory;
import Transfers.TGame;
/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class TestSAProduct {
	
	private SAProduct sa = SAAbstractFactory.getInstance().createSAProduct();
	
	@Test
	public void testCreateProduct() {
		//int results = sa.createProduct(new TGame("name",1,2.5,TProduct.accessory,1,1,"esto es una descripcion <150 caracteres."));
		//assertEquals(3, results);
		int results = sa.createProduct(new TAccessory("name2",1,0.0,1,1,"SONY","ROJO","Esto es una descripcion <150 caracteres."));
		assertEquals(3, results);
	}

	/*public Boolean deleteProduct(Integer id) {
		boolean ret = false;
		if(id != null) {
			TProduct tp = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(id);
			if(tp != null && tp.get_activated()){
				ret = DAOAbstractFactory.getInstance().createDAOProduct().deleteProduct(id);
			}
		}
		return ret;
	}

	public Boolean updateProduct(TProduct tpr) {
		TPlatform tpl;
		TProvider tprd;
		
		if(((TProduct)tpr).get_name().trim().isEmpty())
			return false;
		if(((TProduct)tpr).get_stock() < 0)
			return false;
		if(((TProduct)tpr).get_pvp() < 0)
			return false;
		if((tpl = DAOAbstractFactory.getInstance().createDAOPlatform().readPlatform(((TProduct)tpr).get_platformId())) == null || !tpl.get_activated())
			return false;
		if((tprd = (TProvider) DAOAbstractFactory.getInstance().createDAOProvider().readProvider(((TProduct)tpr).get_providerId())) == null || !tprd.get_activated())
			return false;
		if(((TProduct)tpr).get_type() == TProduct.accessory) {
			if(((TAccessory)tpr).get_brand().isEmpty())
				return false;
			if(((TAccessory)tpr).get_color().isEmpty())
				return false;
		}else {
			if(((TGame)tpr).get_description().isEmpty())
				return false;
			if(((TGame)tpr).get_gender().isEmpty())
				return false;
		}
		
		if(((TProduct)tpr).get_stock() > ((TProduct)tpr).get_unitsProvided())
			((TProduct)tpr).set_unitsProvided(((TProduct)tpr).get_stock()-((TProduct)tpr).get_unitsProvided());
		else
			((TProduct)tpr).set_unitsProvided(0);
		
		return  DAOAbstractFactory.getInstance().createDAOProduct().updateProduct(tpr);
	}

	public Object readProduct(Integer id) {
		return DAOAbstractFactory.getInstance().createDAOProduct().readProduct(id);
	}

	public List<Object> readAllProducts() {
		return DAOAbstractFactory.getInstance().createDAOProduct().readAllProducts();
	}*/
}