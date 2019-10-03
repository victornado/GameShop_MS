package Transfers;

/**
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TPlatform {
	
	private String _name;
	private Integer _id;
	private Boolean _activated;
	
	// CONSTRUCTOR
	
	public TPlatform(String name){
		this._name = name;
	}
	
	public TPlatform(){	
	}
	
	//Getters and setters
	
	
	public String get_name() {
		return _name;
	}
	
	public void set_name(String _name) {
		this._name = _name;
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
		return("ID: " + _id + '\n' + 
			   "Name: " + _name + '\n' +
			   "Activated: " + act);
	}
}