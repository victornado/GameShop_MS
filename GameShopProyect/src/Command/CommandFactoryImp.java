package Command;

import java.util.HashMap;

import Command.ProductCommands.*;
import Command.ProviderCommands.*;
import Command.TicketCommands.*;
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
		_availableCommands.put(Event.REGISTER_PROVIDER, new CreateProductCommand());
		_availableCommands.put(Event.UNSUBSCRIBE_PROVIDER, new DeleteProductCommand());
		_availableCommands.put(Event.MODIFY_PROVIDER, new UpdateProductCommand());
		_availableCommands.put(Event.READ_PROVIDER, new ShowOneProductCommand());
		_availableCommands.put(Event.READ_ALL_PROVIDERS, new ShowAllProductCommand());
		
		/********************************** TICKET **********************************/
		_availableCommands.put(Event.REGISTER_TICKET, new CreateTicketCommand());
		_availableCommands.put(Event.UNSUBSCRIBE_TICKET, new DeleteTicketCommand());
		_availableCommands.put(Event.READ_TICKET, new ShowOneTicketCommand());
		_availableCommands.put(Event.READ_ALL_TICKET, new ShowALLTicketCommand());
	}
}