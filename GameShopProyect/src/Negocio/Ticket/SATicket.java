package Negocio.Ticket;

import java.util.List;

import Negocio.Transfers.TTicket;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author joalow
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface SATicket {
	
	public Integer createTicket(TTicket tt);
	public Boolean deleteTicket(Integer id);
	public Object TOAReadTicket(Integer id) throws Exception;
	public List<Object> readAllTickets();
	public List<Object[]> getBestProduct(String from, String to);
}