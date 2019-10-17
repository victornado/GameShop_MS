package Command;


public class CommandFactory {
	private static Command [] _availableCommands= {
			//añadir comandos
			
		};
		public static Command parse(int event) {
			Command c=null;
			int i=0;//contador
			while(c==null && i<_availableCommands.length ) {
				c=_availableCommands[i].parse(event);
				i++;
			}
			return c;
		}
}