package Integracion.Transacciones;

public interface Transaction {
	/*
	 * Inicia una transacción
	 */
	void init() throws Exception;
	
	/*
	 * Hace commit de todos los cambios realizados por una transacción.
	 */
	void commit();
	
	/*
	 * Deshace todos los cambios realizados por una transaccion
	 */
	void undo();
	
	/*
	 * Devuelve el recurso (objeto Connection) que permite conectarse al sistema transaccional de la BD
	 */
	Object getResource();
}
