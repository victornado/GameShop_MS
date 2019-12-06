package Presentacion.Ticket;

import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TProductQuantity;
import Presentacion.Controller.Event;
import Presentacion.View.GUIGameshopImp;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import utils.Pair;


/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
@SuppressWarnings("serial")
public class GUITicketImp extends GUITicket {
	
	private OperationsPanel _leftPane;
	private ShowPanel _rightPane;
	
	@Override
	protected void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshopImp.TAB_TICKET);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshopImp.TAB_TICKET);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Pair<Object, Integer> data) {
		switch(data.getValue()){
		case Event.RES_REGISTER_TICKET_OK:
			Integer id = (Integer)data.getKey();
			JOptionPane.showMessageDialog(null, "Ticket " + id + " has been correctly inserted into the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			//_rightPane.update((SAAbstractFactory.getInstance().createSATicket()).readAllTickets());
			_leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_REGISTER_TICKET_FAILED:
			JOptionPane.showMessageDialog(null, "Error inserting the ticket in the database.", "Failed",
					JOptionPane.ERROR_MESSAGE);
			break;
			
		case Event.RES_UNSUBSCRIBE_TICKET_OK:
			Integer a = (Integer)data.getKey();
			JOptionPane.showMessageDialog(null, "The ticket " + a + " has been properly unsubscribed.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSATicket()).readAllTickets());
			_leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_UNSUBSCRIBE_TICKET_FAILED:
			JOptionPane.showMessageDialog(null, "Error deleting the ticket in the database.", "Failed",
					JOptionPane.ERROR_MESSAGE);
			break;
			
		case Event.RES_READ_TICKET_OK:
			TProductQuantity tt = (TProductQuantity)data.getKey();
			_rightPane.setInfoInScreen(tt.toString());
			break;
			
		case Event.RES_READ_TICKET_FAILED:
			JOptionPane.showMessageDialog(null, "Error showing a ticket in the database.", "Failed",
					JOptionPane.ERROR_MESSAGE);
			break;
			
		case Event.RES_READALL_TICKET_OK:
			_rightPane.update((List<Object>)data.getKey());
			break;
			
		case Event.RES_READALL_TICKET_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing all tickets in the database.","Failed",JOptionPane.ERROR_MESSAGE);
			break;
		case Event.SHOW_TICKET_QUERY_OK:
			new TicketChart((ArrayList<Object[]>)data.getKey());
			break;
		case Event.SHOW_TICKET_QUERY_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing the selected query.","Failed",JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}