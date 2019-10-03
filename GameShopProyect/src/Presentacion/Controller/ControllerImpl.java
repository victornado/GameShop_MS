package Presentacion.Controller;

import java.util.ArrayList;
import java.util.List;

import Main.Main;
import Negocio.SA.SAAbstractFactory;
import Presentacion.Employee.FormUpdateEmployee;
import Presentacion.Employee.GUIEmployee;
import Presentacion.Platform.FormUpdatePlatform;
import Presentacion.Platform.GUIPlatform;
import Presentacion.Product.GUIProduct;
import Presentacion.Provider.FormUpdateProvider;
import Presentacion.Provider.GUIProvider;
import Presentacion.Ticket.GUITicket;
import Presentacion.View.GUIGameshop;
import Presentacion.View.IGUI;
import Transfers.TEmployee;
import Transfers.TPlatform;
import Transfers.TProduct;
import Transfers.TProvider;
import Transfers.TTicket;

/** 
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ControllerImpl extends Controller {
	
	private IGUI gui;
	private GUIGameshop gs;
	
	public ControllerImpl() {
		gs = new GUIGameshop(Main.applicationName);
		List<Object> guis = new ArrayList<Object>();
		guis.add((new GUIProvider()));
		guis.add((new GUIPlatform()));
		guis.add((new GUIEmployee()));
		guis.add((new GUIProduct()));
		guis.add((new GUITicket()));
		gs.initTabs(guis);
	}

	@Override
	public void action(Object data, Integer event) {
		Integer id;
		TProvider tpr;
		TEmployee tpe;
		TPlatform tpla;
		TProduct tprod;
		TTicket tti;
		
		gui = gs.getGuiAt(event/100 - 1);

		switch(event) {
		
		////////////////////////////////////////////////////////////// PROVIDER ///////////////////////////////////////////////
		case Event.REGISTER_PROVIDER:
			tpr = (TProvider)data;
			int resRegisterProv = (SAAbstractFactory.getInstance().createSAProvider()).createProvider(tpr);
			if(resRegisterProv > 0)
				gui.actualiza(Event.RES_REGISTER_PROVIDER_OK, new Integer(resRegisterProv));
			else if (resRegisterProv <= 0)
				gui.actualiza(Event.RES_REGISTER_PROVIDER_FAILED, null);
			break;
			
		case Event.UNSUBSCRIBE_PROVIDER:
			id = (Integer)data;
			boolean resDeleteProv = (SAAbstractFactory.getInstance().createSAProvider()).deleteProvider(id);
			if(resDeleteProv)
				gui.actualiza(Event.RES_UNSUBSCRIBE_PROVIDER_OK, id);
			else
				gui.actualiza(Event.RES_UNSUBSCRIBE_PROVIDER_FAILED, null);
			break;
			
		case Event.MODIFY_PROVIDER:
			tpr = (TProvider)data;
			if(SAAbstractFactory.getInstance().createSAProvider().updateProvider(tpr))
				gui.actualiza(Event.RES_MODIFY_PROVIDER_OK, null);
			else
				gui.actualiza(Event.RES_MODIFY_PROVIDER_FAILED, null);
			break;
			
		case Event.READ_PROVIDER:
			id = (Integer)data;
			TProvider t = (TProvider)(SAAbstractFactory.getInstance().createSAProvider()).readProvider(id);
			if (t != null) 
				gui.actualiza(Event.RES_READ_PROVIDER_OK, t);
			else
				gui.actualiza(Event.RES_READ_PROVIDER_FAILED, null);
			break;
			
		case Event.READ_ALL_PROVIDERS:
			List<Object> providers = (SAAbstractFactory.getInstance().createSAProvider()).readAllProviders();
			if(providers == null)
				gui.actualiza(Event.RES_READALL_PROVIDERS_FAILED, null);
			else
				gui.actualiza(Event.RES_READALL_PROVIDERS_OK, providers);
			break;
			
			
		////////////////////////////////////////////////////////////// EMPLOYEE ///////////////////////////////////////////////	
			
		case Event.REGISTER_EMPLOYEE:
			tpe = (TEmployee)data;
			int resRegisterEmp = (SAAbstractFactory.getInstance().createSAEmployee()).createEmployee(tpe);
			if(resRegisterEmp > 0)
				gui.actualiza(Event.RES_REGISTER_EMPLOYEE_OK, new Integer(resRegisterEmp));
			else
				gui.actualiza(Event.RES_REGISTER_EMPLOYEE_FAILED, null);
			break;
			
		case Event.UNSUBSCRIBE_EMPLOYEE:
			id = (Integer)data;
			boolean resDeleteEmp = (SAAbstractFactory.getInstance().createSAEmployee()).deleteEmployee(id);
			if(resDeleteEmp)
				gui.actualiza(Event.RES_UNSUBSCRIBE_EMPLOYEE_OK, id);
			else
				gui.actualiza(Event.RES_UNSUBSCRIBE_EMPLOYEE_FAILED, null);
			break;
			
		case Event.MODIFY_EMPLOYEE:
			tpe = (TEmployee)data;
			if(SAAbstractFactory.getInstance().createSAEmployee().updateEmployee(tpe))
				gui.actualiza(Event.RES_MODIFY_EMPLOYEE_OK, tpe.get_id());
			else
				gui.actualiza(Event.RES_MODIFY_EMPLOYEE_FAILED, null);
			break;
			
		case Event.READ_EMPLOYEE:
			id = (Integer)data;
			TEmployee te = (TEmployee)(SAAbstractFactory.getInstance().createSAEmployee()).readEmployee(id);
			if (te != null) 
				gui.actualiza(Event.RES_READ_EMPLOYEE_OK, te);
			else
				gui.actualiza(Event.RES_READ_EMPLOYEE_FAILED, null);
			break;
			
		case Event.READ_ALL_EMPLOYEES:
			List<Object> employees = (SAAbstractFactory.getInstance().createSAEmployee()).readAllEmployees();
			if(employees == null)
				gui.actualiza(Event.RES_READALL_EMPLOYEES_FAILED, null);
			else
				gui.actualiza(Event.RES_READALL_EMPLOYEES_OK, employees);
			break;
		////////////////////////////////////////////////////////////// PRODUCT ////////////////////////////////////////////////
			
		case Event.REGISTER_PRODUCT:
			tprod = (TProduct) data;
			int resRegister= (SAAbstractFactory.getInstance().createSAProduct()).createProduct(tprod);
			if(resRegister > 0)
				gui.actualiza(Event.RES_REGISTER_PRODUCT_OK, new Integer(resRegister));
			else
				gui.actualiza(Event.RES_REGISTER_PRODUCT_FAILED, null);
			break;
			
		case Event.UNSUBSCRIBE_PRODUCT:
			id = (Integer)data;
			boolean resDelete = (SAAbstractFactory.getInstance().createSAProduct()).deleteProduct(id);
			if(resDelete)
				gui.actualiza(Event.RES_UNSUBSCRIBE_PRODUCT_OK, id);
			else
				gui.actualiza(Event.RES_UNSUBSCRIBE_PRODUCT_FAILED, null);
			break;
			
		case Event.MODIFY_PRODUCT:
			tprod = (TProduct) data;
			if(SAAbstractFactory.getInstance().createSAProduct().updateProduct(tprod))
				gui.actualiza(Event.RES_MODIFY_PRODUCT_OK, tprod.get_id());
			else
				gui.actualiza(Event.RES_MODIFY_PRODUCT_FAILED, null);
			break;
			
		case Event.READ_PRODUCT:
			id = (Integer)data;
			tprod = (TProduct) (SAAbstractFactory.getInstance().createSAProduct()).readProduct(id);
			if (tprod != null) 
				gui.actualiza(Event.RES_READ_PRODUCT_OK, tprod);
			else
				gui.actualiza(Event.RES_READ_PRODUCT_FAILED, null);
			break;
			
		case Event.READ_ALL_PRODUCT:
			List<Object> products = (SAAbstractFactory.getInstance().createSAProduct()).readAllProducts();
			if(products == null)
				gui.actualiza(Event.RES_READALL_PRODUCT_FAILED, null);
			else
				gui.actualiza(Event.RES_READALL_PRODUCT_OK, products);
			break;
			
		////////////////////////////////////////////////////////////// PLATFORM ///////////////////////////////////////////////
			
		case Event.REGISTER_PLATFORM:
			tpla = (TPlatform)data;
			int resRegisterPla = (SAAbstractFactory.getInstance().createSAPlatform()).createPlatform(tpla);
			if(resRegisterPla > 0)
				gui.actualiza(Event.RES_REGISTER_PLATFORM_OK, new Integer(resRegisterPla));
			else
				gui.actualiza(Event.RES_REGISTER_PLATFORM_FAILED, null);
			break;
			
		case Event.UNSUBSCRIBE_PLATFORM:
			id = (Integer) data;
			boolean resDeletePla = (SAAbstractFactory.getInstance().createSAPlatform()).deletePlatform(id);
			if(resDeletePla)
				gui.actualiza(Event.RES_UNSUBSCRIBE_PLATFORM_OK, id);
			else
				gui.actualiza(Event.RES_UNSUBSCRIBE_PLATFORM_FAILED, null);
			break;
		
		case Event.MODIFY_PLATFORM:
			tpla = (TPlatform) data;
			if(SAAbstractFactory.getInstance().createSAPlatform().updatePlatform(tpla))
				gui.actualiza(Event.RES_MODIFY_PLATFORM_OK, tpla.get_id());
			else
				gui.actualiza(Event.RES_MODIFY_PLATFORM_FAILED, null);
			break;
			
		case Event.READ_PLATFORM:
			id = (Integer) data;
			TPlatform tt = (TPlatform) (SAAbstractFactory.getInstance().createSAPlatform()).readPlatform(id);
			if (tt != null) 
				gui.actualiza(Event.RES_READ_PLATFORM_OK, tt);
			else
				gui.actualiza(Event.RES_READ_PLATFORM_FAILED, null);
			break;
			
		case Event.READ_ALL_PLATFORMS:
			List<Object> platforms = (SAAbstractFactory.getInstance().createSAPlatform()).readAllPlatforms();
			if(platforms == null)
				gui.actualiza(Event.RES_READALL_PLATFORM_FAILED, null);
			else
				gui.actualiza(Event.RES_READALL_PLATFORM_OK, platforms);
			break;
		case Event.READ_ALL_PRODUCTS_FROM_PLATFORM:
			id = (Integer) data;
			List<Object> prods = SAAbstractFactory.getInstance().createSAPlatform().readAllProductsOfAPlatform(id);
			if(prods != null)
				gui.actualiza(Event.RES_READALL_PRODUCTS_FROM_PLATFORM_OK, prods);
			else
				gui.actualiza(Event.RES_READALL_PRODUCTS_FROM_PLATFROM_FAILED, null);
			break;
			
		////////////////////////////////////////////////////////////// TICKET ///////////////////////////////////////////////	
		case Event.REGISTER_TICKET:
			tti = (TTicket)data;
			int resRegisterTicket = (SAAbstractFactory.getInstance().createSATicket().createTicket(tti));
			if(resRegisterTicket > 0)
				gui.actualiza(Event.RES_REGISTER_TICKET_OK, new Integer(resRegisterTicket));
			else
				gui.actualiza(Event.RES_REGISTER_TICKET_FAILED, null);
			break;
		case Event.UNSUBSCRIBE_TICKET:
			id = (Integer) data;
			boolean resDeleteTi = (SAAbstractFactory.getInstance().createSATicket()).deleteTicket(id);
			if(resDeleteTi)
				gui.actualiza(Event.RES_UNSUBSCRIBE_TICKET_OK, id);
			else
				gui.actualiza(Event.RES_UNSUBSCRIBE_TICKET_FAILED, null);
			break;
		case Event.READ_TICKET:
			id = (Integer) data;
			TTicket ticket = (TTicket)(SAAbstractFactory.getInstance().createSATicket().readTicket(id));
			if (ticket != null) 
				gui.actualiza(Event.RES_READ_TICKET_OK, ticket);
			else
				gui.actualiza(Event.RES_READ_TICKET_FAILED, null);
			break;
		case Event.READ_ALL_TICKET:
			List<Object> tickets = (SAAbstractFactory.getInstance().createSATicket().readAllTickets());
			if(tickets == null)
				gui.actualiza(Event.RES_READALL_TICKET_FAILED, null);
			else
				gui.actualiza(Event.RES_READALL_TICKET_OK, tickets);
			break;
		}
	}
	
}