package Command;

public abstract class CommandFactory {
	private static CommandFactory _instance;
	
	public static CommandFactory getInstance() {
		if(_instance == null)
			_instance = new CommandFactoryImp();
		return _instance;
	}
	
	public abstract Command parse(int event);
}
