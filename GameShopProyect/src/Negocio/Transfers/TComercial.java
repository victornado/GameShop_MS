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
public class TComercial extends TEmpleado {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Integer nVentas;

	/** 
	* @return the nVentas
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer getnVentas() {
		// begin-user-code
		return nVentas;
		// end-user-code
	}

	/** 
	* @param nVentas the nVentas to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setnVentas(Integer nVentas) {
		// begin-user-code
		this.nVentas = nVentas;
		// end-user-code
	}
}