package Negocio.Product;

import java.util.List;

import javax.swing.JOptionPane;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Querys.LockModeType;
import Integracion.Querys.QueryEvents;
import Integracion.Querys.QueryFactory;
import Integracion.Transacciones.Transaction;
import Integracion.Transacciones.TransactionManager;
import Negocio.Transfers.TAccessory;
import Negocio.Transfers.TGame;
import Negocio.Transfers.TProduct;
import Negocio.Transfers.TProvider;
import utils.Pair;

public class SAProductImpl implements SAProduct {

	public Integer createProduct(TProduct tpr) {
		int id = -1;
		TProvider tprd;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			if (!tpr.get_name().trim().isEmpty() && tpr.get_unitsProvided() > 0 && tpr.get_pvp() >= 0) {

				tprd = (TProvider) DAOAbstractFactory.getInstance().createDAOProvider()
						.readProvider(tpr.get_providerId(), LockModeType.PESSIMISTIC);

				if (tprd != null && tprd.get_activated()) {
					if (tpr.get_type() == TProduct.accessory && !((TAccessory) tpr).get_brand().isEmpty()
							&& !((TAccessory) tpr).get_color().isEmpty()
							|| tpr.get_type() == TProduct.game && !((TGame) tpr).get_description().isEmpty()
									&& !((TGame) tpr).get_gender().isEmpty()) {

						TProduct tp = DAOAbstractFactory.getInstance().createDAOProduct()
								.readProductByName(tpr.get_name(), LockModeType.PESSIMISTIC);
						if (tp == null)
							id = DAOAbstractFactory.getInstance().createDAOProduct().createProduct(tpr);
					}
				}
			}
			t.commit();
		} catch (Exception e) {
			t.undo();
		} finally {
			tm.deleteTransaction();
		}
		return id;
	}

	public Boolean deleteProduct(Integer id) {
		boolean ret = false;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			if (id != null) {
				TProduct tp = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(id,
						LockModeType.PESSIMISTIC);
				if (tp != null && tp.get_activated()) {
					ret = DAOAbstractFactory.getInstance().createDAOProduct().deleteProduct(id);
				}
			}
			t.commit();
		} catch (Exception e) {
			t.undo();
		} finally {
			tm.deleteTransaction();
		}
		return ret;
	}

	public Boolean updateProduct(TProduct tpr) {
		TProvider tprd;
		Boolean ok=false;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			if (((TProduct) tpr).get_name().trim().isEmpty())
				return false;
			if (((TProduct) tpr).get_stock() < 0)
				return false;
			if (((TProduct) tpr).get_pvp() < 0)
				return false;
			if ((tprd = (TProvider) DAOAbstractFactory.getInstance().createDAOProvider()
					.readProvider(((TProduct) tpr).get_providerId(), LockModeType.PESSIMISTIC)) == null
					|| !tprd.get_activated())
				return false;
			if (((TProduct) tpr).get_type().equals(TProduct.accessory)) {
				if (((TAccessory) tpr).get_brand().isEmpty())
					return false;
				if (((TAccessory) tpr).get_color().isEmpty())
					return false;
			} else {
				if (((TGame) tpr).get_description().isEmpty()) // Comun a JUEGO y ACCESORIO ???
					return false;
				if (((TGame) tpr).get_gender().isEmpty())
					return false;
			}

			if (((TProduct) tpr).get_stock() > ((TProduct) tpr).get_unitsProvided())
				((TProduct) tpr).set_unitsProvided(((TProduct) tpr).get_stock() - ((TProduct) tpr).get_unitsProvided());
			else
				((TProduct) tpr).set_unitsProvided(0);
			ok=DAOAbstractFactory.getInstance().createDAOProduct().updateProduct(tpr);
			t.commit();
		} catch (Exception e) {
			t.undo();
		} finally {
			tm.deleteTransaction();
		}
		return ok;
	}

	public Object readProduct(Integer id) {
		TProduct p = null;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			p = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(id, LockModeType.PESSIMISTIC);
			t.commit();
		} catch (Exception e) {
			t.undo();
		} finally {
			tm.deleteTransaction();
		}
		return p;
	}

	public List<Object> readAllProducts() {
		List<Object> p = null;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			p = DAOAbstractFactory.getInstance().createDAOProduct().readAllProducts(LockModeType.PESSIMISTIC);
			t.commit();
		} catch (Exception e) {
			t.undo();
		} finally {
			tm.deleteTransaction();
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pair<String, Integer>> getProductsCount() {
		try {
			return (List<Pair<String, Integer>>) QueryFactory.getInstance().newQuery(QueryEvents.GET_PRODUCT_COUNT)
					.execute(null, LockModeType.PESSIMISTIC);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: Cannot execute the selected query.", "Fatal error",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
}