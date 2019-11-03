package Integracion.Querys;

public interface Query {
	Object execute(Object data, Integer lock) throws Exception;
}
