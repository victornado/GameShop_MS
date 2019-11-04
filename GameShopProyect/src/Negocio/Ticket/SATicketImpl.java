
package Negocio.Ticket;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Product.DAOProduct;
import Integracion.Querys.LockModeType;
import Integracion.Querys.Query;
import Integracion.Querys.QueryEvents;
import Integracion.Querys.QueryFactory;
import Integracion.Ticket.DAOTicket;
import Negocio.SA.SAAbstractFactory;
import Transfers.TAsociated;
import Transfers.TProduct;
import Transfers.TProductQuantity;
import Transfers.TTicket;
import javafx.util.Pair;

public class SATicketImpl implements SATicket {

	public Integer createTicket(TTicket tt) {
		Integer res = -1;
		TTicket tti = (TTicket) tt;
		if (correctInputData(tt)) { // modificar stock de cada producto, calcular precio final

			double preciofin = 0.0;
			int units = 0;
			for (int i = 0; i < tti.get_products().size(); ++i) {
				// TProduct tp = (TProduct) tti.get_products().get(i);
				TAsociated ta = (TAsociated) tti.get_products().get(i);

				// units = tp.get_unitsInTicket();
				// tp =
				// DAOAbstractFactory.getInstance().createDAOProduct().readProduct(tp.get_id());
				// preciofin += (tp.get_pvp() * units);
				units = ta.get_cantidad();
				preciofin += ta.get_precio() * units;
			}

			tti.set_finalPrice(preciofin);
			res = DAOAbstractFactory.getInstance().createDAOTicket().createTicket(tti);

			if (res > 0) {
				// Si se ha creado el ticket correctamente, actualizamos los valores del stock
				// en la base de datos
				units = 0;
				for (int i = 0; i < tti.get_products().size(); ++i) {
					// TProduct tp = (TProduct) tti.get_products().get(i);
					TAsociated ta = (TAsociated) tti.get_products().get(i);

					// units = tp.get_unitsInTicket();
					// tp =
					// DAOAbstractFactory.getInstance().createDAOProduct().readProduct(tp.get_id());
					// DAOAbstractFactory.getInstance().createDAOProduct().updateProduct(tp);
					units = ta.get_cantidad();
					TProduct tp = (TProduct) SAAbstractFactory.getInstance().createSAProduct()
							.readProduct(ta.get_idProduct());

					tp.set_stock(tp.get_stock() - units);
					tp.set_unitsProvided(0);
					DAOAbstractFactory.getInstance().createDAOProduct().updateProduct(tp);
				}
			}

		}
		return res;
	}

	public Boolean deleteTicket(Integer id) {
		boolean deleted = false;
		DAOTicket daoTicket = DAOAbstractFactory.getInstance().createDAOTicket();
		DAOProduct daoProduct = DAOAbstractFactory.getInstance().createDAOProduct();

		if (id != null) {
			TTicket tt = (TTicket) daoTicket.readTicket(id, LockModeType.PESSIMISTIC);
			for (int i = 0; i < tt.get_products().size(); i++) {
				TAsociated ta = (TAsociated) tt.get_products().get(i);
				TProduct tp = daoProduct.readProduct(ta.get_idProduct(), LockModeType.PESSIMISTIC);
				if (tp != null) {
					tp.set_stock(ta.get_cantidad() + tp.get_stock());
					tp.set_unitsProvided(0);
					daoProduct.updateProduct(tp);
				}
			}
			deleted = daoTicket.deleteTicket(id);
		}

		return deleted;
	}

	@SuppressWarnings("unchecked")
	public Object TOAReadTicket(Integer id) throws Exception {
		TProductQuantity toa = null;
		TTicket tt = null;
		DAOTicket daoTicket = DAOAbstractFactory.getInstance().createDAOTicket();
		HashMap<Integer, Pair<String, Integer>> productsToShow;
		if (id != null) {
			tt = (TTicket) daoTicket.readTicket(id, LockModeType.PESSIMISTIC);
			toa = new TProductQuantity(id, tt.get_finalPrice(), tt.get_date(), null);
			Query q = QueryFactory.getInstance().newQuery(QueryEvents.GET_INFO_EVENT);
			if (q != null) {

				productsToShow = (HashMap<Integer, Pair<String, Integer>>) q.execute(id, LockModeType.PESSIMISTIC);
			} else
				return null;

			// TODO si no funciona el casteo hacer aqui un while() y cambiar la query
			toa.set_productsToShow(productsToShow);
		}
		return toa;
	}

	public List<Object> readAllTickets() {
		List<Object> tickets = null;
		tickets = DAOAbstractFactory.getInstance().createDAOTicket().readAllTickets(LockModeType.PESSIMISTIC);
		return tickets;
	}

	/*
	 * private boolean prodListNotEmpty(TTicket tt) { //damos por hecho en el DAO
	 * que la lista no llega vacia ===> Aqui se comprueba si esta vacia o no return
	 * tt.get_products().size()>0; }
	 */

	private boolean correctInputData(TTicket tt) { // comprobamos que todos los datos introducidos son correctos y
													// existen

		// Cada uno de los productos existen y hay stock suficiente
		for (int i = 0; i < tt.get_products().size(); i++) {
			// TProduct prod = (TProduct) tt.get_products().get(i);
			TAsociated ta = (TAsociated) tt.get_products().get(i);

			TProduct aux = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(ta.get_idProduct(),
					LockModeType.PESSIMISTIC);
			if (aux == null)
				return false;

			if ((aux.get_stock() - ta.get_cantidad() < 0))
				return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBestProduct(String from, String to) {
		try {
			Pair<Boolean, Pair<Timestamp, Timestamp>> dates = checkDates(from, to);
			if (dates.getKey()) {
				return (List<Object[]>) QueryFactory.getInstance().newQuery(QueryEvents.GET_BEST_PRODUCT)
						.execute(dates.getValue(), LockModeType.PESSIMISTIC);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: Cannot execute the selected query.", "Fatal error",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	private Pair<Boolean, Pair<Timestamp, Timestamp>> checkDates(String from, String to) throws Exception {
		/*Timestamp fromTs = Timestamp.valueOf(from + " 15:20:10");
		Timestamp toTs = Timestamp.valueOf(to + " 15:20:10");
		//Instant i = Instant.parse(fromTs.toString());
		System.out.println(fromTs.toString() + "  " + toTs.toString());*/
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = (Date)formatter.parse(from);
		Timestamp fromTs = new Timestamp(date.getTime());
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		date = (Date)formatter.parse(to);
		Timestamp toTs = new Timestamp(date.getTime());
		return new Pair<Boolean, Pair<Timestamp, Timestamp>>(true, new Pair<Timestamp, Timestamp>(fromTs, toTs));
	}
}