package Presentacion.Product;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Presentacion.View.IGUI;
import Transfers.TAccessory;
import Transfers.TGame;
import Transfers.TProduct;

@SuppressWarnings("serial")
public class FormUpdateProduct extends FormProduct {
	
	private JCheckBox _reactivate;
	private TProduct _tprod;
	private int _initStock;
	
	public FormUpdateProduct(Object o) {
		super();
		_tprod = (TProduct)o;
		_initStock = _tprod.get_stock();
		this.setVisible(false);
		this.setTitle("Update product");
		this.remove(this._next);
		initComponents(_tprod.get_type());
	}
	
	@Override
	protected void initComponents(String select) {
		super.initComponents(select);
		
		this._nameText.setText(_tprod.get_name());
		this._stockInt.setValue(_tprod.get_stock());
		this._typeElection.setSelectedItem(_tprod.get_type());
		this._typeElection.setEnabled(false);
		this._providerElection.setSelectedItem(_tprod.get_providerId());
		this._platformElection.setSelectedItem(_tprod.get_platformId());
		this._pvpDoub.setValue(_tprod.get_pvp());
		
		this._description.setText(_tprod.get_description());
		if(_tprod.get_type().equals(TProduct.game)) {
			this._genderText.setText(((TGame)_tprod).get_gender());
		}else {
			this._brand.setText(((TAccessory)_tprod).get_brand());
			this._color.setText(((TAccessory)_tprod).get_color());
		}
		
		this._reactivate = new JCheckBox("Activated");
		this._reactivate.setBounds(50, 150, 140, 50); //this._reactivate.setBounds(200, 240, 140, 50);
		this.add(_reactivate);
		this._reactivate.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(_reactivate.isSelected()) {
					_tprod.set_activated(true);
					_reactivate.setEnabled(false);
				}else
					_tprod.set_activated(false);
			}
			
		});
		if(_tprod.get_activated()) {
			this._reactivate.setEnabled(false);
			this._reactivate.setSelected(true);
		}
		this.add(this._reactivate);
	}
	
	@Override
	protected void okButtonAction(String elected){
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				_tprod.set_name(_nameText.getText());
				_tprod.set_unitsProvided(_initStock);
				String [] info = ((String)_providerElection.getSelectedItem()).split(" - ");
				_tprod.set_providerId((Integer)Integer.parseInt(info[0]));
				info = ((String)_platformElection.getSelectedItem()).split(" - ");
				_tprod.set_platformId((Integer)Integer.parseInt(info[0]));
				_tprod.set_pvp((Double)_pvpDoub.getValue());
				_tprod.set_stock((Integer)_stockInt.getValue());
				
				_tprod.set_activated(_reactivate.isSelected());
				
				
				if(_tprod.get_type().equals(TProduct.game)) {
					((TGame) _tprod).set_description(_description.getText());
					((TGame)_tprod).set_gender(_genderText.getText());
				}else {
					((TAccessory)_tprod).set_brand(_brand.getText());
					((TAccessory)_tprod).set_color(_color.getText());
				}
				
				Controller.getInstance().action(_tprod, Event.MODIFY_PRODUCT);
				
				setVisible(false);
				dispose();
			}
		});
	}

}
