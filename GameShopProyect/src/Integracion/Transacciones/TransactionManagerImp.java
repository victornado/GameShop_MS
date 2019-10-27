package Integracion.Transacciones;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {

	private ConcurrentHashMap<Thread, Transaction> _transactions;
	
	public TransactionManagerImp() {
		_transactions = new ConcurrentHashMap<Thread, Transaction>();
	}
	
	@Override
	public synchronized Transaction newTransaction() {
		Thread current = Thread.currentThread();
		Transaction tExe = null;
		
		if(!_transactions.contains(current)) {
			tExe = new TransactionGameShopDB();
			_transactions.put(current, tExe);
		}
		else {
			tExe = _transactions.get(current);
		}
		
		return tExe;
	}

	@Override
	public synchronized void deleteTransaction() {
		_transactions.remove(Thread.currentThread());
	}

	@Override
	public synchronized Transaction getTransaction() {
		return _transactions.get(Thread.currentThread());
	}

}
