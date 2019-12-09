package Presentacion.Command;

import java.util.HashMap;


import Presentacion.Command.ConferenceCommands.*;
import Presentacion.Command.DepartmentCommands.*;
import Presentacion.Command.EmployeeCommands.*;
import Presentacion.Command.ProductCommands.*;
import Presentacion.Command.ProviderCommands.*;
import Presentacion.Command.TicketCommands.*;
import Presentacion.Controller.Event;

public class CommandFactoryImp extends CommandFactory {
	private static HashMap<Integer, Command> _availableCommands = new HashMap<Integer, Command>();

	@Override
	public Command parse(int event) {
		return _availableCommands.get(event);
	}

	@Override
	protected void createAvailableCommands() {
		/********************************** PROVIDER **********************************/
		_availableCommands.put(Event.REGISTER_PROVIDER, new CreateProviderCommand());
		_availableCommands.put(Event.UNSUBSCRIBE_PROVIDER, new DeleteProviderCommand());
		_availableCommands.put(Event.MODIFY_PROVIDER, new UpdateProviderCommand());
		_availableCommands.put(Event.READ_PROVIDER, new ShowOneProviderCommand());
		_availableCommands.put(Event.READ_ALL_PROVIDERS, new ShowAllProvidersCommand());

		/********************************** PRODUCT **********************************/
		_availableCommands.put(Event.REGISTER_PRODUCT, new CreateProductCommand());
		_availableCommands.put(Event.UNSUBSCRIBE_PRODUCT, new DeleteProductCommand());
		_availableCommands.put(Event.MODIFY_PRODUCT, new UpdateProductCommand());
		_availableCommands.put(Event.READ_PRODUCT, new ShowOneProductCommand());
		_availableCommands.put(Event.READ_ALL_PRODUCT, new ShowAllProductCommand());

		/********************************** TICKET **********************************/
		_availableCommands.put(Event.REGISTER_TICKET, new CreateTicketCommand());
		_availableCommands.put(Event.UNSUBSCRIBE_TICKET, new DeleteTicketCommand());
		_availableCommands.put(Event.READ_TICKET, new ShowOneTicketCommand());
		_availableCommands.put(Event.READ_ALL_TICKET, new ShowALLTicketCommand());

		/******************************** CONFERENCE ********************************/
		_availableCommands.put(Event.REGISTER_CONFERENCE, new CreateConferenceCommand());
		_availableCommands.put(Event.UNSUBSCRIBE_CONFERENCE, new DeleteConferenceCommand());
		_availableCommands.put(Event.MODIFY_CONFERENCE, new UpdateConferenceCommand());
		_availableCommands.put(Event.READ_CONFERENCE, new ShowOneConferenceCommand());
		_availableCommands.put(Event.READ_ALL_CONFERENCE, new ShowAllConferenceCommand());

		/******************************** DEPARTMENT ********************************/
		_availableCommands.put(Event.REGISTER_DEPARTMENT, new CreateDepartmentCommand());
		_availableCommands.put(Event.UNSUBSCRIBE_DEPARTMENT, new DeleteDepartmentCommand());
		_availableCommands.put(Event.MODIFY_DEPARTMENT, new UpdateDepartmentCommand());
		_availableCommands.put(Event.READ_DEPARTMENT, new ShowOneDepartmentCommand());
		_availableCommands.put(Event.READ_ALL_DEPARTMENT, new ShowAllDepartmentCommand());

		/********************************* EMPLOYEE *********************************/
		_availableCommands.put(Event.REGISTER_EMPLOYEE, new CreateEmployeeCommand());
		_availableCommands.put(Event.UNSUBSCRIBE_EMPLOYEE, new DeleteEmployeeCommand());
		_availableCommands.put(Event.MODIFY_EMPLOYEE, new UpdateEmployeeCommand());
		_availableCommands.put(Event.READ_EMPLOYEE, new ShowOneEmployeeCommand());
		_availableCommands.put(Event.READ_ALL_EMPLOYEE, new ShowAllEmployeeCommand());
		
		_availableCommands.put(Event.UPDATE_LIST_EMPLOYEE, new UpdateComboBoxDpto());

		/********************************** QUERIES **********************************/
		_availableCommands.put(Event.SHOW_PROVIDER_QUERY, new BestProviderQueryCommand());
		_availableCommands.put(Event.SHOW_PRODUCT_QUERY, new ProductCountQueryCommand());
		_availableCommands.put(Event.SHOW_TICKET_QUERY, new ProductsDateQueryCommand());
	}
}