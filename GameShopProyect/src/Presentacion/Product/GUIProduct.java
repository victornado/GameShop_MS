/**
 * 
 */
package Presentacion.Product;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.SA.SAAbstractFactory;
import Presentacion.View.GUIGameshop;
import Presentacion.View.IGUI;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import Transfers.TAccessory;
import Transfers.TGame;
import Transfers.TProduct;
import Transfers.TProvider;
import Presentacion.Controller.Event;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
@SuppressWarnings("serial")
public class GUIProduct extends JPanel implements IGUI {
	
	private OperationsPanel _leftPane;
	private ShowPanel _rightPane;
	
	public GUIProduct() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshop.TAB_PRODUCT);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshop.TAB_PRODUCT);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Integer e, Object t) {
		Integer id ;
		switch(e) {
		case Event.RES_REGISTER_PRODUCT_OK:
			id = (Integer)t;
			JOptionPane.showMessageDialog(null, "Product " + id + " has been correctly inserted in the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAProduct()).readAllProducts());
			_leftPane.addInfoToComboBox();
			break;
		case Event.RES_REGISTER_PRODUCT_FAILED:
			JOptionPane.showMessageDialog(this, "Error when inserting the product into the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		case Event.RES_UNSUBSCRIBE_PRODUCT_OK:
			id = (Integer)t;
			JOptionPane.showMessageDialog(null, "Product " + id + " has been removed correctly.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAProduct()).readAllProducts());
			break;
		case Event.RES_UNSUBSCRIBE_PRODUCT_FAILED:
			JOptionPane.showMessageDialog(this, "Error when unsubscribing the product from the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		case Event.RES_MODIFY_PRODUCT_OK:
			JOptionPane.showMessageDialog(this, "Product correctly updated in the database.","Success",JOptionPane.INFORMATION_MESSAGE);		
			_rightPane.update((SAAbstractFactory.getInstance().createSAProduct()).readAllProducts());
			_leftPane.addInfoToComboBox();
			break;
		case Event.RES_MODIFY_PRODUCT_FAILED:
			JOptionPane.showMessageDialog(this, "Error when modifying the product from the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		case Event.RES_READ_PRODUCT_OK:
			TProduct tp = (TProduct)t;
			String text;
			if(tp.get_type().equalsIgnoreCase(TProduct.game))
				text = ((TGame)tp).toString();
			else
				text = ((TAccessory)tp).toString();
			/*String act = tp.get_activated() ? "Yes" : "No";
			String text = "ID: " + tp.get_id() + '\n' +
					"Name: " + tp.get_name() + '\n'+
					"Type: " + tp.get_type() + '\n' +
					"Stock: " + tp.get_stock() + '\n' +
					"PVP: " + tp.get_pvp() + '\n' +
					"Provider ID: " + tp.get_providerId() + '\n' +
					"Platform ID: " + tp.get_platformId() + '\n' +
					"Activated: " + act + '\n'+
					"Units Provided: " + tp.get_unitsProvided() + '\n';
			if(tp.get_type().equals(TProduct.accessory))
				text += "Brand: " + ((TAccessory)tp).get_brand() + '\n' +
						"Color: " + ((TAccessory)tp).get_color() + '\n' ;
			else
				text += "Gender: " +((TGame)tp).get_gender() + '\n';
			text+= "Description: " + tp.get_description() + '\n';*/
			_rightPane.setInfoInScreen(text);
			break;
		case Event.RES_READ_PRODUCT_FAILED:
			JOptionPane.showMessageDialog(this, "Error when reading the product from the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		case Event.RES_READALL_PRODUCT_OK:
			_rightPane.update((List<Object>)t);
			break;
		case Event.RES_READALL_PRODUCT_FAILED:
			JOptionPane.showMessageDialog(this, "Error when reading all products from the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;	
		}
	}
}