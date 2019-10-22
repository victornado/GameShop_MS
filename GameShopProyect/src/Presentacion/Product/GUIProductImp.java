package Presentacion.Product;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;

import Negocio.SA.SAAbstractFactory;
import Presentacion.View.GUIGameshopImp;
import Presentacion.View.OperationsPanel;
import Presentacion.View.ShowPanel;
import Transfers.TAccessory;
import Transfers.TGame;
import Transfers.TProduct;
import javafx.util.Pair;
import Presentacion.Controller.Event;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
@SuppressWarnings("serial")
public class GUIProductImp extends GUIProduct {
	
	private OperationsPanel _leftPane;
	private ShowPanel _rightPane;
	
	@Override
	protected void alingmentPanels() {
		this.setLayout(new BorderLayout());
		
		this._leftPane = new OperationsPanel(GUIGameshopImp.TAB_PRODUCT);
		this.add(_leftPane, BorderLayout.WEST);
		_leftPane.setVisible(true);
		
		this._rightPane = new ShowPanel(GUIGameshopImp.TAB_PRODUCT);
		this.add(_rightPane, BorderLayout.EAST);
		_rightPane.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualiza(Pair<Object, Integer> data) {
		Integer id;
		switch(data.getValue()) {
		case Event.RES_REGISTER_PRODUCT_OK:
			id = (Integer)data.getKey();
			JOptionPane.showMessageDialog(null, "Product " + id + " has been correctly inserted in the database.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			_rightPane.update((SAAbstractFactory.getInstance().createSAProduct()).readAllProducts());
			_leftPane.addInfoToComboBox();
			break;
		case Event.RES_REGISTER_PRODUCT_FAILED:
			JOptionPane.showMessageDialog(this, "Error when inserting the product into the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		case Event.RES_UNSUBSCRIBE_PRODUCT_OK:
			id = (Integer)data.getKey();
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
			TProduct tp = (TProduct)data.getKey();
			String text;
			if(tp.get_type().equalsIgnoreCase(TProduct.game))
				text = ((TGame)tp).toString();
			else
				text = ((TAccessory)tp).toString();
			_rightPane.setInfoInScreen(text);
			break;
		case Event.RES_READ_PRODUCT_FAILED:
			JOptionPane.showMessageDialog(this, "Error when reading the product from the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;
		case Event.RES_READALL_PRODUCT_OK:
			_rightPane.update((List<Object>)data.getKey());
			break;
		case Event.RES_READALL_PRODUCT_FAILED:
			JOptionPane.showMessageDialog(this, "Error when reading all products from the database.","Failed",JOptionPane.ERROR_MESSAGE);		
			break;	
		}
	}

}