
package Negocio.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Product.DAOProduct;
import Integracion.Ticket.DAOTicket;
import Negocio.SA.SAAbstractFactory;
import Transfers.TAsociated;
import Transfers.TProduct;
import Transfers.TProductQuantity;
import Transfers.TTicket;
import javafx.util.Pair;
import Integracion.Querys.Query;
import Integracion.Querys.QueryFactory;


public class SATicketImpl implements SATicket {
	
	public Integer createTicket(TTicket tt) {
		Integer res = -1;
		TTicket tti = (TTicket)tt;
		if(correctInputData(tt)){ //modificar stock de cada producto, calcular precio final
			
			double preciofin=0.0;
			int units=0;
			for(int i = 0; i < tti.get_products().size(); ++i){
				//TProduct tp = (TProduct) tti.get_products().get(i);
				TAsociated ta = (TAsociated) tti.get_products().get(i);

				//units = tp.get_unitsInTicket();
				//tp = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(tp.get_id());
				//preciofin += (tp.get_pvp() * units);
				units = ta.get_cantidad();
				preciofin += ta.get_precio() * units;
			}
			
			tti.set_finalPrice(preciofin);
			res = DAOAbstractFactory.getInstance().createDAOTicket().createTicket(tti);
			
			if(res > 0){
				//Si se ha creado el ticket correctamente, actualizamos los valores del stock en la base de datos
				units=0;
				for(int i = 0; i < tti.get_products().size(); ++i){
					//TProduct tp = (TProduct) tti.get_products().get(i);
					TAsociated ta = (TAsociated) tti.get_products().get(i);
					
					//units = tp.get_unitsInTicket();
					//tp = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(tp.get_id());
					//DAOAbstractFactory.getInstance().createDAOProduct().updateProduct(tp);
					units = ta.get_cantidad();
					TProduct tp = (TProduct)SAAbstractFactory.getInstance().createSAProduct().readProduct(ta.get_idProduct());
					
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
		
		if(id != null) {
			TTicket tt = (TTicket)daoTicket.readTicket(id);
			for(int i = 0; i < tt.get_products().size(); i++) {
				TAsociated ta = (TAsociated)tt.get_products().get(i);
				TProduct tp = daoProduct.readProduct(ta.get_idProduct());
				if(tp!=null) {
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
		DAOProduct dp = DAOAbstractFactory.getInstance().createDAOProduct();
		List<Object> pl = null;
		
		if(id != null){
			tt = (TTicket)daoTicket.readTicket(id);
			toa = new TProductQuantity(id, tt.get_finalPrice(), tt.get_date(), null);
			pl = new ArrayList<Object>();
			pl = tt.get_products();
			Query q = QueryFactory.getInstance().newQuery("PONER AQUI EL EVENTO DE QUERY");
			
			// TODO si no funciona el casteo hacer aqui un while() y cambiar la query
			toa.set_productsToShow((HashMap<Integer, Pair<String, Integer>>)q.execute(id));
		}
		return toa;
	}
	
	public List<Object> readAllTickets() {
		List<Object> tickets = null;
		tickets = DAOAbstractFactory.getInstance().createDAOTicket().readAllTickets();
		return tickets;
	}
	
	/*private boolean prodListNotEmpty(TTicket tt)
	{ //damos por hecho en el DAO que la lista no llega vacia ===> Aqui se comprueba si esta vacia o no
		return tt.get_products().size()>0;
	}*/
	
	private boolean correctInputData(TTicket tt){ //comprobamos que todos los datos introducidos son correctos y existen
		
		//Cada uno de los productos existen y hay stock suficiente
		for(int i=0;i < tt.get_products().size();i++)
		{
			//TProduct prod = (TProduct) tt.get_products().get(i);
			TAsociated ta = (TAsociated) tt.get_products().get(i);
			
			TProduct aux = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(ta.get_idProduct());
			if(aux==null)
				return false;
		
			if((aux.get_stock() - ta.get_cantidad() < 0))
				return false;
		}
		return true;
	}
}