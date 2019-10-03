package Presentacion.Provider;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.SA.SAAbstractFactory;
import Presentacion.Controller.Event;
import Presentacion.View.GUIGameshop;
import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import Transfers.TProvider;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
@SuppressWarnings("serial")
public class GUIProvider extends JPanel implements IGUI {
	
	private OperationsPanel _leftPane;
	private ShowPanel _rightPane;
	
	public GUIProvider() {
		alignmentPanels();
	}
	
	private void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshop.TAB_PROVIDER);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshop.TAB_PROVIDER);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Integer e, Object t) {
		Integer id;
		switch(e){
		case Event.RES_REGISTER_PROVIDER_OK:
			id = (Integer)t;
			JOptionPane.showMessageDialog(null, "Provider " + id + " has been correctly insertes into the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAProvider()).readAllProviders());
			_leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_REGISTER_PROVIDER_FAILED:
			JOptionPane.showMessageDialog(this, "Error entering the provider in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_UNSUBSCRIBE_PROVIDER_OK:
			id = (Integer)t;
			JOptionPane.showMessageDialog(null, "The provider " + id + " has been successfully unsubscribed.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAProvider()).readAllProviders());
			break;
			
		case Event.RES_UNSUBSCRIBE_PROVIDER_FAILED:
			JOptionPane.showMessageDialog(this, "Error while removing the provider in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_MODIFY_PROVIDER_FAILED:
			JOptionPane.showMessageDialog(this, "Error while modifying the provider in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_MODIFY_PROVIDER_OK:
			id = (Integer)t;
			JOptionPane.showMessageDialog(this, "Provider correctly updated in the database.","Success",JOptionPane.INFORMATION_MESSAGE);		
			_rightPane.update((SAAbstractFactory.getInstance().createSAProvider()).readAllProviders());
			_leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_READ_PROVIDER_OK:
			TProvider tp = (TProvider)t;
			/*String act = tp.get_activated() ? "Yes" : "No";*/
			/*_rightPane.setInfoInScreen("ID: " + tp.get_id() + '\n' + 
										"NIF: " + tp.get_nif() + '\n' +
										"Address: " + tp.get_address() + '\n' +
										"Phone number: " + tp.get_phoneNumber() + '\n' +
										"Activated: " + act);*/
			_rightPane.setInfoInScreen(tp.toString());
			break;
			
		case Event.RES_READ_PROVIDER_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing the provider in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		
		case Event.RES_READALL_PROVIDERS_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing all providers.","Failed",JOptionPane.ERROR_MESSAGE);			
			break;
			
		case Event.RES_READALL_PROVIDERS_OK:
			_rightPane.update((List<Object>)t);
			break;
		}
	}


}