package Command;

import javafx.util.Pair;;

public abstract class Command {
	public abstract Pair<Object, Integer> execute(Object data);//devuelve una pair con lo que recibe del SA y el evento asociado
	public abstract Command parse(Integer event);
}
