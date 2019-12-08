package Negocio.Transfers;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import utils.Pair;

public class TProductQuantity {
	private Integer _idTicket;
	private Double _finalPrice;
	private Timestamp _date;
	private HashMap<Integer, Pair<String, Integer>> _productsToShow; // <IDProducto, <Nombre, Cantidad>>

	public TProductQuantity(Integer id, Double finalP, Timestamp date,
			HashMap<Integer, Pair<String, Integer>> products) {
		_idTicket = id;
		_finalPrice = finalP;
		_date = date;
		_productsToShow = products;
	}

	public Integer get_idTicket() {
		return _idTicket;
	}

	public void set_idTicket(Integer _idTicket) {
		this._idTicket = _idTicket;
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

	public HashMap<Integer, Pair<String, Integer>> get_productsToShow() {
		return _productsToShow;
	}

	public void set_productsToShow(HashMap<Integer, Pair<String, Integer>> _productsToShow) {
		this._productsToShow = _productsToShow;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("ID: " + _idTicket + '\n'
				+ "Date: " + _date + '\n'
				+ "Final price: " + _finalPrice + '\n' + '\t');
		for (Map.Entry<Integer, Pair<String, Integer>> entry : _productsToShow.entrySet()) {
			str.append(entry.getValue().getKey() + " " + entry.getValue().getValue() + '\n' + '\t');
		}
		return str.toString();
	}

}
