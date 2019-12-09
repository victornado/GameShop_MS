package Presentacion.Provider;

import java.awt.BorderLayout;

import java.util.List;

import javax.swing.JOptionPane;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TProvider;
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
public class GUIProviderImp extends GUIProvider {
	
	@Override
	protected void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshopImp.TAB_PROVIDER);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshopImp.TAB_PROVIDER);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Pair<Object, Integer> data) {
		Integer id;
		switch(data.getValue()){
		case Event.RES_REGISTER_PROVIDER_OK:
			id = (Integer)data.getKey();
			JOptionPane.showMessageDialog(null, "Provider " + id + " has been correctly insertes into the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAProvider()).readAllProviders());
			_leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_REGISTER_PROVIDER_FAILED:
			JOptionPane.showMessageDialog(this, "Error entering the provider in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_UNSUBSCRIBE_PROVIDER_OK:
			id = (Integer)data.getKey();
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
			JOptionPane.showMessageDialog(this, "Provider correctly updated in the database.","Success",JOptionPane.INFORMATION_MESSAGE);		
			_rightPane.update((SAAbstractFactory.getInstance().createSAProvider()).readAllProviders());
			_leftPane.addInfoToComboBox(/*(List<Object>)data.getKey()*/);
			break;
			
		case Event.RES_READ_PROVIDER_OK:
			TProvider tp = (TProvider)data.getKey();
			_rightPane.setInfoInScreen(tp.toString());
			break;
			
		case Event.RES_READ_PROVIDER_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing the provider in the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		
		case Event.RES_READALL_PROVIDERS_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing all providers.","Failed",JOptionPane.ERROR_MESSAGE);			
			break;
			
		case Event.RES_READALL_PROVIDERS_OK:
			_rightPane.update((List<Object>)data.getKey());
			break;
		case Event.SHOW_PROVIDER_QUERY_OK:
			_rightPane.updateBestProvider((String)data.getKey());
			break;
		case Event.SHOW_PROVIDER_QUERY_FAILED:
			JOptionPane.showMessageDialog(this, (String)data.getKey(),"Failed",JOptionPane.ERROR_MESSAGE);	
			break;
		}
	}
}