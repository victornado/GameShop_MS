package Negocio.Product;

import java.util.List;

import Integracion.DAO.DAOAbstractFactory;
import Transfers.TPlatform;
import Transfers.TProduct;
import Transfers.TProvider;
import Transfers.TAccessory;
import Transfers.TGame;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class SAProductImpl implements SAProduct {
	
	public Integer createProduct(TProduct tpr) {
		int id = -1;
		TPlatform tpl;
		TProvider tprd;
		
		if(!tpr.get_name().trim().isEmpty() && tpr.get_unitsProvided() > 0 && 
				tpr.get_pvp() >= 0){
			tpl = DAOAbstractFactory.getInstance().createDAOPlatform().readPlatform(tpr.get_platformId());
			if(tpl != null && tpl.get_activated()){ //Si la plataforma existe y esta activada:
				tprd = (TProvider) DAOAbstractFactory.getInstance().createDAOProvider().readProvider(tpr.get_providerId());
				if(tprd != null && tprd.get_activated()){
					if(tpr.get_type() == TProduct.accessory && !((TAccessory)tpr).get_brand().isEmpty() &&
							!((TAccessory)tpr).get_color().isEmpty() ||
							tpr.get_type() == TProduct.game && !((TGame)tpr).get_description().isEmpty() &&
							!((TGame)tpr).get_gender().isEmpty()){
						
						TProduct tp = DAOAbstractFactory.getInstance().createDAOProduct().readProductByNameAndPlatform(tpr.get_name(), tpl.get_id());
						if(tp == null)
							id = DAOAbstractFactory.getInstance().createDAOProduct().createProduct(tpr);
					}
				}
			}
		}
		return id;
	}

	public Boolean deleteProduct(Integer id) {
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
	}
}