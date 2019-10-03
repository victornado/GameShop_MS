
package Negocio.Ticket;

import java.util.List;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Ticket.DAOTicket;
import Transfers.TProduct;
import Transfers.TTicket;


public class SATicketImpl implements SATicket {
	
	public Integer createTicket(TTicket tt) {
		Integer res = -1;
		TTicket tti = (TTicket)tt;
		if(/*prodListNotEmpty(tt) && */correctInputData(tt)){ //modificar stock de cada producto, calcular precio final
			
			double preciofin=0.0;
			int units=0;
			for(int i = 0; i < tti.get_products().size(); ++i){
				TProduct tp = (TProduct) tti.get_products().get(i);
				//units = tp.get_unitsProvided();
				units = tp.get_unitsInTicket();
				tp = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(tp.get_id());
				preciofin += (tp.get_pvp() * units);
			}
			
			tti.set_finalPrice(preciofin);
			res = DAOAbstractFactory.getInstance().createDAOTicket().createTicket(tti);
			
			if(res > 0){
				//Si se ha creado el ticket correctamente, actualizamos los valores del stock en la base de datos
				units=0;
				for(int i = 0; i < tti.get_products().size(); ++i){
					TProduct tp = (TProduct) tti.get_products().get(i);
					units = tp.get_unitsInTicket();
					tp = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(tp.get_id());
					tp.set_stock(tp.get_stock() - units);
					DAOAbstractFactory.getInstance().createDAOProduct().updateProduct(tp);
				}
			}
			 
		}
		return res;
	}

	public Boolean deleteTicket(Integer id) {
		boolean deleted = false;
		DAOTicket daoTicket = DAOAbstractFactory.getInstance().createDAOTicket();
		
		if(id != null)
			deleted = daoTicket.deleteTicket(id);
		
		return deleted;
	}

	public Object readTicket(Integer id) {
		TTicket ret = null;
		DAOTicket daoTicket = DAOAbstractFactory.getInstance().createDAOTicket();
		if(id != null){
			ret = (TTicket)daoTicket.readTicket(id);
		}
		return ret;
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
	
	private boolean correctInputData(TTicket tt)
	{ //comprobamos que todos los datos introducidos son correctos y existen
	
		//Empleado existe y esta activo
		if(DAOAbstractFactory.getInstance().createDAOEmployee().readEmployee(tt.get_employeeId())==null)
			return false;
		
		//Cada uno de los productos existen y hay stock suficiente
		int prodAmount = tt.get_products().size();
		for(int i=0;i<prodAmount;i++)
		{
			TProduct prod = (TProduct) tt.get_products().get(i);
			TProduct aux = DAOAbstractFactory.getInstance().createDAOProduct().readProduct(prod.get_id());
			if(aux==null)
				return false;
			//if((aux.get_stock() - prod.get_unitsProvided()<0))
			if((aux.get_stock() - prod.get_unitsInTicket() < 0))
				return false;
		}
		return true;
	}
}