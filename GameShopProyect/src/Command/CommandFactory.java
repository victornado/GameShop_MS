package Command;

public abstract class CommandFactory {
	private static CommandFactory _instance;
	
	public CommandFactory getInstancia() {
		if(_instance == null)
			_instance = new CommandFactoryImp();
		return _instance;
	}
	
	public abstract Command parse(int event);
}
