package Presentacion.Command;

public abstract class CommandFactory {
	private static CommandFactory _instance;
	
	public static CommandFactory getInstance() {
		if(_instance == null) {
			_instance = new CommandFactoryImp();
			_instance.createAvailableCommands();
		}
		return _instance;
	}
	
	public abstract Command parse(int event);
	protected abstract void createAvailableCommands();
}
