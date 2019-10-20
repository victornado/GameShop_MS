package Command;

import Command.ProductCommands.*;
import Command.ProviderCommands.*;
import Command.TicketCommands.*;

public class CommandFactoryImp extends CommandFactory {
	private static Command[] _availableCommands = {
			new CreateProductCommand(),
			new DeleteProductCommand(),
			new UpdateProductCommand(),
			new ShowOneProductCommand(),
			new ShowAllProductCommand(),
			
			new CreateProviderCommand(),
			new DeleteProviderCommand(),
			new UpdateProviderCommand(),
			new ShowOneProviderCommand(),
			new ShowAllProvidersCommand(),
			
			new CreateTicketCommand(),
			new DeleteTicketCommand(),
			new ShowOneTicketCommand(),
			new ShowALLTicketCommand(),
	};

	@Override
	public Command parse(int event) {
		Command c = null;
		int i = 0; // contador
		while (c == null && i < _availableCommands.length) {
			c = _availableCommands[i].parse(event);
			i++;
		}
		return c;
	}
}