/**
 * 
 */
package Negocio.Empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author carlo
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleado.Tecnico.findByespecialidad", query = "select obj from Tecnico obj where :especialidad = obj.especialidad "),
		@NamedQuery(name = "Negocio.Empleado.Tecnico.findBysobresueldo", query = "select obj from Tecnico obj where :sobresueldo = obj.sobresueldo ") })
public class Tecnico extends Empleado implements Serializable {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Tecnico() {
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Object especialidad;

	/** 
	* @return the especialidad
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Object getEspecialidad() {
		// begin-user-code
		return especialidad;
		// end-user-code
	}

	/** 
	* @param especialidad the especialidad to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setEspecialidad(Object especialidad) {
		// begin-user-code
		this.especialidad = especialidad;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Object sobresueldo;

	/** 
	* @return the sobresueldo
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Object getSobresueldo() {
		// begin-user-code
		return sobresueldo;
		// end-user-code
	}

	/** 
	* @param sobresueldo the sobresueldo to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setSobresueldo(Object sobresueldo) {
		// begin-user-code
		this.sobresueldo = sobresueldo;
		// end-user-code
	}
}