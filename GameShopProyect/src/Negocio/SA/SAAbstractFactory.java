package Negocio.SA;

import Negocio.Employee.SAEmployee;
import Negocio.Platform.SAPlatform;
import Negocio.Product.SAProduct;
import Negocio.Provider.SAProvider;
import Negocio.Ticket.SATicket;

/** 
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class SAAbstractFactory {

	private static SAAbstractFactory instance;

	public static SAAbstractFactory getInstance() {
		if(instance == null)
			instance = new SAAbstractFactoryImpl();
		return instance;
	}
	
	public abstract SAEmployee createSAEmployee();
	public abstract SAPlatform createSAPlatform();
	public abstract SAProduct createSAProduct();
	public abstract SAProvider createSAProvider();
	public abstract SATicket createSATicket();
}