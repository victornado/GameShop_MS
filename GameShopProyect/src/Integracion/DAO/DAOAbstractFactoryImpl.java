/**
 * 
 */
package Integracion.DAO;

import Integracion.Employee.DAOEmployee;
import Integracion.Employee.DAOEmployeeImpl;
import Integracion.Platform.DAOPlatform;
import Integracion.Platform.DAOPlatformImpl;
import Integracion.Product.DAOProduct;
import Integracion.Product.DAOProductImpl;
import Integracion.Provider.DAOProvider;
import Integracion.Provider.DAOProviderImpl;
import Integracion.Ticket.DAOTicket;
import Integracion.Ticket.DAOTicketImpl;


public class DAOAbstractFactoryImpl extends DAOAbstractFactory {
	public DAOEmployee createDAOEmployee() {
		return new DAOEmployeeImpl();
	}

	public DAOPlatform createDAOPlatform() {
		return new DAOPlatformImpl();
	}

	public DAOProduct createDAOProduct() {
		return new DAOProductImpl();
	}

	public DAOProvider createDAOProvider() {
		return new DAOProviderImpl();
	}

	public DAOTicket createDAOTicket() {
		return new DAOTicketImpl();
	}
}