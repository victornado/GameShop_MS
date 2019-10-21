package Presentacion.View;

import Presentacion.Controller.Event;
import Presentacion.Provider.GUIProvider;
import Transfers.*;

public class ViewDispatcherImp extends ViewDispatcher {

	@Override
	public void createView(Integer event, Object data) {
		switch(event) {
		/******************************** PROVIDER ********************************/
		case Event.RES_REGISTER_PROVIDER_OK:
			GUIProvider.getInstance().actualiza(event, (Integer)data);
			break;
		case Event.RES_REGISTER_PROVIDER_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_UNSUBSCRIBE_PROVIDER_OK:
			GUIProvider.getInstance().actualiza(event, (Integer)data);
			break;
		case Event.RES_UNSUBSCRIBE_PROVIDER_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_MODIFY_PROVIDER_OK:
			GUIProvider.getInstance().actualiza(event, (Integer)data);
			break;
		case Event.RES_MODIFY_PROVIDER_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_READ_PROVIDER_OK:
			GUIProvider.getInstance().actualiza(event, (TProvider)data);
			break;
		case Event.RES_READ_PROVIDER_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_READALL_PROVIDERS_OK:
			GUIProvider.getInstance().actualiza(event, data); // TODO ver que se le pasa a actualiza
			break;
		case Event.RES_READALL_PROVIDERS_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
			
		/******************************** PRODUCT ********************************/
		case Event.RES_REGISTER_PRODUCT_OK:
			GUIProvider.getInstance().actualiza(event, (Integer)data);
			break;
		case Event.RES_REGISTER_PRODUCT_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_UNSUBSCRIBE_PRODUCT_OK:
			GUIProvider.getInstance().actualiza(event, (Integer)data);
			break;
		case Event.RES_UNSUBSCRIBE_PRODUCT_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_MODIFY_PRODUCT_OK:
			GUIProvider.getInstance().actualiza(event, (Integer)data);
			break;
		case Event.RES_MODIFY_PRODUCT_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_READ_PRODUCT_OK:
			GUIProvider.getInstance().actualiza(event, (TProvider)data);
			break;
		case Event.RES_READ_PRODUCT_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_READALL_PRODUCT_OK:
			GUIProvider.getInstance().actualiza(event, data); // TODO ver que se le pasa a actualiza
			break;
		case Event.RES_READALL_PRODUCT_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
			
		/******************************** TICKET ********************************/
		case Event.RES_REGISTER_TICKET_OK:
			GUIProvider.getInstance().actualiza(event, (Integer)data);
			break;
		case Event.RES_REGISTER_TICKET_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_UNSUBSCRIBE_TICKET_OK:
			GUIProvider.getInstance().actualiza(event, (Integer)data);
			break;
		case Event.RES_UNSUBSCRIBE_TICKET_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_READ_TICKET_OK:
			GUIProvider.getInstance().actualiza(event, (TProvider)data);
			break;
		case Event.RES_READ_TICKET_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		case Event.RES_READALL_TICKET_OK:
			GUIProvider.getInstance().actualiza(event, data); // TODO ver que se le pasa a actualiza
			break;
		case Event.RES_READALL_TICKET_FAILED:
			GUIProvider.getInstance().actualiza(event, null);
			break;
		}
	}

}