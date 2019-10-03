package Negocio.SA;

import Negocio.Employee.SAEmployee;
import Negocio.Employee.SAEmployeeImpl;
import Negocio.Platform.SAPlatform;
import Negocio.Platform.SAPlatformImpl;
import Negocio.Product.SAProduct;
import Negocio.Product.SAProductImpl;
import Negocio.Provider.SAProvider;
import Negocio.Provider.SAProviderImpl;
import Negocio.Ticket.SATicket;
import Negocio.Ticket.SATicketImpl;

/** 
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAAbstractFactoryImpl extends SAAbstractFactory {
	
	public SAEmployee createSAEmployee() {
		return new SAEmployeeImpl();
	}

	public SAPlatform createSAPlatform() {
		return new SAPlatformImpl();
	}

	public SAProduct createSAProduct() {
		return new SAProductImpl();
	}

	public SAProvider createSAProvider() {
		return new SAProviderImpl();
	}

	public SATicket createSATicket() {
		return new SATicketImpl();
	}

	
	
}