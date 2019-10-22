package Integracion.Transacciones;

public abstract class TransactionManager {
	private static TransactionManager _instance;
	
	public static TransactionManager getInstance() {
		if(_instance == null)
			_instance = new TransactionManagerImp();
		return _instance;
	}
	
	public abstract Transaction newTransaction();
	public abstract void deleteTransaction();
	public abstract Transaction getTransaction();
}
