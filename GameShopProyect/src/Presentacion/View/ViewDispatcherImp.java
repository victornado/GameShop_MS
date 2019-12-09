package Presentacion.View;

import Presentacion.Conferencia.GUIConferencia;

import Presentacion.Controller.Event;
import Presentacion.Departamento.GUIDepartment;
import Presentacion.Empleado.GUIEmployee;
import Presentacion.Product.GUIProduct;
import Presentacion.Provider.GUIProvider;
import Presentacion.Ticket.GUITicket;
import utils.Pair;

public class ViewDispatcherImp extends ViewDispatcher {

	@Override
	public void createView(Pair<Object, Integer> data) {
		switch(data.getValue()) {
		/******************************** PROVIDER ********************************/
		case Event.RES_REGISTER_PROVIDER_OK:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_REGISTER_PROVIDER_FAILED:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_PROVIDER_OK:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_PROVIDER_FAILED:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_PROVIDER_OK:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_PROVIDER_FAILED:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_READ_PROVIDER_OK:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_READ_PROVIDER_FAILED:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_PROVIDERS_OK:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_PROVIDERS_FAILED:
			GUIProvider.getInstance().actualiza(data);
			break;
			
		/******************************** PRODUCT ********************************/
		case Event.RES_REGISTER_PRODUCT_OK:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_REGISTER_PRODUCT_FAILED:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_PRODUCT_OK:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_PRODUCT_FAILED:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_PRODUCT_OK:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_PRODUCT_FAILED:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_READ_PRODUCT_OK:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_READ_PRODUCT_FAILED:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_PRODUCT_OK:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_PRODUCT_FAILED:
			GUIProduct.getInstance().actualiza(data);
			break;
			
		/******************************** TICKET ********************************/
		case Event.RES_REGISTER_TICKET_OK:
			GUITicket.getInstance().actualiza(data);
			break;
		case Event.RES_REGISTER_TICKET_FAILED:
			GUITicket.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_TICKET_OK:
			GUITicket.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_TICKET_FAILED:
			GUITicket.getInstance().actualiza(data);
			break;
		case Event.RES_READ_TICKET_OK:
			GUITicket.getInstance().actualiza(data);
			break;
		case Event.RES_READ_TICKET_FAILED:
			GUITicket.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_TICKET_OK:
			GUITicket.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_TICKET_FAILED:
			GUITicket.getInstance().actualiza(data);
			break;
			
		/******************************* CONFERENCIA *****************************/
		/*case Event.UPDATE_LIST_EMPLOYEE:
			GUIConferencia.getInstance().actualiza(data);
			break;*/
		case Event.RES_REGISTER_CONFERENCE_OK:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_REGISTER_CONFERENCE_FAILED:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_CONFERENCE_OK:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_CONFERENCE_FAILED:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_CONFERENCE_OK:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_CONFERENCE_FAILED:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_READ_CONFERENCE_OK:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_READ_CONFERENCE_FAILED:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_CONFERENCE_OK:
			GUIConferencia.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_CONFERENCE_FAILED:
			GUIConferencia.getInstance().actualiza(data);
			break;
			
		/****************************** DEPARTAMENTO *****************************/
		case Event.RES_REGISTER_DEPARTMENT_OK:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_REGISTER_DEPARTMENT_FAILED:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_DEPARTMENT_OK:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_DEPARTMENT_FAILED:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_DEPARTMENT_OK:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_DEPARTMENT_FAILED:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_READ_DEPARTMENT_OK:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_READ_DEPARTMENT_FAILED:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_DEPARTMENT_OK:
			GUIDepartment.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_DEPARTMENT_FAILED:
			GUIDepartment.getInstance().actualiza(data);
			break;
			
		/******************************** EMPLEADO *******************************/
		case Event.UPDATE_LIST_EMPLOYEE: // Para actualizar la lista de depatamentos en el FormEmployee
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_REGISTER_EMPLOYEE_OK:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_REGISTER_EMPLOYEE_FAILED:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_EMPLOYEE_OK:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_UNSUBSCRIBE_EMPLOYEE_FAILED:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_EMPLOYEE_OK:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_MODIFY_EMPLOYEE_FAILED:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_READ_EMPLOYEE_OK:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_READ_EMPLOYEE_FAILED:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_EMPLOYEE_OK:
			GUIEmployee.getInstance().actualiza(data);
			break;
		case Event.RES_READALL_EMPLOYEE_FAILED:
			GUIEmployee.getInstance().actualiza(data);
			break;
			
			
		/******************************** QUERIES ********************************/
		case Event.SHOW_PROVIDER_QUERY_OK:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.SHOW_PROVIDER_QUERY_FAILED:
			GUIProvider.getInstance().actualiza(data);
			break;
		case Event.SHOW_PRODUCT_QUERY_OK:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.SHOW_PRODUCT_QUERY_FAILED:
			GUIProduct.getInstance().actualiza(data);
			break;
		case Event.SHOW_TICKET_QUERY_OK:
			GUITicket.getInstance().actualiza(data);
			break;
		case Event.SHOW_TICKET_QUERY_FAILED:
			GUITicket.getInstance().actualiza(data);
			break;
		}
	}

}
