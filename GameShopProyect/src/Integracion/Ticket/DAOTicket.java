/**
 * 
 */
package Integracion.Ticket;

import java.util.List;

import Transfers.TTicket;

import java.sql.Timestamp;

public interface DAOTicket {
	
	public Integer createTicket(TTicket tt);
	public Boolean deleteTicket(Integer id);
	public TTicket readTicket(Integer id);
	public List<Object> readAllTickets();
}