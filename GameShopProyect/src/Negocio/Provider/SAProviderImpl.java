package Negocio.Provider;

import java.util.List;

import javax.swing.JOptionPane;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Product.DAOProduct;
import Integracion.Provider.DAOProvider;
import Integracion.Querys.LockModeType;
import Integracion.Querys.QueryEvents;
import Integracion.Querys.QueryFactory;
import Integracion.Transacciones.Transaction;
import Integracion.Transacciones.TransactionManager;
import Negocio.Transfers.TProduct;
import Negocio.Transfers.TProvider;

/**
 * @author GameShop
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAProviderImpl implements SAProvider {

	public Integer createProvider(TProvider tp) {
		int id = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			if (validateData(tp)) {
				DAOProvider daoProvider = DAOAbstractFactory.getInstance().createDAOProvider();
				TProvider tpl = (TProvider) daoProvider.readProviderByNIF(tp.get_nif(), LockModeType.PESSIMISTIC);
				if (tpl == null) {
					id = daoProvider.createProvider(tp);

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

	public Boolean deleteProvider(Integer id) {
		boolean ret = false;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			DAOProvider daopi = DAOAbstractFactory.getInstance().createDAOProvider();

			if (id != null) {
				TProvider tprnif = (TProvider) daopi.readProvider(id, LockModeType.PESSIMISTIC);
				// Si devuelve un transfer significa que existe y por lo tanto se procede a
				// borrarlo
				if (tprnif != null && tprnif.get_activated())
					ret = daopi.deleteProvider(tprnif);
				if(ret) {
					DAOProduct daopr = DAOAbstractFactory.getInstance().createDAOProduct();
					List<TProduct> l = daopr.readProductsFromProvider(tprnif.get_id(),LockModeType.PESSIMISTIC);
					for(TProduct tprd : l) {
						if(tprd.get_activated()) {
							daopr.deleteProduct(tprd.get_id());
						}
					}
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

	public Boolean updateProvider(TProvider tp) {
		TProvider tpr = null;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		boolean ok = false;
		try {
			t.init();
			if (!validateData(tp) || (tpr = (TProvider) DAOAbstractFactory.getInstance().createDAOProvider()
					.readProvider(tp.get_id(), LockModeType.PESSIMISTIC)) == null)
				return false;
			if (!tpr.get_nif().equalsIgnoreCase(tp.get_nif())) {
				if (DAOAbstractFactory.getInstance().createDAOProvider().readProviderByNIF(tp.get_nif(),
						LockModeType.PESSIMISTIC) != null)
					return false;
			}
			ok = DAOAbstractFactory.getInstance().createDAOProvider().updateProvider(tp);
			//Thread.sleep(5000);
			t.commit();
		} catch (Exception e) {
			t.undo();
		} finally {
			tm.deleteTransaction();
		}

		return ok;
	}

	public Object readProvider(Integer id) {
		TProvider ret = null;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			DAOProvider daoProvider = DAOAbstractFactory.getInstance().createDAOProvider();

			if (id != null)
				ret = (TProvider) daoProvider.readProvider(id, LockModeType.PESSIMISTIC);
			t.commit();
		} catch (Exception e) {
			t.undo();
		} finally {
			tm.deleteTransaction();
		}
		return ret;
	}

	public List<Object> readAllProviders() {
		List<Object> providers = null;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction t = tm.newTransaction();
		try {
			t.init();
			providers = DAOAbstractFactory.getInstance().createDAOProvider().readAllProviders(LockModeType.PESSIMISTIC);
			t.commit();
		} catch (Exception e) {
			t.undo();
		} finally {
			tm.deleteTransaction();
		}
		return providers;
	}

	private boolean validateData(TProvider tp) {
		if (tp == null || tp.get_address() == null || tp.get_nif() == null || tp.get_phoneNumber() == null
				|| !checkNIF(tp.get_nif()) || tp.get_address().length() > 50 || tp.get_address().trim().isEmpty()
				|| tp.get_phoneNumber().toString().length() != 9)
			return false;
		else
			return true;
	}

	// ESTOS 3 METODOS SON PARA COMPROBAR LA VALIDEZ DE UN NIF
	private boolean checkNIF(String NIF) {
		String upperLetter = "";
		if (NIF.length() != 9 || !Character.isLetter(NIF.charAt(8)))
			return false;

		upperLetter = (NIF.substring(8)).toUpperCase();

		if (NIFnumbers(NIF) && NIFletter(NIF).equals(upperLetter))
			return true;
		else
			return false;
	}

	private String NIFletter(String NIF) {
		int myNif = Integer.parseInt(NIF.substring(0, 8)), rest = 0;
		String letter = "";
		String[] posibilities = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
				"V", "H", "L", "C", "K", "E" };
		rest = myNif % 23;

		letter = posibilities[rest];

		return letter;
	}

	private boolean NIFnumbers(String NIF) {
		String number = "", myNif = "";
		String[] numberRange = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		for (int i = 0; i < NIF.length() - 1; ++i) {
			number = NIF.substring(i, i + 1);
			for (int j = 0; j < numberRange.length; ++j) {
				if (number.equals(numberRange[j]))
					myNif += numberRange[j];
			}
		}
		if (myNif.length() != 8)
			return false;
		else
			return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getBestProvider() {
		try {
			return (List<Object>) QueryFactory.getInstance().newQuery(QueryEvents.GET_BEST_PROVIDER).execute(null,
					LockModeType.PESSIMISTIC);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: Cannot execute the selected query.", "Fatal error",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

}