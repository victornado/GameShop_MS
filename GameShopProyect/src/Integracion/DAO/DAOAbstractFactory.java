/**
 * 
 */
package Integracion.DAO;

import Integracion.Product.DAOProduct;
import Integracion.Provider.DAOProvider;
import Integracion.Ticket.DAOTicket;


public abstract class DAOAbstractFactory {

	private static DAOAbstractFactory instance;

	public static DAOAbstractFactory getInstance() {
		if(instance == null) instance = new DAOAbstractFactoryImpl();
		return instance;
	}
	public abstract DAOProduct createDAOProduct();
	public abstract DAOProvider createDAOProvider();
	public abstract DAOTicket createDAOTicket();
}