/**
 * 
 */
package Integracion.Ticket;

import java.util.List;

import Negocio.Transfers.TTicket;

public interface DAOTicket {
	
	public Integer createTicket(TTicket tt) throws Exception;
	public Boolean deleteTicket(Integer id)throws Exception;
	public TTicket readTicket(Integer id, Integer lock)throws Exception;
	public List<Object> readAllTickets(Integer lock)throws Exception;
}