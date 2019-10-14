package Transfers;

public class TAsociated {
	
	private Integer _idTicket;
	private Integer _idProduct;
	private Integer _cantidad;
	private Double _precio;
	
	public TAsociated(Integer ticket, Integer product) {
		this._idTicket = ticket;
		this._idProduct = product;
	}
	
	public TAsociated() {
		
	}
	
	public Integer getTicket() {
		return this._idTicket;
	}

	public void set_ticket(Integer id) {
		this._idTicket = id;
	}
	
	public Integer get_idProduct() {
		return _idProduct;
	}

	public void set_idProduct(Integer _idProduct) {
		this._idProduct = _idProduct;
	}

	public Integer get_cantidad() {
		return _cantidad;
	}

	public void set_cantidad(Integer _cantidad) {
		this._cantidad = _cantidad;
	}

	public Double get_precio() {
		return _precio;
	}

	public void set_precio(Double _precio) {
		this._precio = _precio;
	}

}
