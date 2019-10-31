package Integracion.Querys;

public abstract class QueryFactory {
	private static QueryFactory _instance;
	
	public static QueryFactory getInstance() {
		if(_instance == null)
			_instance = new QueryFactoryImp();
		return _instance;
	}
	
	public abstract Query newQuery(Integer queryEvent);
}
