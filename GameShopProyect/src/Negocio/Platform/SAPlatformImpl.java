package Negocio.Platform;

import java.util.List;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Platform.DAOPlatform;
import Transfers.TPlatform;


public class SAPlatformImpl implements SAPlatform {

	@Override
	public Integer createPlatform(TPlatform tpla) {
		int id = -1;
		if(tpla != null && tpla.get_name() != null && !tpla.get_name().trim().equals("") && (tpla.get_name().length() < 45)){ //Comprobamos que el transfer no esta vacio, y que hay nombre introducido.
			DAOPlatform daoPlatform = DAOAbstractFactory.getInstance().createDAOPlatform(); 
			TPlatform tpl = daoPlatform.readByName(tpla.get_name());
			if(tpl == null)
				id = daoPlatform.createPlatform(tpla);
		}
		return id;
	}

	@Override
	public Boolean deletePlatform(Integer id) {
		boolean ret = false;
		if(id != null) {
			DAOPlatform daoPlatform = DAOAbstractFactory.getInstance().createDAOPlatform();
			TPlatform ternif = daoPlatform.readPlatform(id);
			// Si devuelve un transfer significa que existe y por lo tanto se procede a borrarlo
			if(ternif != null && ternif.get_activated())
				ret = daoPlatform.deletePlatform(id);
		}
		return ret;
	}

	@Override
	public Boolean updatePlatform(TPlatform tpla) {
		if(tpla != null && tpla.get_name() != null && tpla.get_id() != null && tpla.get_activated() != null && tpla.get_name().length() < 45
				&& !tpla.get_name().trim().equals("") && tpla.get_id() > 0) {
			TPlatform tp = DAOAbstractFactory.getInstance().createDAOPlatform().readPlatform(tpla.get_id());
			if(tp!=null) {
				if(tp.get_name().equalsIgnoreCase(tpla.get_name())) {
					if(DAOAbstractFactory.getInstance().createDAOPlatform().readByName(tpla.get_name()) == null)
						return false;
				}
				return DAOAbstractFactory.getInstance().createDAOPlatform().updatePlatform(tpla);
			}
		}
		return false;
	}

	@Override
	public TPlatform readPlatform(Integer id) {
		TPlatform ret = null;
		if(id != null){
			DAOPlatform daoPlatform = DAOAbstractFactory.getInstance().createDAOPlatform();
			ret = (TPlatform) daoPlatform.readPlatform(id);
		}
		return ret;
	}

	@Override
	public List<Object> readAllPlatforms() {
		List<Object> platforms = null;
		platforms = DAOAbstractFactory.getInstance().createDAOPlatform().readAllPlatforms();
		return platforms;
	}

	@Override
	public List<Object> readAllProductsOfAPlatform(Integer id) {
		if(DAOAbstractFactory.getInstance().createDAOPlatform().readPlatform(id) == null)
			return null;
		return DAOAbstractFactory.getInstance().createDAOPlatform().readAllProductsOfAPlatform(id);
	}
	
	
}