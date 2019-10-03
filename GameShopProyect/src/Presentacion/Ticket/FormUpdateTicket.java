package Presentacion.Ticket;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Transfers.TTicket;

@SuppressWarnings("serial")
public class FormUpdateTicket extends FormTicket {
	
	private TTicket _tTicket;
	
	public FormUpdateTicket(TTicket tt) {
		super();
		this.setTitle("Modify a ticket");
		this.setSize(new Dimension(320, 300));
		super.disableEmployeeElection();
		_tTicket = tt;
		completeFields();
	}
	
	private void completeFields() {
		disableEmployeeElection();
		
	}
	
	private void acceptButtonAction() {
		_accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
