package Transfers;

/**
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 * */
public class TEmployee {
	
	private String _nif;
	private String _name;
	private String turn;
	private Integer _id;
	private Boolean _activated;
	
	// CONSTRUCTOR
	public TEmployee(String name, String nif, String turn) {
		this._name = name;
		this._nif = nif;
		this.turn = turn;
	}
	public TEmployee() {
		
	}
	
	public String get_nif() {
		return _nif;
	}
	
	public void set_nif(String _nif) {
		this._nif = _nif;
	}
	
	public String get_name() {
		return _name;
	}
	
	public void set_name(String _name) {
		this._name = _name;
	}
	
	public String getTurn() {
		return turn;
	}
	
	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	public Integer get_id() {
		return _id;
	}
	
	public void set_id(Integer _id) {
		this._id = _id;
	}
	
	public Boolean get_activated() {
		return _activated;
	}
	
	public void set_activated(Boolean _activated) {
		this._activated = _activated;
	}
	

	@Override
	public String toString() {
		String act = _activated ? "Yes" : "No";
		return ("ID: " + _id + '\n' + 
				"Name: " + _name + '\n' +
				"NIF: " + _nif + '\n' +
				"Round: " + turn + '\n' +
				"Activated: " + act);
	}
}