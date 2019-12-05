/**
 * 
 */
package Negocio.Transfers;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlo
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class TDepartamento {
	
	public TDepartamento(String nombre, Double fact, Integer numEmployees, Integer floor) {
		this.nEmpleados = numEmployees;
		this.factura = fact;
		this.nombre= nombre;
		this.planta = floor;
	}
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Integer ID;

	/** 
	* @return the ID
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer getID() {
		// begin-user-code
		return ID;
		// end-user-code
	}

	/** 
	* @param ID the ID to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setID(Integer ID) {
		// begin-user-code
		this.ID = ID;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private String nombre;

	/** 
	* @return the nombre
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public String getNombre() {
		// begin-user-code
		return nombre;
		// end-user-code
	}

	/** 
	* @param nombre the nombre to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNombre(String nombre) {
		// begin-user-code
		this.nombre = nombre;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Integer nEmpleados;

	/** 
	* @return the nEmpleados
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer getnEmpleados() {
		// begin-user-code
		return nEmpleados;
		// end-user-code
	}

	/** 
	* @param nEmpleados the nEmpleados to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setnEmpleados(Integer nEmpleados) {
		// begin-user-code
		this.nEmpleados = nEmpleados;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Double factura;

	/** 
	* @return the factura
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Double getFactura() {
		// begin-user-code
		return factura;
		// end-user-code
	}

	/** 
	* @param factura the factura to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setFactura(Double factura) {
		// begin-user-code
		this.factura = factura;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Integer planta;

	/** 
	* @return the planta
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer getPlanta() {
		// begin-user-code
		return planta;
		// end-user-code
	}

	/** 
	* @param planta the planta to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setPlanta(Integer planta) {
		// begin-user-code
		this.planta = planta;
		// end-user-code
	}
}