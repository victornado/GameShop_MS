package Transfers;

import java.sql.Timestamp;
import java.util.List;
/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class TTicket {
	
	private Double _finalPrice;
	private Timestamp _date;
	private Integer _id;
	private Integer _employeeId;
	private List<Object> _products;
	
	// CONSTRUCTOR
	public TTicket(Integer employeeID, List<Object> productsList) {
		_date = new Timestamp(System.currentTimeMillis());
		_employeeId = employeeID;
		_products = productsList;
	}
	public TTicket(){
	}
	
	public Double get_finalPrice() {
		return _finalPrice;
	}
	public void set_finalPrice(Double _finalPrice) {
		this._finalPrice = _finalPrice;
	}
	public Timestamp get_date() {
		return _date;
	}
	public void set_date(Timestamp _date) {
		this._date = _date;
	}
	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public Integer get_employeeId() {
		return _employeeId;
	}
	public void set_employeeId(Integer _employeeId) {
		this._employeeId = _employeeId;
	}
	public List<Object> get_products() {
		return _products;
	}
	public void set_products(List<Object> _productsId) {
		this._products = _productsId;
	}
	
	
	@Override
	public String toString() {
		String tabulador = "   ";
		String ticketInfo = "ID: " + _id + '\n' +
				"Employee ID: " + _employeeId + '\n' +
				"Creation date: " + _date + '\n' +
				"Total price: " + _finalPrice;
		String productsInfo = "\n\nProduct list:\n" + tabulador;
		for(int i = 0; i < _products.size(); ++i){
			TProduct ttp = (TProduct) _products.get(i);
			productsInfo += "ID: " + ttp.get_id() + " Name: " + ttp.get_name() + " PlatformID: " + ttp.get_platformId() +
					" Stock: " + ttp.get_stock() + "\n" + tabulador;
		}
		return ticketInfo + productsInfo;
	}
}