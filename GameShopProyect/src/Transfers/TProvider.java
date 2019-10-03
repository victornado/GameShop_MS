package Transfers;

/** 
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TProvider {
	
	private String _nif;
	private String _address;
	private Integer _phoneNumber;
	private Integer _id;
	private Boolean _activated;
	
	// CONSTRUCTORES
	public TProvider(String NIF, String dir, int tphn) {
		this._nif = NIF;
		this._address = dir;
		this._phoneNumber = tphn;
	}
	
	public TProvider() {
		
	}
	
	public String get_nif() {
		return _nif;
	}
	public void set_nif(String _nif) {
		this._nif = _nif;
	}
	public String get_address() {
		return _address;
	}
	public void set_address(String _address) {
		this._address = _address;
	}
	public Integer get_phoneNumber() {
		return _phoneNumber;
	}
	public void set_phoneNumber(Integer _phoneNumber) {
		this._phoneNumber = new Integer(_phoneNumber);
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
				"NIF: " + _nif + '\n' +
				"Address: " + _address + '\n' +
				"Phone number: " + _phoneNumber + '\n' +
				"Activated: " + act);
	}
}