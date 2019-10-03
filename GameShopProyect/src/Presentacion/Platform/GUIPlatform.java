/**
 * 
 */
package Presentacion.Platform;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.SA.SAAbstractFactory;
import Presentacion.View.GUIGameshop;
import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import Transfers.TPlatform;
import Transfers.TProvider;
import Presentacion.Controller.Event;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
@SuppressWarnings("serial")
public class GUIPlatform extends JPanel implements IGUI {
	
	private OperationsPanel _leftPane;
	private ShowPanel _rightPane;
	
	public GUIPlatform() {
		alignmentPanels();
	}
	
	private void alignmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshop.TAB_PLATFORM);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshop.TAB_PLATFORM);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Integer e, Object t) {
		switch(e){
		case Event.RES_REGISTER_PLATFORM_OK:
			Integer id = (Integer)t;
			JOptionPane.showMessageDialog(null, "The platform " + id + " has been successfully inserted.", "Success", 
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAPlatform()).readAllPlatforms());
			_leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_REGISTER_PLATFORM_FAILED:
			JOptionPane.showMessageDialog(this, "Error inserting the platform in the database.",
					"Failed",JOptionPane.ERROR_MESSAGE);
			break;
			
		case Event.RES_UNSUBSCRIBE_PLATFORM_OK:
			Integer a = (Integer)t;
			JOptionPane.showMessageDialog(null, "Platform " + a + " has been successfully unsubscribed.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAPlatform()).readAllPlatforms());
			break;
			
		case Event.RES_UNSUBSCRIBE_PLATFORM_FAILED:
			JOptionPane.showMessageDialog(this, "Error removing the platform from the database.",
					"Failed",JOptionPane.ERROR_MESSAGE);		
			break;
			
		case Event.RES_MODIFY_PLATFORM_FAILED:
			JOptionPane.showMessageDialog(this, "Error al intentar modificar la plataforma en la base de datos.",
					"Failed",JOptionPane.ERROR_MESSAGE);	
			break;
			
		case Event.RES_MODIFY_PLATFORM_OK:
			JOptionPane.showMessageDialog(this, "Platform updated correctly in the database.",
					"Success",JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAPlatform()).readAllPlatforms());
			_leftPane.addInfoToComboBox();
			break;
			
		case Event.RES_READ_PLATFORM_OK:
			TPlatform tp = (TPlatform)t;
			_rightPane.setInfoInScreen(tp.toString());
			break;
			
		case Event.RES_READ_PLATFORM_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing the platform.",
					"Failed",JOptionPane.ERROR_MESSAGE);
			break;
		
		case Event.RES_READALL_PLATFORM_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing all platforms.", "Failed", JOptionPane.ERROR_MESSAGE);			
			break;
			
		case Event.RES_READALL_PLATFORM_OK:
			_rightPane.update((List<Object>)t);
			break;
			
		case Event.RES_READALL_PRODUCTS_FROM_PLATFORM_OK:
			_rightPane.updateProductFromPlatform((List<Object>)t);
			break;
			
		case Event.RES_READALL_PRODUCTS_FROM_PLATFROM_FAILED:
			JOptionPane.showMessageDialog(this, "Error showing all products from one platform.",
					"Failed",JOptionPane.ERROR_MESSAGE);			

			break;
		}
	}
	
}