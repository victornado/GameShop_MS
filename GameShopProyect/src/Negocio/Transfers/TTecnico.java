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
public class TTecnico extends TEmpleado {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private String especialidad;

	/** 
	* @return the especialidad
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public String getEspecialidad() {
		// begin-user-code
		return especialidad;
		// end-user-code
	}

	/** 
	* @param especialidad the especialidad to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setEspecialidad(String especialidad) {
		// begin-user-code
		this.especialidad = especialidad;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Double sobresueldo;

	/** 
	* @return the sobresueldo
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Double getSobresueldo() {
		// begin-user-code
		return sobresueldo;
		// end-user-code
	}

	/** 
	* @param sobresueldo the sobresueldo to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setSobresueldo(Double sobresueldo) {
		// begin-user-code
		this.sobresueldo = sobresueldo;
		// end-user-code
	}
}